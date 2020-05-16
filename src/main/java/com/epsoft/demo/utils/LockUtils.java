package com.epsoft.demo.utils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.epsoft.demo.bean.po.LockModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LockUtils {

    //将requestid保存在该变量中
    static ThreadLocal<String> requestIdTL = new ThreadLocal<>();

    /**
     * 获取当前线程requestid
     *
     * @return
     */
    public static String getRequestId() {
        String requestId = requestIdTL.get();
        if (requestId == null || "".equals(requestId)) {
            requestId = UUID.randomUUID().toString();
            requestIdTL.set(requestId);
        }
        log.info("requestId:{}", requestId);
        return requestId;
    }

    /**
     * 获取锁
     *
     * @param lock_key        锁key
     * @param locktimeout(毫秒) 持有锁的有效时间，防止死锁
     * @param gettimeout(毫秒)  获取锁的超时时间，这个时间内获取不到将重试
     * @return
     */
    public static boolean lock(String lock_key, long locktimeout, int gettimeout) throws Exception {
        log.info("start");
        boolean lockResult = false;
        String request_id = getRequestId();
        long starttime = System.currentTimeMillis();
        while (true) {
            LockModel lockModel = LockUtils.get(lock_key);
            if (Objects.isNull(lockModel)) {
                //插入一条记录,重新尝试获取锁
                LockUtils.insert(LockModel.builder().lock_key(lock_key).request_id("").lock_count(0).timeout(0L).version(0).build());
            } else {
                String reqid = lockModel.getRequest_id();
                //如果reqid为空字符，表示锁未被占用
                if ("".equals(reqid)) {
                    lockModel.setRequest_id(request_id);
                    lockModel.setLock_count(1);
                    lockModel.setTimeout(System.currentTimeMillis() + locktimeout);
                    if (LockUtils.update(lockModel) == 1) {
                        lockResult = true;
                        break;
                    }
                } else if (request_id.equals(reqid)) {
                    //如果request_id和表中request_id一样表示锁被当前线程持有者，此时需要加重入锁
                    lockModel.setTimeout(System.currentTimeMillis() + locktimeout);
                    lockModel.setLock_count(lockModel.getLock_count() + 1);
                    if (LockUtils.update(lockModel) == 1) {
                        lockResult = true;
                        break;
                    }
                } else {
                    //锁不是自己的，并且已经超时了，则重置锁，继续重试
                    if (lockModel.getTimeout() < System.currentTimeMillis()) {
                        LockUtils.resetLock(lockModel);
                    } else {
                        //如果未超时，休眠100毫秒，继续重试
                        if (starttime + gettimeout > System.currentTimeMillis()) {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        log.info("end");
        return lockResult;
    }

    /**
     * 释放锁
     *
     * @param lock_key
     * @throws Exception
     */
    public static void unlock(String lock_key) throws Exception {
        //获取当前线程requestId
        String requestId = getRequestId();
        LockModel lockModel = LockUtils.get(lock_key);
        //当前线程requestId和库中request_id一致 && lock_count>0，表示可以释放锁
        if (Objects.nonNull(lockModel) && requestId.equals(lockModel.getRequest_id()) && lockModel.getLock_count() > 0) {
            if (lockModel.getLock_count() == 1) {
                //重置锁
                resetLock(lockModel);
            } else {
                lockModel.setLock_count(lockModel.getLock_count() - 1);
                LockUtils.update(lockModel);
            }
        }
    }

    /**
     * 重置锁
     *
     * @param lockModel
     * @return
     * @throws Exception
     */
    public static int resetLock(LockModel lockModel) throws Exception {
        lockModel.setRequest_id("");
        lockModel.setLock_count(0);
        lockModel.setTimeout(0L);
        return LockUtils.update(lockModel);
    }

    /**
     * 更新lockModel信息，内部采用乐观锁来更新
     *
     * @param lockModel
     * @return
     * @throws Exception
     */
    public static int update(LockModel lockModel) throws Exception {
        return exec(conn -> {
            String sql = "UPDATE t_lock SET request_id = ?,lock_count = ?,timeout = ?,version = version + 1 WHERE lock_key = ? AND  version = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            int colIndex = 1;
            ps.setString(colIndex++, lockModel.getRequest_id());
            ps.setInt(colIndex++, lockModel.getLock_count());
            ps.setLong(colIndex++, lockModel.getTimeout());
            ps.setString(colIndex++, lockModel.getLock_key());
            ps.setInt(colIndex++, lockModel.getVersion());
            return ps.executeUpdate();
        });
    }

    public static LockModel get(String lock_key) throws Exception {
        return exec(conn -> {
            String sql = "select * from t_lock t WHERE t.lock_key=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            int colIndex = 1;
            ps.setString(colIndex++, lock_key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return LockModel.builder().
                        lock_key(lock_key).
                        request_id(rs.getString("request_id")).
                        lock_count(rs.getInt("lock_count")).
                        timeout(rs.getLong("timeout")).
                        version(rs.getInt("version")).build();
            }
            return null;
        });
    }

    public static int insert(LockModel lockModel) throws Exception {
        return exec(conn -> {
            String sql = "insert into t_lock (lock_key, request_id, lock_count, timeout, version) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            int colIndex = 1;
            ps.setString(colIndex++, lockModel.getLock_key());
            ps.setString(colIndex++, lockModel.getRequest_id());
            ps.setInt(colIndex++, lockModel.getLock_count());
            ps.setLong(colIndex++, lockModel.getTimeout());
            ps.setInt(colIndex++, lockModel.getVersion());
            return ps.executeUpdate();
        });
    }

    public static <T> T exec(SqlExec<T> sqlExec) throws Exception {
        Connection conn = JdbcUtil.getConn();
        try {
            return sqlExec.exec(conn);
        } finally {
        	JdbcUtil.closeConn(conn);
        }
    }

    @FunctionalInterface
    public interface SqlExec<T> {
        T exec(Connection conn) throws Exception;
    }

   
}

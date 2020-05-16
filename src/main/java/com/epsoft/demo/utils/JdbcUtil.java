package com.epsoft.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {

	public static final String url = "jdbc:mysql://127.0.0.1:3306/mybatis?useUnicode=true&characterEncoding=gbk&zeroDateTimeBehavior=convertToNull";
	public static final String username = "root";
	public static final String password = "123456";	
	public static final String driver = "com.mysql.jdbc.Driver";
	
	 public static Connection getConn() {
	        Connection conn = null;
	        try {
	            Class.forName(driver);  //加载数据库驱动
	            try {
	                conn = DriverManager.getConnection(url, username, password);  //连接数据库
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return conn;
	    }

	    /**
	     * 关闭数据库链接
	     *
	     * @return
	     */
	    public static void closeConn(Connection conn) {
	        if (conn != null) {
	            try {
	                conn.close();  //关闭数据库链接
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    } 
}

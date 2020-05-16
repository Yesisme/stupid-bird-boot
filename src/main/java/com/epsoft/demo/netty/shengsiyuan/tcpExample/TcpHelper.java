package com.epsoft.demo.netty.shengsiyuan.tcpExample;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.util.CharsetUtil;

/**
 * Created by 凯 on 2015/11/10.
 */
public class TcpHelper {
	
	static Logger log = LoggerFactory.getLogger(TcpHelper.class);

    /**
     * 日志对象
     */

    /**
     * Tcp发送接口
     *
     * @param ip       ip地址
     * @param port     端口
     * @return
     */
    public static String tcpSend(String ip, int port, String sendstr ,String charsetName) throws IOException {
        /**
         * s:socket对象
         * result:方法返回字符串
         * errMessage:异常信息
         * os:发送数据流（数据出处, 从端口的outputstream上向外送数据）
         * is:输入流对象
         */
        Socket socket = null;
        String result = "";
        String errMessage;
        PrintWriter os;
        DataInputStream input;
        String sendMesssage =  sendstr;
        OutputStream out = null;
        InputStream in = null;

        log.info(String.format("TCP发送报文：%s", sendMesssage));
        try {
            socket = new Socket();
            socket.setReceiveBufferSize(32*1024);
            socket.setSendBufferSize(32*1024);
            InetSocketAddress endpoint = new InetSocketAddress(ip, port);
            socket.connect(endpoint);
        } catch (IOException e) {
            String a = e.getMessage();
            errMessage = String.format("创建socket（tcp）连接异常，连接IP:%s,连接端口:%d", ip, port);
            log.error(errMessage);
            throw e;
        }finally {
        	if(socket!=null) {
        		socket.close();
        	}
        	
        }

        try {
            //获取输出流
            out = socket.getOutputStream();
            in = socket.getInputStream();
            //out.write(sendMesssage.getBytes("utf-8"));
            out.write(sendMesssage.getBytes("GBK"));
            out.flush();
        } catch (IOException e) {
            errMessage = String.format("获取socket输出流失败");
            log.error(errMessage);
            throw e;
        }

        //写入发送信息，并发送

        try {
        	BufferedReader br = new BufferedReader(new InputStreamReader(in,charsetName));
        	StringBuilder sb = new StringBuilder();
        	
        	String str = "";
        	while(( str = br.readLine())!=null){
        		sb= sb.append(str);
        	}
        	result = sb.toString();
//            BufferedReader br = new BufferedReader( new InputStreamReader(in, "utf-8") , 64*1024);
//            StringWriter received = new StringWriter( 64*1024);
//            char[] charBuf = new char[ 64*1024];
//            int size =0;
//            char lastChar = 0;
//            do {
//                size = br.read(charBuf , 0 ,  64*1024);
//                int end=charBuf[size-1]==0?size-1:size;
//                lastChar = charBuf[size-1]==0?charBuf[size-1]:charBuf[size];
//                if(lastChar == 0){
//                    received.write(charBuf, 0, end);
//                }
//            }while(lastChar != 0);
//
//            result = received.toString();

        } catch (IOException e) {
            errMessage = String.format("socket获取返回流对象失败");
            log.error(errMessage);
            throw e;
        }

        try {
            out.close(); //关闭Socket输出流
            in.close();//关闭Socket输入流
            socket.close(); //关闭Socket
        } catch (IOException e) {
            errMessage = String.format("关闭socket连接失败");
            log.error(errMessage);
            throw e;
        }
        log.info(String.format("TCP收到报文：%s", result));
        return result;
    }


    public static void main(String[] args) throws IOException{
        // 129.1.1.157
        //192.168.0.57
        String result = TcpHelper.tcpSend("127.0.0.1", 8899, "hello world", "utf-8");
//        9201  查询人员信息
//        String result = TcpHelper.tcpSend("192.168.0.232", 9900, "{\"data\":\"$$1~1|330521D15600000500096947366F5AAD|D10535933|330521|||||~~~1~1~D10535933~330521D15600000500096947366F5AAD~330521~330521~~~330500810126$$\",\"hospitalId\":\"330521100004\",\"action\":\"9201\",\"ICInfo\":\"1|330521D15600000500096947366F5AAD|D10535933|330521|||||\",\"tradeId\":\"0\"}" );
        
        //9202   1001
//      String result = TcpHelper.tcpSend("192.168.0.232", 9900, "{\"data\":\"$$1~1|339900D156000005000FFFFFCS000020||T01000020||||SCE7100S|00248380|DLLVER1.2.7|LIBVER14|339900813155~1~~T01000020~0~339900~1~13~0~~0~GH_0~J11.101~流行性感冒~流行性感冒~2018-07-09 00:00:00~100054~妇科~012~李春香~1~1~24~0~0~24~2~1|2|3|8000000000000003|自费诊疗及服务项目|2018-07-09 00:00:00|10001|自费诊疗及服务项目|0|无|次|0|0||0|1|0|1|0||||^2|2|1|f11020000102|门诊诊查费(二级医院)|2018-07-09 00:00:00|10001|门诊诊查费(二级医院)|0|无|次|0|0||0|1|24|1|24||||^~~~0~~~$$\",\"hospitalId\":\"330521100004\",\"action\":\"9202\",\"ICInfo\":\"1|330521D15600000500096947366F5AAD|D10535933|330521|E520|82865137|2.1.0.4|2.1.0.4|330500812208,33050020110901810126\",\"tradeId\":\"0\"}" );
       
        //9203   1002   -》 4
//        String result = TcpHelper.tcpSend("129.1.1.157", 9900, "{\"data\":\"$$1~1|330521D15600000500096947366F5AAD|D10535933|330521|||||~~~D10535933~330521D15600000500096947366F5AAD~330521~330521~12~0~~~GHJS_10709753~Z01.901~未见异常                          ~未见异常                          ~2018-07-02 15:59:06~202~普外科~199833110330521621010021~盛伟    ~Bhp001~Bhp001~15.00~0.00~0.00~15.00~1~4001070975302|2|1|f11020000102|门诊诊查费(二级医院)|2018-07-02 15:59:06|3159|门诊诊查费|无|无|次|0|0|0|0|1|15.0000|1.00|15.00|0.00|0.00||1^~~0~0~~~~$$\",\"hospitalId\":\"330521100004\",\"action\":\"9203\",\"ICInfo\":\"1|330521D15600000500096947366F5AAD|D10535933|330521|E520|82865137|2.1.0.4|2.1.0.4|330500812208,33050020110901810126\",\"tradeId\":\"0\"}" );
        
//        9206   
//        String result = TcpHelper.tcpSend("129.1.1.157", 9900, "{\"data\":\"$$1~1|330521D15600000500096947366F5AAD|D10535933|330521|||||~~~690261174~-1~0~$$\",\"hospitalId\":\"330521100002\",\"action\":\"9206\",\"ICInfo\":\"1|330521D15600000500096947366F5AAD|D10535933|330521|||||\",\"tradeId\":\"0\"}" );
//        
        //9204   690377468->690373042
//        $$1~4|330521544B4E0179D739D07400000000|D10535933|330521|E520|82865137|2.1.0.4|2.1.0.4|330500812208,33050020110901810126~~~D10535933~330521544B4E0179D739D07400000000~690285023~~~~$$
        //String result = TcpHelper.tcpSend("129.1.1.157", 9900, "{\"data\":\"$$1~1|330521D15600000500096947366F5AAD|D10535933|330521|E520|82865137|2.1.0.4|2.1.0.4|330500812208,33050020110901810126~~~D10535933~330521D15600000500096947366F5AAD~690482372~~~~$$\",\"hospitalId\":\"330521100002\",\"action\":\"9204\",\"ICInfo\":\"1|330521D15600000500096947366F5AAD|D10535933|330521|E520|82865137|2.1.0.4|2.1.0.4|330500812208,33050020110901810126\",\"tradeId\":\"0\"}" );
//        System.out.println(result);
    }
}

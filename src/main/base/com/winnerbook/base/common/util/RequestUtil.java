package com.winnerbook.base.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 * 接口请求类
 * @author zhaojianj
 */
public class RequestUtil {

    public static Logger log = Logger.getLogger(RequestUtil.class);

    public static JSONObject postURL(String requestParam, String address, String encoding) {
        String rec_string = "";
        URL url = null;
        HttpURLConnection urlConn = null;
        try {
            /*得到url地址的URL类*/
            url = new URL(address);
            /*获得打开需要发送的url连接*/
            urlConn = (HttpURLConnection) url.openConnection();
            /*设置连接超时时间*/
            urlConn.setConnectTimeout(30000);
            /*设置读取响应超时时间*/
            urlConn.setReadTimeout(30000);
            /*设置post发送方式*/
            urlConn.setRequestMethod("POST");
            //urlConn.setRequestProperty("contentType", "utf-8");
			/*发送requestParam*/
            urlConn.setDoOutput(true);
            OutputStream out = urlConn.getOutputStream();
            out.write(requestParam.getBytes());
            out.flush();
            out.close();
            /*发送完毕 获取返回流，解析流数据*/
            BufferedReader rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), encoding));
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = rd.read()) > -1) {
                sb.append((char) ch);
            }
            rec_string = sb.toString().trim();
            /*解析完毕关闭输入流*/
            rd.close();
        } catch (Exception e) {
            /*异常处理*/
            rec_string = "{\"message\":\"请求异常！\",\"status\":\"-107\"}";
            e.printStackTrace();
        } finally {
            /*关闭URL连接*/
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
        /*返回响应内容*/
        log.info("返回内容:" + rec_string);
        return JSONObject.fromObject(rec_string);
    }
}

package com.winnerbook.base.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.ConnectionPoolTimeoutException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.multipart.PartBase;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/***
 * http post 请求
 */
public class HttpRequestUtil {

	//get 请求
	public static String sendGet(String url, String param) {
		 String result = "";
		 BufferedReader in = null;
		 try {
			 String urlName = url;
			 if(StringUtils.isNotBlank(param)){
				 urlName = url + "?" + param;
			 }
			 URL realUrl = new URL(urlName);
			 // 打开和URL之间的连接
			 URLConnection conn = realUrl.openConnection();
			 // 设置通用的请求属性
			 conn.setRequestProperty("accept", "*/*");
			 conn.setRequestProperty("connection", "Keep-Alive");
			 conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			 // 建立实际的连接
			 conn.connect();
			 // 获取所有响应头字段
			 Map<String, List<String>> map = conn.getHeaderFields();
			 // 遍历所有的响应头字段
			 for (String key : map.keySet()) {
				 System.out.println(key + "--->" + map.get(key));
			 }
			 // 定义BufferedReader输入流来读取URL的响应
			 in = new BufferedReader(
					 new InputStreamReader(conn.getInputStream()));
			 String line;
			 while ((line = in.readLine()) != null) {
				 result += line;
			 }
		 } catch (Exception e) {
			 System.out.println("发送GET请求出现异常！" + e);
			 e.printStackTrace();
		 }
		 // 使用finally块来关闭输入流
		 finally {
			 try {
				 if (in != null) {
					 in.close();
				 }
			 } catch (IOException ex) {
				 ex.printStackTrace();
			 }
		 }
		 return result;
	 }
	
	
	
	
	//post请求
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    public static String setWbPost(String url, String param,byte[] img){
    	PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl;
			
				realUrl = new URL(url);
			
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            if(null!=img){
            	conn.setRequestProperty("Content-Type", "multipart/form-data;");
            }
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (MalformedURLException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        return result;
    }
    
    
    public static String postFile(String url, byte[] postBody, String postName, Map<String,String> params) {
		String reStr = "";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);

			MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create();
			mEntityBuilder.addBinaryBody(postName, postBody);

			if (params != null) {
				// text params
				for (Entry<String, String> entry : params.entrySet()) {
					mEntityBuilder.addTextBody(entry.getKey(), entry.getValue());
				}
			}

			httppost.setEntity(mEntityBuilder.build());

			// set Timeout
			/*RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeOut)
					.setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
			httppost.setConfig(requestConfig);*/
			// get responce
			HttpResponse responce = httpClient.execute(httppost);
			// get http status code
			int resStatu = responce.getStatusLine().getStatusCode();

			if (resStatu == HttpStatus.SC_OK) {
				// get result data
				HttpEntity entity = responce.getEntity();
				reStr = EntityUtils.toString(entity);
			}
			else {
			}
		}
		catch (ConnectionPoolTimeoutException e) {
			e.printStackTrace();
		}
		catch (ConnectTimeoutException e) {
			e.printStackTrace();
		}
		catch (SocketTimeoutException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return reStr;
	}

    
    public static String sendPostMap(String url,Map<String,Object> map,byte[] imgByte){
    	
		try {
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = null;
			HttpPost post = new HttpPost(url);
			
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			for(Entry<String, Object> entry : map.entrySet()){
				pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()+""));
			}
			
			post.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));
			
			response = client.execute(post);
			String	ret  = EntityUtils.toString(response.getEntity(),"UTF-8");
			System.out.println(ret);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
    }
    
    private static class ByteArrayPart extends PartBase {
		private byte[] mData;
		private String mName;

		public ByteArrayPart(byte[] data, String name, String type)
				throws IOException {
			super(name, type, "UTF-8", "binary");
			mName = name;
			mData = data;
		}

		protected void sendData(OutputStream out) throws IOException {
			out.write(mData);
		}

		protected long lengthOfData() throws IOException {
			return mData.length;
		}

		protected void sendDispositionHeader(OutputStream out)
				throws IOException {
			super.sendDispositionHeader(out);
			StringBuilder buf = new StringBuilder();
			buf.append("; filename=\"").append(mName).append("\"");
			out.write(buf.toString().getBytes());
		}
	}

	private static final int NOT_MODIFIED 		   = 304;// Not Modified: There was no new data to return.
	private static final int BAD_REQUEST 		   = 400;// Bad Request: The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.
	private static final int NOT_AUTHORIZED 	   = 401;// Not Authorized: Authentication credentials were missing or incorrect.
	private static final int FORBIDDEN 			   = 403;// Forbidden: The request is understood, but it has been refused.  An accompanying error message will explain why.
	private static final int NOT_FOUND             = 404;// Not Found: The URI requested is invalid or the resource requested, such as a user, does not exists.
	private static final int NOT_ACCEPTABLE        = 406;// Not Acceptable: Returned by the Search API when an invalid format is specified in the request.
	private static final int INTERNAL_SERVER_ERROR = 500;// Internal Server Error: Something is broken.  Please post to the group so the Weibo team can investigate.
	private static final int BAD_GATEWAY           = 502;// Bad Gateway: Weibo is down or being upgraded.
	private static final int SERVICE_UNAVAILABLE   = 503;// Service Unavailable: The Weibo servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.

    
	private static String getCause(int statusCode) {
		String cause = null;
		switch (statusCode) {
		case NOT_MODIFIED:
			break;
		case BAD_REQUEST:
			cause = "The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.";
			break;
		case NOT_AUTHORIZED:
			cause = "Authentication credentials were missing or incorrect.";
			break;
		case FORBIDDEN:
			cause = "The request is understood, but it has been refused.  An accompanying error message will explain why.";
			break;
		case NOT_FOUND:
			cause = "The URI requested is invalid or the resource requested, such as a user, does not exists.";
			break;
		case NOT_ACCEPTABLE:
			cause = "Returned by the Search API when an invalid format is specified in the request.";
			break;
		case INTERNAL_SERVER_ERROR:
			cause = "Something is broken.  Please post to the group so the Weibo team can investigate.";
			break;
		case BAD_GATEWAY:
			cause = "Weibo is down or being upgraded.";
			break;
		case SERVICE_UNAVAILABLE:
			cause = "Service Unavailable: The Weibo servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.";
			break;
		default:
			cause = "";
		}
		return statusCode + ":" + cause;
	}
}

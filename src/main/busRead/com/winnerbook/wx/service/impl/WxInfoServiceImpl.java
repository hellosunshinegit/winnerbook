package com.winnerbook.wx.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weibo4j.Timeline;
import weibo4j.http.ImageItem;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

import com.winnerbook.activity.dao.ActivityDao;
import com.winnerbook.activity.dto.Activity;
import com.winnerbook.activity.dto.ReadBookClub;
import com.winnerbook.activity.service.ReadBookClubService;
import com.winnerbook.base.common.util.ConstantUtils;
import com.winnerbook.base.common.util.DateTimeUtils;
import com.winnerbook.base.common.util.HttpRequestUtil;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.book.dto.BookListType;
import com.winnerbook.book.service.BookListTypeService;
import com.winnerbook.course.dto.Course;
import com.winnerbook.course.service.CourseService;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.service.UserService;
import com.winnerbook.web.dto.News;
import com.winnerbook.web.service.NewsService;
import com.winnerbook.wx.dao.WxInfoDao;
import com.winnerbook.wx.dto.WxInfo;
import com.winnerbook.wx.service.WbSendInfoService;
import com.winnerbook.wx.service.WxInfoService;

@Service("wxInfoService")
public class WxInfoServiceImpl extends BaseServiceImpl implements WxInfoService{
	
	@Autowired
	private WxInfoDao wxInfoDao;
	
	@Autowired
	private ActivityDao activityDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WbSendInfoService wbSendInfoService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private ReadBookClubService readBookClubService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private BookListTypeService bookListTypeService;
	
	@Override
	public WxInfo getWxInfo(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", Integer.parseInt(id));
		return wxInfoDao.findWxInfo(map);
	}

	@Override
	public int updateWxInfo(WxInfo wxInfo) {
		return wxInfoDao.updateWxInfo(wxInfo);
	}

	@Override
	public void updateAccessToken(String accessToken) {
		WxInfo wxInfo = getWxInfo("1");
		wxInfo.setAccesstoken(accessToken);
		wxInfo.setAccesstokendate(new Date());
		updateWxInfo(wxInfo);
	}

	@Override
	public void updateJsapiTicket(String jsapiticket) {
		WxInfo wxInfo = getWxInfo("1");
		wxInfo.setJsapiticket(jsapiticket);
		wxInfo.setJsapiticketdate(new Date());
		updateWxInfo(wxInfo);
	}

	@Override
	public String setWbAccess_token(String code) {
		
		//获取Access Token  post方式  已修改数据库，直接从数据库中获取appkey 和sercet两个值,还有Access Token,在测试阶段需要1天获取一次，通过审核后30天获取一次。
		WxInfo wxInfo = getWxInfo("2");
		
		String getAccess_token = "https://api.weibo.com/oauth2/access_token";
		String param = "client_id=YOUR_CLIENT_ID&client_secret=YOUR_CLIENT_SECRET&grant_type=authorization_code&redirect_uri=YOUR_REGISTERED_REDIRECT_URI&code=CODE";
		param = param.replace("YOUR_CLIENT_ID", wxInfo.getAppid());
		param = param.replace("YOUR_CLIENT_SECRET", wxInfo.getAppsecret());
		param = param.replace("YOUR_REGISTERED_REDIRECT_URI", wxInfo.getRedirectUri());
		param = param.replace("CODE", code);
		System.out.println(param);
		
		String access_token_json = HttpRequestUtil.sendPost(getAccess_token, param);
		System.out.println("====access_token_json========"+access_token_json);
		String access_token = "";
		if(StringUtils.isNotBlank(access_token_json)){
			JSONObject newTokenJson = JSONObject.fromObject(access_token_json);
			if(null!=newTokenJson.get("errcode")){
	        	System.out.println("获取token失败:"+newTokenJson);
	        	return "";
	        }
			access_token = newTokenJson.get("access_token").toString();
			if(StringUtils.isNotBlank(access_token)){
				//存入数据库
				wxInfo.setAccesstoken(access_token);
				wxInfo.setAccesstokendate(new Date());
				updateWxInfo(wxInfo);
			}
		}
		
		return access_token;
	}

	@Override
	public String getWbAccessToken(String code) {
		WxInfo wxInfo = getWxInfo("2");
		if(StringUtils.isNotBlank(wxInfo.getAccesstoken())){
			//判断是否过期，
			 Date accessTokenDate = wxInfo.getAccesstokendate();
			Long times = DateTimeUtils.getDiffMinute(accessTokenDate);
            if(times > 42480) {//测试中的token，1天过期，提前半个小时获取，23.5小时*60=1410    审核通过的token 有效期30天 29.5*24*60 42480
            	//重新获取
            	return setWbAccess_token(code);
            }else{
            	return wxInfo.getAccesstoken();
            }
		}else{//重新获取返回
			return setWbAccess_token(code);
		}
	}

	@Override
	public String sendWbInfo(String idInfo,String accessToken) {
		String[] idInfoArray = idInfo.split("_");
		String type = idInfoArray[0];
		String id = idInfoArray[1];
		String title = "";
		String pic = "";
		Status status = null;
		if("activity".equals(type)){//发送活动的数据
			//根据id查询活动信息
			Map<String, Object> parameter  = new HashMap<String, Object>();
			parameter.put("id", Integer.parseInt(id));
			Activity activity = activityDao.findById(parameter);
			
			User user = userService.findUserById(activity.getCreateUserId()+"");
			
			//发微博
			title = (StringUtils.isNotBlank(activity.getWbTitle())?activity.getWbTitle():activity.getTitle())+"\n"+ConstantUtils.H5_URL+"page/activity/activityDetail.html?busId="+user.getBelongBusUserId()+"&userId=&id="+activity.getId()+"&sourse=wb";
			pic = activity.getWbImg();
		}else if("new".equals(type)){//企业风采
			//通过id查询企业风采信息
			News news = newsService.findById(id);
			User user = userService.findUserById(news.getCreateUserId()+"");
			//发微博
			title = (StringUtils.isNotBlank(news.getWbTitle())?news.getWbTitle():news.getNewTitle())+"\n"+ConstantUtils.H5_URL+"page/detail/detail.html?busId="+user.getBelongBusUserId()+"&userId=&id="+news.getNewId()+"&list_type=2&sourse=wb";
			pic = news.getWbImg();
		}else if("club".equals(type)){
			ReadBookClub readBookClub = readBookClubService.findById(id);
			User user = userService.findUserById(readBookClub.getCreateUserId()+"");
			//发微博
			title = (StringUtils.isNotBlank(readBookClub.getWbTitle())?readBookClub.getWbTitle():readBookClub.getClubTitle())+"\n"+ConstantUtils.H5_URL+"page/detail/detail.html?busId="+user.getBelongBusUserId()+"&userId=&id="+readBookClub.getClubId()+"&list_type=3&sourse=wb";
			pic = readBookClub.getWbImg();
		}else if("course".equals(type)){
			Course course = courseService.findById(id);
			User user = userService.findUserById(course.getCreateUserId()+"");
			//发微博
			title = (StringUtils.isNotBlank(course.getWbTitle())?course.getWbTitle():course.getTitle())+"\n"+ConstantUtils.H5_URL+"page/detail/courseDetail.html?busId="+user.getBelongBusUserId()+"&userId=&courseId="+course.getCourseId()+"&type=1&sourse=wb";
			pic = course.getWbImg();
		}else if("booklisttype".equals(type)){
			BookListType bookListType = bookListTypeService.findById(id);
			User user = userService.findUserById(bookListType.getCreateUserId()+"");
			//发微博
			title = (StringUtils.isNotBlank(bookListType.getWbTitle())?bookListType.getWbTitle():bookListType.getTypeName())+"\n"+ConstantUtils.H5_URL+"page/list/bookList.html?busId="+user.getBelongBusUserId()+"&userId=&typeId="+bookListType.getId()+"&sourse=wb";
			pic = bookListType.getWbImg();
		}
		
		status = sendPost(accessToken, title, pic);
		
		if(null!=status){
			wbSendInfoService.insertWbSendInfo(id,type,title,status);
			return status.getId();
		}
		
		return "";
	}
	
	
	public Status sendPost(String accessToken,String title,String filePath){
		Status status = null;
		try {
			title = title.replace("#", "");//title title限制在140字以内
			/*if(title.length()>140){
				title = title.substring(0, 135)+"...";
			}*/
						
			byte[] imgByte = null;
			
			Timeline tm = new Timeline(accessToken);
			if(StringUtils.isNotBlank(filePath)){
				title = URLEncoder.encode(title, "UTF-8");
				imgByte = getImageFromNetByUrl(ConstantUtils.BASE_PATH_URL+filePath);
				
				ImageItem it = new ImageItem("pic",imgByte);
				status = tm.uploadStatusShare(title, it);
			}else{
				status = tm.updateStatusShare(title);
			}
			//System.out.println(status.toString());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	
	public static byte[] getImageFromNetByUrl(String strUrl) {
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
			byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
			return btImg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static byte[] readInputStream(InputStream inStream) throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[10240];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

}

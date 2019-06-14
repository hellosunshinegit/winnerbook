package com.winnerbook.busInfo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.common.util.ConstantUtils;
import com.winnerbook.base.common.util.DateTimeUtils;
import com.winnerbook.base.common.util.FileUtils;
import com.winnerbook.base.common.util.QrcodeZxing;
import com.winnerbook.base.common.util.Tools;
import com.winnerbook.base.common.util.WaterMarkUtils;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.busInfo.dao.BusInfoDao;
import com.winnerbook.busInfo.dao.UserBusCourseTypeDao;
import com.winnerbook.busInfo.dto.BusInfo;
import com.winnerbook.busInfo.dto.UserBusCourseType;
import com.winnerbook.busInfo.dto.UserBusInfo;
import com.winnerbook.busInfo.service.BusInfoService;
import com.winnerbook.share.dto.Qrcode;
import com.winnerbook.share.service.QrcodeService;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.service.UserService;

@Service("busInfoService")
public class BusInfoServiceImpl extends BaseServiceImpl implements BusInfoService{
	
	@Autowired
	private BusInfoDao busInfoDao;
	
	@Autowired
	private UserBusCourseTypeDao userBusCourseTypeDao;
	
	@Autowired
	private QrcodeService qrcodeService;
	
	@Autowired
	private UserService userService;

	@Override
	public UserBusInfo findById(String userId) {
		// TODO Auto-generated method stub
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("userId", Integer.parseInt(userId));
		return busInfoDao.findById(parameter);
	}

	@Override
	public PageDTO<UserBusInfo> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<UserBusInfo> pageDTO = new PageDTO<UserBusInfo>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = busInfoDao.selectCount(parameter);
		List<UserBusInfo> data = null;
		if (rowSize > 0) {
			data = busInfoDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}

	@Override
	public void update(UserBusInfo busInfo,HttpServletRequest request) {
		//根据userid查询此用户是否完善企业信息,如果没有数据，则insert，否则update
		UserBusInfo userBusInfo = findById(busInfo.getUserId().toString());
		userBusInfo.setBusName(busInfo.getBusName());
		userBusInfo.setBusLogo(busInfo.getBusLogo());
		userBusInfo.setBusDes(busInfo.getBusDes());
		userBusInfo.setBusDetail(busInfo.getBusDetail());
		userBusInfo.setBrandDate(busInfo.getBrandDate());
		userBusInfo.setBusNumber(busInfo.getBusNumber());
		userBusInfo.setBusIndustry(busInfo.getBusIndustry());
		userBusInfo.setBusProvince(busInfo.getBusProvince());
		userBusInfo.setBusCity(busInfo.getBusCity());
		userBusInfo.setBusCounty(busInfo.getBusCounty());
		userBusInfo.setBusAddress(busInfo.getBusAddress());
		userBusInfo.setBrandBusName(busInfo.getBrandBusName());
		userBusInfo.setMobileBusName(busInfo.getMobileBusName());
		int userBusInfoCount = findBusInfoById(busInfo.getUserId().toString());
		if(userBusInfoCount==0){
			busInfoDao.insert(userBusInfo);
			logRecord("2","企业信息添加，id："+busInfo.getUserId());
		}else{
			busInfoDao.update(userBusInfo);
			logRecord("2","企业信息更新，id："+busInfo.getUserId());
		}
	}

	@Override
	public List<User> findBusUserName() {
		// TODO Auto-generated method stub
		return busInfoDao.findBusUserName();
	}

	@Override
	public int findBusInfoById(String userId) {
		return busInfoDao.findBusInfoById(userId);
	}

	@Override
	public String customCourseTypeSubmit(String str) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(str);
			Integer userId = null!=jsonObject.getString("userId")?jsonObject.getInt("userId"):0;
			List<UserBusCourseType> userBusCourseTypes = new LinkedList<>();
			JSONArray courseTypeArray = jsonObject.getJSONArray("courseTypeArray");
			if(null!=courseTypeArray && courseTypeArray.size()>0){
				for(int i = 0;i<courseTypeArray.size();i++){
					JSONObject courseType = (JSONObject) courseTypeArray.get(i);
					if(StringUtils.isNotBlank(courseType.getString("courseTypeId"))){
						UserBusCourseType record = new UserBusCourseType();
						record.setUserId(userId);
						record.setCourseTypeId(courseType.getInt("courseTypeId"));
						record.setBusCreateDate(new Date());
						userBusCourseTypes.add(record);
					}
				}
			}
			
			//先查询是否有值  
			List<UserBusCourseType> busCourseTypes = userBusCourseTypeDao.findByUserId(userId.toString());
			if(busCourseTypes.size()>0){
				userBusCourseTypeDao.delete(userId.toString());
			}
			
			userBusCourseTypeDao.insert(userBusCourseTypes);
			return "200";
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
	}

	@Override
	public Qrcode getBusQrcode(String busId) {
		//根据busId查询是否有二维码
		Qrcode qrcode = qrcodeService.getQrcodeByBusId(busId);
		if(null==qrcode){
			//根据id查询name
			User user = userService.findUserById(busId);
			
			//给企业生成二维码信息
			qrcode = new Qrcode();
			qrcode.setName("企业预览二维码："+user.getUserUnitName());
			qrcode.setAddress("登陆后，右上角-后台预览手机端");
			qrcode.setForwardUrl(ConstantUtils.H5_URL+"?busId="+busId);
			qrcode.setBusId(Integer.parseInt(busId));
			qrcodeService.insert(qrcode);
			qrcode.setIsNewGenerate("1");//是新生成
			
			//更新企业信息表存qrcodeId
			UserBusInfo userBusInfo = findById(busId);
			userBusInfo.setManageQrcodeId(qrcode.getId());
			busInfoDao.update(userBusInfo);
 		}else{
 			qrcode.setIsNewGenerate("0");//不是新生成
 		}
		return qrcode;
		
	}

	@Override
	public Qrcode getBusBrandQrcode(String busId) {
		Qrcode qrcode = qrcodeService.getBrandQrcodeByBusId(busId);
		if(null==qrcode){
			//根据id查询name
			User user = userService.findUserById(busId);
			
			//给企业生成二维码信息
			qrcode = new Qrcode();
			qrcode.setName("企业名牌二维码："+user.getUserUnitName());
			qrcode.setAddress("企业名牌");
			qrcode.setForwardUrl(ConstantUtils.H5_URL+"?busId="+busId);
			qrcode.setBusId(Integer.parseInt(busId));
			qrcodeService.insert(qrcode);
			qrcode.setIsNewGenerate("1");//是新生成
			
			//更新企业信息表存qrcodeId
			UserBusInfo userBusInfo = findById(busId);
			userBusInfo.setBrandQrcodeId(qrcode.getId());
			busInfoDao.update(userBusInfo);
 		}else{
 			qrcode.setIsNewGenerate("0");//不是新生成
 		}
		return qrcode;
	}

	@Override
	public void uploadBrandImg(String busId,HttpServletResponse response) {
		UserBusInfo userBusInfo = findById(busId);
		if(StringUtils.isNotBlank(userBusInfo.getBrandDate())){
			userBusInfo.setBrandDateChinese(DateTimeUtils.transformDateChinese(userBusInfo.getBrandDate()));
		}
		Qrcode qrcode = getBusBrandQrcode(busId);
		if(null!=qrcode && StringUtils.isNotBlank(qrcode.getImg())){
			qrcode.setImg(ConstantUtils.BASE_PATH_URL+qrcode.getImg()); 
		}
		
		String imgPath = QrcodeZxing.class.getClassLoader().getResource("bus_brand_custom_max.jpg").getPath();
		String urlPath = WaterMarkUtils.addWaterMark(imgPath, userBusInfo.getBusName(), userBusInfo.getBusNumber(), qrcode.getImg(), userBusInfo.getBrandDateChinese());
		//urlPath 保存数据库
		if(StringUtils.isNotBlank(urlPath)){
			//判断数据库中是否已经有值，有值则删除
			if(StringUtils.isNotBlank(userBusInfo.getBrandImg())){
				FileUtils.delFile(userBusInfo.getBrandImg());
			}
			//保存数据库并下载
			userBusInfo.setBrandImg(urlPath);
			busInfoDao.update(userBusInfo);
 			FileUtils.downloadFilename(FileUtils.getRealtyPathName(urlPath),userBusInfo.getBusName()+"-企业名牌.jpg", response);
		}
	}

	@Override
	public String getGenerateCode() {
		return getBusNumber();
	}
	
	public String getBusNumber(){
		String busNumber = Tools.getBusNumber();
		int numberCount = busInfoDao.getNumber(busNumber);
		if(numberCount>0){
			getBusNumber();
		}
		return busNumber;
	}

}

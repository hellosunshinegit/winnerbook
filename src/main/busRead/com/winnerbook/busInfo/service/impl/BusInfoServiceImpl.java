package com.winnerbook.busInfo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.common.util.ConstantUtils;
import com.winnerbook.base.common.util.FileUtils;
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
	public void update(BusInfo busInfo,HttpServletRequest request) {
		//根据userid查询此用户是否完善企业信息,如果没有数据，则insert，否则update
		//判断是否需要上传文件
		String isAgin = request.getParameter("isAgin");//isAgin==1 重新上传   null没有重新上传
		int userBusInfoCount = findBusInfoById(busInfo.getUserId().toString());
		//上传企业头像
		if((null!=isAgin && isAgin.equals("1")) || userBusInfoCount==0){
			request.setAttribute("path","bus_logo");
			//删除之前的图片
			if(StringUtils.isNotBlank(busInfo.getBusLogo())){
				FileUtils.delFile(busInfo.getBusLogo());
			}
			String urlPath = FileUtils.uploadImgFile(request);
			busInfo.setBusLogo(urlPath);
		}
		if(userBusInfoCount==0){
			busInfoDao.insert(busInfo);
			logRecord("2","企业信息添加，id："+busInfo.getUserId());
		}else{
			busInfoDao.update(busInfo);
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
 		}else{
 			qrcode.setIsNewGenerate("0");//不是新生成
 		}
		return qrcode;
		
	}

}

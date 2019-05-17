package com.winnerbook.activity.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.activity.dao.ActivityDao;
import com.winnerbook.activity.dto.Activity;
import com.winnerbook.activity.service.ActivityService;
import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.common.util.DateTimeUtils;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.system.dto.User;

@Service("activityService")
public class ActivityServiceImpl extends BaseServiceImpl implements ActivityService{
	
	@Autowired
	private ActivityDao activityDao;
	
	SimpleDateFormat df_min = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Override
	public Activity findById(String id) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("id", Integer.parseInt(id));
		return activityDao.findById(parameter);
	}

	@Override
	public PageDTO<Activity> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<Activity> pageDTO = new PageDTO<Activity>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = activityDao.selectCount(parameter);
		List<Activity> data = null;
		if (rowSize > 0) {
			data = activityDao.listByPage(parameter);
			//判断是否失效
			for(Activity activity:data){
				activity.setIsInvalid(getInvalid(activity.getStartDate(), activity.getStartDateTime().toString()));
			}
			
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public void insert(Activity activity) {
		activity.setContent(activity.getContent().replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*"));
		User sessionUser = getSessionUser();
		activity.setCreateDate(new Date());
		activity.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		activity.setCreateUserName(sessionUser.getUserUnitName());
		activityDao.insert(activity);
		logRecord("2","活动信息添加，id："+activity.getId());
	}

	@Override
	public void update(Activity activity) {
		activity.setContent(activity.getContent().replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*"));
		activity.setUpdateDate(new Date());
		activityDao.update(activity);
		logRecord("3","活动信息更新，id："+activity.getId());
	}

	@Override
	public Map<String, Object> getActivitys(Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> activityList = activityDao.getActivitys(parameter);
		//把日期转换为周几 
		for(Map<String, Object> activity:activityList){
			if(null!=activity.get("startDate") && StringUtils.isNotBlank(activity.get("startDate").toString())){
				activity.put("week", DateTimeUtils.dateToWeek(activity.get("startDate").toString()));
				
				//判断活动是否已经失效 活动开始时间和现在时间对比，超过活动开始事件就不可以报名了
				activity.put("isInvalid", getInvalid(activity.get("startDate").toString(), activity.get("startDateTime").toString()));
				try {
					//转换开始事件和结束时间格式化
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
					activity.put("startDate", sdf.format(df.parse(activity.get("startDate").toString())));
					activity.put("endDate", sdf.format(df.parse(activity.get("endDate").toString())));
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		int readBookClubCount = activityDao.getActivitysCount(parameter);
		map.put("list", activityList);
		map.put("count", readBookClubCount);
		return map;
	}

	@Override
	public Map<String, Object> getActivityDetail(Map<String, Object> parameter) {
		return activityDao.getActivityDetail(parameter);
	}
	
	public String getInvalid(String startDateStr,String startDateTimeStr){
		Date startDate;
		String returnStr = "0";
		try {
			startDate = df_min.parse(startDateStr+" "+startDateTimeStr);
			long min = DateTimeUtils.getDiffMinute(startDate);
			if(min>=0){
				returnStr = "1";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnStr;
	}
	
}

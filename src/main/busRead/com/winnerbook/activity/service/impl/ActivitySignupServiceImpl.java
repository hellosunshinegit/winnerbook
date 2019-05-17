package com.winnerbook.activity.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.activity.dao.ActivitySignupDao;
import com.winnerbook.activity.dto.ActivitySignup;
import com.winnerbook.activity.service.ActivitySignupService;
import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.common.util.DateTimeUtils;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;

@Service("activitySignupService")
public class ActivitySignupServiceImpl extends BaseServiceImpl implements ActivitySignupService{
	
	@Autowired
	private ActivitySignupDao activitySignupDao;

	@Override
	public PageDTO<ActivitySignup> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<ActivitySignup> pageDTO = new PageDTO<ActivitySignup>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = activitySignupDao.selectCount(parameter);
		List<ActivitySignup> data = null;
		if (rowSize > 0) {
			data = activitySignupDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public int insert(ActivitySignup activitySignup) {
		activitySignupDao.insert(activitySignup);
		return activitySignup.getId();
	}

	@Override
	public Map<String, Object> getActivitySignups(Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> activitySingupList = activitySignupDao.getActivitySignups(parameter);
		//把日期转换为周几 
		for(Map<String, Object> activity:activitySingupList){
			if(null!=activity.get("startDate") && StringUtils.isNotBlank(activity.get("startDate").toString())){
				activity.put("week", DateTimeUtils.dateToWeek(activity.get("startDate").toString()));
				
				//判断活动是否已经失效 活动开始时间和现在时间对比，超过活动开始事件就不可以报名了
				activity.put("isInvalid",new ActivityServiceImpl().getInvalid(activity.get("startDate").toString(), activity.get("startDateTime").toString()));
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
		int readBookClubCount = activitySignupDao.getActivitySignupCount(parameter);
		map.put("list", activitySingupList);
		map.put("count", readBookClubCount);
		return map;
	}

	@Override
	public List<Map<String, Object>> isRepeatSingup(String busId,
			String userId, String activityId) {
		Map<String, Object> map = new HashMap<>();
		map.put("busId", busId);
		map.put("userId", userId);
		map.put("activityId", activityId);
		return activitySignupDao.isRepeatSingup(map);
	}

}

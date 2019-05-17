package com.winnerbook.activity.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.activity.dao.ReadBookClubDao;
import com.winnerbook.activity.dto.ReadBookClub;
import com.winnerbook.activity.service.ReadBookClubService;
import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.system.dto.User;

@Service("readBookClubService")
public class ReadBookClubServiceImpl extends BaseServiceImpl implements ReadBookClubService{
	
	@Autowired
	private ReadBookClubDao readBookClubDao;

	@Override
	public ReadBookClub findById(String clubId) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("clubId", Integer.parseInt(clubId));
		return readBookClubDao.findById(parameter);
	}

	@Override
	public PageDTO<ReadBookClub> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<ReadBookClub> pageDTO = new PageDTO<ReadBookClub>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = readBookClubDao.selectCount(parameter);
		List<ReadBookClub> data = null;
		if (rowSize > 0) {
			data = readBookClubDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public void insert(ReadBookClub readBookClub) {
		readBookClub.setClubDes(readBookClub.getClubDes().replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*"));
		User sessionUser = getSessionUser();
		readBookClub.setCreateDate(new Date());
		readBookClub.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		readBookClub.setCreateUserName(sessionUser.getUserUnitName());
		readBookClubDao.insert(readBookClub);
		logRecord("2","读书会展示信息添加，id："+readBookClub.getClubTitle());
	}

	@Override
	public void update(ReadBookClub readBookClub) {
		readBookClub.setClubDes(readBookClub.getClubDes().replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*"));
		readBookClub.setUpdateDate(new Date());
		readBookClubDao.update(readBookClub);
		logRecord("3","读书会展示信息更新，id："+readBookClub.getClubId());
	}

	@Override
	public void delete(String clubId) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("clubId", clubId);
		readBookClubDao.delete(parameter);
	}

	@Override
	public Map<String, Object> getBookClubs(Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> readBookClubList = readBookClubDao.getBookClubs(parameter);
		int readBookClubCount = readBookClubDao.getBookClubsCount(parameter);
		map.put("list", readBookClubList);
		map.put("count", readBookClubCount);
		return map;
	}

	@Override
	public Map<String, Object> getReadBookClubDetail(
			Map<String, Object> parameter) {
		return readBookClubDao.getReadBookClubDetail(parameter);
	}
	
	
}

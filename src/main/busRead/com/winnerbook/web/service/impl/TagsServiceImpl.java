package com.winnerbook.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.system.dto.User;
import com.winnerbook.web.dao.TagsDao;
import com.winnerbook.web.dto.Tags;
import com.winnerbook.web.service.TagsService;

@Service("tagsService")
public class TagsServiceImpl extends BaseServiceImpl implements TagsService{
	
	@Autowired
	private TagsDao tagsDao;
	
	@Override
	public Tags findById(String id) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("tagId", Integer.parseInt(id));
		return tagsDao.findById(parameter);
	}

	@Override
	public PageDTO<Tags> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<Tags> pageDTO = new PageDTO<Tags>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = tagsDao.selectCount(parameter);
		List<Tags> data = null;
		if (rowSize > 0) {
			data = tagsDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public void insert(Tags tags) {
		User sessionUser = getSessionUser();
		tags.setCreateDate(new Date());
		tags.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		tags.setCreateUserName(sessionUser.getUserUnitName());
		tagsDao.insert(tags);
		logRecord("2","版块添加，："+tags.getTagName());
	}

	@Override
	public void update(Tags tags) {
		tags.setUpdateDate(new Date());
		tagsDao.update(tags);
		logRecord("3","版块信息更新，id："+tags.getTagId());
	}

	@Override
	public List<Tags> getTagsList() {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("sessionUser",getSessionUser());
		return tagsDao.getTagsList(parameter);
	}

	@Override
	public int tagsCount() {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("sessionUser",getSessionUser());
		return tagsDao.tagsCount(parameter);
	}
	
}

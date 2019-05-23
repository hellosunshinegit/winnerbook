package com.winnerbook.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.eval.ConcatEval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.common.util.ConstantUtils;
import com.winnerbook.base.common.util.HttpRequestUtil;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.system.dto.User;
import com.winnerbook.web.dao.NewsDao;
import com.winnerbook.web.dto.News;
import com.winnerbook.web.service.NewsService;

@Service("newsService")
public class NewsServiceImpl extends BaseServiceImpl implements NewsService{
	
	@Autowired
	private NewsDao newsDao;

	@Override
	public News findById(String id) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("newId", Integer.parseInt(id));
		return newsDao.findById(parameter);
	}

	@Override
	public PageDTO<News> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<News> pageDTO = new PageDTO<News>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = newsDao.selectCount(parameter);
		List<News> data = null;
		if (rowSize > 0) {
			data = newsDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public void insert(News news) {
		User sessionUser = getSessionUser();
		news.setNewChannel("0");
		news.setCreateDate(new Date());
		news.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		news.setCreateUserName(sessionUser.getUserUnitName());
		newsDao.insert(news);
		logRecord("2","企业风采添加，："+news.getNewTitle());
		//发送微博
	}

	@Override
	public void update(News news) {
		news.setUpdateDate(new Date());
		newsDao.update(news);
		logRecord("3","企业风采更新，id："+news.getNewId());
	}

	@Override
	public Map<String, Object> getNewsList(Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> newsList = newsDao.getNewsList(parameter);
		int newsCount = newsDao.getNewsCount(parameter);
		map.put("list", newsList);
		map.put("count", newsCount);
		return map;
	}

	@Override
	public void updateStatus(String newId, String status) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("newId", newId);
		parameter.put("newStatus", status);
		newsDao.updateStatus(parameter);
		logRecord("3","企业风采更新状态，id："+newId);

	}

	@Override
	public Map<String, Object> getNewsDetail(Map<String, Object> parameter) {
		return newsDao.getNewsDetail(parameter);
	}
	
}

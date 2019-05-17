package com.winnerbook.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.web.dao.NewsDao;
import com.winnerbook.web.dto.News;

@Repository("newsDao")
public class NewsDaoImpl  extends BaseDAO implements NewsDao{
	private static final String NEWS_MAPPER = "NewsMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(NEWS_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<News> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(NEWS_MAPPER + "listByPage", parameter);
	}

	@Override
	public News findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(NEWS_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(News record) {
		return this.sqlSession.insert(NEWS_MAPPER+"insert",record);
	}

	@Override
	public int update(News record) {
		return this.sqlSession.insert(NEWS_MAPPER+"update",record);
	}

	@Override
	public List<Map<String, Object>> getNewsList(Map<String, Object> parameter) {
		return this.sqlSession.selectList(NEWS_MAPPER+"getNewsList", parameter);
	}

	@Override
	public void updateStatus(Map<String, Object> parameter) {
		this.sqlSession.selectList(NEWS_MAPPER+"updateStatus",parameter);
	}

	@Override
	public int getNewsCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(NEWS_MAPPER + "getNewsCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public Map<String, Object> getNewsDetail(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(NEWS_MAPPER+"getNewsDetail",parameter);
	}
}

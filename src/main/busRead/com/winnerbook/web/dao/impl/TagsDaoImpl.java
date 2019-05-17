package com.winnerbook.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.web.dao.TagsDao;
import com.winnerbook.web.dto.Tags;

@Repository("tagsDao")
public class TagsDaoImpl  extends BaseDAO implements TagsDao{
	private static final String TAGS_MAPPER = "TagsMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(TAGS_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<Tags> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(TAGS_MAPPER + "listByPage", parameter);
	}

	@Override
	public Tags findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(TAGS_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(Tags record) {
		return this.sqlSession.insert(TAGS_MAPPER+"insert",record);
	}

	@Override
	public int update(Tags record) {
		return this.sqlSession.insert(TAGS_MAPPER+"update",record);
	}

	@Override
	public List<Tags> getTagsList(Map<String, Object> parameter) {
		return this.sqlSession.selectList(TAGS_MAPPER+"getTagsList",parameter);
	}

	@Override
	public int tagsCount(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(TAGS_MAPPER+"tagsCount",parameter);
	}
}

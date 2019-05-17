package com.winnerbook.course.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.course.dao.ReadThoughtDao;
import com.winnerbook.course.dto.ReadThought;

@Repository("readThoughtDao")
public class ReadThoughtDaoImpl  extends BaseDAO implements ReadThoughtDao{
	private static final String READTHOUGHT_MAPPER = "ReadThoughtMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(READTHOUGHT_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<ReadThought> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(READTHOUGHT_MAPPER + "listByPage", parameter);
	}

	@Override
	public ReadThought findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(READTHOUGHT_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(ReadThought readThought) {
		return this.sqlSession.insert(READTHOUGHT_MAPPER+"insert",readThought);
	}

	@Override
	public int update(ReadThought readThought) {
		return this.sqlSession.insert(READTHOUGHT_MAPPER+"update",readThought);
	}

	@Override
	public void deleteReadThought(Map<String, Object> parameter) {
		this.sqlSession.delete(READTHOUGHT_MAPPER+"delete",parameter);
	}

	@Override
	public List<Map<String, Object>> getReadThoughts(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(READTHOUGHT_MAPPER+"getReadThoughts",parameter);
	}

	@Override
	public int getReadThoughtsCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(READTHOUGHT_MAPPER + "getReadThoughtsCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public Map<String, Object> getReadThoughtsDetail(
			Map<String, Object> parameter) {
		return this.sqlSession.selectOne(READTHOUGHT_MAPPER+"getReadThoughtsDetail",parameter);
	}

	@Override
	public List<Map<String, Object>> getReadThoughtUser(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(READTHOUGHT_MAPPER+"getReadThoughtUser",parameter);
	}

	@Override
	public int getReadThoughtUserCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(READTHOUGHT_MAPPER + "getReadThoughtUserCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

}

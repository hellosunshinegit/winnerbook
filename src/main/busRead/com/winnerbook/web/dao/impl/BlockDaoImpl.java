package com.winnerbook.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.web.dao.BlockDao;
import com.winnerbook.web.dto.Block;

@Repository("blockDao")
public class BlockDaoImpl  extends BaseDAO implements BlockDao{
	private static final String BLOCK_MAPPER = "BlockMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(BLOCK_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<Block> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BLOCK_MAPPER + "listByPage", parameter);
	}

	@Override
	public Block findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(BLOCK_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(Block record) {
		return this.sqlSession.insert(BLOCK_MAPPER+"insert",record);
	}

	@Override
	public int update(Block record) {
		return this.sqlSession.insert(BLOCK_MAPPER+"update",record);
	}

	@Override
	public List<Block> getBlockList(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BLOCK_MAPPER+"getBlockList", parameter);
	}

	@Override
	public List<Map<String, Object>> getBlocks(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BLOCK_MAPPER+"getBlocks", parameter);
	}
}

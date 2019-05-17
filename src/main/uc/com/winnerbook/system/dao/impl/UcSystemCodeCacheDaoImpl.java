package com.winnerbook.system.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.system.dao.UcSystemCodeCacheDao;
import com.winnerbook.system.dto.UcSystemCodeCache;


@Repository(value="ucSystemCodeCacheDao")
public class UcSystemCodeCacheDaoImpl extends BaseDAO implements UcSystemCodeCacheDao{
	
	private static final String UCSYSTEMCODECACHE_MAPPER = "UcSystemCodeCacheMapper.";

	@Override
	public UcSystemCodeCache findCodeByCodeName(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(UCSYSTEMCODECACHE_MAPPER+"findCodeByCodeName", parameter);
	}

	
}

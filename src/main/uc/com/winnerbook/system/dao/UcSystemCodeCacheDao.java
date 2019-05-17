package com.winnerbook.system.dao;

import java.util.Map;

import com.winnerbook.system.dto.UcSystemCodeCache;

public interface UcSystemCodeCacheDao {

	/**
	 * 根据code查询
	 * @param parameter
	 * @return
	 */
	UcSystemCodeCache findCodeByCodeName(Map<String, Object> parameter);
}

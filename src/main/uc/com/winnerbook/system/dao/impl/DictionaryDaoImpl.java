/** 
* 2015-3-3
* UserInfoDaoImpl.java 
* author:hxs
*/ 
package com.winnerbook.system.dao.impl; 

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.system.dao.DictionaryDao;
import com.winnerbook.system.dto.Dictionary;
import com.winnerbook.system.dto.User;

@Repository("dictionaryDao")
public class DictionaryDaoImpl extends BaseDAO implements DictionaryDao{
	private static final String DICTIONARY_MAPPER = "DictionaryMapper.";
	@Override
	public Dictionary findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(DICTIONARY_MAPPER + "findById", parameter);
	}

	@Override
	public void insert(Dictionary dictionary) {
		this.sqlSession.insert(DICTIONARY_MAPPER + "insert", dictionary);
	}

	@Override
	public List<Dictionary> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(DICTIONARY_MAPPER + "listByPage", parameter);
	}

	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(DICTIONARY_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public void update(Dictionary dictionary) {
		this.sqlSession.update(DICTIONARY_MAPPER+"update", dictionary);
	}

	@Override
	public void delete(String dicId) {
		this.sqlSession.update(DICTIONARY_MAPPER+"delete", dicId);
	}
	
	@Override
	public List<User> findDictionaryByOrganIds(Map<String, Object> parameter) {
		return this.sqlSession.selectList(DICTIONARY_MAPPER+"findDictionaryByOrganIds", parameter);
	}

	@Override
	public List<Dictionary> getDictionaries(String dicCode) {
		return this.sqlSession.selectList(DICTIONARY_MAPPER + "getDictionariesByCode", dicCode);
	}

}

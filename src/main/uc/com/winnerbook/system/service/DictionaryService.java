package com.winnerbook.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.system.dto.Dictionary;
import com.winnerbook.system.dto.UcSystemCodeCache;

public interface DictionaryService {
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	Dictionary findById(String id);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageDTO<Dictionary> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 * @param dictionary
	 */
	void insert(Dictionary dictionary);
	
	/**
	 * 修改
	 * @param dictionary
	 */
	void update(Dictionary dictionary);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * 得到code相同的数据
	 * @param code
	 * @return
	 */
	List<Dictionary> getDictionaries(String dicCode);
	
	/**
	 * 
	 * @param cacheKey
	 * @param params
	 * @return
	 */
	public List getDataListByCacheCode(String cacheKey,List params);

	
}

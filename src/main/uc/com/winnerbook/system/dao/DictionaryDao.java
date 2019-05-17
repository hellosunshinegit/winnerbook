/** 
* 2015-3-3
* userDao.java 
* author:hanxiaoshuang
*/ 
package com.winnerbook.system.dao; 

import java.util.List;
import java.util.Map;

import com.winnerbook.system.dto.Dictionary;
import com.winnerbook.system.dto.User;

public interface DictionaryDao {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	Dictionary findById(Map<String, Object> parameter);
	
	/**
	 * 查询总条数
	 * @param parameter
	 * @return
	 */
	int  selectCount(Map<String, Object> parameter);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	List<Dictionary> listByPage(Map<String, Object> parameter);

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
	void delete(String dicId);
	
	/**
	 * 得到code相同的数据
	 * @param code
	 * @return
	 */
	List<Dictionary> getDictionaries(String dicCode);
	
	/**
	 * 根据organId 查询此机构下是否有字典
	 * @return
	 */
	List<User> findDictionaryByOrganIds(Map<String, Object> parameter);

}

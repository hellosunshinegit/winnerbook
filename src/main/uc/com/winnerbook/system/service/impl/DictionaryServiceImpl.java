package com.winnerbook.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.common.dto.CacheCodeBean;
import com.winnerbook.base.frame.dao.JdbcDao;
import com.winnerbook.system.dao.DictionaryDao;
import com.winnerbook.system.dao.UcSystemCodeCacheDao;
import com.winnerbook.system.dto.Dictionary;
import com.winnerbook.system.service.DictionaryService;

@Service(value="dictionaryService")
public class DictionaryServiceImpl implements DictionaryService{
	
	private static final Logger logger = LoggerFactory.getLogger(DictionaryServiceImpl.class);
	
	private static final HashMap _cacheCodeMap = new HashMap();
	
	private static final HashMap<String,List<List>> _dictionaryMap=new HashMap<String,List<List>>();
	
	@Autowired
	private DictionaryDao dictionaryDao;
	
	@Autowired
	private UcSystemCodeCacheDao ucSystemCodeCacheDao;
	
	@Autowired
	private JdbcDao jdbcDao;
	
	@Override
	public Dictionary findById(String id) {
		Map<String, Object> parameter = new HashMap<String,Object>();
		parameter.put("dicId", id);
		return dictionaryDao.findById(parameter);
	}

	@Override
	public PageDTO<Dictionary> listByPage(Map<String, Object> parameter,
			Integer pageIndex, Integer pageSize) {
		PageDTO<Dictionary> pageDTO = new PageDTO<Dictionary>(pageIndex,pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = dictionaryDao.selectCount(parameter);
		List<Dictionary> data = null;
		if (rowSize > 0) {
			data = dictionaryDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}

	@Override
	public void insert(Dictionary dictionary) {
		dictionary.setDicCreatedate(new Date());
		dictionaryDao.insert(dictionary);
	}

	@Override
	public void update(Dictionary dictionary) {
		try {
			dictionary.setDicUpdatedate(new Date());
			dictionaryDao.update(dictionary);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String dicId) {
		dictionaryDao.delete(dicId);
	}

	@Override
	public List<Dictionary> getDictionaries(String dicCode) {
		return dictionaryDao.getDictionaries(dicCode);
	}
	
	@Override
	public List getDataListByCacheCode(String cacheKey,List params) {
    	CacheCodeBean cacheCode = getCacheCodeInfo(cacheKey);
    	cacheKey = cacheKey.toUpperCase();
        List returnList = new ArrayList();
        //如果带参数则直接从数据库查询
        if (params!=null && params.size()>0){
        	String strSQL =  cacheCode.getListSql();
        	for (int i = 0;i < params.size(); i++){
	        	 int beign=StringUtils.indexOf(strSQL, "?");
	        	 if(beign>-1){
	        		 String value=(String)params.get(i);
		       		 /*int endClose=StringUtils.indexOf(strSQL, "]",beign);
		       		 int endOpen=StringUtils.lastIndexOf(strSQL, "[",beign);
		       		 String oldParm="";
		       		 String newParm="";
		       		 String value=(String)params.get(i);
		       		 if(endOpen>-1 && endClose>-1){
		       			oldParm=StringUtils.substring(strSQL, endOpen, endClose+1);
		       			newParm=StringUtils.isNotBlank(value)? StringUtils.replaceOnce(StringUtils.replaceOnce(StringUtils.replaceOnce(oldParm, "?",value),"[",""),"]",""):"1=1";
		       		 }else{
		       			oldParm="?";
		       			newParm=value;
		       		 }*/
	        		
	        		 strSQL = StringUtils.replaceOnce(strSQL,"?",value);
	        	 }
        		//strSQL = StringUtils.replaceOnce(strSQL, "?",(String)params.get(i));
        	}
        	return jdbcDao.queryData(strSQL);
        }
        if(CacheCodeBean.CONFIG_TYPE_DICTIONARY_DATA.equals(cacheCode.getConfigType())){
        	returnList = queryDictionaryByCode(cacheKey);
    	}else{
    		returnList = jdbcDao.queryData(cacheCode.getListSql());
    	}
        return returnList;
    }
	
	/**
     * 获得cachecode的配置信息,无法从cach_code 获得信息，构造一个CacheCode对象，
     * @param code
     * @return
     */
	private CacheCodeBean getCacheCodeInfo(String code) {
    	CacheCodeBean cacheCode = (CacheCodeBean) _cacheCodeMap.get(code);
        if (cacheCode == null) {
			List list = null;
        	try{
        		list = jdbcDao.queryData("select t.code,t.list_sql,t.is_Cached from uc_system_code_cache t where t.code='"+code+"'");
        	}catch(Exception e){
        		
        	}
        	if (list!=null && list.size()>0){
        		List tempList=(List)list.get(0);
        		if(null!=tempList&&tempList.size()>0){
        			cacheCode=new CacheCodeBean();
        			cacheCode.setCode((String)tempList.get(0));
        			cacheCode.setListSql((String)tempList.get(1));
        			cacheCode.setIsCached((String)tempList.get(2));
        		}
        	}
        	//无法从cach_code 获得信息，构造一个CacheCode对象，从pram_data读取数据
        	if (cacheCode==null){
        		cacheCode = new CacheCodeBean();
        		cacheCode.setCode(code);
        		cacheCode.setConfigType(CacheCodeBean.CONFIG_TYPE_DICTIONARY_DATA);
        		cacheCode.setIsCached("1");  //设置缓存
        		cacheCode.setListSql("select t.dic_itemvalue,t.dic_itemname from uc_dictionary t where 1=1 and t.dic_itemcode='"+code+"' order by t.dic_itemsort asc");		
        	}
        	if (cacheCode!=null){
	            synchronized (_cacheCodeMap) {
	                _cacheCodeMap.put(code, cacheCode);
	            }
        	}
        }
        return cacheCode;
    }
	
	public List queryDictionaryByCode(String code){
		if(_dictionaryMap.get(code)==null){
			String sql="select t.dic_itemvalue,t.dic_itemname from uc_dictionary t where 1=1 and t.dic_itemcode='"+code+"' order by t.dic_itemsort asc";
			_dictionaryMap.put(code,jdbcDao.queryData(sql));
		}
		return _dictionaryMap.get(code);
	}

}

package com.winnerbook.base.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.winnerbook.base.common.UIComponentBean;
import com.winnerbook.base.common.UITreeBean;
import com.winnerbook.system.dto.Dictionary;
import com.winnerbook.system.dto.UcSystemCodeCache;
import com.winnerbook.system.service.DictionaryService;

public class Helper {
	
	@Autowired
	private DictionaryService dictionaryService;
		
	 /**
	 * @Description 获得字典表或缓存中的列表，封装成UIComponentBean，用于扩展标签
	 * @param codeName
	 * @param params
	 * @return List<UIComponentBean>
	 */
    public static List<UIComponentBean> getCodeListToUIComponentBean(String codeName,List params){
    	List list = getCodeList(codeName,params);
    	List<UIComponentBean> retList = new ArrayList<UIComponentBean>();
    	for(java.util.Iterator it= list.iterator();it.hasNext();){
    		Dictionary tmp = (Dictionary)it.next();
    		UIComponentBean bean = new UIComponentBean();
    		if(null!=tmp){
    			bean.setKey(tmp.getDicItemvalue());
    			bean.setValue(tmp.getDicItemname());
    			}
    		
    		retList.add(bean);
    		}
    	return retList;
    }
    
    public static List<UIComponentBean> getCodeListToUIComponentBean(String codeName){
    	return getCodeListToUIComponentBean(codeName,null);
    }
    /**
	 * @Description 获得字典表或缓存中的列表
	 * @param codeName
	 * @param params
	 * @return List
	 */
    public static List getCodeList(String itemcode,List params){
    	List list = null;
	    try{
	    	DictionaryService dictionaryService=(DictionaryService)SpringContextHolder.getBean("dictionaryService");
	    	//根据code得到code的list
	    	list = dictionaryService.getDictionaries(itemcode);
	     }catch(Exception e){
	    	 
	     }
         if (list==null){
        	list = new ArrayList();
         }
         return list;
    }
    
    /**
	 * 
	 * @param bean
	 * @param moduleId
	 * @return
	 */
	public static String getDictionary(String code,String value){
		String itemName="";
		DictionaryService dictionaryService=(DictionaryService)SpringContextHolder.getBean("dictionaryService");
		List<Dictionary> list = dictionaryService.getDictionaries(code);
		for(Dictionary dataDic:list){
			if(value.equals(dataDic.getDicItemvalue())){
				itemName=dataDic.getDicItemname();
			}
		}
	    return itemName;
	}
	
	 /**
     * 
     * @param codeName
     * @param params
     * @param expendLevel
     * @return
     */
    public static List<UITreeBean> getCodeListToUITreeBean(String codeName,List params,String expendLevel,String rootNodeId,String rootNodeName){
    	List list = getCodeListTree(codeName,params);
    	List<UITreeBean> retList = new ArrayList<UITreeBean>();
    	UITreeBean bean = new UITreeBean();
    	if(StringUtils.isNotBlank(rootNodeId)&&StringUtils.isNotBlank(rootNodeName)){
    		bean.setId(rootNodeId);
    		bean.setpId(rootNodeId);
    		bean.setName(rootNodeName);
    	}else{
	    	if(null!=list&&list.size()>0){
	    		List tmp = (List)list.get(0);
	    		if(tmp.size()>0){
	    			bean.setId((String)tmp.get(0));
	    			if(tmp.size()>1){
	    				bean.setpId((String)tmp.get(1));
	    			}
	    			if(tmp.size()>2){
	    				bean.setName((String)tmp.get(2));
	    			}
	    		}
	    	}
    	}
    	int treeLevel=1;
    	setChildren(bean, list,expendLevel,treeLevel);
    	retList.add(bean);
    	return retList;
    }
    
    /**
     * 
     * @param bean
     * @param list
     * @param expendLevel
     * @param treeLevel
     */
    private static void setChildren(UITreeBean bean,List list,String expendLevel,int treeLevel){
    	List<UITreeBean> children=new ArrayList<UITreeBean>();
    	for (int i = 0; i < list.size(); i++) {
    		List tmp = (List)list.get(i);
    		if(bean.getId().equals((String)tmp.get(1))){
    			if(treeLevel<=Integer.parseInt(expendLevel)){
	    			bean.setOpen(true);
	    		}
	    		bean.setParent(true);
    			treeLevel++;
    			UITreeBean tmpBean=new UITreeBean();
    			tmpBean.setId((String)tmp.get(0));
    			tmpBean.setpId((String)tmp.get(1));
    			tmpBean.setName((String)tmp.get(2));
    			setChildren(tmpBean,list,expendLevel,treeLevel);
    			children.add(tmpBean);
    		}
		}
    	bean.setChildren(children);
    }
    
    /**
     * @Description 获得字典表或缓存中的列表
     * @param codeName
     * @param params
     * @return List
     */
    public static List getCodeListTree(String codeName,List params){
    	List list = null;
    	try{
    		DictionaryService dictionaryService=(DictionaryService)SpringContextHolder.getBean("dictionaryService");
    		list = dictionaryService.getDataListByCacheCode(codeName,params);
    	}catch(Exception e){
    		
    	}
    	if (list==null){
    		list = new ArrayList<UcSystemCodeCache>();
    	}
    	return list;
    }

	  
}

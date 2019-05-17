package com.winnerbook.web.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class PageUtil {
	public static final Integer pageIndexDefault = 0;
	public static final Integer limitDefault = 10;

	//获取pageindex
	public static int getPageIndex(Integer pageIndex){
		if(pageIndex!=0 && !StringUtils.isNotBlank(pageIndex.toString())){
			pageIndex = pageIndexDefault-1;
		}
		if(pageIndex<0){
			pageIndex = pageIndexDefault;
		}
		return pageIndex*limitDefault;
  	}
	
	
	public static Map<String,Object> getParam(Map<String, Object> map,String busId,String pageIndex){
		if(null==pageIndex){
			pageIndex = "0";
		}
		map.put("busId",busId);
		map.put("start",PageUtil.getPageIndex(Integer.parseInt(pageIndex)));
		map.put("limit",PageUtil.limitDefault);
		return map;
	}

}

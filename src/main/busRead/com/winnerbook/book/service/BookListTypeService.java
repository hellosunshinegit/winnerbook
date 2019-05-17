package com.winnerbook.book.service;

import java.util.List;
import java.util.Map;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.book.dto.BookListType;

public interface BookListTypeService {

	
	BookListType findById(String id);
	
	PageDTO<BookListType> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	Map<String, Object> insert(String dataJson);

	Map<String, Object> update(String dataJson);
	
	List<Map<String, Object>> getBookListByTypeId(String id);
	
	Map<String, Object> getBookListTypes(Map<String, Object> parameter);
		
}

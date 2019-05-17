package com.winnerbook.book.dao;

import java.util.List;
import java.util.Map;

import com.winnerbook.book.dto.BookListLabelId;
import com.winnerbook.book.dto.BookListType;
import com.winnerbook.book.dto.BookListTypeId;

public interface BookListTypeDao {

	int selectCount(Map<String, Object> parameter);

	List<BookListType> listByPage(Map<String, Object> parameter);

	int insert(BookListType record);

	BookListType findById(Map<String, Object> parameter);

	int update(BookListType record);
	
	int insertBathBookListTypeId(List<BookListTypeId> bookListTypeIds);
	
	int insertBookListLabelId(List<BookListLabelId> bookListLabelIds);
	
	List<Map<String, Object>> getBookListByTypeId(Map<String, Object> parameter);
	
	int deleteBookListByTypeId(Integer typeId);
	
	int deleteLabelByTypeId(Integer typeId);
	
	List<Map<String, Object>> getBookListTypes(Map<String, Object> parameter);
	
	int getBookListTypesCount(Map<String, Object> parameter);
}
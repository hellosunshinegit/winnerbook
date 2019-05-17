package com.winnerbook.course.service;

import java.util.List;
import java.util.Map;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.course.dto.BookList;

public interface BookListService {

	/**
	 * 根据id查询
	 * @param parameter
	 * @return
	 */
	BookList findById(String bookId,String bluId);
	
	/**
	 * 分页查询
	 * @param parameter
	 * @return
	 */
	PageDTO<BookList> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	/**
	 * 新增
	 */
	int insert(BookList bookList);
	
	int insertBook(BookList bookList);

	/**
	 * 修改
	 * @param dictionary
	 */
	void update(BookList bookList);
	
	List<BookList> getBookListAll(Map<String, Object> parameter);
	
	
	int searchBookNameInsert(String bookName);
	
	//web
	Map<String, Object> getBooks(Map<String, Object> parameter);
	
	String searchBookList(String bookList);
	
	String searchBookNameUrl(String bookName);
	
	Map<String, Object> getBookDetail(Map<String, Object> parameter);
	
	List<BookList> getBookListByName(String bookName);
	
	List<BookList> getBookListByNameLogin(String bookName);
	
	Map<String, Object> getLabelBookLists(Map<String, Object> parameter);
	

		
}

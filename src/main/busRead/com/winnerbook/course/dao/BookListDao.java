package com.winnerbook.course.dao;

import java.util.List;
import java.util.Map;

import com.winnerbook.course.dto.BookList;
import com.winnerbook.course.dto.BookListUserId;

public interface BookListDao {

	/**
	 * 查询总条数
	 * 
	 * @param parameter
	 * @return
	 */
	int selectCount(Map<String, Object> parameter);

	/**
	 * 分页查询
	 * 
	 * @param parameter
	 * @return
	 */
	List<BookList> listByPage(Map<String, Object> parameter);

	int insert(BookList record);
	
	int insertBookListUser(BookListUserId bookListUserId);

	BookList findById(Map<String, Object> parameter);
	Map<String, Object> findByBookListUser(Map<String, Object> parameter);

	int update(BookList record);
	int updateBookListUser(BookList record);
	
	List<BookList> getBookListAll(Map<String, Object> parameter);
	
	List<BookList> getBookListByName(Map<String, Object> parameter);
	
	List<BookList> getBookListByNameLogin(Map<String, Object> parameter);
	
	//web
	List<Map<String, Object>> getBooks(Map<String, Object> parameter);
	
	int getBooksCount(Map<String, Object> parameter);
	
	Map<String, Object> getBookDetail(Map<String, Object> parameter);
	Map<String, Object> getBookDetailUser(Map<String, Object> parameter);

	List<Map<String, Object>> getLabelBookLists(Map<String, Object> parameter);
	int getLabelBookListsCount(Map<String, Object> parameter);
	
	
}
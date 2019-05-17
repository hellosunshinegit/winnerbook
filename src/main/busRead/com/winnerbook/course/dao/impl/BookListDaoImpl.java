package com.winnerbook.course.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.course.dao.BookListDao;
import com.winnerbook.course.dto.BookList;
import com.winnerbook.course.dto.BookListUserId;

@Repository("bookListDao")
public class BookListDaoImpl  extends BaseDAO implements BookListDao{
	private static final String BOOKLIST_MAPPER = "BookListMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(BOOKLIST_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<BookList> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BOOKLIST_MAPPER + "listByPage", parameter);
	}

	@Override
	public BookList findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(BOOKLIST_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(BookList bookList) {
		return this.sqlSession.insert(BOOKLIST_MAPPER+"insert",bookList);
	}

	@Override
	public int update(BookList bookList) {
		return this.sqlSession.insert(BOOKLIST_MAPPER+"update",bookList);
	}

	@Override
	public List<BookList> getBookListAll(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BOOKLIST_MAPPER+"getBookListAll",parameter);
	}

	@Override
	public List<Map<String, Object>> getBooks(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BOOKLIST_MAPPER+"getBooks",parameter);
	}

	@Override
	public int getBooksCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(BOOKLIST_MAPPER + "getBooksCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public Map<String, Object> getBookDetail(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(BOOKLIST_MAPPER+"getBookDetail",parameter);
	}

	@Override
	public List<BookList> getBookListByName(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BOOKLIST_MAPPER+"getBookListByName",parameter);
	}

	@Override
	public List<BookList> getBookListByNameLogin(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BOOKLIST_MAPPER+"getBookListByNameLogin",parameter);
	}

	@Override
	public int insertBookListUser(BookListUserId bookListUserId) {
		return this.sqlSession.insert(BOOKLIST_MAPPER+"insertBookListUser",bookListUserId);
	}

	@Override
	public List<Map<String, Object>> getLabelBookLists(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(BOOKLIST_MAPPER+"getLabelBookLists",parameter);
	}

	@Override
	public int getLabelBookListsCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(BOOKLIST_MAPPER + "getLabelBookListsCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public Map<String, Object> findByBookListUser(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(BOOKLIST_MAPPER+"findByBookListUser",parameter);
	}

	@Override
	public int updateBookListUser(BookList record) {
		return this.sqlSession.update(BOOKLIST_MAPPER+"updateBookListUser",record);
	}

	@Override
	public Map<String, Object> getBookDetailUser(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(BOOKLIST_MAPPER+"getBookDetailUser",parameter);
	}

}

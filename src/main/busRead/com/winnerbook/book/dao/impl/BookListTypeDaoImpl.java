package com.winnerbook.book.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.book.dao.BookListTypeDao;
import com.winnerbook.book.dto.BookListLabelId;
import com.winnerbook.book.dto.BookListType;
import com.winnerbook.book.dto.BookListTypeId;

@Repository("bookListTypeDao")
public class BookListTypeDaoImpl  extends BaseDAO implements BookListTypeDao{
	private static final String BOOKLISTTYPE_MAPPER = "BookListTypeMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(BOOKLISTTYPE_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<BookListType> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BOOKLISTTYPE_MAPPER + "listByPage", parameter);
	}

	@Override
	public BookListType findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(BOOKLISTTYPE_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(BookListType bookListType) {
		return this.sqlSession.insert(BOOKLISTTYPE_MAPPER+"insert",bookListType);
	}

	@Override
	public int update(BookListType bookListType) {
		return this.sqlSession.insert(BOOKLISTTYPE_MAPPER+"update",bookListType);
	}

	@Override
	public int insertBathBookListTypeId(List<BookListTypeId> bookListTypeIds) {
		return this.sqlSession.insert(BOOKLISTTYPE_MAPPER+"insertBathBookListTypeId",bookListTypeIds);
	}

	@Override
	public int insertBookListLabelId(List<BookListLabelId> bookListLabelIds) {
		return this.sqlSession.insert(BOOKLISTTYPE_MAPPER+"insertBookListLabelId",bookListLabelIds);
	}

	@Override
	public List<Map<String, Object>> getBookListByTypeId(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(BOOKLISTTYPE_MAPPER+"getBookListByTypeId",parameter);
	}

	@Override
	public int deleteBookListByTypeId(Integer typeId) {
		return this.sqlSession.delete(BOOKLISTTYPE_MAPPER+"deleteBookListByTypeId",typeId);
	}

	@Override
	public int deleteLabelByTypeId(Integer typeId) {
		return this.sqlSession.delete(BOOKLISTTYPE_MAPPER+"deleteLabelByTypeId",typeId);
	}

	@Override
	public List<Map<String, Object>> getBookListTypes(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BOOKLISTTYPE_MAPPER+"getBookListTypes",parameter);
	}

	@Override
	public int getBookListTypesCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(BOOKLISTTYPE_MAPPER + "getBookListTypesCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<Map<String, Object>> getBusBookListTypes(
			Map<String, Object> parameter) {
		return this.sqlSession.selectList(BOOKLISTTYPE_MAPPER+"getBusBookListTypes",parameter);
	}

	@Override
	public int getBusBookListTypesCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(BOOKLISTTYPE_MAPPER + "getBusBookListTypesCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public int getBookNameByUserId(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(BOOKLISTTYPE_MAPPER + "getBookNameByUserId", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

}

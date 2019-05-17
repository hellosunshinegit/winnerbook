package com.winnerbook.course.dao.impl;

import org.springframework.stereotype.Repository;
import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.course.dao.BookListSearchLogDao;
import com.winnerbook.course.dto.BookListSearchLog;

@Repository("bookListSearchLogDao")
public class BookListSearchLogDaoImpl  extends BaseDAO implements BookListSearchLogDao{
	private static final String BOOKLISTSEARCHLOG_MAPPER = "BookListSearchLogMapper.";
	
	@Override
	public int insert(BookListSearchLog bookListSearchLog) {
		return this.sqlSession.insert(BOOKLISTSEARCHLOG_MAPPER+"insert",bookListSearchLog);
	}
}

package com.winnerbook.book.dao.impl;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.book.dao.BookListClickInfoDao;
import com.winnerbook.book.dto.BookListClickInfo;

@Repository("bookListClickInfoDao")
public class BookListClickInfoDaoImpl  extends BaseDAO implements BookListClickInfoDao{
	private static final String BOOKLISTCLICKINFO_MAPPER = "BookListClickInfoMapper.";
	
	@Override
	public int insert(BookListClickInfo bookListClickInfo) {
		return this.sqlSession.insert(BOOKLISTCLICKINFO_MAPPER+"insert",bookListClickInfo);
	}

}

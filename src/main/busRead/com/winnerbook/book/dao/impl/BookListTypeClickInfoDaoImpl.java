package com.winnerbook.book.dao.impl;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.book.dao.BookListTypeClickInfoDao;
import com.winnerbook.book.dto.BookListTypeClickInfo;

@Repository("bookListTypeClickInfoDao")
public class BookListTypeClickInfoDaoImpl  extends BaseDAO implements BookListTypeClickInfoDao{
	private static final String BOOKLISTTYPECLICKINFO_MAPPER = "BookListTypeClickInfoMapper.";
	
	@Override
	public int insert(BookListTypeClickInfo bookListTypeClickInfo) {
		return this.sqlSession.insert(BOOKLISTTYPECLICKINFO_MAPPER+"insert",bookListTypeClickInfo);
	}

}

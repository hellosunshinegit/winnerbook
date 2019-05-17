package com.winnerbook.book.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.book.dao.BookTypeLabelDao;
import com.winnerbook.book.dto.BookTypeLabel;

@Repository("bookTypeLabelDao")
public class BookTypeLabelDaoImpl  extends BaseDAO implements BookTypeLabelDao{
	private static final String BOOKLISTTYPE_MAPPER = "BookTypeLabelMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(BOOKLISTTYPE_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<BookTypeLabel> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BOOKLISTTYPE_MAPPER + "listByPage", parameter);
	}

	@Override
	public BookTypeLabel findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(BOOKLISTTYPE_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(BookTypeLabel bookTypeLabel) {
		return this.sqlSession.insert(BOOKLISTTYPE_MAPPER+"insert",bookTypeLabel);
	}

	@Override
	public int update(BookTypeLabel bookTypeLabel) {
		return this.sqlSession.insert(BOOKLISTTYPE_MAPPER+"update",bookTypeLabel);
	}

	@Override
	public List<BookTypeLabel> getBookTypeLabelAll(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BOOKLISTTYPE_MAPPER+"getBookTypeLabelAll",parameter);
	}

}

package com.winnerbook.book.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.book.dto.BookTypeLabel;

public interface BookTypeLabelDao {

	int selectCount(Map<String, Object> parameter);

	List<BookTypeLabel> listByPage(Map<String, Object> parameter);

	int insert(BookTypeLabel record);

	BookTypeLabel findById(Map<String, Object> parameter);

	int update(BookTypeLabel record);
	
	List<BookTypeLabel> getBookTypeLabelAll(Map<String, Object> parameter);
}
package com.winnerbook.book.service;

import java.util.List;
import java.util.Map;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.book.dto.BookTypeLabel;

public interface BookTypeLabelService {

	
	BookTypeLabel findById(String bookId);
	
	PageDTO<BookTypeLabel> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize);
	
	void insert(BookTypeLabel bookTypeLabel);

	void update(BookTypeLabel bookTypeLabel);
	
	List<BookTypeLabel> getBookTypeLabelAll(Map<String, Object> parameter);
	
}

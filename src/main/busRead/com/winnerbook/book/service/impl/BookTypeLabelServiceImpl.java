package com.winnerbook.book.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.book.dao.BookTypeLabelDao;
import com.winnerbook.book.dto.BookTypeLabel;
import com.winnerbook.book.service.BookTypeLabelService;
import com.winnerbook.system.dto.User;

@Service("bookTypeLabelService")
public class BookTypeLabelServiceImpl extends BaseServiceImpl implements BookTypeLabelService{
	
	@Autowired
	private BookTypeLabelDao bookTypeLabelDao;
	
	@Override
	public BookTypeLabel findById(String labelId) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("labelId", Integer.parseInt(labelId));
		return bookTypeLabelDao.findById(parameter);
	}

	@Override
	public PageDTO<BookTypeLabel> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<BookTypeLabel> pageDTO = new PageDTO<BookTypeLabel>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = bookTypeLabelDao.selectCount(parameter);
		List<BookTypeLabel> data = null;
		if (rowSize > 0) {
			data = bookTypeLabelDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public void insert(BookTypeLabel bookTypeLabel) {
		User sessionUser = getSessionUser();
		bookTypeLabel.setCreateDate(new Date());
		bookTypeLabel.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		bookTypeLabel.setCreateUserName(sessionUser.getUserUnitName());
		bookTypeLabelDao.insert(bookTypeLabel);
		logRecord("2","书单标题信息添加，id："+bookTypeLabel.getLabelId());
	}

	@Override
	public void update(BookTypeLabel bookTypeLabel) {
		bookTypeLabel.setUpdateDate(new Date());
		bookTypeLabelDao.update(bookTypeLabel);
		logRecord("3","书单标题信息更新，id："+bookTypeLabel.getLabelId());
	}

	@Override
	public List<BookTypeLabel> getBookTypeLabelAll(Map<String, Object> parameter) {
		return bookTypeLabelDao.getBookTypeLabelAll(parameter);
	}

}

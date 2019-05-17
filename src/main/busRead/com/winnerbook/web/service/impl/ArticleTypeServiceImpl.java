package com.winnerbook.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.system.dto.User;
import com.winnerbook.web.dao.ArticleTypeDao;
import com.winnerbook.web.dto.ArticleType;
import com.winnerbook.web.service.ArticleTypeService;

@Service("articleTypeService")
public class ArticleTypeServiceImpl extends BaseServiceImpl implements ArticleTypeService{
	
	@Autowired
	private ArticleTypeDao articleTypeDao;

	@Override
	public ArticleType findById(String id) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("typeId", Integer.parseInt(id));
		return articleTypeDao.findById(parameter);
	}

	@Override
	public PageDTO<ArticleType> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<ArticleType> pageDTO = new PageDTO<ArticleType>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = articleTypeDao.selectCount(parameter);
		List<ArticleType> data = null;
		if (rowSize > 0) {
			data = articleTypeDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public void insert(ArticleType articleType) {
		User sessionUser = getSessionUser();
		articleType.setCreateDate(new Date());
		articleType.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		articleType.setCreateUserName(sessionUser.getUserUnitName());
		articleTypeDao.insert(articleType);
		logRecord("2","文章类型添加，："+articleType.getTypeName());
	}

	@Override
	public void update(ArticleType articleType) {
		articleType.setUpdateDate(new Date());
		articleTypeDao.update(articleType);
		logRecord("3","文章类型信息更新，id："+articleType.getTypeId());
	}

	@Override
	public List<ArticleType> findArticleType() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionUser",getSessionUser());
		return articleTypeDao.findArticleType(map);
	}
	
}

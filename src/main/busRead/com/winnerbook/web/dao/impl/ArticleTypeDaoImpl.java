package com.winnerbook.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.web.dao.ArticleTypeDao;
import com.winnerbook.web.dto.ArticleType;

@Repository("articleTypeDao")
public class ArticleTypeDaoImpl  extends BaseDAO implements ArticleTypeDao{
	private static final String ARTICLETYPE_MAPPER = "ArticleTypeMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(ARTICLETYPE_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<ArticleType> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(ARTICLETYPE_MAPPER + "listByPage", parameter);
	}

	@Override
	public ArticleType findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(ARTICLETYPE_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(ArticleType record) {
		return this.sqlSession.insert(ARTICLETYPE_MAPPER+"insert",record);
	}

	@Override
	public int update(ArticleType record) {
		return this.sqlSession.insert(ARTICLETYPE_MAPPER+"update",record);
	}

	@Override
	public List<ArticleType> findArticleType(Map<String, Object> parameter) {
		return this.sqlSession.selectList(ARTICLETYPE_MAPPER+"findArticleType",parameter);
	}
}

package com.winnerbook.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.web.dao.ArticleDao;
import com.winnerbook.web.dto.Article;

@Repository("articleDao")
public class ArticleDaoImpl  extends BaseDAO implements ArticleDao{
	private static final String ARTICLE_MAPPER = "ArticleMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(ARTICLE_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<Article> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(ARTICLE_MAPPER + "listByPage", parameter);
	}

	@Override
	public Map<String,Object> findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(ARTICLE_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(Article record) {
		return this.sqlSession.insert(ARTICLE_MAPPER+"insert",record);
	}

	@Override
	public int update(Article record) {
		return this.sqlSession.insert(ARTICLE_MAPPER+"update",record);
	}

	@Override
	public void updateStatus(Map<String, Object> parameter) {
		this.sqlSession.update(ARTICLE_MAPPER+"updateStatus",parameter);
	}

	@Override
	public List<Map<String, Object>> getArticles(Map<String, Object> parameter) {
		return this.sqlSession.selectList(ARTICLE_MAPPER+"getArticles",parameter);
	}

	@Override
	public int getArticlesCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(ARTICLE_MAPPER + "getArticlesCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public Map<String, Object> getArticleDetail(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(ARTICLE_MAPPER+"getArticleDetail",parameter);
	}
}

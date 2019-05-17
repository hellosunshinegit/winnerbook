package com.winnerbook.web.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.web.dao.ArticleTagDao;
import com.winnerbook.web.dto.ArticleTag;

@Repository("articleTagDao")
public class ArticleTagDaoImpl  extends BaseDAO implements ArticleTagDao{
	private static final String ARTICLETAG_MAPPER = "ArticleTagMapper.";
	
	@Override
	public int insertArticleTag(List<ArticleTag> articleTags) {
		return this.sqlSession.insert(ARTICLETAG_MAPPER+"insertArticleTag",articleTags);
	}

	@Override
	public void deleteArticleTag(String articleId) {
		this.sqlSession.delete(ARTICLETAG_MAPPER+"deleteArticleTag",articleId);
	}

	@Override
	public List<ArticleTag> getArticleTagByArticleId(String articleId) {
		return this.sqlSession.selectList(ARTICLETAG_MAPPER+"getArticleTagByArticleId",articleId);
	}

	@Override
	public List<Map<String, Object>> getAllArticleTag() {
		return this.sqlSession.selectList(ARTICLETAG_MAPPER+"getAllArticleTag");
	}
}

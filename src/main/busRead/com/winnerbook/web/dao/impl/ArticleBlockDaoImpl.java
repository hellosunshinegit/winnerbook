package com.winnerbook.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.web.dao.ArticleBlockDao;
import com.winnerbook.web.dto.ArticleBlock;

@Repository("articleBlockDao")
public class ArticleBlockDaoImpl  extends BaseDAO implements ArticleBlockDao{
	private static final String ARTICLEBLOCK_MAPPER = "ArticleBlockMapper.";
	
	@Override
	public int insertArticleBlock(List<ArticleBlock> articleBlocks) {
		return this.sqlSession.insert(ARTICLEBLOCK_MAPPER+"insertArticleBlock",articleBlocks);
	}

	@Override
	public void deleteArticleBlock(String articleId) {
		this.sqlSession.delete(ARTICLEBLOCK_MAPPER+"deleteArticleBlock",articleId);
	}

	@Override
	public List<ArticleBlock> getArticleBlockByArticleId(String articleId) {
		return this.sqlSession.selectList(ARTICLEBLOCK_MAPPER+"getArticleBlockByArticleId",articleId);
	}

	@Override
	public List<Map<String, Object>> getAllArticleBlock() {
		return this.sqlSession.selectList(ARTICLEBLOCK_MAPPER+"getAllArticleBlock");
	}
}

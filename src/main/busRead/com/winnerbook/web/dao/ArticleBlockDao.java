package com.winnerbook.web.dao;

import java.util.List;
import java.util.Map;

import com.winnerbook.web.dto.ArticleBlock;

public interface ArticleBlockDao {
    
    int insertArticleBlock(List<ArticleBlock> articleBlocks);
    
    void deleteArticleBlock(String articleId);
    
    List<ArticleBlock> getArticleBlockByArticleId(String articleId);
    
    List<Map<String,Object>> getAllArticleBlock();
    
}
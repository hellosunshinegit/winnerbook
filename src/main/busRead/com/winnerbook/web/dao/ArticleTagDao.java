package com.winnerbook.web.dao;

import java.util.List;
import java.util.Map;
import com.winnerbook.web.dto.ArticleTag;

public interface ArticleTagDao {
    
    int insertArticleTag(List<ArticleTag> articleBlocks);
    
    void deleteArticleTag(String articleId);
    
    List<ArticleTag> getArticleTagByArticleId(String articleId);
    
    List<Map<String,Object>> getAllArticleTag();
    
}
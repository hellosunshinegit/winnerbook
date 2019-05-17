package com.winnerbook.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.system.dto.User;
import com.winnerbook.web.dao.ArticleBlockDao;
import com.winnerbook.web.dao.ArticleDao;
import com.winnerbook.web.dao.ArticleTagDao;
import com.winnerbook.web.dao.ArticleTypeDao;
import com.winnerbook.web.dao.BlockDao;
import com.winnerbook.web.dto.Article;
import com.winnerbook.web.dto.ArticleBlock;
import com.winnerbook.web.dto.ArticleTag;
import com.winnerbook.web.dto.ArticleType;
import com.winnerbook.web.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl extends BaseServiceImpl implements ArticleService{
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private ArticleTypeDao articleTypeDao;

	@Autowired
	private BlockDao blockDao;
	
	@Autowired
	private ArticleBlockDao articleBlockDao;
	
	@Autowired
	private ArticleTagDao articleTagDao;
	
	@Override
	public Map<String,Object> findById(String id) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("articleId", Integer.parseInt(id));
		return articleDao.findById(parameter);
	}

	@Override
	public PageDTO<Article> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<Article> pageDTO = new PageDTO<Article>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = articleDao.selectCount(parameter);
		List<Article> data = null;
		if (rowSize > 0) {
			data = articleDao.listByPage(parameter);
			
			//读取文章类型表
			List<ArticleType> articleTypes = articleTypeDao.findArticleType(new HashMap<String,Object>());
			Map<Integer,Object> articleTypeMap = new HashMap<>();
			for(ArticleType articleType:articleTypes){
				if(StringUtils.isNotBlank(articleType.getTypeId().toString())){
					articleTypeMap.put(articleType.getTypeId(), articleType.getTypeName());
				}
			}
			
			//获取文章对应的板块的值
			List<Map<String, Object>> blockList = articleBlockDao.getAllArticleBlock();
			Map<Integer,Object> blockMap = new HashMap<>();
			for(Map map:blockList){
				blockMap.put(Integer.parseInt(map.get("articleId").toString()), map.get("blockStr"));
			}
			
			for(Article article:data){
				article.setArticleTypeName(null!=articleTypeMap.get(article.getArticleTypeId())?articleTypeMap.get(article.getArticleTypeId()).toString():"");
				article.setBlockStr(null!=blockMap.get(article.getArticleId())?blockMap.get(article.getArticleId()).toString():"");
			}
		}
		
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public void insert(Article article) {
		User sessionUser = getSessionUser();
		article.setCreateDate(new Date());
		article.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		article.setCreateUserName(sessionUser.getUserUnitName());
		articleDao.insert(article);
		//获取article的id，批量插入版块表
		if(StringUtils.isNotBlank(article.getBlockStr())){
			String[]  blockIds = article.getBlockStr().split(",");
			articleBlockDao.insertArticleBlock(getArticleBlock(blockIds, article.getArticleId()));
		}
		if(StringUtils.isNotBlank(article.getArticleTagIds())){
			String[] tagIds = article.getArticleTagIds().split(",");
			articleTagDao.insertArticleTag(getArticleTag(tagIds, article.getArticleId()));
		}
		
		logRecord("2","文章添加，："+article.getArticleTitle());
	}

	@Override
	public void update(Article article) {
		article.setUpdateDate(new Date());
		articleDao.update(article);
		//查询是否已经有数据，先删除，后insert
		List<ArticleBlock> articleBlocks = articleBlockDao.getArticleBlockByArticleId(article.getArticleId().toString());
		if(articleBlocks.size()>0){
			articleBlockDao.deleteArticleBlock(article.getArticleId().toString());

			if(StringUtils.isNotBlank(article.getBlockStr())){
				String[]  blockIds = article.getBlockStr().split(",");
				articleBlockDao.insertArticleBlock(getArticleBlock(blockIds, article.getArticleId()));
			}
		}
		
		List<ArticleTag> articleTags = articleTagDao.getArticleTagByArticleId(article.getArticleId().toString());
		if(articleTags.size()>0){
			articleTagDao.deleteArticleTag(article.getArticleId().toString());
			if(StringUtils.isNotBlank(article.getArticleTagIds())){
				String[] tagIds = article.getArticleTagIds().split(",");
				articleTagDao.insertArticleTag(getArticleTag(tagIds, article.getArticleId()));
			}
		}
		
		logRecord("3","文章信息更新，id："+article.getArticleId());
	}
	
	public List<ArticleBlock> getArticleBlock(String[]  blockIds,Integer articleId){
		List<ArticleBlock> articleBlocks = new LinkedList<>();
		for(String block:blockIds){
			ArticleBlock articleBlock = new ArticleBlock();
			articleBlock.setArticleId(articleId);
			articleBlock.setBlockId(Integer.parseInt(block));
			articleBlock.setAbCreatedate(new Date());
			articleBlocks.add(articleBlock);
		}
		return articleBlocks;
	}
	
	
	public List<ArticleTag> getArticleTag(String[] tagIds,Integer articleId){
		List<ArticleTag> articleTags = new LinkedList<>();
		for(String tagId:tagIds){
			ArticleTag articleTag = new ArticleTag();
			articleTag.setArticleId(articleId);
			articleTag.setTagId(Integer.parseInt(tagId));
			articleTag.setAtCreatedate(new Date());
			articleTags.add(articleTag);
		}
		return articleTags;
	}

	@Override
	public void updateStatus(String articleId, String status) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("articleId", articleId);
		parameter.put("articleStatus", status);
		articleDao.updateStatus(parameter);
	}

	@Override
	public Map<String, Object> getArticles(Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> articleList = articleDao.getArticles(parameter);
		int articleCount = articleDao.getArticlesCount(parameter);
		map.put("articleList", articleList);
		map.put("articleCount", articleCount);
		return map;
	}

	@Override
	public Map<String, Object> getArticleDetail(Map<String, Object> parameter) {
		return articleDao.getArticleDetail(parameter);
	}
	
}

package com.winnerbook.web.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.web.dao.BannerDao;
import com.winnerbook.web.dto.Banner;

@Repository("bannerDao")
public class BannerDaoImpl  extends BaseDAO implements BannerDao{
	private static final String BANNER_MAPPER = "BannerMapper.";
	
	@Override
	public int selectCount(Map<String, Object> parameter) {
		Object obj = this.sqlSession.selectOne(BANNER_MAPPER + "selectCount", parameter);
		return obj == null ? 0 : Integer.parseInt(obj.toString());
	}

	@Override
	public List<Banner> listByPage(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BANNER_MAPPER + "listByPage", parameter);
	}

	@Override
	public Banner findById(Map<String, Object> parameter) {
		return this.sqlSession.selectOne(BANNER_MAPPER+"findById",parameter);
	}

	@Override
	public int insert(Banner record) {
		return this.sqlSession.insert(BANNER_MAPPER+"insert",record);
	}

	@Override
	public int update(Banner record) {
		return this.sqlSession.insert(BANNER_MAPPER+"update",record);
	}

	@Override
	public List<Map<String, Object>> getBannerList(Map<String, Object> parameter) {
		return this.sqlSession.selectList(BANNER_MAPPER+"getBannerList", parameter);
	}
}

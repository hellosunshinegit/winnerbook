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
import com.winnerbook.web.dao.BannerDao;
import com.winnerbook.web.dto.Banner;
import com.winnerbook.web.service.BannerService;

@Service("bannerService")
public class BannerServiceImpl extends BaseServiceImpl implements BannerService{
	
	@Autowired
	private BannerDao bannerDao;

	@Override
	public Banner findById(String id) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("bannerId", Integer.parseInt(id));
		return bannerDao.findById(parameter);
	}

	@Override
	public PageDTO<Banner> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<Banner> pageDTO = new PageDTO<Banner>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = bannerDao.selectCount(parameter);
		List<Banner> data = null;
		if (rowSize > 0) {
			data = bannerDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public void insert(Banner banner) {
		User sessionUser = getSessionUser();
		banner.setCreateDate(new Date());
		banner.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		banner.setCreateUserName(sessionUser.getUserUnitName());
		bannerDao.insert(banner);
		logRecord("2","轮播图添加，："+banner.getBannerTitle());
	}

	@Override
	public void update(Banner banner) {
		banner.setUpdateDate(new Date());
		bannerDao.update(banner);
		logRecord("3","轮播图信息更新，id："+banner.getBannerId());
	}

	@Override
	public List<Map<String, Object>> getBannerList(Map<String, Object> parameter) {
		return bannerDao.getBannerList(parameter);
	}
	
}

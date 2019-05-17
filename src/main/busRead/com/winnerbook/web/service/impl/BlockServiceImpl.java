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
import com.winnerbook.web.dao.BlockDao;
import com.winnerbook.web.dto.Block;
import com.winnerbook.web.service.BlockService;

@Service("blockService")
public class BlockServiceImpl extends BaseServiceImpl implements BlockService{
	
	@Autowired
	private BlockDao blockDao;

	@Override
	public Block findById(String id) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("blockId", Integer.parseInt(id));
		return blockDao.findById(parameter);
	}

	@Override
	public PageDTO<Block> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<Block> pageDTO = new PageDTO<Block>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = blockDao.selectCount(parameter);
		List<Block> data = null;
		if (rowSize > 0) {
			data = blockDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public void insert(Block block) {
		User sessionUser = getSessionUser();
		block.setCreateDate(new Date());
		block.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		block.setCreateUserName(sessionUser.getUserUnitName());
		blockDao.insert(block);
		logRecord("2","版块添加，："+block.getBlockName());
	}

	@Override
	public void update(Block block) {
		block.setUpdateDate(new Date());
		blockDao.update(block);
		logRecord("3","版块信息更新，id："+block.getBlockId());
	}

	@Override
	public List<Block> getBlockList() {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("sessionUser",getSessionUser());
		return blockDao.getBlockList(parameter);
	}

	@Override
	public List<Map<String, Object>> getBlocks(Map<String, Object> parameter) {
		return blockDao.getBlocks(parameter);
	}
	
}

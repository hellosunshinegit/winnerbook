package com.winnerbook.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.system.dao.UcAddressDao;
import com.winnerbook.system.dto.UcAddress;
import com.winnerbook.system.service.UcAddressService;

@Service(value = "ucAddressService")
public class UcAddressServiceImpl extends BaseServiceImpl implements UcAddressService {

	@Autowired
	private UcAddressDao ucAddressDao;
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public UcAddress findById(String id) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("addressId", id);
		return ucAddressDao.findById(parameter);
	}

	@Override
	public PageDTO<UcAddress> listByPage(Map<String, Object> parameter,
			Integer pageIndex, Integer pageSize) {
		PageDTO<UcAddress> pageDTO = new PageDTO<UcAddress>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = ucAddressDao.selectCount(parameter);
		//上父级节点显示汉字 先查询所有的地址
		Map<Long,String> addressMap = new HashMap<>();
		List<UcAddress> addressList = ucAddressDao.findAddressByParentId(new HashMap<String,Object>());
		if(null!=addressList && addressList.size()>0){
			for(UcAddress ucAddress:addressList){
				addressMap.put(ucAddress.getAddressId(), ucAddress.getAddressName());
			}
		}
		
		List<UcAddress> data = null;
		if (rowSize > 0) {
			data = ucAddressDao.listByPage(parameter);
			for(UcAddress ucAddress:data){
				ucAddress.setAddressParenName(null!=addressMap.get(ucAddress.getAddressParentId())?addressMap.get(ucAddress.getAddressParentId()).toString():"");
			}
		}
		
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}

	@Override
	public void insert(UcAddress ucAddress) {
		ucAddress.setAddressCreateDate(new Date());
		ucAddressDao.insert(ucAddress);
		logRecord("2","地址添加，id："+ucAddress.getAddressId());
	}

	@Override
	public void update(UcAddress ucAddress) {
		ucAddress.setAddressUpdateDate(new Date());
		ucAddressDao.update(ucAddress);
		logRecord("3","地址修改，id："+ucAddress.getAddressId());
	}

	@Override
	public void delete(String addressId) {
		ucAddressDao.delete(addressId);
		logRecord("4","地址删除，id："+addressId);
	}

	@Override
	public List<UcAddress> findAddressOneLevel() {
		return ucAddressDao.findAddressOneLevel();
	}

	@Override
	public List<UcAddress> findAddressByParentId(String addressId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("addressId", addressId);
		return ucAddressDao.findAddressByParentId(parameter);
	}
}

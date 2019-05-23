package com.winnerbook.share.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.common.util.ConstantUtils;
import com.winnerbook.base.common.util.FileUtils;
import com.winnerbook.base.common.util.QRCodeUtil;
import com.winnerbook.base.common.util.QrcodeZxing;
import com.winnerbook.base.common.util.UUIDGenerator;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.share.dao.QrcodeDao;
import com.winnerbook.share.dto.Qrcode;
import com.winnerbook.share.service.QrcodeService;
import com.winnerbook.system.dto.User;

@Service("qrcodeService")
public class QrcodeServiceImpl extends BaseServiceImpl implements QrcodeService{
	
	private static final Logger logger = LoggerFactory.getLogger(QrcodeServiceImpl.class);
	
	@Autowired
	private QrcodeDao qrcodeDao;

	@Override
	public Qrcode findById(String id) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("id", Integer.parseInt(id));
		return qrcodeDao.findById(parameter);
	}
	
	@Override
	public PageDTO<Qrcode> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<Qrcode> pageDTO = new PageDTO<Qrcode>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = qrcodeDao.selectCount(parameter);
		List<Qrcode> data = null;
		if (rowSize > 0) {
			data = qrcodeDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public int insert(Qrcode qrcode) {
		User sessionUser = getSessionUser();
		
		String filename = UUIDGenerator.getUUID();
		
		Map<String, String> mapPath = FileUtils.getDirPath("qrcode");
		String dirPath = mapPath.get("dirPath").toString();
		String urlPath = mapPath.get("urlPath").toString();

		urlPath+=filename+"."+GlobalConfigure.QRCODEIMAGE_EXT;//访问路径
		
		qrcode.setImg(urlPath);
		qrcode.setStatus("1");//启用
		qrcode.setScanCount(0);
		qrcode.setCreateDate(new Date());
		qrcode.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		qrcode.setCreateUserName(sessionUser.getUserUnitName());
		logRecord("2","二维码添加，："+qrcode.getName());
		
		qrcodeDao.insert(qrcode);
		
		//获取当前访问地址的url
		String encoderContent = ConstantUtils.QROCDE_HTTP.replace("\\", "")+"?id="+qrcode.getId();
		
		//实际存储路径
		dirPath+= filename+"."+GlobalConfigure.QRCODEIMAGE_EXT;
		
		File localFile = new File(dirPath);
		if (!localFile.exists()) {
			localFile.mkdirs();
		}
		
		//生成二维码
		try {
			QrcodeZxing.getQrcodeImg(encoderContent, dirPath);
			
			/*QRCodeUtil qrCodeUtil = new QRCodeUtil();
			qrCodeUtil.encoderQRCode(encoderContent, dirPath, GlobalConfigure.QRCODEIMAGE_EXT);//生成二维码
			logger.info("二维码生成成功");*/
			
			//更新生成二维码的url
			Map<String, Object> map_url = new HashMap<>();
			map_url.put("id", qrcode.getId());
			map_url.put("qrcodeUrl", encoderContent);
			qrcodeDao.updateQrcodeUrl(map_url);
		} catch (Exception e) {
			logger.info("二维码生成失败"+e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return qrcode.getId();
	}
	
	@Override
	public void update(Qrcode qrcode) {
		qrcode.setUpdateDate(new Date());
		qrcodeDao.update(qrcode);
		logRecord("3","二维码更新，id："+qrcode.getId());
	}

	@Override
	public Qrcode getQrcodeByBusId(String busId) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("busId", busId);
		List<Qrcode> qrcodes = qrcodeDao.getQrcodeByBusId(parameter);
		return qrcodes.size()>0?qrcodes.get(0):null;
	}
	
}

package com.winnerbook.share.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.common.util.FileUtils;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.share.dto.Qrcode;
import com.winnerbook.share.dto.QrcodeRecord;
import com.winnerbook.share.service.QrcodeRecordService;
import com.winnerbook.share.service.QrcodeService;

/**
 * 二维码
 * @author hxs
 */
@Controller
@RequestMapping(value="/qrcodeController")
public class QrcodeController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(QrcodeController.class);
	
	@Autowired
	private QrcodeService qrcodeService;
	
	@Autowired
	private QrcodeRecordService qrcodeRecordService;
	
	//查询列表
	@RequestMapping(value="/qrcodeList.html")
	public String qrcodeList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("qrcodeList....");
		String name = request.getParameter("name");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("sessionUser",getSessionUser());
		PageDTO<Qrcode> pageDTO  = qrcodeService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/busRead/share/qrcode/qrcodeList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addQrcode.html")
	public String addBook(){
		return "manage/busRead/share/qrcode/editQrcode";
	}
	
	//添加提交
	@RequestMapping(value="addSubmitQrcode.html")
	public String addSubmitQrcode(Qrcode qrcode){
		qrcodeService.insert(qrcode);
		return "redirect:/qrcodeController/qrcodeList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateQrcode.html")
	public String updateQrcode(String id,ModelMap modelMap){
		modelMap.addAttribute("qrcode",qrcodeService.findById(id));
		return "manage/busRead/share/qrcode/editQrcode";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitQrcode.html")
	public String updateSubmitQrcode(Qrcode qrcode){
		qrcodeService.update(qrcode);
		return "redirect:/qrcodeController/qrcodeList.html";
	}
	
	//扫描记录
	@RequestMapping("viewQrcodeRecord.html")
	public String viewQrcodeRecord(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("qrcodeList....");
		String mobileDevice = request.getParameter("mobileDevice");
		String qrcodeId = request.getParameter("qrcodeId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mobileDevice", mobileDevice);
		map.put("qrcodeId", qrcodeId);
		PageDTO<QrcodeRecord> pageDTO  = qrcodeRecordService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		model.addAttribute("qrcodeInfo",qrcodeService.findById(qrcodeId));
		return "manage/busRead/share/qrcode/qrcodeRecordList";
	}
	
	
	//点击图片下载
	@RequestMapping("qrcodeUpload.html")
	public void qrcodeUpload(String id,HttpServletResponse response){
		Qrcode qrcode = qrcodeService.findById(id);
		FileUtils.downloadFilename(FileUtils.getRealtyPathName(qrcode.getImg()), qrcode.getName()+"-"+qrcode.getAddress()+".png", response);
	}
}

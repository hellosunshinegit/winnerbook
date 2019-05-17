package com.winnerbook.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.share.dto.Qrcode;
import com.winnerbook.share.service.QrcodeService;
import com.winnerbook.web.service.QrcodeServiceWeb;

@Controller
public class QrcodeWeb {
	
	private static final Logger logger = LoggerFactory.getLogger(QrcodeWeb.class);
	
	@Autowired
	private QrcodeServiceWeb qrcodeServiceWeb;
	
	@Autowired
	private QrcodeService qrcodeService;
	
	//扫描二维码记录
	@RequestMapping(value="qrcode.jhtml")
	public void getBookTypeLists(HttpServletRequest request,HttpServletResponse response){
		JSONResponse result = new JSONResponse();
		String id = request.getParameter("id");
		if(!StringUtils.isNotBlank(id)){
			result.setMsg("二维码链接不正确");
		}
		
		Qrcode qrcode = qrcodeService.findById(id);
		if(null==qrcode){
			result.setMsg("获取信息不正确");
		}
		if(!"1".equals(qrcode.getStatus())){
			result.setMsg("二维码已经被禁用");
		}
		
		if(StringUtils.isNotBlank(result.getMsg())){
			String returnHtml="<div style='font-size:35px;text-align: center;'>"+result.getMsg()+"，请联系管理员。</div>";
			response.setContentType("text/html;charset=UTF-8");
		    try {
				response.getWriter().print(returnHtml);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else{
			result.setSuccess(true);
			qrcodeServiceWeb.addSacnRecord(id, request);
			try {
				response.sendRedirect(qrcode.getForwardUrl());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

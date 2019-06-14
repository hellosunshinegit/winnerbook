package com.winnerbook.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.book.service.BookListTypeService;
import com.winnerbook.book.service.BookTypeLabelService;
import com.winnerbook.course.service.BookListService;
import com.winnerbook.web.service.ClickInfoService;
import com.winnerbook.web.utils.ConstantWebUtils;
import com.winnerbook.web.utils.PageUtil;
import com.winnerbook.web.utils.ValidateWebUtils;
@Controller
public class BookH5Web {
	
	@Autowired
	private BookListService bookListService;
	
	@Autowired
	private BookTypeLabelService bookTypeLabelService;
	
	@Autowired
	private BookListTypeService bookListTypeService;
	
	@Autowired
	private ClickInfoService clickInfoService;
	
	//通过标签查询类型
	@RequestMapping(value="getBookTypeLists.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getBookTypeLists(String busId,String pageIndex,@RequestParam String labelId,@RequestParam("callback") String callback){
		busId = ValidateWebUtils.defaultBus(busId);
		JSONResponse result = new JSONResponse();
		
		Map<String, Object> parameter_bookTypeList  = new HashMap<String, Object>();
		parameter_bookTypeList.put("labelId", Integer.parseInt(labelId));
		Map<String, Object> bookTypes = bookListTypeService.getBookListTypes(PageUtil.getParam(parameter_bookTypeList, busId, pageIndex));
		result.setData(bookTypes);
 		result.setSuccess(true);
 		result.setMsg("获取标签下的书单成功");
 		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
/*	
	
	//根据类型查询对应的书籍
	@RequestMapping("/getLabelBookLists.jhtml")
	@ResponseBody
	public String getBookLabels(@RequestParam String typeId,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		List<Map<String, Object>> bookLists = bookListService.getLabelBookLists(typeId);
		result.setData(bookLists);
 		result.setSuccess(true);
 		result.setMsg("获取标签下的书籍成功");
 		return callback+"("+JSONObject.fromObject(result)+")";
	}*/

	
	//获取书籍列表
	@RequestMapping(value="getBookLists.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getBookLists(String busId,String pageIndex,String typeId,String userId,HttpServletRequest request,@RequestParam("callback") String callback){
		busId = ValidateWebUtils.defaultBus(busId);
		
		JSONResponse result = new JSONResponse();
		
		//记录书单点击次数
		clickInfoService.bookListTypeClick(userId, typeId, request);
		
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("typeId", typeId);
		Map<String, Object> bookList = bookListService.getLabelBookLists(PageUtil.getParam(parameter, busId, pageIndex));
	
		result.setSuccess(true);
		result.setMsg("获取书籍信息成功");
		result.setData(bookList);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//获取书籍详情表
	@RequestMapping(value="getBookDetail.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getArticles(@RequestParam String bookId,String bluId,String busId,String userId,HttpServletRequest request,@RequestParam("callback") String callback){
		JSONResponse result = new JSONResponse();
		busId = ValidateWebUtils.defaultBus(busId);
		
		//记录书籍点击列表
		clickInfoService.bookListClick(userId, bookId, request);
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("busId", busId);
		parameter.put("bookId", bookId);
		parameter.put("bluId", bluId);
		Map<String, Object> bookList = bookListService.getBookDetail(parameter);
		
 		result.setSuccess(true);
		result.setMsg("获取书单详情成功");
		result.setData(bookList);
		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
	//获取企业书单
	@RequestMapping(value="getBusBookTypeLists.jhtml",produces = {"application/json;charset=utf-8"})
	@ResponseBody
	public String getBookTypeLists(String busId,String pageIndex,@RequestParam("callback") String callback){
		busId = ValidateWebUtils.defaultBus(busId);
		JSONResponse result = new JSONResponse();
		
		Map<String, Object> parameter_bookTypeList  = new HashMap<String, Object>();
		Map<String, Object> bookTypes = bookListTypeService.getBusBookListTypes(PageUtil.getParam(parameter_bookTypeList, busId, pageIndex));
		result.setData(bookTypes);
 		result.setSuccess(true);
 		result.setMsg("获取企业书单成功");
 		return callback+"("+JSONObject.fromObject(result)+")";
	}
	
}

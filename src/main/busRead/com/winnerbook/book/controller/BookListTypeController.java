package com.winnerbook.book.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.book.dto.BookListType;
import com.winnerbook.book.service.BookListTypeService;
import com.winnerbook.book.service.BookTypeLabelService;
import com.winnerbook.wx.service.WxInfoService;

/**
 * 书单
 * @author hxs
 */
@Controller
@RequestMapping(value="/bookListTypeController")
public class BookListTypeController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(BookListTypeController.class);
	
	@Autowired
	private BookListTypeService bookListTypeService;
	
	@Autowired
	private BookTypeLabelService bookTypeLabelService;
	
	@Autowired
	private WxInfoService wxInfoService;
	
	//查询列表
	@RequestMapping(value="/bookListType.html")
	public String bookListType(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("booklistType....");
		String typeName =request.getParameter("typeName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeName", typeName);
		map.put("sessionUser",getSessionUser());
		PageDTO<BookListType> pageDTO  = bookListTypeService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		//获取微博appid
		map.put("wxInfo", wxInfoService.getWxInfo("2"));
		model.addAllAttributes(map);
		return "manage/busRead/book/bookListType/bookListTypeList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addBookListType.html")
	public String addBookListType(ModelMap modelMap){
		//读取标签
		modelMap.put("bookTypeLabel", bookTypeLabelService.getBookTypeLabelAll(null));
		return "manage/busRead/book/bookListType/editBookListType";
	}
	
	//添加提交
	@RequestMapping(value="addBookListTypeSubmit.html")
	@ResponseBody
	public JSONResponse addBookListTypeSubmit(@RequestBody String dataJson){
		JSONResponse response = new JSONResponse();
		try {
			Map<String, Object> map = bookListTypeService.insert(dataJson);//会返回信息不全的数据
			if(!map.isEmpty()){
				response.setData(map);
			}
			response.setSuccess(true);
			response.setMsg("保存成功");
		} catch (Exception e) {
			response.setMsg("保存失败");
		}
		return response;
	}
	
	//点击修改
	@RequestMapping(value="updateBookListType.html")
	public String updateBookTypeLabel(@RequestParam String id,ModelMap modelMap){
		modelMap.put("bookTypeLabel", bookTypeLabelService.getBookTypeLabelAll(null));
		modelMap.addAttribute("bookListType",bookListTypeService.findById(id));//根据id查询
		//根据id查询书籍
		modelMap.addAttribute("bookLists",JSONArray.fromObject(bookListTypeService.getBookListByTypeId(id)).toString());
		return "manage/busRead/book/bookListType/editBookListType";
	}
	
	//修改提交
	@RequestMapping(value="updateBookListTypeSubmit.html")
	@ResponseBody
	public JSONResponse updateBookListTypeSubmit(@RequestBody String dataJson){
		JSONResponse response = new JSONResponse();
		try {
			Map<String, Object> map =bookListTypeService.update(dataJson);
			if(!map.isEmpty()){
				response.setData(map);
			}
			response.setSuccess(true);
			response.setMsg("保存成功");
		} catch (Exception e) {
			response.setMsg("保存失败"+e.getMessage());
		}
		return response;
	}
	
}

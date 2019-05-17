package com.winnerbook.book.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.book.dto.BookTypeLabel;
import com.winnerbook.book.service.BookTypeLabelService;

/**
 * 书单
 * @author hxs
 */
@Controller
@RequestMapping(value="/bookTypeLabelController")
public class BookTypeLabelController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(BookTypeLabelController.class);
	
	@Autowired
	private BookTypeLabelService bookTypeLabelService;
	
	//查询列表
	@RequestMapping(value="/bookTypeLabelList.html")
	public String bookTypeLabelList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("bookTypeLabelList....");
		String labelName =request.getParameter("labelName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("labelName", labelName);
		map.put("sessionUser",getSessionUser());
		PageDTO<BookTypeLabel> pageDTO  = bookTypeLabelService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/busRead/book/bookTypeLabel/bookTypeLabelList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addBookTypeLabel.html")
	public String addBookTypeLabel(){
		return "manage/busRead/book/bookTypeLabel/editBookTypeLabel";
	}
	
	//添加提交
	@RequestMapping(value="addBookTypeLabelSubmit.html")
	public String addBookTypeLabelSubmit(BookTypeLabel bookTypeLabel){
		bookTypeLabelService.insert(bookTypeLabel);
		return "redirect:/bookTypeLabelController/bookTypeLabelList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateBookTypeLabel.html")
	public String updateBookTypeLabel(@RequestParam String labelId,ModelMap modelMap){
		modelMap.addAttribute("bookTypeLabel",bookTypeLabelService.findById(labelId));
		return "manage/busRead/book/bookTypeLabel/editBookTypeLabel";
	}
	
	//修改提交
	@RequestMapping(value="updateBookTypeLabelSubmit.html")
	public String updateSubmitBook(BookTypeLabel bookTypeLabel){
		bookTypeLabelService.update(bookTypeLabel);
		return "redirect:/bookTypeLabelController/bookTypeLabelList.html";
	}
	
}

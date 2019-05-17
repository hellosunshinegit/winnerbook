package com.winnerbook.course.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

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

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.course.dto.BookList;
import com.winnerbook.course.service.BookListService;

/**
 * 书单
 * @author hxs
 */
@Controller
@RequestMapping(value="/bookListController")
public class BookListController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(BookListController.class);
	
	@Autowired
	private BookListService bookListService;
	
	//查询列表
	@RequestMapping(value="/bookList.html")
	public String bookList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("bookList....");
		String bookName =request.getParameter("bookName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookName", bookName);
		map.put("sessionUser",getSessionUser());
		PageDTO<BookList> pageDTO  = bookListService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/busRead/course/book/bookList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addBook.html")
	public String addBook(){
		return "manage/busRead/course/book/editBook";
	}
	
	//添加提交
	@RequestMapping(value="addSubmitBook.html")
	public String addSubmitBook(BookList bookList){
		bookList.setSource("1");
		bookListService.insertBook(bookList);
		return "redirect:/bookListController/bookList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateBook.html")
	public String updateBook(String bookId,String bluId,ModelMap modelMap){
		modelMap.addAttribute("bookList",bookListService.findById(bookId,bluId));
		return "manage/busRead/course/book/editBook";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitBook.html")
	public String updateSubmitBook(BookList bookList){
		bookListService.update(bookList);
		return "redirect:/bookListController/bookList.html";
	}
	
	//查看详情
	@RequestMapping("viewBook.html")
	public String viewBook(String bookId,String bluId,ModelMap modelMap){
		modelMap.addAttribute("bookList",bookListService.findById(bookId,bluId));
		return "manage/busRead/course/book/viewBook";
	}
	
	
	//点击搜索书单
	@RequestMapping(value="searchBookList.html",produces = {"application/json;charset=utf-8"})//设置返回格式，不然乱码
	@ResponseBody
	public String searchBookList(@RequestBody String bookList){
		String bookJson = bookListService.searchBookList(bookList);
		return bookJson;
	}
	
	@RequestMapping(value="searchBookName.html",produces = {"application/json;charset=utf-8"})//设置返回格式，不然乱码
	@ResponseBody
	public String searchBookName(@RequestBody String bookNameUrl){
		logger.info("bookNameUrl===="+bookNameUrl);
		String bookJson = bookListService.searchBookNameUrl(bookNameUrl);
		logger.info("bookJson===="+bookJson);
		return bookJson;
	}
	
	//验证在同一个添加人中书名不可以重复
	@RequestMapping("validateBookName.html")
	@ResponseBody
	public List<BookList> validateBookName(@RequestBody String bookName){
		return bookListService.getBookListByNameLogin(bookName);
	}
	
}

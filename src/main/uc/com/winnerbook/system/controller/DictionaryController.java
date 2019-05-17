package com.winnerbook.system.controller;

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
import com.winnerbook.system.dto.Dictionary;
import com.winnerbook.system.service.DictionaryService;

/**
 * 参数维护
 * 
 * @date 2016-2-15
 * 
 */
@Controller
@RequestMapping(value="/dictionaryController")
public class DictionaryController extends BaseController{
	@Autowired
	private DictionaryService dictionaryService; 
	
	private static final Logger logger = LoggerFactory.getLogger(DictionaryController.class);
	
	@RequestMapping(value="/dictionaryList.html")
	public String dictionaryList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		
		String dicItemcode =request.getParameter("dicItemcode");
		String dicItemcodename =request.getParameter("dicItemcodename");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dicItemcode", dicItemcode);
		map.put("dicItemcodename", dicItemcodename);
		PageDTO<Dictionary> pageDTO  = dictionaryService.listByPage(map, pageIndex,10);
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/uc/dictionary/dictionaryList";
	}
	
	//点击添加
	@RequestMapping(value="addInputDictionary.html")
	public String addInputDictionary(ModelMap modelMap){
		return "manage/uc/dictionary/editDictionary";
	}
	//添加提交
	@RequestMapping(value="addSubmitDictionary.html")
	public String addSubmitDictionary(Dictionary dictionary){
		try {
			dictionaryService.insert(dictionary);
		} catch (Exception e) {
			logger.debug("添加失败："+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/dictionaryController/dictionaryList.html";
	}
	//点击修改
	@RequestMapping(value="updateInputDictionary.html")
	public String updateInputDictionary(@RequestParam String dicId,ModelMap modelMap){
		modelMap.addAttribute("dictionary",dictionaryService.findById(dicId));
		return "manage/uc/dictionary/editDictionary";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitDictionary.html")
	public String updateSubmitDictionary(Dictionary dictionary){
		dictionaryService.update(dictionary);
		return "redirect:/dictionaryController/dictionaryList.html";
	}
	
	//删除
	@RequestMapping(value="deleteDictionary.html")
	public String deleteMenu(@RequestParam String dicId){
		dictionaryService.delete(dicId);
		return "redirect:/dictionaryController/dictionaryList.html";
	}
}

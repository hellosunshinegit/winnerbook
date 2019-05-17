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
import com.winnerbook.system.dto.Menu;
import com.winnerbook.system.service.MenuService;

/**
 * 后台菜单控制器
 * 
 * @date 2016-2-15
 * 
 */
@Controller
@RequestMapping(value="/menuController")
public class MenuController extends BaseController{
	@Autowired
	private MenuService menuService;
	
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@RequestMapping(value="/menuList.html")
	public String menuList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		
		String menuName =request.getParameter("menuName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuName", menuName);
		PageDTO<Menu> pageDTO  = menuService.listByPage(map, pageIndex,10);
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/uc/menu/menuList";
	}
	
	//点击添加
	@RequestMapping(value="addInputMenu.html")
	public String addInputMenu(ModelMap modelMap){
		modelMap.addAttribute("menuList",menuService.findMenuByCode());
		return "manage/uc/menu/editMenu";
	}
	//添加提交
	@RequestMapping(value="addSubmitMenu.html")
	public String addSubmitMenu(Menu menu){
		try {
			menuService.insert(menu);
		} catch (Exception e) {
			logger.debug("添加失败："+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/menuController/menuList.html";
	}
	//点击修改
	@RequestMapping(value="updateInputMenu.html")
	public String updateInputMenu(@RequestParam String menuId,ModelMap modelMap){
		modelMap.addAttribute("menu",menuService.findById(menuId));
		modelMap.addAttribute("menuList",menuService.findMenuByCode());
		return "manage/uc/menu/editMenu";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitMenu.html")
	public String updateSubmitMenu(Menu menu){
		menuService.update(menu);
		return "redirect:/menuController/menuList.html";
	}
	
	//删除
	@RequestMapping(value="deleteMenu.html")
	public String deleteMenu(@RequestParam String menuId){
		menuService.delete(menuId);
		return "redirect:/menuController/menuList.html";
	}
}

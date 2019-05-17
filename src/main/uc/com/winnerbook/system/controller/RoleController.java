package com.winnerbook.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.system.dto.Menu;
import com.winnerbook.system.dto.Role;
import com.winnerbook.system.dto.RoleMenu;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.service.MenuService;
import com.winnerbook.system.service.RoleMenuService;
import com.winnerbook.system.service.RoleService;

/**
 * 前台用户控制器
 * 
 * @author hxs
 * @date 2015-3-2
 * 
 */
@Controller
@RequestMapping(value="/roleController")
public class RoleController extends BaseController{
	@Autowired
	private RoleService roleService;
	@Autowired 
	private RoleMenuService roleMenuService;
	
	@Autowired
	private MenuService menuService;
	
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@RequestMapping(value="/roleList.html")
	public String  roleList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		
		String roleName =request.getParameter("roleName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleName", roleName);
		map.put("sessionUser",getSessionUser());
		PageDTO<Role> pageDTO  = roleService.listByPage(map, pageIndex, pageSize);
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/uc/role/roleList";
	}
	
	//点击添加
	@RequestMapping(value="addInputRole.html")
	public String addInputRole(){
		return "manage/uc/role/editRole";
	}
	//添加提交
	@RequestMapping(value="addSubmitRole.html")
	public String addSubmitRole(Role role,HttpServletRequest request){
		try {
			roleService.insert(role);
		} catch (Exception e) {
			logger.debug("添加失败："+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/roleController/roleList.html";
	}
	//点击修改
	@RequestMapping(value="updateInputRole.html")
	public String updateInputRole(@RequestParam String roleId,ModelMap modelMap){
		modelMap.addAttribute("role",roleService.findById(roleId));
		return "manage/uc/role/editRole";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitRole.html")
	public String updateSubmitRole(Role role,HttpServletRequest request){
		roleService.update(role);
		return "redirect:/roleController/roleList.html";
	}
	
	//删除
	@RequestMapping(value="deleteRole.html")
	public String deleteRole(@RequestParam String roleId){
		roleService.delete(roleId);
		return "redirect:/roleController/roleList.html";
	}
	
	//点击分配权限按钮
	@RequestMapping(value="assignPermission.html")
	public String roleAssignsPerRole(@RequestParam String roleId,ModelMap modelMap){
		Role role = roleService.findById(roleId);
		modelMap.addAttribute("role", role);
		//查询所有的菜单权限 判断如果是1，查询所有，如果不是1，则根据当前登陆人查询
		List<Menu> menuLists = new ArrayList<Menu>();
		User sessionUser = getSessionUser();
		if(sessionUser.getIsAdmin().equals("1")){
			menuLists = menuService.findAllMenue();
		}else{
			menuLists = menuService.findMenueByUserId(sessionUser.getUserId().toString());
		}
		modelMap.addAttribute("menuLists", menuLists);
		
		return "manage/uc/role/assignPermissions";
	}
	
	//分配权限提交
	@RequestMapping(value="assignPermissionSubmit.html")
	public void assignPermissionSubmit(HttpServletRequest request,HttpServletResponse response){
		try {
			roleMenuService.insert(request);
			response.getWriter().print("1");
		} catch (Exception e) {
			try {
				response.getWriter().print("-1");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	//查询此角色下的权限
	@RequestMapping(value="findMenuByRoleId.html")
	@ResponseBody
	public List<RoleMenu> findMenuByRoleId(@RequestParam String roleId){
		return roleMenuService.findById(roleId);
	}
	
	//查询此角色下是否有权限
	@RequestMapping(value="findMenuByRoleIdCount.html")
	@ResponseBody
	public Integer findMenuByRoleIdCount(@RequestParam String roleId,HttpServletResponse response){
		return roleMenuService.roleIsMenu(roleId);
	}
	
	//清除权限
	@RequestMapping(value="clearPermission.html")
	public void clearPermission(@RequestParam String roleId,HttpServletResponse response) throws Exception{
		Integer roleMenuCount = roleMenuService.roleIsMenu(roleId);
		if(roleMenuCount!=0){
			//删除角色下权限
			roleMenuService.delete(roleId);
			response.getWriter().print("1");
		}else{
			response.getWriter().print("0");
		}
	}
	
}

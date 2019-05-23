/** 
 * UserController.java  后台用户控制器
 */ 
package com.winnerbook.system.controller; 

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.winnerbook.base.common.util.ConstantUtils;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.share.dto.Qrcode;
import com.winnerbook.share.service.QrcodeService;
import com.winnerbook.system.dto.Role;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.dto.UserRole;
import com.winnerbook.system.service.UserRoleService;
import com.winnerbook.system.service.UserService;

/**
 * 后台用户列表
 * 
 */
@Controller
@RequestMapping(value="/userController")
public class UserController extends BaseController{
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private QrcodeService qrcodeService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/userList.html")
	public String  userList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		
		String userName =request.getParameter("userName");
		String userContactMobile =request.getParameter("userContactMobile");
		String userStatue =request.getParameter("userStatue");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("userContactMobile", userContactMobile);
		map.put("userStatue", userStatue);
		map.put("sessionUser",getSessionUser());
		PageDTO<User> pageDTO  = userService.listByPage(map, pageIndex,10);
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/uc/user/userList";
	}
	//点击添加
	@RequestMapping(value="addInputUser.html")
	public String addInputUser(Model model){
		model.addAttribute("sessionUser",getSessionUser());
		return "manage/uc/user/editUser";
	}
	
	//添加提交
	@RequestMapping(value="addSubmitUser.html")
	public String addSubmitUser(User user,HttpServletRequest request){
		try {
			userService.insert(user);
			
			//如果是企业管理员，则记录企业id
			if(user.getIsBusinessAdmin().equals("1") && getSessionUser().getUserId().toString().equals(getAdmin().getUserId().toString())){
				userService.updateBelongBusUser(user.getUserId().toString(), user.getUserId().toString());
			}
		} catch (Exception e) {
			logger.debug("添加失败："+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/userController/userList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateInputUser.html")
	public String updateInputUser(@RequestParam String userId,ModelMap modelMap){
		modelMap.addAttribute("sessionUser",getSessionUser());
		modelMap.addAttribute("user",userService.findUserById(userId));
		return "manage/uc/user/editUser";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitUser.html")
	public String updateSubmitUser(User user){
		userService.update(user);
		//如果是企业管理员，则记录企业id
		if(user.getIsBusinessAdmin().equals("1") && getSessionUser().getUserId().toString().equals(getAdmin().getUserId().toString())){
			userService.updateBelongBusUser(user.getUserId().toString(), user.getUserId().toString());
		}
		return "redirect:/userController/userList.html";
	}
	
	//删除
	@RequestMapping(value="deleteUser.html")
	public String deleteUser(@RequestParam String userId){
		userService.delete(userId);
		return "redirect:/userController/userList.html";
	}
	
	//修改密码
	@RequestMapping(value="editPassword.html")
	public String editPassword(){
		return "manage/uc/user/editPassword";
	}
	
	//修改密码提交
	@RequestMapping(value="editPasswordSubmit.html")
	@ResponseBody
	public Map<String, Object> editPasswordSubmit(@RequestParam String userPassword){
		return userService.updateUserPassword(userPassword);
	}
	//重置密码
	@RequestMapping(value="resetPassword.html")
	public String resetPassword(User user){
		userService.resetPassword(user);
		return "redirect:/userController/userList.html";
	}
	//点击分配角色按钮
	@RequestMapping(value="userRole.html")
	public String userRole(@RequestParam String userId,ModelMap modelMap){
		User user = userService.findUserById(userId);
		modelMap.addAttribute("user", user);
		//查询所有的角色
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionUser",getSessionUser());
		List<Role> userRoleLists = userRoleService.findAllRole(map);
		modelMap.addAttribute("userRoleLists", userRoleLists);
		return "manage/uc/user/userRole";
	}
	
	//分配角色提交
	@RequestMapping(value="userRoleSubmit.html")
	public void userRoleSubmit(HttpServletRequest request,HttpServletResponse response){
		try {
			userRoleService.insert1(request);
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
	
	//查询此用户下的角色
	@RequestMapping(value="findRoleByUserId.html")
	@ResponseBody
	public List<UserRole> findMenuByUserId(@RequestParam String userId){
		return userRoleService.findById1(userId);
	}
		
	//查询用户是否有角色
	@RequestMapping(value="findRoleByUserIdCount.html")
	@ResponseBody
	public Integer findRoleByUserIdCount(@RequestParam String userId,HttpServletResponse response){	
		return userRoleService.roleIsUser(userId);
	}
		
	//用户名唯一性验证
	@RequestMapping(value="checkUserName.html")
	@ResponseBody
	public Map<String, Integer> checkUserName(String userId,@RequestParam String userName){
		return userService.checkUserName(userId,userName);
	}
	
	@RequestMapping(value="importUser.html")
	public String importUser(String type,ModelMap modelMap){
		modelMap.put("type", type);
		return "manage/uc/user/importUser";
	}
	
	//导入用户信息
	@RequestMapping(value="importUserSubmit.html")
	@ResponseBody
	public Map<String,Object> importUserSubmit(HttpServletRequest request){
		return userService.importUser(request);
	}

	
	//获取企业管理员对应的课程包
	@RequestMapping(value="userCourseTypes.html")
	@ResponseBody
	public String userCourseTypes(){
		return userService.userCourseTypes();
	}
	
	//根据所属企业id得到二维码链接
	@RequestMapping("getBusQrcode.html")
	@ResponseBody
	public Qrcode getBusQrcode(@RequestBody String busIdJson){
		JSONObject jsonObject = JSONObject.fromObject(busIdJson);
		Qrcode qrcode = qrcodeService.getQrcodeByBusId(jsonObject.getString("busId"));
		if(null!=qrcode){
			qrcode.setImg(ConstantUtils.BASE_PATH_URL+qrcode.getImg()); 
		}
		return qrcode;
	}
}

	

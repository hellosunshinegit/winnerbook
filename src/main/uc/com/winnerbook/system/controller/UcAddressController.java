package com.winnerbook.system.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.system.dto.UcAddress;
import com.winnerbook.system.service.UcAddressService;

/**
 * 后台菜单控制器
 * 
 * @date 2016-2-19
 * @author hanxiaoshuang
 */
@Controller
@RequestMapping(value="/ucAddressController")
public class UcAddressController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(UcAddressController.class);
	
	@Autowired
	private UcAddressService ucAddressService;
	
	@RequestMapping(value="/addressList.html")
	public String addressList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		
		String addressName =request.getParameter("addressName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("addressName", addressName);
		PageDTO<UcAddress> pageDTO  = ucAddressService.listByPage(map, pageIndex,10);
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/uc/address/addressList";
	}
	
	//点击省查询市
	@RequestMapping(value="addressQuery.html")
	@ResponseBody
	public List<UcAddress> addressQuery(@RequestParam String addressId){
		return ucAddressService.findAddressByParentId(addressId);
	}
	
	//点击添加跳转
	@RequestMapping(value="addInputAddress.html")
	public String addInputAddress(){
		return "manage/uc/address/editAddress";
	}
	
	//添加提交
	@RequestMapping(value="addSubmitAddress.html")
	public String addSubmitAddress(UcAddress ucAddress,HttpServletRequest request){
		ucAddress.setAddressCreateDate(new Date());
		try {
			ucAddressService.insert(ucAddress);
		} catch (Exception e) {
			logger.debug("添加失败："+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/ucAddressController/addressList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateInputAddress.html")
	public String updateInputAddress(@RequestParam String addressId,ModelMap modelMap){
		modelMap.addAttribute("address",ucAddressService.findById(addressId));
		return "manage/uc/address/editAddress";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitAddress.html")
	public String updateSubmitAddress(UcAddress address,HttpServletRequest request){
		address.setAddressUpdateDate(new Date());
		ucAddressService.update(address);
		return "redirect:/ucAddressController/addressList.html";
	}
	
	//删除
	@RequestMapping(value="deleteAddress.html")
	public String deleteAddress(@RequestParam String addressId){
		ucAddressService.delete(addressId);
		return "redirect:/ucAddressController/addressList.html";
	}
	
}

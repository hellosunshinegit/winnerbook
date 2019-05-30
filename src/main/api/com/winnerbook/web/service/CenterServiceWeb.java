package com.winnerbook.web.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.winnerbook.base.frame.service.impl.WebBaseServiceImpl;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.dto.UserApplyBusAdmin;
import com.winnerbook.system.service.UserApplyBusAdminService;

@Service("centerServiceWeb")
public class CenterServiceWeb extends WebBaseServiceImpl{
	
	@Autowired
	private UserApplyBusAdminService userApplyBusAdminService;
	
	public String addApplyRecord(String userId,String busId,String applyBusName,String applyBusDes){
		
		User user = getLoginUser(userId);
		
		UserApplyBusAdmin userApplyBusAdmin = new UserApplyBusAdmin();
		userApplyBusAdmin.setUserId(Integer.parseInt(userId));
		userApplyBusAdmin.setUserName(user.getUserUnitName());
		userApplyBusAdmin.setBusId(Integer.parseInt(busId));
		userApplyBusAdmin.setApplyBusName(applyBusName);
		userApplyBusAdmin.setApplyBusDes(applyBusDes);
		userApplyBusAdmin.setStatus("0");
		userApplyBusAdmin.setCreateDate(new Date());
		
		userApplyBusAdminService.insert(userApplyBusAdmin);
		
		logRecord(userId, "2", "用户id："+user.getUserId()+",用户名称："+user.getUserUnitName()+"申请成为企业管理员");
		
		return userApplyBusAdmin.getUaId()+"";
	}
	
}

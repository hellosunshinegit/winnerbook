/** 
* 2015-3-2
* UserInfoServiceImpl.java 
* author:hxs
*/ 
package com.winnerbook.system.service.impl; 

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.common.util.ConstantUtils;
import com.winnerbook.base.common.util.EncryptUtil;
import com.winnerbook.base.common.util.FileUtils;
import com.winnerbook.base.common.util.Tools;
import com.winnerbook.base.common.util.ValidateUtils;
import com.winnerbook.base.frame.content.ThreadLocalWrapper;
import com.winnerbook.base.frame.content.UserContext;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.book.dao.BookListTypeDao;
import com.winnerbook.book.dto.BookListType;
import com.winnerbook.busInfo.dao.BusInfoDao;
import com.winnerbook.busInfo.dao.UserBusCourseTypeDao;
import com.winnerbook.busInfo.dto.UserBusCourseType;
import com.winnerbook.busInfo.dto.UserBusInfo;
import com.winnerbook.course.dao.CourseDao;
import com.winnerbook.share.dto.Qrcode;
import com.winnerbook.share.service.QrcodeService;
import com.winnerbook.system.dao.DefaultParamterDao;
import com.winnerbook.system.dao.RoleDao;
import com.winnerbook.system.dao.RoleMenuDao;
import com.winnerbook.system.dao.UserDao;
import com.winnerbook.system.dao.UserRoleDao;
import com.winnerbook.system.dto.DefaultParamter;
import com.winnerbook.system.dto.Role;
import com.winnerbook.system.dto.RoleMenu;
import com.winnerbook.system.dto.User;
import com.winnerbook.system.dto.UserRole;
import com.winnerbook.system.service.UserService;
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserBusCourseTypeDao userBusCourseTypeDao;
	
	@Autowired
	private DefaultParamterDao defaultParamterDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RoleMenuDao roleMenuDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private QrcodeService qrcodeService;
	
	@Autowired
	private BusInfoDao busInfoDao;
	
	@Autowired
	private BookListTypeDao bookListTypeDao;
	
	@Override
	public List<Map<String, Object>> findUserByUserName(String userName) {
		Map<String, Object> parameter = new HashMap<String,Object>();
		parameter.put("userName", userName);
		List<Map<String, Object>> users = userDao.findUserByUserName(parameter);
		return users;
	}

	@Override
	public User findUserById(String userId) {
		Map<String, Object> parameter = new HashMap<String,Object>();
		parameter.put("userId", userId);
		return this.userDao.findUserById(parameter);
	}
	
	
	@Override
	public PageDTO<User> listByPage(Map<String, Object> parameter,
			Integer pageIndex, Integer pageSize) {
		PageDTO<User> pageDTO = new PageDTO<User>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = userDao.selectCount(parameter);
		List<User> data = null;
		if (rowSize > 0) {
			data = userDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	//删除
	@Override
	public void delete(String id) {
		userDao.delete(id);
		logRecord("4","用户删除，id："+id);
	}
	
	//修改
	@Override
	public void update(User user) {
		User userone = getSessionUser();
		user.setUserUpdateDate(new Date());
		user.setUserUpdateUserId(userone.getUserId());
		user.setUserUpdateUserName(userone.getUserUnitName());
		String pwd = "";
		if(StringUtils.isNotBlank(user.getUserName())){
			//截取后6位作为手机号默认登录密码
			if(user.getUserName().length()==11){
				pwd = user.getUserName().substring(5, 11);
			}
		}
		user.setUserPassword(EncryptUtil.hash(pwd));
		userDao.update(user);
		logRecord("3","用户修改，id："+user.getUserId());
	}
	//新增
	@Override
	public int insert(User user) {
		String pwd = "";
		if(StringUtils.isNotBlank(user.getUserName())){
			//截取后6位作为手机号默认登录密码
			if(user.getUserName().length()==11){
				pwd = user.getUserName().substring(5, 11);
			}
		}
		User userone = getSessionUser();
		user.setUserParentId(userone.getUserId());
		user.setUserPassword(EncryptUtil.hash(pwd));
		user.setUserCreateDate(new Date());
		user.setUserCreateUserId(userone.getUserId());
		user.setUserCreateUserName(userone.getUserUnitName());
		//如果所属企业的id存在，则放入
		if(StringUtils.isNotBlank(userone.getBelongBusUserId())){
			user.setBelongBusUserId(userone.getBelongBusUserId());
		}
		
		userDao.insert(user);
		//企业管理员添加企业用户，则需要给用户赋权限
		if(userone.getIsBusinessAdmin().equals("1")){
			//查询企业对应的系统默认的角色id
			Map<String,Object> roleMap = defaultParamterDao.getDefaultBusRoleByBusId(userone.getUserId().toString());
			//赋予企业用户角色
			UserRole userRole = new UserRole();
			userRole.setUserId(user.getUserId());
			userRole.setRoleId(Long.parseLong(roleMap.get("busUserRoleId").toString()));
			userRole.setUrCreatedate(new Date());
			userRoleDao.insert(userRole);
		}
		//如果是企业管理员，则根据系统默认参数给企业管理员赋予角色  insert user_role表
		if(userone.getIsAdmin().equals("1") && user.getIsBusinessAdmin().equals("1")){//admin添加企业管理员时
			//查询自定义数据
			DefaultParamter defaultParamter = defaultParamterDao.findDefaultParamter();
			//赋予企业管理员角色信息
			UserRole userRole = new UserRole();
			userRole.setUserId(user.getUserId());
			userRole.setRoleId(Long.parseLong(defaultParamter.getBusAdminRoleId()));
			userRole.setUrCreatedate(new Date());
			userRoleDao.insert(userRole);
			
			//为企业管理员创建企业用户角色，并且赋予企业用户权限
			Role role = new Role();
			role.setRoleName(defaultParamter.getBusUserRoleName());
			role.setRoleDesc("系统默认创建");
			role.setRoleStatus("1");//启用
			role.setRoleCreateUserId(user.getUserId());
			role.setRoleCreatedate(new Date());
			role.setRoleCreateUserName(user.getUserUnitName());
			roleDao.insert(role);
			
			//保存企业管理员id对应的企业用户的角色id关联表
			Map<String,Object> busAdminRoleMap = new HashMap<>();
			busAdminRoleMap.put("busAdminId", user.getUserId());
			busAdminRoleMap.put("busUserRoleId", role.getRoleId());
			defaultParamterDao.insertDefaultBusRole(busAdminRoleMap);
			
			//给企业用户角色赋权限
			String busUserMuenIds = defaultParamter.getBusUserMenuIds();
			String[] menuArray = busUserMuenIds.split(",");
			List<RoleMenu> roleMenuList = new ArrayList<>();
			for(String menu:menuArray){
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setRoleId(role.getRoleId());
				roleMenu.setMenuId(Long.parseLong(menu));
				roleMenu.setRmCreatedate(new Date());
				roleMenuList.add(roleMenu);
			}
			roleMenuDao.insertBathRoleMenu(roleMenuList);
			
			//给企业默认推荐一个课程在h5端展示，admin添加的已发布的模板第一条
			List<Map<String, Object>> courseMap = courseDao.getCourseAdminCreate(null);
			if(null!=courseMap && courseMap.size()>0){
				for(int i = 0;i<courseMap.size();i++){
					Map<String, Object> course_release = new HashMap<>();
					course_release.put("courseId",null!=courseMap.get(i).get("courseId")?courseMap.get(i).get("courseId"):0);
					course_release.put("userId", user.getUserId());
					course_release.put("status", "1");
					course_release.put("createDate", new Date());
					course_release.put("createUserId", user.getUserId());
					course_release.put("createUserName", user.getUserUnitName());
					courseDao.insertCourseRelease(course_release);
				}
			}
			
			//给企业生成后台扫描二维码信息
			Qrcode qrcode = new Qrcode();
			qrcode.setName("企业预览二维码："+user.getUserUnitName());
			qrcode.setAddress("登陆后，右上角-后台预览手机端");
			qrcode.setForwardUrl(ConstantUtils.H5_URL+"?busId="+user.getUserId());
			qrcode.setBusId(Integer.parseInt(user.getUserId()+""));
			qrcodeService.insert(qrcode);
			
			//给企业生成名牌上的二维码
			Qrcode qrcode_brand = new Qrcode();
			qrcode_brand.setName("企业名牌二维码："+user.getUserUnitName());
			qrcode_brand.setAddress("企业名牌");
			qrcode_brand.setForwardUrl(ConstantUtils.H5_URL+"?busId="+user.getUserId());
			qrcode_brand.setBusId(Integer.parseInt(user.getUserId()+""));
			qrcodeService.insert(qrcode_brand);
			
			//创建企业信息，在user_bus_info表中
			UserBusInfo userBusInfo = new UserBusInfo();
			userBusInfo.setUserId(Integer.parseInt(user.getUserId()+""));
			//企业编号
			userBusInfo.setBusNumber(Tools.getBusNumber());
			if(qrcode.getId()>0){
				userBusInfo.setManageQrcodeId(qrcode.getId());
			}
			if(qrcode_brand.getId()>0){
				userBusInfo.setBrandQrcodeId(qrcode_brand.getId());
			}
			busInfoDao.insert(userBusInfo);
			
			//给企业默认添加一个老板书单
			BookListType bookListType = new BookListType();
			bookListType.setTypeName("老板书单");
			bookListType.setStatus("1");
			bookListType.setTypeSort(10);
			bookListType.setCreateDate(new Date());
			bookListType.setCreateUserId(Integer.parseInt(user.getUserId()+""));
			bookListType.setCreateUserName(user.getUserUnitName());
			bookListTypeDao.insert(bookListType);
			
		}
		logRecord("2","用户添加，id："+user.getUserId());
		return Integer.parseInt(user.getUserId().toString());
	}
	
	//修改用户密码提交
	@Override
	public Map<String,Object> updateUserPassword(String userPassword) {
		Map<String,Object>  map = new HashMap<>();
		try {
			User user = getSessionUser();
			//判断修改密码不可以和修改前的密码一致
			String beforePwd = user.getUserPassword();
			String updatePwd = EncryptUtil.hash(userPassword);
			if(beforePwd.equals(updatePwd)){
				map.put("code", "-1");
				map.put("msg", "不可以和原密码相同");
				return map;
			}
			user.setUserPassword(EncryptUtil.hash(userPassword));
			user.setUserUpdateDate(new Date());
			user.setUserUpdateUserId(user.getUserId());
			user.setUserUpdateUserName(user.getUserName());
			userDao.update(user);
			map.put("code", "200");
		} catch (Exception e) {
			e.getMessage();
			map.put("code", "-1");
		}
		
		return map;
	}
   //分配角色
	@Override
	public void userRole(Role role) {
		role.setRoleCreatedate(new Date());
		userDao.userRole(role);	
	}

	@Override
	public void organSubmit(HttpServletRequest request) {
		UserContext userContext = (UserContext) request.getSession().getAttribute(ThreadLocalWrapper.USER_CONTEXT_KEY);
		User userone = userContext.getUser();
		String userId = request.getParameter("userId");
		//根据用户id查询User
		User user = findUserById(userId);
		user.setUserUpdateUserId(userone.getUserId());
		user.setUserUpdateUserName(userone.getUserName());
		update(user);
	}

	//重置密码
	@Override
	public void resetPassword(User user) {
		user.setUserPassword(EncryptUtil.hash(ConstantUtils.initial_password));
		userDao.resetPassword(user);
	}

	@Override
	public Map<String,Integer> checkUserName(String userId,String userName) {
		Map<String,Integer> map = new HashMap<>();
		//判手机号也不可以重复
		List<User> userNameList = userDao.checkUserName(userId,userName);
		map.put("userNameCount", userNameList.size());
		return map;
	}

	@Override
	@Transactional
	public Map<String,Object> importUser(HttpServletRequest request) {
		Map<String,Object> mapResult = new HashMap<>();
		
		List<User> userListInsert = new LinkedList<>();
		List<Map<String,Object>> repeatPhoneList = new LinkedList<>();
		List<Map<String,Object>> phoneFormatList = new LinkedList<>();
		/*Map<Object,Integer> repeatExcelUserNameMap = new HashMap<>();
		Map<Object,Integer> repeatExcelPhoneMap = new HashMap<>();*/
		
		//解析excel中的数据到User中
		List<Map<String, String>> excelData = FileUtils.analysisExcel(request);
		
		//判断导入的数据和数据库中的数据是否有重复的，重复的加提示  map.put("sessionUser",getSessionUser());
		List<Map<String,Object>> userList = userDao.getUserByBusinessId(new HashMap<String,Object>(){{put("sessionUser",getSessionUser());}});
		Map<String,Object> userNameMap = new HashMap<>();
		for(Map user:userList){
			userNameMap.put(user.get("userName").toString(), user.get("userName"));
		}
		
		try {
			for(Map<String, String> map:excelData){
				String userName = null!=map.get("cell1")?map.get("cell1").toString():"";
				
				/*//判断excel中是否有重复的数据重复的登录名和手机号
				if(null==repeatExcelUserNameMap.get(userName)){
					repeatExcelUserNameMap.put(userName, 1);
				}else{
					repeatExcelUserNameMap.put(userName,repeatExcelUserNameMap.get(userName)+1);
				}
				
				//判断excel中是否有重复的数据重复的登录名和手机号
				if(null==repeatExcelPhoneMap.get(contactMobile)){
					repeatExcelPhoneMap.put(contactMobile, 1);
				}else{
					repeatExcelPhoneMap.put(contactMobile,repeatExcelPhoneMap.get(contactMobile)+1);
				}*/
				
				//判断excel中是否有数据库已经存在的手机号
				if(null!=userNameMap.get(userName)){//证明数据库中有重复的手机号，不可以添加
					//记录重复数据
					Map<String,Object> map_phone = new HashMap<>();
					map_phone.put("contactMobile", userName);
					repeatPhoneList.add(map_phone);
					continue;
				}
				//判断手机号格式是否正确
				if(ValidateUtils.checkPhone(userName)==false){
					//记录手机号错误数据
					Map<String,Object> map_phone = new HashMap<>();
					map_phone.put("contactMobile", userName);
					phoneFormatList.add(map_phone);
					continue;
				}
				
				userListInsert.add(getUser(map));
			}
			if(userListInsert.size()>0){
				List<String> userIdList = new LinkedList<>();
				for(User user:userListInsert){
					userDao.importUser(user);
					userIdList.add(user.getUserId().toString());
				}
				//给导入的用户赋予企业用户的角色
				//查询企业对应的系统默认的角色id
				User sessionUser = getSessionUser();
				Map<String,Object> roleMap = defaultParamterDao.getDefaultBusRoleByBusId(sessionUser.getUserId().toString());
				if(null!=roleMap){
					//赋予企业用户角色
					List<UserRole> userRoles = new LinkedList<>();
					for(String userIdStr:userIdList){
						UserRole userRole = new UserRole();
						userRole.setUserId(Long.parseLong(userIdStr));
						userRole.setRoleId(Long.parseLong(roleMap.get("busUserRoleId").toString()));
						userRole.setUrCreatedate(new Date());
						userRoles.add(userRole);
					}
					userRoleDao.insertBathUserRole(userRoles);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
			} catch (Exception e2) {
			}
			mapResult.put("code", "-1");
			return mapResult;
		}
		mapResult.put("excelDataCount", null!=excelData?excelData.size():0);
		mapResult.put("userListInsert", null!=userListInsert?userListInsert.size():0);
		mapResult.put("repeatPhoneList", repeatPhoneList);
		mapResult.put("phoneFormatList", phoneFormatList);
		mapResult.put("code", "200");
		return mapResult;
	}
	
	
	public User getUser(Map map){
		User userone = getSessionUser();
		String pwd="";
		String username = null!=map.get("cell1")?map.get("cell1").toString():"";
		if(StringUtils.isNotBlank(username)){
			//截取后6位作为手机号默认登录密码
			if(username.length()==11){
				pwd = username.substring(5, 11);
			}
		}
		User user = new User();
		user.setUserUnitName(null!=map.get("cell0")?map.get("cell0").toString():"");
		user.setUserName(null!=map.get("cell1")?map.get("cell1").toString():"");
		user.setDepartment(null!=map.get("cell2")?map.get("cell2").toString():"");
		user.setIsDepartLeader(null!=map.get("cell3")?map.get("cell3").toString().equals("是")?"1":"0":"");
		user.setIsCompanyLeader(null!=map.get("cell4")?map.get("cell4").toString().equals("是")?"1":"0":"");
		user.setUserParentId(userone.getUserId());
		user.setUserPassword(EncryptUtil.hash(pwd));
		user.setSourceType("2");//导入2  新增1
		user.setUserCreateDate(new Date());
		user.setUserCreateUserId(userone.getUserId());
		user.setUserCreateUserName(userone.getUserUnitName());
		
		//如果所属企业的id存在，则放入
		if(StringUtils.isNotBlank(userone.getBelongBusUserId())){
			user.setBelongBusUserId(userone.getBelongBusUserId());
		}
		
		return user;
	}

	@Override
	public User getAdmin() {
		return userDao.getAdmin().get(0);
	}

	@Override
	public void updateBelongBusUser(String userId, String belongBusUserId) {
		Map<String, Object> parameter = new HashMap<String,Object>();
		parameter.put("userId", userId);
		parameter.put("belongBusUserId", belongBusUserId);
		userDao.updateBelongBusUser(parameter);
	}

	@Override
	public String userCourseTypes() {
		User user = getSessionUser();
		String courseTypes = "";
		if(null!=user && !user.getIsAdmin().equals("1")){
			//如果是企业管理员，直接查询，如果不是企业用户，也不是管理员，那么需要查询企业用户的上一级，企业管理员的课程包的权限
			String userId = StringUtils.isNotBlank(user.getBelongBusUserId())?user.getBelongBusUserId():"0";
			List<UserBusCourseType> userCourseTypes = userBusCourseTypeDao.findByUserId(userId);
			
			for(int i = 0;i<userCourseTypes.size();i++){
				if(i!=0){
					courseTypes+=",";
				}
				courseTypes+=userCourseTypes.get(i).getCourseTypeId();
			}
		}
		return courseTypes;
	}

	@Override
	public User getUserByUserBus(String userId, String busId) {
		Map<String, Object> parameter = new HashMap<String,Object>();
		parameter.put("userId", userId);
		parameter.put("busId", busId);
		return userDao.getUserByUserBus(parameter);
	}

	@Override
	public User isExistsByBusId(String busId, String phone) {
		Map<String, Object> parameter = new HashMap<String,Object>();
		parameter.put("busId", busId);
		parameter.put("phone", phone);
		return userDao.isExistsByBusId(parameter);
	}

	@Override
	public Qrcode getBusQrcode(String busId) {
		return null;
	}
	
}

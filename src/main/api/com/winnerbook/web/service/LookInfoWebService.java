package com.winnerbook.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnerbook.activity.dao.BusLookInfoDao;
import com.winnerbook.activity.dto.BusLookInfo;
import com.winnerbook.base.common.JSONResponse;
import com.winnerbook.base.frame.service.impl.WebBaseServiceImpl;
import com.winnerbook.course.service.CourseTypeService;
import com.winnerbook.system.dto.Dictionary;
import com.winnerbook.system.service.DictionaryService;

@Service("lookInfoWebService")
public class LookInfoWebService extends WebBaseServiceImpl{
	
	@Autowired
	private BusLookInfoDao busLookInfoDao;

	@Autowired
	private CourseTypeService courseTypeService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	//活动报名
	public JSONResponse addLookInfo(HttpServletRequest request){
		JSONResponse result = new JSONResponse();
		
		String busName       = request.getParameter("busName");//企业名称
		String bookListName  = request.getParameter("bookListName");//企业书单
		String courseTypes   = request.getParameter("courseTypes");//课程包
		String readBookPlan  = request.getParameter("readBookPlan");//读书计划
		String readBookPlanOther  = request.getParameter("readBookPlanOther");//读书计划
		String mustReadNum   = request.getParameter("mustReadNum");//1年必读几本
		String selectReadNum = request.getParameter("selectReadNum");//1年选读几本
		String isReadThought = request.getParameter("isReadThought");//是否写读后感
		String isReadClud    = request.getParameter("isReadClud");//是否召开读书会
		String longReadClud  = request.getParameter("longReadClud");//多久召开读书会
		String isShareWb     = request.getParameter("isShareWb");//是否分享微博
		String isReadBook    = request.getParameter("isReadBook");//企业家是否读书
		String isWriteBook   = request.getParameter("isWriteBook");//企业家是否写书
		String isShareBook   = request.getParameter("isShareBook");//企业家是否分享书
		String publicNum     = request.getParameter("publicNum");//公众号
		String homepageUrl   = request.getParameter("homepageUrl");//公司主页
		String empNum        = request.getParameter("empNum");//公司员工数量
		String companyAddress = request.getParameter("companyAddress");//公司地址
		String companyIndustry = request.getParameter("companyIndustry");//公司所属行业
		String telphone = request.getParameter("telphone");//联系电话
		String isUseBookYun = request.getParameter("isUseBookYun");//是否使用企业读书云
		
		//判断busName和telphone不可为空
		if(!StringUtils.isNotBlank(busName)){
			result.setMsg("企业名称为必填项");
			return result;
		}
		
		if(!StringUtils.isNotBlank(telphone)){
			result.setMsg("联系电话为必填项");
			return result;
		}
		
		BusLookInfo lookInfo = new BusLookInfo();
		if(StringUtils.isNotBlank(courseTypes)){
			Map<String, Object> courseTypeMap = new HashMap<String, Object>();
			List<Map<String,Object>> listMap = courseTypeService.getCoursePackage();
			for(Map<String,Object> map:listMap){
				courseTypeMap.put(map.get("typeId")+"", map.get("typeName")+"");
			}
			
			String[] courseTypesArray = courseTypes.split(",");
			String courseTypeStr = "";
			int i = 0;
			for(String courseType:courseTypesArray){
				if(null!=courseTypeMap.get(courseType)){
					if(i!=0){
						courseTypeStr+=",";
					}
					courseTypeStr+=courseTypeMap.get(courseType);
					i++;
				}
			}
			lookInfo.setCourseList(courseTypeStr);
		}else{
			lookInfo.setCourseList(courseTypes);
		}
		
		//读书计划 readBookPlan
		if(StringUtils.isNotBlank(readBookPlan)){
			List<Dictionary> dictionaries = dictionaryService.getDictionaries("READ_BOOK_PLAN");
			Map<String, Object> dicMap = new HashMap<>();
			for(Dictionary dictionary:dictionaries){
				dicMap.put(dictionary.getDicItemvalue(), dictionary.getDicItemname());
			}
			lookInfo.setReadBookPlan(null!=dicMap.get(readBookPlan)?dicMap.get(readBookPlan)+"":"");
			if("3".equals(readBookPlan)){
				if(StringUtils.isNotBlank(readBookPlanOther)){
					lookInfo.setReadBookPlan(lookInfo.getReadBookPlan()+"-"+readBookPlanOther);
				}
			}
		}else{
			lookInfo.setReadBookPlan(readBookPlan);
		}
		
		lookInfo.setBusName(busName);
		lookInfo.setBookListName(bookListName);
		lookInfo.setMustReadNum(mustReadNum);
		lookInfo.setSelectReadNum(selectReadNum);
		lookInfo.setIsReadThought(isReadThought);
		lookInfo.setIsReadClud(isReadClud);
		lookInfo.setLongReadClud(longReadClud);
		lookInfo.setIsShareWb(isShareWb);
		lookInfo.setIsReadBook(isReadBook);
		lookInfo.setIsWriteBook(isWriteBook);
		lookInfo.setIsShareBook(isShareBook);
		lookInfo.setPublicNum(publicNum);
		lookInfo.setHomepageUrl(homepageUrl);
		lookInfo.setEmpNum(empNum);
		lookInfo.setCompanyAddress(companyAddress);
		lookInfo.setCompanyIndustry(companyIndustry);
		lookInfo.setTelphone(telphone);
		lookInfo.setIsUseBookYun(isUseBookYun);
		lookInfo.setCreateDate(new Date());
		lookInfo.setStatus("0");
		
		int recordId = busLookInfoDao.insert(lookInfo);

		logRecord("","2","企业调研报告添加");
		result.setSuccess(true);
		result.setData(recordId);
		return result;
	}
	

}

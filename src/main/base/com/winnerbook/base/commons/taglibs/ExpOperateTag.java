package com.winnerbook.base.commons.taglibs;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.SystemProperties;

import com.winnerbook.base.common.util.Helper;

public class ExpOperateTag extends BodyTagSupport{

	private String moduleId;
	
	private String type="button";
	
	private String preParams;
	
	private String afterParams;
	
	private String colId="";
	
	private String operateTab="1";
	/**
	 * 
	 */
	private static final long serialVersionUID = -6464767126325447227L;
	private JspWriter out;
	public void init() {
		out = pageContext.getOut();
	}

	@Override
	public int doStartTag() throws JspException {
		init();
		return super.EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			StringBuffer sb=new StringBuffer();
			HttpSession session = pageContext.getSession();
			/*LoginUserInfoBean bean=(LoginUserInfoBean)session.getAttribute(SystemProperties.MINXINLOAN_LOGIN_USER);
			List<OperateVO> list=Helper.getUserOperate(bean, moduleId);
			for (int i = 0; i < list.size(); i++) {
				OperateVO vo=list.get(i);
				String url=vo.getOperateUrl();
				if(StringUtils.isNotBlank(preParams)){
					url=preParams+url;
				}
				if(StringUtils.isNotBlank(afterParams)){
					url=url+afterParams;
				}
				if("button".equals(type)&&1==vo.getOperateType()&&!"模块显示".equals(vo.getOperateName())&&vo.getOperateTab().toString().equals(operateTab)){
					sb.append("<input type=\"button\" id=\"btn"+vo.getId()+"\" value=\""+vo.getOperateName()+"\" class=\"btn\" onclick=\""+url+"\"/>&nbsp;&nbsp;");
				}else if("link".equals(type)&&2==vo.getOperateType()&&vo.getOperateTab().toString().equals(operateTab)){
					sb.append("<a href="+url+" id=\"link_"+vo.getId()+"_"+colId+"\">"+vo.getOperateName()+"</a>&nbsp;&nbsp;");
				}
			}*/
			out.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.EVAL_PAGE;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPreParams(String preParams) {
		this.preParams = preParams;
	}

	public void setAfterParams(String afterParams) {
		this.afterParams = afterParams;
	}

	public void setColId(String colId) {
		this.colId = colId;
	}

	public void setOperateTab(String operateTab) {
		this.operateTab = operateTab;
	}
	
}

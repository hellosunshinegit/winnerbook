package com.winnerbook.base.commons.taglibs;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;

import com.winnerbook.base.common.UIComponentBean;
import com.winnerbook.base.common.util.Helper;

public class ExpSelectTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2900233361611357594L;
	private String code;
	private String params;
	private String id;
	private String name;
	private String headerKey;
	private String headerValue;
	private String value;
	private String cssClass;
	private String style;
	private String onChange;
	private String disabled;
	private String dataType;
	private String requireMsg;
	private String require;
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
			// require="true" requireMsg="申请期限为必填项!" dataType="Require"
			StringBuffer sb=new StringBuffer();
			sb.append("<select");
			if(StringUtils.isNotBlank(id)){
				sb.append(" id=\""+id+"\"");
			}
			if(StringUtils.isNotBlank(name)){
				sb.append(" name=\""+name+"\"");
			}
			if(StringUtils.isNotBlank(cssClass)){
				sb.append(" class=\""+cssClass+"\"");
			}
			if(StringUtils.isNotBlank(style)){
				sb.append(" style=\""+style+"\"");
			}
			if(StringUtils.isNotBlank(onChange)){
				sb.append(" onChange=\""+onChange+"\"");
			}
			if(StringUtils.isNotBlank(disabled)&&"true".equals(disabled)){
				sb.append(" disabled=\"true\"");
			}
			if(StringUtils.isNotBlank(dataType)){
				sb.append(" dataType=\""+dataType+"\"");
			}
			if(StringUtils.isNotBlank(requireMsg)){
				sb.append(" requireMsg=\""+requireMsg+"\"");
			}
			if(StringUtils.isNotBlank(require)){
				sb.append(" require=\""+require+"\"");
			}
			sb.append(">");
			if(StringUtils.isNotBlank(headerKey)||StringUtils.isNotBlank(headerValue)){
				sb.append("<option value=\""+headerKey+"\" selected>").append(headerValue).append("</option>");
			}
			if(StringUtils.isNotBlank(code)){
				List<UIComponentBean> retList=null;
		        if (StringUtils.isNotBlank(params)) {
		        	String[] paramsList =StringUtils.split(params, ",");
		        	retList = Helper.getCodeListToUIComponentBean(code.trim(), Arrays.asList(paramsList));
			     }else{
			    	retList = Helper.getCodeListToUIComponentBean(code.trim());
			     }
		        if(retList != null && retList.size() > 0){
		        	for (int i = 0; i < retList.size(); i++) {
		        		UIComponentBean bean=retList.get(i);
		        		sb.append("<option value=\""+bean.getKey()+"\"");
		        		if(StringUtils.isNotBlank(value)&&value.equals(bean.getKey())){
		        			sb.append(" selected");
		        		}
		        		sb.append(" >").append(bean.getValue()).append("</option>");
					}
		        }
			}
			sb.append("</select>");
			out.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.EVAL_PAGE;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}


	
	public void setRequireMsg(String requireMsg) {
		this.requireMsg = requireMsg;
	}

	public void setRequire(String require) {
		this.require = require;
	}


	
}

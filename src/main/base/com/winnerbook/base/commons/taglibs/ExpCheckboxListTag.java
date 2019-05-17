package com.winnerbook.base.commons.taglibs;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.winnerbook.base.common.UIComponentBean;
import com.winnerbook.base.common.util.Helper;

public class ExpCheckboxListTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7190381894199718361L;
	private String code;
	private String params;
	private String name;
	private String value;
	private String colCount = "5";
	private String disabled;
	private String onChange;
	private String onClick;
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
		        		if(i>0&&i%Integer.parseInt(colCount)==0){
		        			sb.append("<br>");
		        		}
		        		sb.append("<input type=\"checkbox\" name=\""+name+"\" value=\""+bean.getKey()+"\"");
		        		if(StringUtils.isNotBlank(value)){
		        			String[] arr=StringUtils.split(value,',');
		        			for (int j = 0; j < arr.length; j++) {
		        				if(arr[j].equals(bean.getKey())){
		        					sb.append(" checked=\"checked\"");
		        					break;
		        				}
							}
		        		}
		        		if(StringUtils.isNotBlank(onClick)){
		    				sb.append(" onClick=\""+onClick+"\"");
		    			}
		        		if(StringUtils.isNotBlank(onChange)){
		    				sb.append(" onChange=\""+onChange+"\"");
		    			}
		        		if(StringUtils.isNotBlank(disabled)&&"true".equals(disabled)){
		    				sb.append(" disabled=\"true\"");
		    			}
		        		sb.append(" />").append(bean.getValue()).append("&nbsp;&nbsp;&nbsp;");
					}
		        }
			}
			out.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.EVAL_PAGE;
	}
	

	public void setCode(String code) {
		this.code = code;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setColCount(String colCount) {
		this.colCount = colCount;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

}

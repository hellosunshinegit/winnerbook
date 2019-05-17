package com.winnerbook.base.commons.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.winnerbook.base.common.util.Helper;


public class ExpDictionaryTag extends BodyTagSupport{

	private String code;
	
	private String value;
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
			out.print(Helper.getDictionary(code.trim(), value.trim()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.EVAL_PAGE;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

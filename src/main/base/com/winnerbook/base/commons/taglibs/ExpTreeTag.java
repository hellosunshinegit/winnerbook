package com.winnerbook.base.commons.taglibs;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;

import com.winnerbook.base.common.UITreeBean;
import com.winnerbook.base.common.util.Helper;
import com.winnerbook.base.common.util.JsonUtil;

public class ExpTreeTag extends BodyTagSupport{
	private static final long serialVersionUID = -9079160136312661975L;
	protected String code;
	protected String params;
	protected String id="zTree01";
	protected String expendLevel="3";
	protected String onClick;
	protected String checkStyle;
	protected String chkboxType="{ \"Y\": \"s\", \"N\": \"s\" }";
	protected String value;
	protected String style="width:200px;height:300px;";
	protected String rootNodeId;
	protected String rootNodeName;
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
			sb.append("<div id=\""+id+"\" class=\"ztree\"></div>");
			sb.append("<script type=\"text/javascript\">");
			sb.append("var setting = {treeId:\"tree_"+id+"\"");
			if(StringUtils.isNotBlank(onClick)){
				sb.append(",callback: {onClick: "+onClick+"}");
			}
			if(StringUtils.isNotBlank(checkStyle)){
				sb.append(",check: {enable: true,chkStyle: \""+checkStyle+"\",chkboxType: "+chkboxType+"}");
			}
			sb.append("};");
			if(StringUtils.isNotBlank(code)){
				List<UITreeBean> retList=null;
		        if (StringUtils.isNotBlank(params)) {
		        	String[] paramsList =StringUtils.split(params, ",");
		        	retList = Helper.getCodeListToUITreeBean(code.trim(), Arrays.asList(paramsList),expendLevel,rootNodeId,rootNodeName);
			    }else{
			    	retList = Helper.getCodeListToUITreeBean(code.trim(),null,expendLevel,rootNodeId,rootNodeName);
			    }
		        sb.append("var zNodes =").append(JsonUtil.getJsonArrayString4JavaPOJO(retList,"")).append(";");
		        sb.append("$.fn.zTree.init($(\"#"+id+"\"), setting, zNodes);");
		        if(StringUtils.isNotBlank(value)){
		        	String[] arr=StringUtils.split(value,',');
		        	sb.append("var treeObj = $.fn.zTree.getZTreeObj(\""+id+"\");");
		        	for (int i = 0; i < arr.length; i++) {
		        		sb.append("var nodes = treeObj.getNodesByParam(\"id\",\""+arr[i]+"\",null);");
		        		if(StringUtils.isBlank(checkStyle)){
		        			sb.append("treeObj.selectNode(nodes[0]);");
		        		}else{
		        			sb.append("treeObj.checkNode(nodes[0],true,false);");
		        		}
					}
		        }
		        
			}
			sb.append("</script>");
			out.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.EVAL_PAGE;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public void setExpendLevel(String expendLevel) {
		this.expendLevel = expendLevel;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public void setCheckStyle(String checkStyle) {
		this.checkStyle = checkStyle;
	}

	public void setChkboxType(String chkboxType) {
		this.chkboxType = chkboxType;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setRootNodeId(String rootNodeId) {
		this.rootNodeId = rootNodeId;
	}

	public void setRootNodeName(String rootNodeName) {
		this.rootNodeName = rootNodeName;
	}
	
}

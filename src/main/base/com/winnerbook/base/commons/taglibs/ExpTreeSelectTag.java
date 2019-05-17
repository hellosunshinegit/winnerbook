package com.winnerbook.base.commons.taglibs;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang3.StringUtils;

import com.winnerbook.base.common.UITreeBean;
import com.winnerbook.base.common.util.Helper;
import com.winnerbook.base.common.util.JsonUtil;

public class ExpTreeSelectTag extends ExpTreeTag{
	private static final long serialVersionUID = -9079160136312661975L;
	private String dataType;
	private String msg;
	private String name;
	
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
			sb.append("<div id=\"menuContent_"+name+"\" class=\"menuContent\" style=\"display:none;z-index:99999; position: absolute;\">");
			sb.append("<ul id=\""+id+"\" class=\"ztree\" style=\"margin-top:0; width:160px;\"></ul>");
			sb.append("</div><input type=\"hidden\" value=\""+value+"\" name=\""+name+"\" id=\""+name+"Hidd\">");
			sb.append("<input type=\"text\" id=\""+name+"Val\" readonly=\"readonly\" ");
			if(StringUtils.isNotBlank(dataType)||StringUtils.isNotBlank(msg)){
				sb.append("dataType=\""+dataType+"\" msg=\""+msg+"\"");
			}
			sb.append("/><a id=\"menuBtn_"+name+"\" href=\"#\" onclick=\"showMenu_"+name+"();\">选择</a>");
			sb.append("<script type=\"text/javascript\">");
			sb.append("var setting = {treeId:\"tree_"+id+"\"");
			sb.append(",callback: {onClick: function(e, treeId, node){$('#"+name+"Hidd').val(node.id);$('#"+name+"Val').val(node.name);hideMenu_"+name+"();");
			if(StringUtils.isNotBlank(onClick)){
				sb.append(onClick+"(e, treeId, node);");
			}
			sb.append("}}");
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
		        			sb.append("$('#"+name+"Hidd').val(nodes[0].id);$('#"+name+"Val').val(nodes[0].name);");
		        		}else{
		        			sb.append("treeObj.checkNode(nodes[0],true,false);");
		        		}
					}
		        }
			}
			sb.append("function showMenu_"+name+"() {var obj = $(\"#"+name+"Val\");var offset = $(\"#"+name+"Val\").offset();");
			sb.append("$(\"#menuContent_"+name+"\").css({left:offset.left + \"px\", top:offset.top + obj.outerHeight() + \"px\"}).slideDown(\"fast\");");
			sb.append("$(\"body\").bind(\"mousedown\", onBodyDown_"+name+");}");
			sb.append("function hideMenu_"+name+"() {$(\"#menuContent_"+name+"\").fadeOut(\"fast\");$(\"body\").unbind(\"mousedown\", onBodyDown_"+name+");}");
			sb.append("function onBodyDown_"+name+"(event) {if (!(event.target.id == \"menuBtn_"+name+"\" || event.target.id == \"menuContent_"+name+"\" || $(event.target).parents(\"#menuContent_"+name+"\").length>0)) {hideMenu_"+name+"();}}");
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

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

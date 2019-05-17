package com.winnerbook.base.home.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.winnerbook.base.common.util.FileUtils;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.course.dto.ReadThought;
import com.winnerbook.course.service.ReadThoughtService;

@Controller
@RequestMapping(value="fileUploadController")
public class FileUploadController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	
	@Autowired
	private ReadThoughtService readThoughtService;
	
	//iframe引入
	@RequestMapping(value="uploadFileIframe.html")
	public String uploadFileIframe(HttpServletRequest request,ModelMap modelMap){
		String filePath = request.getParameter("filePath");
		modelMap.put("uploadId", "upload_"+filePath);//渲染上传文件
		modelMap.put("typeExts", request.getParameter("typeExts"));//1 图片  2视频   3音频
		modelMap.put("path", request.getParameter("path")+"/"+filePath);//上传路径指定，也可以不指定
		modelMap.put("filePath", filePath);//文件上传的路径
		modelMap.put("fileName", request.getParameter("fileName"));//文件上传的路径名称
		modelMap.put("res", "res"+"_"+filePath);//文件上传成功后显示上传结果
		return "manage/fileUpload";
	}
	
	//上传1个文件提交
	@RequestMapping(value="uploadFileSubmit.html")
	public void uploadFileSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter out = response.getWriter();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//上传路径  path路径可传可不传，最好上传，会根据业务区分目录
			request.setAttribute("userId", getSessionUser().getUserId());
			request.setAttribute("path", request.getParameter("path"));
			String urlPath = FileUtils.uploadImgFile(request);
			map.put("urlPath", urlPath);
			map.put("fileName", FileUtils.getMultipartFile(request).getOriginalFilename());
			map.put("code","200");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code","-1");
		}
		out.print(JSONObject.fromObject(map));
	}
	
	//点击删除附件
	@RequestMapping(value="fileDelete.html")
	@ResponseBody
	public String fileDelete(@RequestParam String filePath){
		String result = "";
		try {
			FileUtils.delFile(filePath);
			result="1";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除附件失败:"+e.getMessage());
			result="-1";
		}
		return result;
	}
	
	//下载附件
	@RequestMapping(value="downLoad.html")
	public void downLoad(@RequestBody String downParm,HttpServletResponse response){
		//FileUtils.downloadFilename(FileUtils.getRealtyPathName(path), fileName, response);
	}
}

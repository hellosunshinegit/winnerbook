package com.winnerbook.base.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.winnerbook.base.common.GlobalConfigure;

public class FileUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(FileUtils.class);

	// excel导入
	private static final int version2003 = 2003;

	private static final int version2007 = 2007;

	private static int version = version2003;

	private static Workbook wb = null;

	private static Sheet sheet = null;

	private static Cell cell = null;

	private static Row row = null;

	public static boolean isBlank(Object str) {
		if (str == null) {
			return true;
		}
		if (str instanceof String) {
			str = str.toString();
			if ("".equalsIgnoreCase(((String) str).trim())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNotBlank(Object str) {
		return !isBlank(str);
	}

	/**
	 * 获取文件的后缀名
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件后缀名,例如: .txt
	 */
	public static String getFileExtension(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			throw new RuntimeException("文件名称不能为空!");
		}
		int index = fileName.lastIndexOf(".");
		if (index == -1) {
			throw new RuntimeException("文件名没有后缀名!");
		}
		return fileName.substring(index);
	}

	// request转换为Map<String, MultipartFile>
	public static Map<String, MultipartFile> getMultipartFileMaps(
			HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		return fileMap;
	}

	// 获取单个文件
	public static MultipartFile getMultipartFile(HttpServletRequest request) {
		MultipartFile file = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			file = entity.getValue();
		}
		return file;
	}

	// 获取存储路径，实际存储和访问的路径
	public static Map<String, String> getDirPath(String path) {
		Map<String, String> urlMap = new HashMap<String, String>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date theDate = new Date();
		String nowdata = (df.format(theDate));
		String dirPath = ConstantUtils.FILE_SERVER_LOCAL_PATH + path
				+ (StringUtils.isNotBlank(path) ? "/" : "") + nowdata + "/";
		String urlPath = ConstantUtils.SERVER_LOCAL_PATH + path
				+ (StringUtils.isNotBlank(path) ? "/" : "") + nowdata + "/";
		urlMap.put("dirPath", dirPath);
		urlMap.put("urlPath", urlPath);
		return urlMap;
	}

	/**
	 * 上传文件到指定的目录中
	 * 
	 * @param dirPath
	 *            上级目录
	 * @param originalFileName
	 *            原文件名
	 * @param targetFileName
	 *            目标文件名
	 * @param data
	 *            文件字节数据
	 * @return 图片可访问URL: /XXXXXX/XXXXXX.jpg
	 * @throws IOException
	 */
	public static String uploadFileC(String path, String busId, byte[] data,
			String originalFileName, HttpServletRequest request)
			throws IOException {
		// 上传文件目录
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date theDate = new Date();
		String nowdata = (df.format(theDate));
		String dirPath = ConstantUtils.FILE_SERVER_LOCAL_PATH + path + "/"
				+ nowdata + "/";
		// String dirPath =
		// request.getSession().getServletContext().getRealPath(GlobalConfigure.IMAGE_SERVER_LOCAL_PATH)+"/"+path+"/"+nowdata+"/";

		File localFile = new File(dirPath);
		if (!localFile.exists()) {
			localFile.mkdirs();
		}
		String newFileName = busId + "_" + System.currentTimeMillis()
				+ getFileExtension(originalFileName);
		StringBuilder builder = new StringBuilder();
		builder.append(dirPath);
		builder.append(newFileName);
		String targetFileName = builder.toString();
		org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(
				targetFileName), data);
		String picUrl = ConstantUtils.SERVER_LOCAL_PATH + path + "/" + nowdata
				+ "/" + newFileName.replaceAll("\\\\", "/");
		return picUrl;
	}

	/**
	 * 上传单个图片，视频 目前使用的是这个。。。。2019.03.21 默认上传路径为/usr/local/webapp/upload/
	 * 访问路径/upload/
	 * 
	 * @param request
	 * @return
	 */
	public static String uploadImgFile(HttpServletRequest request) {
		String userId = "";
		if (StringUtils.isNotBlank(request.getParameter("userId"))) {
			userId = request.getParameter("userId");
		} else {
			userId = null != request.getAttribute("userId") ? request
					.getAttribute("userId").toString() : "";
		}

		String path = "";
		if (StringUtils.isNotBlank(request.getParameter("path"))) {
			path = request.getParameter("path");
		} else {
			path = null != request.getAttribute("path") ? request.getAttribute(
					"path").toString() : "";
		}
		
		String type = StringUtils.isNotBlank(request.getParameter("type"))?request.getParameter("type"):"";
		
		MultipartFile file = getMultipartFile(request);
		Map<String, String> urlPathMap = getDirPath(path);// 可传可不传
		String dirPath = urlPathMap.get("dirPath");// 实际存入磁盘的路径

		String newFileName = userId + "_" + System.currentTimeMillis()
				+ getFileExtension(file.getOriginalFilename());
		dirPath = dirPath + newFileName;
		logger.info("文件实际存储路径：" + dirPath);
		File localFile = new File(dirPath);
		if (!localFile.exists()) {
			localFile.mkdirs();
		}
		try {
			file.transferTo(localFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		//如果是图片，进行压缩
		if("1".equals(type)){
			ThumbnailatorUtil.generateFixedSizeImage(dirPath);
		}
		// 返回需要存入数据库的url
		String urlPath = urlPathMap.get("urlPath")
				+ newFileName.replaceAll("\\\\", "/");
		return urlPath;
	}

	/**
	 * 删除制定目录的文件
	 * 
	 * @param filePath文件路径
	 *            /upload/
	 * @return
	 */
	public static void delFile(String filePath) {
		filePath = ConstantUtils.FILE_SERVER_LOCAL_PATH
				+ filePath.substring(filePath.indexOf("upload") + 6,
						filePath.length());
		
		File file = new File(filePath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
		}
		//获取min的图片，一起删除
		if(filePath.lastIndexOf(".")>=0){
			String filePathMin = filePath.substring(0,filePath.indexOf("."))+"_min"+filePath.substring(filePath.lastIndexOf("."), filePath.length());
			File fileMin = new File(filePathMin);
			// 路径为文件且不为空则进行删除
			if (fileMin.isFile() && fileMin.exists()) {
				fileMin.delete();
			}
		}
	}

	/**
	 * 获取真实路径
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getRealtyPathName(String filePath) {
		if (!"".equals(filePath) && null != filePath) {
			return ConstantUtils.FILE_SERVER_LOCAL_PATH
					+ filePath.substring(filePath.indexOf("upload") + 6,
							filePath.length());
		} else {
			return "";
		}
	}

	/**
	 * 用类型区分上传的路径
	 * 
	 * @param type
	 * @return
	 */
	public static String getFilePath(String type) {
		String resultStr = "";
		if ("C".equals(type)) {// 信贷业务上传附件
			resultStr = GlobalConfigure.CRM_LOAN_BUSINESS_FILE_PATH;
		} else if ("AS".equals(type)) {// 信审审核上传附件
			resultStr = GlobalConfigure.AS_LOAN_BUSINESS_FILE_PATH;
		} else if ("CON".equals(type)) {// 借款标 项目合同 上传图片
			resultStr = GlobalConfigure.BID_CONTRACT_IMGS_PATH;
		} else if ("MAT".equals(type)) {// 借款标 项目资料 上传图片
			resultStr = GlobalConfigure.BID_MATERIAL_IMGS_PATH;
		} else {
			resultStr = GlobalConfigure.CRM_LOAN_BUSINESS_FILE_PATH;
		}
		return resultStr;
	}

	/**
	 * 下载
	 * 
	 * @param path
	 * @param filename
	 * @param response
	 */
	public static void downloadFilename(String path, String filename,
			HttpServletResponse response) {
		InputStream fis = null;
		OutputStream toClient = null;
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 以流的形式下载文件。
			fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.setContentType("application/x-download;charset=GBK");
			response.addHeader(
					"Content-Disposition",
					"attachment;filename="
							+ java.net.URLEncoder.encode(
									filename.replaceAll(" ", ""), "UTF-8"));
			response.addHeader("Content-Length", "" + file.length());
			toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (null != fis) {
					fis.close();
					toClient.close();
				} else {
					logger.info("下载文件不存在。。。");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 导入excel解析
	public static List<Map<String, String>> analysisExcel(
			HttpServletRequest request) {
		List<Map<String, String>> rowCellListMap = null;
		try {
			MultipartFile file = getMultipartFile(request);

			InputStream stream = file.getInputStream();
			// 原文件名称
			String fileName = file.getOriginalFilename();
			if (fileName.endsWith(".xls")) {
				version = version2003;
			} else if (fileName.endsWith(".xlsx")) {
				version = version2007;
			}
			if (version == version2003) {
				wb = new HSSFWorkbook(stream);
			} else if (version == version2007) {
				wb = new XSSFWorkbook(stream);
			}

			sheet = wb.getSheetAt(0);
			// 行数(相当于最后一行的索引),列数
			int count_row = sheet.getLastRowNum(), count_cell = sheet.getRow(0)
					.getPhysicalNumberOfCells();
			rowCellListMap = new ArrayList<>(count_cell);
			for (int i = 0; i < count_row; i++) {
				Map<String, String> cellMap = new HashMap<>();
				for (int j = 0; j < count_cell; j++) {
					row = sheet.getRow(i + 1);
					cell = row.getCell(j);
					if (null != cell) {
						int type = cell.getCellType(); // 得到单元格数据类
						String k = "";
						switch (type) { // 判断数据类型
						case Cell.CELL_TYPE_BLANK:
							k = "";
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							k = cell.getBooleanCellValue() + "";
							break;
						case Cell.CELL_TYPE_ERROR:
							k = cell.getErrorCellValue() + "";
							break;
						case Cell.CELL_TYPE_FORMULA:
							k = cell.getCellFormula();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								k = new DataFormatter().formatRawCellContents(
										cell.getNumericCellValue(), 0,
										"yyyy-mm-dd");// 格式化日期
							} else {
								DecimalFormat df = new DecimalFormat("0");//去除科学计数法
								k = df.format(cell.getNumericCellValue());
							}
							break;
						case Cell.CELL_TYPE_STRING:
							k = cell.getStringCellValue();
							break;
						default:
							break;
						}
						// dataArray[i][j] = k;
						cellMap.put("cell" + j, k);
					}
				}
				rowCellListMap.add(cellMap);
			}
			cell = null;
			row = null;
			sheet = null;
			wb = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rowCellListMap;
	}

	// 根据图片地址下载图片到本服务器
	public static String saveUrlImg(String urlString) {
		Map<String, String> mapDir = getDirPath("dangdangimg");
		String dirPath = mapDir.get("dirPath");// 磁盘存储路径
		String urlPath = mapDir.get("urlPath");

		String filename = System.currentTimeMillis()+getFileExtension(urlString);// 上传文件名称
		try {

			// 构造URL
			URL url = new URL(urlString);

			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			InputStream is = con.getInputStream();

			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;

			// 输出的文件流
			File sf = new File(dirPath);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			OutputStream os = new FileOutputStream(sf.getPath() + "/"+ filename);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			
			//进行图片压缩
			//ThumbnailatorUtil.generateFixedSizeImage(sf.getPath() + "/"+ filename);
			
			// 完毕，关闭所有链接
			os.close();
			is.close();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return urlPath + filename;

	}

}

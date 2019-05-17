package com.winnerbook.base.common;

public class GlobalConfigure {
	
	/** 信贷业务上传路径 **/
	public static final String CRM_LOAN_BUSINESS_FILE_PATH = "crm/loan_business/";
	
	/** 信审审核资料上传路径  **/
	public static final String AS_LOAN_BUSINESS_FILE_PATH = "as/loan_business/";
	
	/** 借款标-项目合同 上传路径 **/
	public static final String BID_CONTRACT_IMGS_PATH = "bid/contract_imgs/";
	
	/** 借款标-项目资料 上传路径 **/
	public static final String BID_MATERIAL_IMGS_PATH = "bid/material_imgs/";

	/** 二维码图片生成路径 **/
	public static final String QRCODEIMAGE_SERVER_LOCAL_PATH = "/resources/qrcode/";
	
	/**  二维码图片扩展名   **/
	public static final String QRCODEIMAGE_EXT = "png";
	

	public static final String SPRING_APPLICATION_CONTEXT_KEY = GlobalConfigure.class
			.getName() + "_SPRING_APPLICATION_CONTEXT_KEY";

	public static final String IMAGE_SERVER_HTTP_PATH = "/fileServer";

	public static final String BASE_PATH = "_BASE_PATH_";

	public static final String IMAGE_REGEX_TYPE = "image/[\\w|-]+";

	public static final int DEFAULT_PAGE_SIZE = 10;

	public static final int MAX_PAGE_SIZE = 100;

	public static final String PAGINATION_SQL_START = "start";

	public static final String PAGINATION_SQL_END = "end";

	public static final String PAGINATION_SQL_LIMIT = "limit";

	public static final String IS_MOBILE_CLIENT = "isMobileClient";

	public static final String KAPTCHA_SESSION_KEY = GlobalConfigure.class
			.getName() + "_kaptcha_session_key_";

	public static final String[] NO_INTERCEPTOR_URL = new String[] { "/mobile/**" };

	public static final String CURRENT_USER = GlobalConfigure.class.getName()
			+ "currentUser";

	public static final String MOBILE_ID_USER = GlobalConfigure.class.getName()
			+ "idUser";;

	public static final Object HTTP_PATH = GlobalConfigure.class.getName()
			+ "_path_";;

	public static final Object HTTP_BASE_PATH = GlobalConfigure.class.getName()
			+ "_basePath_";

	public static final Object HTTP_LOGO_PATH = GlobalConfigure.class.getName()
			+ "_logoPath_";

	public static final String CACHE_OATH = GlobalConfigure.class.getName()
			+ "_cache_oath_";

	public static final String APP_ID = "wx154f160021634cfd";

	public static final String APP_SECRET = "e75b7e6713a8932a3c2c764c7e943cdc";
	
	private static final String JAVA_DATE_FORMATTER = "yyyy-MM-dd";
	/**
     * @Fields CURR_JAVA_DATE_FORMATTER：JAVA_DATE_FORMATTER = "yyyy-MM-dd"
     */
    public static final String CURR_JAVA_DATE_FORMATTER = JAVA_DATE_FORMATTER;
    
    private static final String JAVA_DATETIME_FORMATTER_24 = "yyyy-MM-dd HH:mm:ss";
    
    public static final String CURR_JAVA_DATETIME_FORMATTER = JAVA_DATETIME_FORMATTER_24;
	
	/**
	 * 用户类型:系统录入用户
	 */
	public static final String LOG_IN = "0";
	/**
	 * 用户类型:同步导入用户
	 */
	public static final String SYNC = "1";
	
	/**
	 * 用户状态:正常
	 */
	public static final String STATUS_OK = "0";
	/**
	 * 用户状态:禁用
	 */
	public static final String STATUS_DISABLED = "1";
	
}

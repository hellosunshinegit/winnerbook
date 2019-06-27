package com.winnerbook.course.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.common.util.FileUtils;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.book.dao.BookListTypeDao;
import com.winnerbook.book.dto.BookListType;
import com.winnerbook.course.dao.BookListDao;
import com.winnerbook.course.dao.BookListSearchLogDao;
import com.winnerbook.course.dto.BookList;
import com.winnerbook.course.dto.BookListSearchLog;
import com.winnerbook.course.dto.BookListUserId;
import com.winnerbook.course.service.BookListService;
import com.winnerbook.system.dto.User;

@Service("bookListService")
public class BookListServiceImpl extends BaseServiceImpl implements BookListService{
	
	@Autowired
	private BookListDao bookListDao;
	
	@Autowired
	private BookListSearchLogDao bookListSearchLogDao;
	
	@Autowired
	private BookListTypeDao bookListTypeDao;

	@Override
	public BookList findById(String bookId,String bluId) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(bluId) && !"0".equals(bluId)){
			parameter.put("bluId", bluId);
			Map<String, Object> map_book_user = bookListDao.findByBookListUser(parameter);
			return getBookList(map_book_user);
		}else{
			parameter.put("bookId", Integer.parseInt(bookId));
			return bookListDao.findById(parameter);
		}
	}
	
	
	public BookList getBookList(Map<String, Object> map_book_user){
		//赋值给bookList
		BookList bookList = new BookList();
		bookList.setBluId(Integer.parseInt(map_book_user.get("blu_id").toString()));
		bookList.setBookId(Integer.parseInt(map_book_user.get("book_list_id").toString()));
		bookList.setBookName(null!=map_book_user.get("book_name")?map_book_user.get("book_name").toString():"");
		bookList.setBookSearchName(null!=map_book_user.get("book_search_name")?map_book_user.get("book_search_name").toString():"");
		bookList.setBookAuthor(null!=map_book_user.get("book_author")?map_book_user.get("book_author").toString():"");
		bookList.setBookPublishers(null!=map_book_user.get("book_publishers")?map_book_user.get("book_publishers").toString():"");
		bookList.setBookPublicationDate(null!=map_book_user.get("book_publication_date")?map_book_user.get("book_publication_date").toString():"");
		bookList.setOpenBook(null!=map_book_user.get("open_book")?map_book_user.get("open_book").toString():"");
		bookList.setBookPaper(null!=map_book_user.get("book_paper")?map_book_user.get("book_paper").toString():"");
		bookList.setBookPack(null!=map_book_user.get("book_pack")?map_book_user.get("book_pack").toString():"");
		bookList.setIsSuit(null!=map_book_user.get("is_suit")?map_book_user.get("is_suit").toString():"");
		bookList.setBookIsbn(null!=map_book_user.get("book_isbn")?map_book_user.get("book_isbn").toString():"");
		bookList.setBookClass(null!=map_book_user.get("book_class")?map_book_user.get("book_class").toString():"");
		bookList.setBookImg(null!=map_book_user.get("book_img")?map_book_user.get("book_img").toString():"");
		bookList.setBookUrl(null!=map_book_user.get("book_url")?map_book_user.get("book_url").toString():"");
		bookList.setBookContentDes(null!=map_book_user.get("book_content_des")?map_book_user.get("book_content_des").toString():"");
		bookList.setBookAuthorDes(null!=map_book_user.get("book_author_des")?map_book_user.get("book_author_des").toString():"");
		bookList.setBookStatus(null!=map_book_user.get("book_status")?map_book_user.get("book_status").toString():"");
		return bookList;
	}

	@Override
	public PageDTO<BookList> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<BookList> pageDTO = new PageDTO<BookList>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = bookListDao.selectCount(parameter);
		List<BookList> data = null;
		if (rowSize > 0) {
			data = bookListDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public int insertBook(BookList bookList) {
		bookList.setBookName(bookList.getBookName().trim().replace("》", "").replace("《", ""));
		//添加之间，首先需要判断下系统中有没有书籍名称相同的数据,如果有，则需要insert到中间表
		List<BookList> bookLists = getBookListByName(bookList.getBookName());//得到已经保存的id，然后存入中间表
		if(bookLists.size()>0){//系统中已经存在此书，直接存入中间表
			BookList bookListName = bookLists.get(0);
			logRecord("2","书籍中间表信息添加："+bookList.getBookName());
			return bookListDao.insertBookListUser(getBookListUserId(bookListName, bookList));
		}else{
			return insert(bookList);
		}
	}
	
	
	//返回BookListUserId
	public BookListUserId getBookListUserId(BookList bookListName,BookList bookList){
		User sessionUser = getSessionUser();

		BookListUserId bookListUserId = new BookListUserId();
		bookListUserId.setBookListId(bookListName.getBookId());
		bookListUserId.setUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		bookListUserId.setBookName(bookList.getBookName());
		bookListUserId.setBookSearchName(bookList.getBookSearchName());
		bookListUserId.setBookAuthor(bookList.getBookAuthor());
		bookListUserId.setBookPublishers(bookList.getBookPublishers());
		bookListUserId.setBookPublicationDate(bookList.getBookPublicationDate());
		bookListUserId.setOpenBook(bookList.getOpenBook());
		bookListUserId.setBookPack(bookList.getBookPack());
		bookListUserId.setBookPaper(bookList.getBookPaper());
		bookListUserId.setIsSuit(bookList.getIsSuit());
		bookListUserId.setBookIsbn(bookList.getBookIsbn());
		bookListUserId.setBookClass(bookList.getBookClass());
		
		if(StringUtils.isNotBlank(bookList.getBookImg()) && bookList.getBookImg().indexOf("http")>=0){
			String urlPath = FileUtils.saveUrlImg(bookList.getBookImg());
			bookListUserId.setBookImg(urlPath);
		}else{
			bookListUserId.setBookImg(bookList.getBookImg());
		}
		bookListUserId.setBookUrl(bookList.getBookUrl());
		bookListUserId.setBookContentDes(bookList.getBookContentDes());
		bookListUserId.setBookAuthorDes(bookList.getBookAuthorDes());
		bookListUserId.setBookStatus(bookList.getBookStatus());
		bookListUserId.setCreateDate(new Date());
		bookListUserId.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		bookListUserId.setCreateUserName(sessionUser.getUserUnitName());
		return bookListUserId;
	}
	
	@Override
	public int insert(BookList bookList) {
		bookList.setBookName(bookList.getBookName().trim().replace("》", "").replace("《", ""));
		User sessionUser = getSessionUser();
		bookList.setCreateDate(new Date());
		bookList.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		bookList.setCreateUserName(sessionUser.getUserUnitName());
		if(StringUtils.isNotBlank(bookList.getBookUrl()) && StringUtils.isNotBlank(bookList.getBookImg())){//判断是搜索的图片还是自己上传的图片
			String urlPath = FileUtils.saveUrlImg(bookList.getBookImg());
			bookList.setBookImg(urlPath);
		}
		bookListDao.insert(bookList);
		logRecord("2","书籍信息添加："+bookList.getBookName());
		return bookList.getBookId();
	}

	@Override
	public void update(BookList bookList) {
		List<BookList> bookLists = getBookListByName(bookList.getBookName());//得到已经保存的id，然后存入中间表
		if(bookLists.size()>0){//系统中已经存在此书，直接存入中间表
			BookList bookListName = bookLists.get(0);
			logRecord("2","书籍中间表信息添加："+bookList.getBookName());
			bookListDao.insertBookListUser(getBookListUserId(bookListName, bookList));
			
			//删除原添加的数据  如果getBluId不为空
			//bookListDao.delete()
			
		}else{
			if(null!=bookList.getBluId() && bookList.getBluId()>0){//删除原添加的数据
				bookList.setUpdateDate(new Date());
				if(StringUtils.isNotBlank(bookList.getBookUrl()) && StringUtils.isNotBlank(bookList.getBookImg()) && bookList.getBookImg().indexOf("http")>=0){
					String urlPath = FileUtils.saveUrlImg(bookList.getBookImg());
					bookList.setBookImg(urlPath);
				}
				bookListDao.updateBookListUser(bookList);
				logRecord("3","书籍信息中间表更新，id："+bookList.getBluId());
			}else{
				bookList.setUpdateDate(new Date());
				if(StringUtils.isNotBlank(bookList.getBookUrl()) && StringUtils.isNotBlank(bookList.getBookImg()) && bookList.getBookImg().indexOf("http")>=0){
					String urlPath = FileUtils.saveUrlImg(bookList.getBookImg());
					bookList.setBookImg(urlPath);
				}
				bookListDao.update(bookList);
				logRecord("3","书籍信息更新，id："+bookList.getBookId());
			}
		}
	}

	@Override
	public List<BookList> getBookListAll(Map<String, Object> parameter) {
		return bookListDao.getBookListAll(parameter);
	}

	@Override
	public Map<String, Object> getBooks(Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> bookList = bookListDao.getBooks(parameter);
		map.put("bookList", bookList);
		int bookCount = bookListDao.getBooksCount(parameter);
		map.put("bookCount", bookCount);
		
		BookListType bookListType = new BookListType();
		bookListType.setTypeName("企业全员书单");
		map.put("bookListType", bookListType);
		
		return map;
	}

	@Override
	public String searchBookList(String bookNameJson) {
		String bookName = JSONObject.fromObject(bookNameJson).getString("bookName");
		try {
			//通过搜索的name获取name对应的list
			String searchBookNameUrl = "http://search.dangdang.com/?key="+bookName+"&act=input";
			String bookNamehtmls = Jsoup.connect(searchBookNameUrl).post().html();
			
			HtmlCleaner hc = new HtmlCleaner();
	        TagNode tn = hc.clean(bookNamehtmls);
	        Document dom = new DomSerializer(new CleanerProperties()).createDOM(tn);
	        XPath xPath = XPathFactory.newInstance().newXPath();
			
	        String bookUrl = "//*[@id='search_nature_rg']/ul/li/p[1]/a";
	        List<Map<String, Object>> bookMapList = getHtmlNodeBookList(bookUrl,dom,xPath);
	        
	        
	        String bookPressUrl = "//*[@id='search_nature_rg']/ul/li/p[5]/span[3]";
	        Map<String, Object> bookPressMapList = getHtmlNodeStr(bookPressUrl,dom,xPath,"press");
	        
	        String bookAuthorUrl = "//*[@id='search_nature_rg']/ul/li/p[5]/span[1]/a";
	        Map<String, Object> bookAuthoerMapList = getHtmlNodeStr(bookAuthorUrl,dom,xPath,"author");
	        
	        String contentDesUrl = "//*[@class='detail']";
	        Map<String, Object> contentDesMap = getHtmlNodeStr(contentDesUrl,dom,xPath,"bookContentDes");
	        
	        for(Map<String,Object> map : bookMapList){
	        	map.put("press", null!=bookPressMapList.get("press_"+map.get("liId"))?bookPressMapList.get("press_"+map.get("liId")):""); //出版社
	        	map.put("author", null!=bookAuthoerMapList.get("author_"+map.get("liId"))?bookAuthoerMapList.get("author_"+map.get("liId")):""); //作者
	        	map.put("bookContentDes", null!=contentDesMap.get("bookContentDes_"+map.get("liId"))?contentDesMap.get("bookContentDes_"+map.get("liId")):""); //内容简介
	        }
			
	        return JSONArray.fromObject(bookMapList).toString();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	
	//根据得到的object 获取对应的值
	public Map<String,Object> getHtmlNode(String regular,Document dom,XPath xPath,String attr){
		Map<String, Object> nodeMap = new HashMap<>();
		try {
            Object result = xPath.evaluate(regular, dom, XPathConstants.NODESET);
    		if (result instanceof NodeList) {
    	        NodeList nodeList = (NodeList) result;
    	        Node node = nodeList.item(0);
    	        if(node != null){
    	        	nodeMap.put(attr,node.getTextContent());
    	        }
           }
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return nodeMap;
	}
	
	public List<Map<String, Object>> getHtmlNodeBookList(String regular,Document dom,XPath xPath){
		List<Map<String, Object>> nodeMapList = new ArrayList<>();
		try {
			Object result = xPath.evaluate(regular, dom, XPathConstants.NODESET);
			if (result instanceof NodeList) {
				NodeList nodeList = (NodeList) result;
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if(node == null){
						continue;
					}else{
						Map<String, Object> nodeMap = new HashMap<>();
						NamedNodeMap namedNodeMap = node.getAttributes();
						Node href = namedNodeMap.getNamedItem("href");
						if(null!=href){
							nodeMap.put("href", href.getTextContent());
							nodeMap.put("bookName",node.getTextContent());
							String liId = node.getParentNode().getParentNode().getAttributes().getNamedItem("id").getTextContent();
							nodeMap.put("liId", liId);
							System.out.println(liId);
							
							/*if(nodeMapList.size()>5){
								return nodeMapList;
							}*/
							nodeMapList.add(nodeMap);
						}
					}
				}
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return nodeMapList;
	}
	
	public List<Map<String, Object>> getHtmlNodes(String regular,Document dom,XPath xPath){
		List<Map<String, Object>> nodeMapList = new ArrayList<>();
		try {
			Object result = xPath.evaluate(regular, dom, XPathConstants.NODESET);
			if (result instanceof NodeList) {
				NodeList nodeList = (NodeList) result;
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					Map<String,Object> pressMap = new HashMap<String, Object>();
					pressMap.put("li"+i, null!=node?node.getTextContent():"");
					nodeMapList.add(pressMap);
				}
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return nodeMapList;
	}
	public Map<String, Object> getHtmlNodeStr(String regular,Document dom,XPath xPath,String str){
		Map<String, Object> nodeMap = new HashMap<>();
		try {
			Object result = xPath.evaluate(regular, dom, XPathConstants.NODESET);
			if (result instanceof NodeList) {
				NodeList nodeList = (NodeList) result;
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					String press = null!=node?node.getTextContent():"";
					String liId = "";
					if("author".equals(str)){
						if(null!=node.getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("id")){
							liId = node.getParentNode().getParentNode().getParentNode().getAttributes().getNamedItem("id").getTextContent();
						}
					}else if("bookContentDes".equals(str)){
						if(null!=node.getParentNode().getAttributes().getNamedItem("id")){
							liId = node.getParentNode().getAttributes().getNamedItem("id").getTextContent();
						}
					}else{
						if(null!=node.getParentNode().getParentNode().getAttributes().getNamedItem("id")){
							liId = node.getParentNode().getParentNode().getAttributes().getNamedItem("id").getTextContent();
						}
					}
					nodeMap.put(str+"_"+liId,press);
				}
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return nodeMap;
	}

	@Override
	public String searchBookNameUrl(String bookNameUrlJson) {
		String bookNameUrl = JSONObject.fromObject(bookNameUrlJson).getString("bookNameUrl");
		String bookName = JSONObject.fromObject(bookNameUrlJson).getString("bookName");
		String bookTitle = JSONObject.fromObject(bookNameUrlJson).getString("bookTitle");
		Map<String, Object> mapInfo = new HashMap<>();
		mapInfo.put("searchBookName", bookName);
		mapInfo.put("searchBookTitle", bookTitle);
		try {
			
			//String url = "http://product.dangdang.com/26445445.html";
			mapInfo.put("bookUrl", bookNameUrl);
			String html = Jsoup.connect(bookNameUrl).post().html();  //有的乱码问题待解决     URL url = new URL("http://www.sojson.com");   doc = Jsoup.parse(new URL(url).openStream(), "GBK", url);
			
			HtmlCleaner hc = new HtmlCleaner();
	        TagNode tn = hc.clean(html);
	        Document dom = new DomSerializer(new CleanerProperties()).createDOM(tn);
	        XPath xPath = XPathFactory.newInstance().newXPath();
	        
	        //先判断是否是电子书，如果是电子书，则直接返回
	        String isEBookRegular = "//*[@class='bookname_box']/span[2]";
	        Map<String,Object> isEBookMap = getHtmlNode(isEBookRegular,dom,xPath, "isEBook");
	        if(!isEBookMap.isEmpty() && isEBookMap.get("isEBook").toString().indexOf("电子书")>=0){
	        	mapInfo.put("code", "-2");//属于电子书
				return JSONObject.fromObject(mapInfo).toString();
	        }
	        
	        
	        //先获取图片
	        String imgRegular = "//*[@id='main-img-slider']/li[1]/a/@data-imghref";
	        Map<String,Object> imgMap = getHtmlNode(imgRegular,dom,xPath,"imgsrc");
	        String imgStr = !imgMap.isEmpty()?imgMap.get("imgsrc").toString():"";
	        mapInfo.put("bookImg", imgStr);
	        
	         //作者
	        String authorRegular = "//*[@dd_name='作者']";
			Map<String,Object> authorMap = getHtmlNode(authorRegular,dom,xPath, "author");
			String author = !authorMap.isEmpty()?authorMap.get("author").toString():"";
			if(author.length()==0){//如果作者也没有，则信息不完整
				mapInfo.put("code", "-1");
				return JSONObject.fromObject(mapInfo).toString();
			}
			
			mapInfo.put("bookAuthor", StringUtils.isNotBlank(author)?author.split(":").length>1?author.split(":")[1]:"":"");
			
			//出版社
			String publisherRegular = "//*[@dd_name='出版社']";
			Map<String,Object> publisherMap = getHtmlNode(publisherRegular,dom,xPath,"publisher");
			String publisher = !publisherMap.isEmpty()?publisherMap.get("publisher").toString():"";
			mapInfo.put("bookPublishers",StringUtils.isNotBlank(publisher)?publisher.split(":").length>1?publisher.split(":")[1]:"":"");
			
			//出版时间
			String publisherTimeRegular = "//*[@class='messbox_info']/span[3]";
			Map<String,Object> publisherTimeMap = getHtmlNode(publisherTimeRegular,dom,xPath,"publisherTime");
			String publisherTime = !publisherTimeMap.isEmpty()?publisherTimeMap.get("publisherTime").toString():"";
			String publisherTimeStr = publisherTime.length()>5?publisherTime.split(":")[1].toString().replace("?", ""):"";
			mapInfo.put("bookPublicationDate", publisherTimeStr);
			
			/**[{li0=开 本：128开}, {li1=纸 张：胶版纸}, {li2=包 装：平装-胶订}, {li3=是否套装：是}, {li4=国际标准书号ISBN：9787559426239}, {li5=所属分类：图书&gt;小说&gt;中国近现代小说}]
			 *  开 本：128开
				纸 张：胶版纸
				包 装：平装-胶订
				是否套装：是
				国际标准书号ISBN：9787559426239
				所属分类：图书&gt;小说&gt;中国近现代小说
			 */
			//国际标准书号ISBN：9787531740483
			String ISBNRegular = "//*[@id='detail_describe']/ul/li";
			List<Map<String,Object>> listMap = getHtmlNodes(ISBNRegular,dom,xPath);
			if(null!=listMap && listMap.size()>0){
				String openBook = listMap.size()>0?listMap.get(0).get("li0").toString():"";
				String bookPaper = listMap.size()>1?listMap.get(1).get("li1").toString():"";
				String bookPack = listMap.size()>2?listMap.get(2).get("li2").toString():"";
				String isSuit = listMap.size()>3?listMap.get(3).get("li3").toString():"";
				String bookIsbn = listMap.size()>4?listMap.get(4).get("li4").toString():"";
				String bookClass = listMap.size()>5?listMap.get(5).get("li5").toString():"";
				
				mapInfo.put("openBook", openBook.length()>0 && openBook.split("：").length>1?openBook.split("：")[1]:"");
				mapInfo.put("bookPaper", bookPaper.length()>0 && bookPaper.split("：").length>1?bookPaper.split("：")[1]:"");
				mapInfo.put("bookPack", bookPack.length()>0 && bookPack.split("：").length>1?bookPack.split("：")[1]:"");
				mapInfo.put("isSuit", isSuit.length()>0 && isSuit.split("：").length>1?isSuit.split("：")[1]:"");
				mapInfo.put("bookIsbn", bookIsbn.length()>0 && bookIsbn.split("：").length>1?bookIsbn.split("：")[1]:"");
				mapInfo.put("bookClass", bookClass.length()>0 && bookClass.split("：").length>1?bookClass.split("：")[1].toString().replace("&gt;", "，"):"");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mapInfo.put("code", "200");
		
		//记录点击后的数据
		insertSearchLog(mapInfo);
		
		return JSONObject.fromObject(mapInfo).toString();
	}
	
	
	public int insertSearchLog(Map<String,Object> mapInfo){
		User sessionUser = getSessionUser();
		
		BookListSearchLog bookListSearchLog = new BookListSearchLog();
		bookListSearchLog.setBookName(null!=mapInfo.get("searchBookName")?mapInfo.get("searchBookName").toString():"");
		bookListSearchLog.setBookTitle(null!=mapInfo.get("searchBookTitle")?mapInfo.get("searchBookTitle").toString():"");
		bookListSearchLog.setBookAuthor(mapInfo.get("bookAuthor").toString());
		bookListSearchLog.setBookPublishers(mapInfo.get("bookPublishers").toString());
		bookListSearchLog.setBookPublicationDate(mapInfo.get("bookPublicationDate").toString());
		bookListSearchLog.setOpenBook(null!=mapInfo.get("openBook")?mapInfo.get("openBook").toString():"");
		bookListSearchLog.setBookPack(null!=mapInfo.get("bookPack")?mapInfo.get("bookPack").toString():"");
		bookListSearchLog.setBookPaper(null!=mapInfo.get("bookPaper")?mapInfo.get("bookPaper").toString():"");
		bookListSearchLog.setIsSuit(null!=mapInfo.get("isSuit")?mapInfo.get("isSuit").toString():"");
		bookListSearchLog.setBookIsbn(null!=mapInfo.get("bookIsbn")?mapInfo.get("bookIsbn").toString():"");
		bookListSearchLog.setBookClass(null!=mapInfo.get("bookClass")?mapInfo.get("bookClass").toString():"");
		bookListSearchLog.setBookImg(mapInfo.get("bookImg").toString());
		bookListSearchLog.setBookUrl(null!=mapInfo.get("bookUrl")?mapInfo.get("bookUrl").toString():"");
		bookListSearchLog.setCreateDate(new Date());
		bookListSearchLog.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		bookListSearchLog.setCreateUserName(sessionUser.getUserUnitName());
		
		return bookListSearchLogDao.insert(bookListSearchLog);
	}

	@Override
	public Map<String, Object> getBookDetail(Map<String, Object> parameter) {
		if(StringUtils.isNotBlank(parameter.get("bluId").toString()) && Integer.parseInt(parameter.get("bluId").toString())>0){
			//读取中间表
			return bookListDao.getBookDetailUser(parameter);
		}else{
			return bookListDao.getBookDetail(parameter);
		}
	}

	@Override
	public List<BookList> getBookListByName(String bookName) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("bookName", bookName);
		return bookListDao.getBookListByName(parameter);
	}

	@Override
	public List<BookList> getBookListByNameLogin(String bookNameJson) {
		JSONObject jsonObject = JSONObject.fromObject(bookNameJson);
		String bookName = jsonObject.getString("bookName");
		String bookId = jsonObject.getString("bookId");
		
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("bookId", bookId);
		parameter.put("bookName", bookName);
		parameter.put("sessionUser", getSessionUser());
		return bookListDao.getBookListByNameLogin(parameter);
	}

	@Override
	public Map<String, Object> getLabelBookLists(Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> bookList = bookListDao.getLabelBookLists(parameter);
		int bookListCount = bookListDao.getLabelBookListsCount(parameter);
		map.put("bookList", bookList);
		map.put("bookListCount", bookListCount);
		
		//查询type的name
		Map<String, Object> mapBookListType = new HashMap<>();
		mapBookListType.put("id", Integer.parseInt(parameter.get("typeId").toString()));
		BookListType bookListType = bookListTypeDao.findById(mapBookListType);
		map.put("bookListType", bookListType);
		
		return map;
	}

	public int searchBookNameInsert(String bookName){
		//调用当当网去查下默认获取第一条数据，先插入到书籍表中返回的id放入bookListTypeId中
		Map<String, Object> mapName = new HashMap<>();
		mapName.put("bookName", bookName);
		String searchName = searchBookList(JSONObject.fromObject(mapName).toString());
		if(StringUtils.isNotBlank(searchName)){
			JSONObject object = JSONObject.fromObject(JSONArray.fromObject(searchName).get(0));
			//{"author":"罗贯中","press":" /民主与建设出版社","liId":"p26487696","href":"http://product.dangdang.com/26487696.html","bookName":" 三国演义（上下册）【四大名著普及读本・2019年精校精注全新版】中国首部长篇历史小说，依据正史《三国志》《晋书》《资治通鉴》等订正百余处错误，增加注释两万余字，附三国重要事件时间轴长卷彩页和人物关系图"}
			object.put("bookNameUrl", object.get("href"));
			object.put("bookTitle", object.get("bookName"));
			object.put("bookName",bookName);
			
			JSONObject object2 = JSONObject.fromObject(searchBookNameUrl(object.toString()));
			if(null!=object2){
				object2.put("bookContentDes", object.get("bookContentDes"));
				return insertBookList(object2);
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}

	
	public int insertBookList(JSONObject object){
		User sessionUser = getSessionUser();
		BookList bookList = new BookList();
		bookList.setBookName(null!=object.get("searchBookName")?object.get("searchBookName").toString():"");
		bookList.setBookSearchName(null!=object.get("searchBookTitle")?object.get("searchBookTitle").toString():"");
		bookList.setBookAuthor(null!=object.get("bookAuthor")?object.get("bookAuthor").toString():"");
		bookList.setBookPublishers(null!=object.get("bookPublishers")?object.get("bookPublishers").toString():"");
		bookList.setBookPublicationDate(null!=object.get("bookPublicationDate")?object.get("bookPublicationDate").toString():"");
		bookList.setOpenBook(null!=object.get("openBook")?object.get("openBook").toString():"");
		bookList.setBookPack(null!=object.get("bookPack")?object.get("bookPack").toString():"");
		bookList.setBookPaper(null!=object.get("bookPaper")?object.get("bookPaper").toString():"");
		bookList.setIsSuit(null!=object.get("isSuit")?object.get("isSuit").toString():"");
		bookList.setBookIsbn(null!=object.get("bookIsbn")?object.get("bookIsbn").toString():"");
		bookList.setBookClass(null!=object.get("bookClass")?object.get("bookClass").toString():"");
		bookList.setBookImg(object.get("bookImg").toString());
		bookList.setBookUrl(null!=object.get("bookUrl")?object.get("bookUrl").toString():"");
		bookList.setBookContentDes(null!=object.get("bookContentDes")?object.get("bookContentDes").toString():"");
		bookList.setCreateDate(new Date());
		bookList.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		bookList.setCreateUserName(sessionUser.getUserUnitName());
		bookList.setSource("2");//添加书单时添加书籍
		
		return insert(bookList);
	}
}

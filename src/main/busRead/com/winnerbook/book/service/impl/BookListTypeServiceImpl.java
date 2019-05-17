package com.winnerbook.book.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctc.wstx.util.StringUtil;
import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.book.dao.BookListTypeDao;
import com.winnerbook.book.dto.BookListLabelId;
import com.winnerbook.book.dto.BookListType;
import com.winnerbook.book.dto.BookListTypeId;
import com.winnerbook.book.service.BookListTypeService;
import com.winnerbook.course.dao.BookListDao;
import com.winnerbook.course.dto.BookList;
import com.winnerbook.course.service.BookListService;
import com.winnerbook.system.dto.User;

@Service("bookListTypeService")
public class BookListTypeServiceImpl extends BaseServiceImpl implements BookListTypeService{
	
	@Autowired
	private BookListTypeDao bookListTypeDao;
	
	@Autowired
	private BookListDao bookListDao;
	
	@Autowired
	private BookListService bookListService;
	
	@Override
	public BookListType findById(String id) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("id", Integer.parseInt(id));
		return bookListTypeDao.findById(parameter);
	}

	@Override
	public PageDTO<BookListType> listByPage(Map<String, Object> parameter,Integer pageIndex, Integer pageSize) {
		PageDTO<BookListType> pageDTO = new PageDTO<BookListType>(pageIndex, pageSize);
		parameter.put(GlobalConfigure.PAGINATION_SQL_START, pageDTO.getFirst());
		parameter.put(GlobalConfigure.PAGINATION_SQL_LIMIT, pageDTO.getPageSize());
		long rowSize = bookListTypeDao.selectCount(parameter);
		List<BookListType> data = null;
		if (rowSize > 0) {
			data = bookListTypeDao.listByPage(parameter);
		}
		pageDTO.setRowSize(rowSize);
		pageDTO.setData(data);
		return pageDTO;
	}
	
	@Override
	public Map<String, Object> insert(String dataJson) {
		User sessionUser = getSessionUser();
		//解析
		JSONObject jsonObject = JSONObject.fromObject(dataJson);
		String typeName = jsonObject.getString("typeName");
		String typeDes = jsonObject.getString("typeDes");
		String status = jsonObject.getString("status");
		String bookTags = jsonObject.getString("bookTags");
		String typeImg = jsonObject.getString("typeImg");
		
		BookListType bookListType = new BookListType();
		bookListType.setTypeName(typeName);
		bookListType.setTypeLabel(bookTags);
		bookListType.setTypeDes(typeDes);
		bookListType.setStatus(status);
		bookListType.setTypeImg(typeImg);
		bookListType.setCreateDate(new Date());
		bookListType.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		bookListType.setCreateUserName(sessionUser.getUserUnitName());
		bookListTypeDao.insert(bookListType);
		
		//添加书籍和标签
		Map<String, Object> map = insertBookLabel(jsonObject, bookListType.getId()); //返回的是信息不完整的书籍
		
		logRecord("2","书单信息添加，id："+bookListType.getId());
		return map;
	}

	
	public List<BookListLabelId> insertBookListLabelId(Integer typeId,String[] bookTagIdArray){
		List<BookListLabelId> bookListLabelIds = new LinkedList<>();
		for(String tags:bookTagIdArray){
			BookListLabelId bookListLabelId = new BookListLabelId();
			bookListLabelId.setTypeId(typeId);
			bookListLabelId.setLabelId(Integer.parseInt(tags));
			bookListLabelId.setCreateDate(new Date());
			bookListLabelIds.add(bookListLabelId);
		}
		return bookListLabelIds;
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
		bookList.setBookImg(null!=object.get("bookImg")?object.get("bookImg").toString():"");
		bookList.setBookUrl(null!=object.get("bookUrl")?object.get("bookUrl").toString():"");
		bookList.setBookContentDes(null!=object.get("bookContentDes")?object.get("bookContentDes").toString():"");
		bookList.setCreateDate(new Date());
		bookList.setCreateUserId(Integer.parseInt(sessionUser.getUserId().toString()));
		bookList.setCreateUserName(sessionUser.getUserUnitName());
		bookList.setSource("2");//添加书单时添加书籍
		
		return bookListService.insert(bookList);
	}

	@Override
	public Map<String, Object> update(String dataJson) {
		//解析json数据
		JSONObject jsonObject = JSONObject.fromObject(dataJson);
		Integer typeId = jsonObject.getInt("id");
		String typeName = jsonObject.getString("typeName");
		String typeDes = jsonObject.getString("typeDes");
		String status = jsonObject.getString("status");
		String bookTags = jsonObject.getString("bookTags");
		String typeImg = jsonObject.getString("typeImg");
		
		BookListType bookListType = new BookListType();
		bookListType.setId(typeId);
		bookListType.setTypeName(typeName);
		bookListType.setTypeLabel(bookTags);
		bookListType.setTypeDes(typeDes);
		bookListType.setStatus(status);
		bookListType.setTypeImg(typeImg);
		bookListType.setUpdateDate(new Date());
		bookListTypeDao.update(bookListType);
		
		//删除书籍和标签表
		if(StringUtils.isNotBlank(bookTags)){
			bookListTypeDao.deleteLabelByTypeId(typeId);
		}
		//删除书籍
		bookListTypeDao.deleteBookListByTypeId(typeId);
		
		//添加书籍和标签
		Map<String, Object> map = insertBookLabel(jsonObject, typeId);
		
		logRecord("3","书单信息更新，id："+bookListType.getId());
		
		return map;
	}

	//封装添加方法
	public Map<String, Object> insertBookLabel(JSONObject jsonObject,Integer typeId){
		Map<String, Object> map_result = new HashMap<>();
		
		List<Map<String, Object>> infoDeficiencyList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> noDataList = new ArrayList<Map<String, Object>>();
		
		JSONArray jsonArray = jsonObject.getJSONArray("bookNameList");
		//查询数据库中是否存在这个书名，如果存在，返回id，如果不存在，需要获取当当网的信息，然后默认取第一条，存入书籍数据库
		//根据名字去查询
		List<BookListTypeId> bookListTypeIds = new LinkedList<>();
		for(int i = 0;i<jsonArray.size();i++){
			BookListTypeId bookListTypeId = new BookListTypeId();
			
			String bookName = jsonArray.get(i).toString();
			if(!StringUtils.isNotBlank(bookName)){
				continue;
			}
			if(bookName.length()>0 && bookName.indexOf("》")>0){
				bookName = bookName.trim().replace("》", "").replace("《", "");
			}
			Map<String,Object> mapBookName = new HashMap<>();
			mapBookName.put("bookName", bookName);
			List<BookList> bookLists = bookListDao.getBookListByName(mapBookName);
			if(null!=bookLists && bookLists.size()>0){
				BookList bookList = bookLists.get(0);
				bookListTypeId.setTypeId(typeId);
				bookListTypeId.setBookListId(bookList.getBookId());
				bookListTypeId.setCreateDate(new Date());
				bookListTypeIds.add(bookListTypeId);
			}else{
				//调用当当网去查下默认获取第一条数据，先插入到书籍表中返回的id放入bookListTypeId中
				Map<String, Object> mapName = new HashMap<>();
				mapName.put("bookName", bookName);
				
				String searchList = bookListService.searchBookList(JSONObject.fromObject(mapName).toString());
				if(StringUtils.isNotBlank(searchList) && !searchList.equals("[]")){
					JSONObject object = JSONObject.fromObject(JSONArray.fromObject(searchList).get(0));
					
					//{"author":"罗贯中","press":" /民主与建设出版社","liId":"p26487696","href":"http://product.dangdang.com/26487696.html","bookName":" 三国演义（上下册）【四大名著普及读本・2019年精校精注全新版】中国首部长篇历史小说，依据正史《三国志》《晋书》《资治通鉴》等订正百余处错误，增加注释两万余字，附三国重要事件时间轴长卷彩页和人物关系图"}
					if(null!=object){
						object.put("bookNameUrl", object.get("href"));
						object.put("bookTitle", object.get("bookName"));
						object.put("bookName", bookName);
						
						JSONObject object2 = JSONObject.fromObject(bookListService.searchBookNameUrl(object.toString()));
						if(null!=object2){
							object2.put("bookContentDes", object.get("bookContentDes"));
							
							//向书籍表中放入数据
							Integer bookId = insertBookList(object2);
							bookListTypeId.setTypeId(typeId);
							bookListTypeId.setBookListId(bookId);
							bookListTypeId.setCreateDate(new Date());
							bookListTypeIds.add(bookListTypeId);
							
							if(!object2.getString("code").equals("200")){//可能是电子书，也可能是没有作者等信息，导致信息不全，需要提示前端客户，去书籍中补全数据
								Map<String, Object> map = new HashMap<>();
								map.put("bookName", bookName);
								infoDeficiencyList.add(map);
							}
						}
					}
				}else{
					Map<String, Object> map = new HashMap<>();
					map.put("bookName", bookName);
					noDataList.add(map);
				}
			}
		}
		
		if(bookListTypeIds.size()>0){
			//存入书籍和书单中间表
			bookListTypeDao.insertBathBookListTypeId(bookListTypeIds);
		}
		
		//存入tags
		String bookTagIds = jsonObject.getString("bookTagIds");
		if(StringUtils.isNotBlank(bookTagIds)){
			String[] bookTagIdArray = bookTagIds.split(",");
			bookListTypeDao.insertBookListLabelId(insertBookListLabelId(typeId,bookTagIdArray));
		}
		
		map_result.put("infoDeficiencyList", infoDeficiencyList);//不完整数据添加
		map_result.put("noDataList", noDataList);//没有搜索结果数据添加
		return map_result;
	}
	
	
	@Override
	public List<Map<String, Object>> getBookListByTypeId(String id) {
		Map<String, Object> parameter  = new HashMap<String, Object>();
		parameter.put("typeId", Integer.parseInt(id));
		return bookListTypeDao.getBookListByTypeId(parameter);
	}

	@Override
	public Map<String, Object> getBookListTypes(Map<String, Object> parameter) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> bookListType = bookListTypeDao.getBookListTypes(parameter);
		int bookListCount = bookListTypeDao.getBookListTypesCount(parameter);
		map.put("bookListType", bookListType);
		map.put("bookListCount", bookListCount);
		return map;
	}

}

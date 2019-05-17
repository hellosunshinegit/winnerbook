package com.winnerbook.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.winnerbook.base.common.PageDTO;
import com.winnerbook.base.frame.controller.BaseController;
import com.winnerbook.web.dto.Block;
import com.winnerbook.web.service.BlockService;

/**
 * 版块信息
 * @author hxs
 */
@Controller
@RequestMapping(value="/blockController")
public class BlockController extends BaseController{
	
	@Autowired
	private BlockService blockService;
	
	private static final Logger logger = LoggerFactory.getLogger(BlockController.class);
	
	//查询列表
	@RequestMapping(value="/blockList.html")
	public String blockList(HttpServletRequest request,Model model, Integer pageIndex,Integer pageSize){
		logger.info("Block列表.....");
		String blockName =request.getParameter("blockName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blockName", blockName);
		map.put("sessionUser",getSessionUser());
		PageDTO<Block> pageDTO  = blockService.listByPage(map, pageIndex,10);//默认每页显示10条数据
 
		model.addAttribute("pageDTO", pageDTO);
		model.addAllAttributes(map);
		return "manage/busRead/web/block/blockList";
	}
	
	//点击添加跳转
	@RequestMapping(value="addBlock.html")
	public String addArticle(ModelMap modelMap){
		return "manage/busRead/web/block/editBlock";
	}
	
	//添加提交
	@RequestMapping(value="addSubmitBlock.html")
	public String addSubmitArticle(Block Block){
		blockService.insert(Block);
		return "redirect:/blockController/blockList.html";
	}
	
	//点击修改
	@RequestMapping(value="updateBlock.html")
	public String updateCourseType(@RequestParam String blockId,ModelMap modelMap){
		modelMap.addAttribute("block",blockService.findById(blockId));
		return "manage/busRead/web/block/editBlock";
	}
	
	//修改提交
	@RequestMapping(value="updateSubmitBlock.html")
	public String updateSubmitBlock(Block block){
		blockService.update(block);
		return "redirect:/blockController/blockList.html";
	}
	
}

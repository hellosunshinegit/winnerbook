package com.winnerbook.base.frame.service;

import com.winnerbook.system.dto.User;


public interface BaseService {
	/**
	 * 日志记录
	 * @param type  类型  1 登录  2添加  3 更新   4 删除
	 * @param message  内容
	 */
	public void logRecord(String type,String message);
	/**
	 * 获取userid
	 * @return
	 */
	public User getSessionUser();
}

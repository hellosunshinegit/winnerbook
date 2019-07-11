/** 
* 2016-2-15
* UserTestDaoImpl.java 
* author:hxs
*/ 
package com.winnerbook.system.dao.impl; 

import java.util.List;
import org.springframework.stereotype.Repository;
import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.system.dao.UserTransferLogDao;
import com.winnerbook.system.dto.UserTransferLog;

@Repository("UserTransferLogDao")
public class UserTransferLogDaoImpl extends BaseDAO implements UserTransferLogDao{
	private static final String USERTRANSFERLOG_MAPPER = "UserMapper.";

	@Override
	public List<UserTransferLog> selectLogByUserId(String userId) {
		return this.sqlSession.selectList(USERTRANSFERLOG_MAPPER+"selectLogByUserId",userId);
	}

	@Override
	public int insert(UserTransferLog userTransferLog) {
		return this.sqlSession.insert(USERTRANSFERLOG_MAPPER+"insert",userTransferLog);
	}
	
}

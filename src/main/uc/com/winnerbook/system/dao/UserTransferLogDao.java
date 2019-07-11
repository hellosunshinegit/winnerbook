package com.winnerbook.system.dao; 

import java.util.List;
import com.winnerbook.system.dto.UserTransferLog;

public interface UserTransferLogDao {
	
	List<UserTransferLog> selectLogByUserId(String userId);
	
	int insert(UserTransferLog userTransferLog);
}

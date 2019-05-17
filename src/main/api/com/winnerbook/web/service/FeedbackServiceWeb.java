package com.winnerbook.web.service;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.winnerbook.activity.dao.FeedbackDao;
import com.winnerbook.activity.dto.Feedback;
import com.winnerbook.base.frame.service.impl.WebBaseServiceImpl;
import com.winnerbook.system.dto.User;

@Service("feedbackServiceWeb")
public class FeedbackServiceWeb extends WebBaseServiceImpl{
	
	@Autowired
	private FeedbackDao feedbackDao;
	
	public int insertFeedback(Map<String,Object> map){
		
		String userId = null!=map.get("userId")?map.get("userId").toString():"";
		User user = getLoginUser(userId);
		
		Feedback feedback = new Feedback();
		feedback.setUserId(null!=map.get("userId")?Integer.parseInt(map.get("userId").toString()):0);
		feedback.setRemarks(null!=map.get("remarks")?map.get("remarks").toString():"");
		feedback.setStatus("1");
		feedback.setCreateDate(new Date());
		feedback.setCreateUserName(user.getUserUnitName());
		feedback.setCreateUserId(Integer.parseInt(userId));
		
		return feedbackDao.insertFeedBack(feedback);
		
	}
}

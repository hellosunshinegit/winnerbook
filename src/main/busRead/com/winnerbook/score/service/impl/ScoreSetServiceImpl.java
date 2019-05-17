package com.winnerbook.score.service.impl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.winnerbook.base.frame.service.impl.BaseServiceImpl;
import com.winnerbook.score.dao.ScoreSetDao;
import com.winnerbook.score.service.ScoreSetService;

@Service("scoreSetService")
public class ScoreSetServiceImpl extends BaseServiceImpl implements ScoreSetService{
	
	@Autowired
	private ScoreSetDao scoreSetDao;

	@Override
	public Map<String, Object> findByKeyName(String keyName) {
		Map<String, Object> map = new HashMap<>();
		map.put("keyName", keyName);
		return scoreSetDao.findByKeyName(map);
	}


	
}

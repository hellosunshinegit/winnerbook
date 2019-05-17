package com.winnerbook.score.dao;

import java.util.Map;

public interface ScoreSetDao {
    
    Map<String,Object> findByKeyName(Map<String, Object> map);
    
}
package com.distribution.dao.dividendHistory.mapper.more;

import java.util.Map;

import com.distribution.dao.dividendHistory.mapper.DividendHistoryMapper;


public interface MoreDividendHistoryMapper extends DividendHistoryMapper{
	int insertDividendHistoryBatch(Map<String,Object> map);
	int updateDividendHistoryBatch(Map<String,Object> map);
	
}
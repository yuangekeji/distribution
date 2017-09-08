package com.distribution.dao.dividendHistory.mapper.more;

import java.util.List;
import java.util.Map;

import com.distribution.dao.dividendHistory.mapper.DividendHistoryMapper;
import com.distribution.dao.dividendHistory.model.more.MoreDividendHistory;


public interface MoreDividendHistoryMapper extends DividendHistoryMapper{
	int insertDividendHistoryBatch(Map<String,Object> map);
	int updateAllYesterdayDividendHistory(String yesterday);
	List<MoreDividendHistory> listAllYesterdayDividendHistory(String yesterday);
	
}
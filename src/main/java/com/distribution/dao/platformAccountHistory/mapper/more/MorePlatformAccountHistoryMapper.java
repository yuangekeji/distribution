package com.distribution.dao.platformAccountHistory.mapper.more;

import java.util.List;
import java.util.Map;

import com.distribution.dao.platformAccountHistory.mapper.PlatformAccountHistoryMapper;
import com.distribution.dao.platformAccountHistory.model.PlatformAccountHistory;

public interface MorePlatformAccountHistoryMapper extends PlatformAccountHistoryMapper{
    
    List<PlatformAccountHistory> listPlatformAccountHistory(Map<String,Object> param);

}
package com.distribution.service;

import com.distribution.common.constant.BonusConstant;
import com.distribution.common.utils.Page;
import com.distribution.dao.dividend.mapper.more.MoreDividendMapper;
import com.distribution.dao.memberBonus.mapper.more.MoreMemberBonusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lijingx on 8/28/2017.
 */

@Service
public class DividendService {

    @Autowired
    private MoreDividendMapper dividendMapper;

    @Autowired
    private MoreMemberBonusMapper memberBonusMapper;



    /**
     * description 分紅包列表
     * @author jingxin
     * */
    public Page dividendList(Page page){

        page.setTotalCount(dividendMapper.getDividendListCount(page));
        page.setResult( dividendMapper.getDividendList(page));
        return page;
    }

    /**
     * 查詢分紅包領取明細
     * @param page
     * @return
     */
    public Page dividendDetails(Page page){

        page.setTotalCount(memberBonusMapper.getDividendDetailsCount(page));
        page.setResult( memberBonusMapper.getDividendDetails(page));
        return page;
    }

    public Map memberDividendCount(Integer memberId){
        Map map = new HashMap();

        List<Map> dividendCount = dividendMapper.memberDividendCount(memberId);

        for(Map dc : dividendCount){

            if("1".equals(dc.get("dividendStatus"))){
                map.put("validDividendCount",dc.get("dividendCount"));
                break;
            }else if("2".equals(dc.get("dividendStatus"))){
                map.put("inValidDividendCount",dc.get("dividendCount"));
                break;
            }

        }
        BigDecimal validDividendCount    = map.containsKey("validDividendCount")   ?  (BigDecimal)map.get("validDividendCount") :   new BigDecimal(0);
        BigDecimal inValidDividendCount  = map.containsKey("inValidDividendCount") ?  (BigDecimal) map.get("inValidDividendCount") : new BigDecimal(0);

        map.put("validDividendCount", validDividendCount );
        map.put("inValidDividendCount",inValidDividendCount);
        map.put("totalDividendCount", validDividendCount.add(inValidDividendCount) );
        return map;
    }
}

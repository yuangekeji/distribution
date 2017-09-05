package com.distribution.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.common.constant.BonusConstant;
import com.distribution.dao.basicManage.mapper.more.MoreBasicManageMapper;
import com.distribution.dao.basicManage.model.BasicManage;
import com.distribution.dao.dictionary.mapper.more.MoreDictionaryMapper;
import com.distribution.dao.dictionary.model.Dictionary;

/**
 * description 共同方法Service
 * @author Bright
 * */
@Service
public class CommonService {

    @Autowired
    private MoreDictionaryMapper moreDictionaryMapper;
    
    @Autowired
	private MoreBasicManageMapper moreBasicManageMapper;
    
    private static List<BasicManage> list = null;
    /**
     * description 查询字典表方法
     * @author Bright
     * @param dicType 字典所属模块
     * */
    public List<Dictionary> selectDictionary(String dicType){
        return moreDictionaryMapper.getDictionary(dicType);
    }
    
    /**
     * 初始化参数配置数据
     * Description: 
     * @author su
     */
    public void initBasicManageList(){
    	if(null == list){
    		list = moreBasicManageMapper.listAll();
    	}
    }
    /**
     * 刷新参数配置数据
     * Description: 
     * @author su
     */
    public void refreshBasicManageList(){
    	   list = moreBasicManageMapper.listAll();
    }
    /**
     * 相乘保留两位小数，四舍五入。
     * @date 2017年9月5日 上午10:52:38
     * @param v1
     * @param v2
     * @author su
     * @return
     */
  	public double multiply(double v1,double v2){
  		BigDecimal a1 = new BigDecimal(v1);
  		BigDecimal b1 = new BigDecimal(v2);
  		BigDecimal result = a1.multiply(b1);
  		return result.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
  	}
  	/**
  	 * 获取MaxPercent奖比例
  	 * @date 2017年9月5日 上午10:53:04
  	 * @param typeCode
  	 * @param detailCode
  	 * @author su
  	 * @return
  	 */
  	public double getMaxPercent(String typeCode,String detailCode){
  		BigDecimal big = null;
  		if(list == null){
  			refreshBasicManageList();
  		}
  		for(BasicManage basic : list){
  			if(basic.getTypeCode().equals(typeCode) && basic.getDetailCode().equals(detailCode)){
  				big = basic.getMaxPercent();
  				break;
  			}
  		}
  		return big.doubleValue()/100;
  	}
  	/**
  	 * 获取销售奖比例
  	 * @param typeCode
  	 * @param detailCode
  	 * @author su
  	 * @return
  	 */
  	public double getSalesBonusPercent(String typeCode,String detailCode,String per){
  		BigDecimal big = null;
  		if(list == null){
  			refreshBasicManageList();
  		}
  		for(BasicManage basic : list){
  			if(basic.getTypeCode().equals(typeCode) && basic.getDetailCode().equals(detailCode)){
  				if(per.equals(BonusConstant.SALES_BONUS_PER)){
  					big = basic.getSalesBonusPer();
  					break;
  				}else if(per.equals(BonusConstant.SALES_BONUS_PER1)){
  					big = basic.getSalesBonusPer1();
  					break;
  				}else if(per.equals(BonusConstant.SALES_BONUS_PER2)){
  					big = basic.getSalesBonusPer2();
  					break;
  				}else{
  				}
  			}
  		}
  		return big.doubleValue()/100;
  	}
  	/**
  	 * 获取晋升主任判断标准 
  	 * @author su
  	 * @param typeCode
  	 * @param detailCode
  	 * @return
  	 */
  	public double getPromotionStandard(String typeCode,String detailCode){
  		BigDecimal big = null;
  		if(list == null){
  			refreshBasicManageList();
  		}
  		for(BasicManage basic : list){
  			if(basic.getTypeCode().equals(typeCode) && basic.getDetailCode().equals(detailCode)){
				big = basic.getMinAmt();
				break;
  			}
  		}
  		return big.doubleValue();
  	}
  	/**
  	 * 获取见点奖位可以领取代数值
  	 * @param salesNum 实际销售卡数
  	 * @author su
  	 * @return 对应的可领取节点代数
  	 */
  	public int getRecommendCount(int salesNum){
  		int result = 0;
  		if(list == null){
  			refreshBasicManageList();
  		}
  		ArrayList<Integer> tep = new ArrayList<Integer>();
  		Map<String,Integer> map = new HashMap<String,Integer>();
  		for(BasicManage basic : list){
  			//判断是见点奖点位的放入tep中
  			if(basic.getTypeCode().equals(BonusConstant.D04)){
  				Integer recommend = basic.getRecommendCnt();
  				if(null != recommend && recommend > 0){
  					tep.add(recommend);
  					map.put(recommend.toString(), basic.getRemainCnt());
  				}
  			}
  		}
  		//升序排列
  		Collections.sort(tep);
  		//倒序排列
  		Collections.reverse(tep);
  		//循环处理，从最大的开始判断
  		for(Integer rec : tep){
  			//如果传入的实际推荐数大于当前值，就返回当前可以领取的代数值。
  			if(salesNum >= rec.intValue()){
  				result = map.get(rec.toString());
  				break;
  			}
  		}
  		return result;
  	}
}

/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.distribution.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.distribution.dao.bonusCachePool.model.BonusCachePoolExample;
import com.distribution.dao.bonusPool.model.BonusPoolExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distribution.common.constant.BonusConstant;
import com.distribution.dao.bonusCachePool.mapper.BonusCachePoolMapper;
import com.distribution.dao.bonusCachePool.model.BonusCachePool;
import com.distribution.dao.bonusPool.mapper.BonusPoolMapper;
import com.distribution.dao.bonusPool.model.BonusPool;
import com.distribution.dao.bonusPoolHistory.mapper.BonusPoolHistoryMapper;
import com.distribution.dao.bonusPoolHistory.model.BonusPoolHistory;

@Service
public class BonusPoolService {
	@Autowired
	private BonusPoolMapper bonusPoolMapper;
	@Autowired
	private BonusCachePoolMapper bonusCachePoolMapper;
	@Autowired
	private BonusPoolHistoryMapper bonusPoolHistoryMapper;
	
	
	/**
	 * 更新奖金池
	 * 更新的业务包括当日发奖剩余的营业额、管理员划拨奖金到缓存池；
	 */
	public void updatePool(BigDecimal amount,int poolType,int addOrReduce){
		//查询现有奖金次信息
		BonusPool pool = null;
		if(poolType == BonusConstant.POOL_TYPE_DIVIDEND){
			pool = bonusPoolMapper.selectByPrimaryKey(BonusConstant.POOL_ID_DIVIDEND);
		}else if(poolType == BonusConstant.POOL_TYPE_NODE){
			pool = bonusPoolMapper.selectByPrimaryKey(BonusConstant.POOL_ID_NODE);
		}else{
			return;
		}
		BigDecimal bonusResult;
		//计算奖金
		if(addOrReduce == BonusConstant.POOL_BONUS_ADD){
			bonusResult = new BigDecimal(pool.getTotalAmount()).add(amount); 
		}else if(addOrReduce == BonusConstant.POOL_BONUS_REDUCE && pool.getTotalAmount().doubleValue()>=amount.doubleValue()){
			bonusResult = new BigDecimal(pool.getTotalAmount()).subtract(amount); 
		}else{
			return;
		}
		pool.setTotalAmount(bonusResult.doubleValue());
		pool.setUpdateTime(new Date());
		pool.setUpdateBy(0);
		//更新奖金池
		bonusPoolMapper.updateByPrimaryKeySelective(pool);
		//记录奖金流水池
		BonusPoolHistory history = new BonusPoolHistory();
		history.setPoolId(pool.getId());
		history.setAddRemove(addOrReduce);
		history.setAmout(amount.doubleValue());
		history.setCreateBy(0);
		history.setCreateTime(new Date());
		history.setOperateTime(new Date());
		//history.setRemarks(remarks);
		bonusPoolHistoryMapper.insert(history);
	}
	/**
	 * 
	 * 更新奖金缓存池
	 * 更新业务包括，发奖扣减缓存池、管理员划拨奖金到缓存池；
	 * @date 2017年9月2日 下午1:35:51
	 */
	public void updateCachePool(BigDecimal amount,int poolType,int addOrReduce){
		//查询现有缓存池信息
		BonusCachePool cachePool = null;
		if(poolType == BonusConstant.POOL_TYPE_DIVIDEND){
			cachePool = bonusCachePoolMapper.selectByPrimaryKey(BonusConstant.POOL_ID_DIVIDEND);
		}else if(poolType == BonusConstant.POOL_TYPE_NODE){
			cachePool = bonusCachePoolMapper.selectByPrimaryKey(BonusConstant.POOL_ID_NODE);
		}else{
			return;
		}
		BigDecimal bonusResult;
		//计算奖金
		if(addOrReduce == BonusConstant.POOL_BONUS_ADD){
			bonusResult = new BigDecimal(cachePool.getTotalAmount()).add(amount); 
		}else if(addOrReduce == BonusConstant.POOL_BONUS_REDUCE && cachePool.getTotalAmount().doubleValue()>=amount.doubleValue()){
			bonusResult = new BigDecimal(cachePool.getTotalAmount()).subtract(amount); 
		}else{
			return;
		}
		cachePool.setTotalAmount(bonusResult.doubleValue());
		cachePool.setUpdateTime(new Date());
		cachePool.setUpdateBy(0);
		//更新缓存池
		bonusCachePoolMapper.updateByPrimaryKeySelective(cachePool);
	}
	/**
	 * 
	 * 根据缓存池id
	 * 查询余额
	 * @author su
	 * @date 2017年9月7日 下午5:55:52
	 * @param id
	 * @return
	 */
	public double getBonusCachePool(int id){
		double total = 0;
		BonusCachePool pool = bonusCachePoolMapper.selectByPrimaryKey(id);
		if(null != pool){
			total = pool.getTotalAmount();
		}
		return total;
	}

	public BonusPool getBonusPool(String poolType){
		BonusPoolExample bonusPoolExample = new BonusPoolExample();
		BonusPoolExample.Criteria bonusPoolCriteria = bonusPoolExample.createCriteria();
		bonusPoolCriteria.andPoolTypeEqualTo(poolType);

		List<BonusPool> bonusPools = bonusPoolMapper.selectByExample(bonusPoolExample);
		if(bonusPools != null && bonusPools.size() >0)
			return  bonusPools.get(0);
		else
	      return  new BonusPool();
	}

	public BonusCachePool getBonusCachePool(String poolType){
		BonusCachePoolExample bonusCachePoolExample = new BonusCachePoolExample();
		BonusCachePoolExample.Criteria bonusCachePoolCriteria = bonusCachePoolExample.createCriteria();
		bonusCachePoolCriteria.andPoolTypeEqualTo(poolType);

		List<BonusCachePool> bonusCachePools = bonusCachePoolMapper.selectByExample(bonusCachePoolExample);
		if(bonusCachePools != null && bonusCachePools.size() >0)
			return  bonusCachePools.get(0);
		else
			return  new BonusCachePool();
	}
}

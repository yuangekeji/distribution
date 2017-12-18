package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.pointMaster.mapper.more.MorePointMasterMapper;
import com.distribution.dao.pointMaster.model.PointMaster;
import com.distribution.dao.pointOrder.mapper.more.MorePointOrderMapper;
import com.distribution.dao.pointOrder.model.PointOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class PointService {

    @Autowired
    private MorePointMasterMapper morePointMasterMapper;
    @Autowired
    private MorePointOrderMapper morePointOrderMapper;
    /**
     *
     * @param pointMaster
     * @param memberId
     * @return
     */
    public int insertPointMaster(PointMaster pointMaster, Integer memberId){

        PointMaster pointMasterData = morePointMasterMapper.selectByMemberId(memberId);
        Date date = new Date();
        int count;
        if (pointMasterData != null && pointMasterData.getMemberId() != null) {
            pointMaster.setMemberId(memberId);
            pointMaster.setPointAmt(pointMasterData.getPointAmt().add(pointMaster.getPointAmt()));
            pointMaster.setUpdateId(memberId);
            pointMaster.setUpdateTime(date);
            count = morePointMasterMapper.updateByMemberId(pointMaster);

        }else {
            pointMaster.setMemberId(memberId);
            pointMaster.setCreateId(memberId);
            pointMaster.setCreateTime(date);
            pointMaster.setUpdateId(memberId);
            pointMaster.setUpdateTime(date);
            count = morePointMasterMapper.insert(pointMaster);
        }
        return count;
    }
    /**
     * @param memberId
     * @return
     */
    public PointMaster selectByMemberId(Integer memberId){

        PointMaster pointMaster = morePointMasterMapper.selectByMemberId(memberId);

        return pointMaster;
    }
    /**
     * description 积分兑换单列表查询
     * @author sijeong
     * */
    public Page pointOrderList(Page page){
        page.setTotalCount(morePointOrderMapper.getPointOrderListCount(page));
        page.setResult(morePointOrderMapper.getPointOrderList(page));
        return page;
    }
    /**
     * description 确认收货
     * @author WYN
     * */
    public String confirmOrder(PointOrder pointOrder) {
        int cnt = morePointOrderMapper.updateByPrimaryKeySelective(pointOrder);

        if(cnt > 0){
            return "success";
        }else{
            throw new RuntimeException();
        }
    }
}

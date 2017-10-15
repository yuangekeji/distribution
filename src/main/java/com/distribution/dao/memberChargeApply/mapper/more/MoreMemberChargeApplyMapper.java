package com.distribution.dao.memberChargeApply.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.memberChargeApply.mapper.MemberChargeApplyMapper;
import com.distribution.dao.memberChargeApply.model.more.MoreMemberChargeApply;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MoreMemberChargeApplyMapper extends MemberChargeApplyMapper{

    /**
     * description 查詢總條數
     * @param page
     * @return
     */
    Integer getMemberChargeListCount(Page page);

    /**
     * description 查询充值申请列表
     * @param page
     * @return
     */
    List<MoreMemberChargeApply> getMemberChargeList(Page page);

    /**
     * description 充值申请批准，驳回
     * @author sijeong
     * */
    int confirmMemberCharge(MoreMemberChargeApply moreMemberChargeApply);

    /**
     * description 充值列表下载
     * @author sijeong
     * */
    List<MoreMemberChargeApply> getExcelMemberChargeList(Map map);
}
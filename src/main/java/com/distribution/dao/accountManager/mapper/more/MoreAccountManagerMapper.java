package com.distribution.dao.accountManager.mapper.more;
import java.util.List;
import java.util.Map;

import com.distribution.dao.accountManager.mapper.AccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.member.model.more.MoreMember;

public interface MoreAccountManagerMapper extends AccountManagerMapper{


    /**
     * 查询账户信息
     * @param accountManager
     * @return
     */
    AccountManager selectAccountManager(AccountManager accountManager);

    int updateAccountManagerAmt(AccountManager accountManager);

    int updateAccountManagerAmtWhileAdvance(AccountManager accountManager);

    int updateAccountManager(AccountManager accountManager);

    /**
     * description 查询奖金币和种子币各自总额
     * @author Bright
     * */
    MoreMember getSeedsAndBondsByMemberId(Integer memberId);
    /**
     * description 通过memberId查询账户管理表
     * @author Bright
     * */
    AccountManager selectByMemberId(Integer memberId);
    /**
     * description 根据memberId更新账户管理表
     * @author Bright
     * */
    Integer updateByMemberId(AccountManager accountManager);
    
    List<Map<String,Object>>listBonusTemp();

}
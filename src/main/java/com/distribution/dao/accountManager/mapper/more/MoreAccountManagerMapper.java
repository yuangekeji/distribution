package com.distribution.dao.accountManager.mapper.more;
import com.distribution.dao.accountManager.mapper.AccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.member.model.more.MoreMember;

import java.util.Map;

public interface MoreAccountManagerMapper extends AccountManagerMapper{


    /**
     * 查询账户信息
     * @param accountManager
     * @return
     */
    AccountManager selectAccountManager(AccountManager accountManager);

    int updateAccountManagerAmt(AccountManager accountManager);

    int updateAccountManager(AccountManager accountManager);

    /**
     * description 查询奖金币和种子币各自总额
     * @author Bright
     * */
    MoreMember getSeedsAndBondsByMemberId(Integer memberId);

}
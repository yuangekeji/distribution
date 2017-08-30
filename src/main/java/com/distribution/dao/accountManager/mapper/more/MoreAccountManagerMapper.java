package com.distribution.dao.accountManager.mapper.more;
import com.distribution.dao.accountManager.mapper.AccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import java.util.Map;

public interface MoreAccountManagerMapper extends AccountManagerMapper{


    /**
     * 查询账户信息
     * @param accountManager
     * @return
     */
    AccountManager selectAccountManager(AccountManager accountManager);

    int updateAccountManagerAmt(AccountManager accountManager);

}
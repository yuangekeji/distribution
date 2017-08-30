package com.distribution.service.transfer;

import com.distribution.common.utils.CryptoUtil;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.member.mapper.MemberMapper;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.transfer.mapper.TransferMapper;
import com.distribution.dao.transfer.model.Transfer;
import com.distribution.dao.transfer.model.more.MoreTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijingx on 8/30/2017.
 */

@Service
public class TransferService {

    @Autowired
    private MoreAccountManagerMapper accountManagerMapper;

    @Autowired
    private MoreMemberMapper memberMapper;

    @Autowired
    private TransferMapper transferMapper;

    /**
     * 查询账户信息
     * @param accountManager
     * @return
     */
    public AccountManager selectAccountManager(AccountManager accountManager){
        return accountManagerMapper.selectAccountManager(accountManager);
    }

    /**
     * 处理转账
     * @param transfer
     * @return
     */
    public String insertTransferProcess(MoreTransfer transfer){

        //根据member_id 和 paypwd 查询会员是否存在
        try {

            Map param = new HashMap();
            param.put("memberId",transfer.getMemberId());
            param.put("memberPhone",transfer.getMemberPhone());
            param.put("payPassword", CryptoUtil.md5ByHex(transfer.getPayPassword()));

            Integer count = memberMapper.findMatchMemberQueryPwd(param);

            if(count != null  && count >0 ){

                //step1) 插入转账明细表 todo
                Transfer tsf = new Transfer();
                tsf.setMemberId(transfer.getMemberId());
                tsf.setCreateId(transfer.getMemberId());
                tsf.setTransferAmt(transfer.getTransferAmt());
                tsf.setCreateTime(new Date());
                tsf.setTransferTime(new Date());
                //step1-1) 通过手机号找id收款人的
                Member receivedMember = memberMapper.getMemberByPhone(transfer.getMemberPhone());
                tsf.setReceiveId(receivedMember.getId());

                //step1-2) 插入转账明细表
                int cnt1 = transferMapper.insert(tsf);

                //step 2)账户转出更新
                AccountManager transAccount = new AccountManager();
                transAccount.setMemberId(transfer.getMemberId());

                transAccount = this.selectAccountManager(transAccount);
                transAccount.setTotalBonus(transAccount.getTotalBonus().subtract(transfer.getTransferAmt()));
                transAccount.setBonusAmt(transAccount.getTotalBonus().multiply(new BigDecimal(0.6)));
                transAccount.setSeedAmt(transAccount.getTotalBonus().subtract(transAccount.getBonusAmt()));

                int cnt2 = accountManagerMapper.updateAccountManagerAmt(transAccount);


                //step 3)转入账户更新

                AccountManager recivedAccount = new AccountManager();
                recivedAccount.setMemberId(transfer.getReceiveId());
                recivedAccount = this.selectAccountManager(recivedAccount);

                recivedAccount.setTotalBonus(recivedAccount.getTotalBonus().add(transfer.getTransferAmt()));
                recivedAccount.setBonusAmt(recivedAccount.getTotalBonus().multiply(new BigDecimal(0.6)));
                recivedAccount.setSeedAmt(recivedAccount.getTotalBonus().subtract(recivedAccount.getBonusAmt()));

                int cnt3 =  accountManagerMapper.updateAccountManagerAmt(recivedAccount);

                //转账成功
                return "success";
            }

        }catch (Exception e){
            System.err.println(e.getMessage());
            //程序异常
            return  "error";
        }

        //密码错误
        return  "pwdWrong";
    }
}

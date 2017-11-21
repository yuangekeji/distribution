package com.distribution.service;

import com.distribution.common.constant.Constant;
import com.distribution.common.utils.CryptoUtil;
import com.distribution.common.utils.Page;
import com.distribution.dao.accountFlowHistory.mapper.AccountFlowHistoryMapper;
import com.distribution.dao.accountFlowHistory.model.AccountFlowHistory;
import com.distribution.dao.accountManager.mapper.more.MoreAccountManagerMapper;
import com.distribution.dao.accountManager.model.AccountManager;
import com.distribution.dao.member.mapper.MemberMapper;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.transfer.mapper.TransferMapper;
import com.distribution.dao.transfer.mapper.more.MoreTransferMapper;
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
    private AccountFlowHistoryMapper accountFlowHistoryMapper;
    @Autowired
    private MoreMemberMapper memberMapper;

    @Autowired
    private TransferMapper transferMapper;

    @Autowired
    private MoreTransferMapper moreTransferMapper;
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
            Map param = new HashMap();
            param.put("memberId",transfer.getMemberId());
            param.put("memberPhone",transfer.getMemberPhone());
            param.put("payPassword", CryptoUtil.md5ByHex(transfer.getPayPassword()));

            Integer count = memberMapper.findMatchMemberQueryPwd(param);

            if(count != null  && count >0 ){

                //step1) 插入转账明细表
                Transfer tsf = new Transfer();
                tsf.setMemberId(transfer.getMemberId());
                tsf.setMemberName(transfer.getMemberName());
                tsf.setMemberPhone(transfer.getMemberPhone());
                tsf.setCreateId(transfer.getMemberId());
                tsf.setCreateTime(new Date());
                tsf.setTransferAmt(transfer.getTransferAmt());
                tsf.setTransferTime(new Date());
                tsf.setStatus("0");

                //step1-1) 通过手机号找id收款人的
                Member receivedMember = memberMapper.getMemberByPhone(transfer.getReceivePhone());
                tsf.setReceiveId(receivedMember.getId());
                tsf.setReceivePhone(receivedMember.getMemberPhone());
                tsf.setReceiveName(receivedMember.getMemberName());

                //step 1)账户转出计算
                AccountManager transAccount = new AccountManager();
                transAccount.setMemberId(transfer.getMemberId());

                transAccount = this.selectAccountManager(transAccount);

                //判断如果账户余额小于转账金额就失败
                if(transAccount.getBonusAmt().compareTo(transfer.getTransferAmt()) == -1){
                    throw new RuntimeException();
                }

                transAccount.setBonusAmt(transAccount.getBonusAmt().subtract(transfer.getTransferAmt()));//从奖金币中扣除
                transAccount.setTotalBonus(transAccount.getBonusAmt().add(transAccount.getSeedAmt()));//计算总奖金字段
                transAccount.setUpdateId(tsf.getMemberId());
                transAccount.setUpdateTime(new Date());

                //step 2)转入账户计算
                AccountManager recivedAccount = new AccountManager();
                recivedAccount.setMemberId(tsf.getReceiveId());
                recivedAccount = this.selectAccountManager(recivedAccount);

                recivedAccount.setBonusAmt(recivedAccount.getBonusAmt().add(transfer.getTransferAmt()));//增加奖金币
                recivedAccount.setTotalBonus(recivedAccount.getBonusAmt().add(recivedAccount.getSeedAmt()));//计算总奖金字段


                recivedAccount.setUpdateId(tsf.getMemberId());
                recivedAccount.setUpdateTime(new Date());


                AccountFlowHistory historyout = new AccountFlowHistory();
                historyout.setMemberId(tsf.getMemberId());
                historyout.setCreateTime(new Date());
                historyout.setCreateId(tsf.getMemberId());
                historyout.setType("1");      //1支出 进账2
                historyout.setFlowType(Constant.TRANSFEROUT); //转出
                historyout.setBonusAmt(tsf.getTransferAmt());

                AccountFlowHistory historyin = new AccountFlowHistory();
                historyin.setMemberId(tsf.getReceiveId());
                historyin.setCreateTime(new Date());
                historyin.setCreateId(tsf.getMemberId());
                historyin.setType("2");      //1支出 进账2
                historyin.setFlowType(Constant.TRANSFERIN); //转出
                historyin.setBonusAmt(tsf.getTransferAmt());

                //判断如果会员状态未激活，并且账户的余额和订单金额相同，更新会员款状态
                if("N".equals(receivedMember.getStatus()) && "N".equals(receivedMember.getMoneyStatus())){
                    //账户余额 大于或者等于 订单金额时
                    if(recivedAccount.getBonusAmt().compareTo(receivedMember.getOrderAmount()) > -1){
                        memberMapper.updateMemberMoneyStatusY(receivedMember.getId());
                    }
                }

                if(transAccount.getId() != null && transAccount.getId() >0
                        && recivedAccount.getId() !=null && recivedAccount.getId() >0){
                        //step1-3) 插入转账明细表，计算各账户金额
                        int  cnt1 = transferMapper.insert(tsf);

                        int  cnt2 = accountManagerMapper.updateAccountManagerAmt(transAccount);

                        int  cnt3 = accountManagerMapper.updateAccountManagerAmt(recivedAccount);

                        int  cnt4= accountFlowHistoryMapper.insert(historyout);

                        int  cnt5= accountFlowHistoryMapper.insert(historyin);

                        if(cnt1 >0 && cnt2 >0 && cnt3>0 && cnt4 >0 && cnt5 >0){
                            //转账成功
                            return "success";
                        }else{
                            throw new RuntimeException();
                        }
                }else{
                    throw new RuntimeException();
                }
            }

        //密码错误
        return  "pwdWrong";
    }

    /**
     * description 转账记录查询
     * @author sijeong
     * */
    public Page transferList(Page page) {
        page.setTotalCount(moreTransferMapper.getTransferListCount(page));
        page.setResult( moreTransferMapper.getTransferList(page));
        return page;
    }
}

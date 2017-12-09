package com.distribution.controller;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.platformAccount.model.PlatformAccount;
import com.distribution.service.AdmHandleHistoryService;
import com.distribution.service.AdmWarningService;
import com.distribution.service.BonusPoolService;
import com.distribution.service.BonusService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jingxin on 2017/9/6.
 */

@RequestMapping("/admWarning")
@Controller
public class AdmWarningController extends BasicController{

    private static final Log loger = LogFactory.getLog(AdmWarningController.class);

    @Autowired
    private AdmWarningService admWarningService;

    @Autowired
    private BonusPoolService bonusPoolService;

    @Autowired
    private AdmHandleHistoryService admHandleHistoryService;

    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage orderList(@RequestBody Page page, HttpSession session){
        Admin m = (Admin) getCurrentUser(session);
        page = admWarningService.selectDateBonusHistoryList(page);
        return successMsg(page);
    }

    @RequestMapping("/getFailJobCount")
    @ResponseBody
    public JsonMessage selectFailJobCount(){
        Integer failCnt = admWarningService.selectFailJobCount();
        return successMsg(failCnt);
    }

    @RequestMapping("/getBonusPool")
    @ResponseBody
    public JsonMessage selectBonusPool(String poolType){
        Map map = new HashMap<>();
        map.put("bonusPoolAmt",bonusPoolService.getBonusPool(poolType).getTotalAmount());
        map.put("bonusCachePoolAmt",bonusPoolService.getBonusCachePool(poolType).getTotalAmount());
        return successMsg(map);
    }

    @RequestMapping(value="/payAmtProc")
    @ResponseBody
    public JsonMessage payAmtProc(BigDecimal amount, Integer poolType, HttpSession session){
        Admin admin = (Admin) getCurrentUser(session);
        boolean result = bonusPoolService.updatePayAmtPoolProc(amount,poolType);
        //管理员操作记录
        Map map = new HashMap();
        map.put("handleType", Constant.ADMINHANDLETYPE_PAYAMTPROC);
        if (poolType == 1) {
            map.put("handleId", "补发分红包");
            map.put("handleComment", "补发分红包(从资金池拨资金到发放池), 补发金额: " + amount);
        }else {
            map.put("handleId", "补发广告宣传奖");
            map.put("handleComment", "补发广告宣传奖(从资金池拨资金到发放池), 补发金额: " + amount);
        }
        admHandleHistoryService.addAdminHandleHistory(admin, map);
        return successMsg(result);
    }

    /**
     * job沉淀资金查询
     * @return
     */
    @RequestMapping("/getPlatformAccount")
    @ResponseBody
    public JsonMessage getPlatformAccount(){
        PlatformAccount platformAccount = bonusPoolService.getPlatformAccountById();
        Map result= new HashMap();
        result.put("platformAccount",platformAccount);
        return successMsg(result);
    }

    @RequestMapping("/getTransferBonusPool")
    @ResponseBody
    public JsonMessage getTransferBonusPool(String poolType){
        Map map = new HashMap<>();
        map.put("bonusPoolAmt",bonusPoolService.getBonusPool(poolType).getTotalAmount());
        map.put("accountAmount",bonusPoolService.getPlatformAccountById().getAccountAmount());
        return successMsg(map);
    }

    @RequestMapping(value="/transferPayAmtProc")
    @ResponseBody
    public JsonMessage transferPayAmtProc(BigDecimal amount, Integer poolType, HttpSession session){
        Admin admin = (Admin) getCurrentUser(session);
        bonusPoolService.saveAccountToBonus(amount,admin.getId().toString(),poolType);
        //管理员操作记录
        Map map = new HashMap();
        map.put("handleType", Constant.ADMINHANDLETYPE_PAYAMTPROC);
        if (poolType == 1) {
            map.put("handleId", "拨款到分红包奖金池");
            map.put("handleComment", "拨款到分红包奖金池(从公司账户资金拨资金到分红包奖金池), 拨款金额: " + amount);
        }
        admHandleHistoryService.addAdminHandleHistory(admin, map);
        return successMsg("success");
    }
}

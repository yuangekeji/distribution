package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.member.model.Member;
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
    private BonusService bonusService;

    @Autowired
    private BonusPoolService bonusPoolService;

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
    @RequestMapping("/payAmtProc")
    @ResponseBody
    public JsonMessage payAmtProc(Map map){
        bonusService.insertPayAmtProc(map);
        return successMsg();
    }



}

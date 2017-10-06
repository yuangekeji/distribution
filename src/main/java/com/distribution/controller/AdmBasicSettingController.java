package com.distribution.controller;

import com.distribution.common.constant.BonusConstant;
import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.basicManage.model.BasicManage;
import com.distribution.service.AdmBasicSettingService;
import com.distribution.service.AdmHandleHistoryService;
import com.distribution.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admBasicSetting")
public class AdmBasicSettingController extends BasicController {
    @Autowired
    private AdmBasicSettingService admBasicSettingService;
    @Autowired
    private AdmHandleHistoryService admHandleHistoryService;
    @Autowired
    private CommonService commonService;
    /**
     * description 基本配置会员奖励
     * @author WYN
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage getBasicMemberBonusList(@RequestBody Page page, HttpSession session){
        page = admBasicSettingService.getBasicMemberBonusList(page);
        return successMsg(page);
    }

    @RequestMapping("/updateBasicSetting")
    @ResponseBody
    public JsonMessage updateBasicSetting(@RequestBody BasicManage basicManage, HttpSession session, HttpServletRequest request){
        Admin currentUser = null;
        if(getCurrentUser(session) instanceof Admin) {
            currentUser = (Admin) getCurrentUser(session);
        }

        basicManage.setUpdateId(currentUser.getId());
        basicManage.setUpdateTime(new Date());

        String result =admBasicSettingService.updateBasicSetting(basicManage);
        //管理员操作记录
        Map map = new HashMap();
        map.put("handleType", Constant.ADMINHANDLETYPE_BASICSETTING);
        //提现设置
        if (BonusConstant.D08.equals(basicManage.getTypeCode()) && BonusConstant.CODE_00.equals(basicManage.getDetailCode())) {
            map.put("handleId", "提现设置");
            map.put("handleComment", "提现设置: 每次提现最小金额(元): " + basicManage.getMinAmt() + ", 提现手续费(%): " + basicManage.getMaxPercent());
            admHandleHistoryService.addAdminHandleHistory(currentUser, map);
        }
        //分红包设置
        if (BonusConstant.D02.equals(basicManage.getTypeCode()) && BonusConstant.CODE_00.equals(basicManage.getDetailCode())) {
            map.put("handleId", "分红包设置");
            map.put("handleComment", "分红包设置: 分红包金额(元): " + basicManage.getMaxAmt());
            admHandleHistoryService.addAdminHandleHistory(currentUser, map);
        }
        //广告宣传奖设置
        if (BonusConstant.D03.equals(basicManage.getTypeCode()) && BonusConstant.CODE_00.equals(basicManage.getDetailCode())) {
            map.put("handleId", "广告宣传奖设置");
            map.put("handleComment", "广告宣传奖设置: 广告宣传奖金额(元): " + basicManage.getMaxAmt());
            admHandleHistoryService.addAdminHandleHistory(currentUser, map);
        }

        //刷新缓存中的静态变量
        commonService.refreshBasicManageList();
        return successMsg("result",result);
    }

}

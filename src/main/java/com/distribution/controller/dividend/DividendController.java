package com.distribution.controller.dividend;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.member.model.Member;
import com.distribution.service.dividend.DividendService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by lijingx on 8/28/2017.
 */
@Controller
@RequestMapping("/dividend")
public class DividendController extends BasicController {
   // private static final Log loger = LogFactory.getLog(DividendController.class);

    @Autowired
    private DividendService dividendService;

    /**
     * 查詢分紅包列表
     */
     @RequestMapping("/list")
     @ResponseBody
     public JsonMessage dividendList(@RequestBody Page page, HttpSession session){
         Member m = (Member) getCurrentUser(session);
         page.getParameterMap().put("memberId",m.getId());
         page = dividendService.dividendList(page);
         return successMsg(page);
     }

    /**
     * 查詢分紅包明細
     */

    @RequestMapping("/details")
    @ResponseBody
    public JsonMessage dividendDetails(@RequestBody Page page, HttpSession session){
        return successMsg(dividendService.dividendDetails(page));
    }

}

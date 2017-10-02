package com.distribution.controller;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.service.AdmHandleHistoryService;
import com.distribution.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sijeong on 2017/10/02.
 */
@Controller
@RequestMapping("/admHandleHistory")
public class admHandleHistoryController extends BasicController {

    @Autowired
    private AdmHandleHistoryService admHandleHistoryService;
    /**
     * 查詢管理员列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage adminList(@RequestBody Page page){
        page = admHandleHistoryService.admHandleHistoryList(page);
        return successMsg(page);
    }
}

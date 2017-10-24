package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.service.AdmHandleHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sijeong on 2017/10/02.
 */
@Controller
@RequestMapping("/admHandleHistory")
public class AdmHandleHistoryController extends BasicController {

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

package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notice")
public class NoticeController extends BasicController{
    @Autowired
    private NoticeService noticeService;

    /**
     * description 查询前天公告列表
     * @author Bright
     * */
    @RequestMapping("/getList")
    @ResponseBody
    public JsonMessage getList(){
        return successMsg(noticeService.getList());
    }

    /**
     * description 根据ID查询公告
     * @author Bright
     * */
    @RequestMapping("/getNoticeById/{id}")
    @ResponseBody
    public JsonMessage getNoticeById(@PathVariable Integer id){
        return successMsg(noticeService.getNoticeById(id));
    }
}

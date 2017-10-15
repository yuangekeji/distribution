package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.notice.model.Notice;
import com.distribution.service.AdmNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admNotice")
public class AdmNoticeController extends BasicController {

    @Autowired
    private AdmNoticeService admNoticeService;

    /**
     * description 公告管理列表查询
     * @author Bright
     * */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage list(@RequestBody Page page){
        page = admNoticeService.selectList(page);
        return successMsg(page);
    }

    /**
     * description 添加或者更新公告
     * @author Bright
     * */
    @RequestMapping("/insertOrUpdate")
    @ResponseBody
    public JsonMessage insertOrUpdate(@RequestBody Notice notice, HttpSession session){
        Admin admin = (Admin) getCurrentUser(session);
        Integer it = admNoticeService.insertOrUpdate(notice,admin);
        if(it>0){
            return successMsg();
        }else{
            return failMsg();
        }
    }

    /**
     * description 根据ID查询公告
     * @author Bright
     * */
    @RequestMapping("/getNoticeById/{id}")
    @ResponseBody
    public JsonMessage getNoticeById(@PathVariable Integer id){
        return successMsg(admNoticeService.getNoticeById(id));
    }
}

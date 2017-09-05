package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.utils.Page;
import com.distribution.dao.member.model.Member;
import com.distribution.dao.memberNode.model.more.CustomNode;
import com.distribution.service.NodeService;
import com.distribution.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by jingxin on 2017/9/4.
 */
@Controller
@RequestMapping("/node")
public class NodeController extends BonusController {

    @Autowired
    private NodeService nodeService;

    @RequestMapping("/tree")
    @ResponseBody
    public JsonMessage orderList( HttpSession session){
        Member m = (Member) getCurrentUser(session);
        CustomNode node = nodeService.buildNodeTreeByNode(m.getNodeId());
        return successMsg(node);
    }
}

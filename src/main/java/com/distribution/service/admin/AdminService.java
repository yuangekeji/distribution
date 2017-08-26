package com.distribution.service.admin;

import com.distribution.common.constant.Constant;
import com.distribution.common.constant.JsonMessage;
import com.distribution.dao.admin.mapper.more.MoreAdminMapper;
import com.distribution.dao.admin.model.Admin;
import com.distribution.dao.member.mapper.more.MoreMemberMapper;
import com.distribution.dao.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private MoreAdminMapper moreAdminMapper;
    @Autowired
    private MoreMemberMapper moreMemberMapper;

    /**
     * description 登录
     * @author Bright
     * */
    public JsonMessage login(Map param, String remember, HttpSession session){
        if(null!=remember){
            List<Admin> list = moreAdminMapper.login(param);
            if(!list.isEmpty()){
                session.setAttribute(Constant.SESSION_CURRENT_USER,list.get(0));
                return new JsonMessage(true,"success",null);
            }else{
                return new JsonMessage(false,"fail",null);
            }
        }else{
            List<Member> list = moreMemberMapper.login(param);
            if(!list.isEmpty()){
                session.setAttribute(Constant.SESSION_CURRENT_USER,list.get(0));
                return new JsonMessage(true,"success",null);
            }else{
                return new JsonMessage(false,"fail",null);
            }
        }
    }
}

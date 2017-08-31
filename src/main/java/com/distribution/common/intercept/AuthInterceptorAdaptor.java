package com.distribution.common.intercept;

import com.distribution.common.constant.Constant;
import com.distribution.dao.member.model.Member;
import com.distribution.service.MemberService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 *
 */
public class AuthInterceptorAdaptor extends HandlerInterceptorAdapter {
    private static Logger log = Logger.getLogger(AuthInterceptorAdaptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

            System.out.println("AuthInterceptorAdaptor preHandle:"+request.getContextPath()+","+request.getRequestURI()+","+request.getMethod());

            Member member = (Member)request.getSession().getAttribute(Constant.SESSION_CURRENT_USER);

            String requestURI = request.getRequestURI();

            //会员 不能访问 admin路径
            if(member.getRoleId() == 1){

                if(requestURI.indexOf("admin") > -1){
                    System.out.println("=========会员==不允许访问"+requestURI);
                    response.sendRedirect(request.getContextPath() + "/member/authFail");
                    return false;
                }

             //超级管理员  不带admin不可以访问
            }else if(member.getRoleId() == 2){

                if(requestURI.indexOf("admin") <= -1){
                    System.out.println("=========超级管理员==不允许访问"+requestURI);
                    response.sendRedirect(request.getContextPath() + "/member/authFail");
                    return false;
                }

             //财务
            }else if(member.getRoleId() == 3){


                if(requestURI.indexOf("admin") <= -1){
                    System.out.println("=========财务==不允许访问"+requestURI);
                    response.sendRedirect(request.getContextPath() + "/member/authFail");
                    return false;
                }
            }else{
                return false;
            }


          return true;
    }
}

package com.distribution.common.intercept;

import com.distribution.common.constant.Constant;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 *
 */
public class LoginInterceptorAdaptor extends HandlerInterceptorAdapter {
    private static Logger log = Logger.getLogger(LoginInterceptorAdaptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //获取拦截的方法
            Method method = handlerMethod.getMethod();
            //获取被拦截方法类上有没有注解
            if (handlerMethod.getBean().getClass().getAnnotation(IgnoreLoginCheck.class) != null) {
                //有注解则忽略登录
                return true;
            }
            if (method.getAnnotation(IgnoreLoginCheck.class) == null) {
                //没有注解，说明该方法没有被忽略，需要判断是否登录
                if (request.getSession().getAttribute(Constant.SESSION_CURRENT_USER) == null) {
                    //没有登录，则跳转到登录页
                    response.sendRedirect(request.getContextPath() + "/admin/login");
                    log.info("login interceptor:" + handlerMethod.getBean().getClass().getSimpleName() + "." + method.getName());
                    return false;
                }
            }
        }
        return true;
    }
}

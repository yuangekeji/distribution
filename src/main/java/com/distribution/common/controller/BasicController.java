package com.distribution.common.controller;

import com.distribution.common.constant.JsonMessage;

import com.distribution.common.exception.BizException;
import com.distribution.common.utils.ErrorUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


public class BasicController {
    protected Logger logger = LoggerFactory.getLogger(BasicController.class);

    protected JsonMessage successMsg() {
        return new JsonMessage(true, null, new HashMap<String, Object>());
    }

    protected JsonMessage successMsg(String key, Object value) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(key, value);
        return new JsonMessage(true, null, data);
    }

    protected JsonMessage successMsg(Object data) {
        return new JsonMessage(true, null, data);
    }

    protected JsonMessage failMsg() {
        return new JsonMessage(false, null, new HashMap<String, Object>());
    }

    protected JsonMessage failMsg(String msg) {
        return new JsonMessage(false, msg, new HashMap<String, Object>());
    }

    protected JsonMessage unauthorizedMsg(HttpServletResponse response) {
        response.setStatus(401);
        return new JsonMessage(false, "权限不足", new HashMap<String, Object>());
    }

    protected String generateIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else if (ip.length() > 0 && ip.indexOf(",") > 1) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    protected String handleException(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        // 全局记录异常logger
        if (exception instanceof BizException) {
            logger.error(ErrorUtils.toMessage(exception, null));
        } else if (exception instanceof MaxUploadSizeExceededException) {
            return null;
        } else {
            logger.error(exception.getMessage(), exception);
        }
        // 异常处理中model失效
//        ModelAndView view = new ModelAndView();
//        view.getModel().put("ExceptionFromKey", "web");
        request.setAttribute("ExceptionFromKey", "web");
        return "/error/biz_error";
    }

    protected Object convertMap(Class type, Map map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);

                Object[] args = new Object[1];
                args[0] = value;

                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }
}

package com.distribution.common.utils;


import com.distribution.common.exception.BizException;

import java.util.Locale;


/**
 * Error Code 转换为 Error Message 工具类
 */
public class ErrorUtils {

    public static String toErrorCode(Exception ex) {
        if (ex instanceof BizException) {
            return ex.getMessage();
        }
//        else if (ex instanceof WxErrorException) {
//            return ex.getMessage();
//        }
        else {
            return "系统异常";
        }
    }

    public static String toMessage(Exception ex) {
        return ErrorUtils.toErrorCode(ex);
    }

    public static String toMessage(String errorCode, Locale locale) {
        return MessageUtils.getMessage(errorCode, locale);
    }

    public static String toMessage(Exception ex, Locale locale) {
        return MessageUtils.getMessage(toErrorCode(ex), locale);
    }
}

package com.distribution.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description: 手机号码工具类
 * author: zhiming.dong
 * version: 1.0
 * creationTime:2016-10-11 17:01:45
 */
public class PhoneNumberUtil {

    /**
     * 检测手机号码是否正确
     * author: zhiming.dong
     * version: 1.0
     * creationTime: 2016-10-11 17:58:37
     * */
    public static boolean phoneNumberCheck(String phoneNumber) {
        if(null!=phoneNumber && !"".equals(phoneNumber.trim())) {
            //1 验证手机号码为11位
            //2 验证手机号码为存数字
            //3 验证手机号码第一位为1 第二位为3 4 5 7 8 9
            Pattern p = Pattern.compile("^1(3|4|5|7|8|9)\\d{9}$");
            Matcher m = p.matcher(phoneNumber);
            if(!m.matches()) {
                return false;
            }
        }
        return true;
    }

}

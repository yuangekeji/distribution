package com.distribution.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 消息工具类(已实现国际化功能)
 * 
 * <pre>
 * MessageUtils.getMessage(&quot;M0001&quot;, locale);
 * </pre>
 * 
 * @author Patrick.li
 * @create Sep 11, 2013
 * 
 */
@Component
public class MessageUtils {
	private static MessageSource messageSource;

	/**
	 * 设置消息源，本方法使用Spring的MessageSource
	 * 
	 * @param messageSource
	 */
	@Autowired(required = true)
	public void setMessageSource(MessageSource messageSource) {
		MessageUtils.messageSource = messageSource;
	}

	/**
	 * 根据消息Key取得消息
	 * 
	 * @param key
	 *            消息ID
	 * @return 消息内容
	 */
	public static String getMessage(String key, Locale locale) {
		return getMessage(key, null, locale);
	}

	/**
	 * 根据消息Key取得消息，可以传参数
	 * 
	 * @param key
	 *            消息ID
	 * @param args
	 *            参数数组: 参数格式：例如Msg:用户{0}{1}{2}...
	 * @param locale
	 *            区域
	 * @return 消息内容
	 */
	public static String getMessage(String key, Object[] args, Locale locale) {
		return messageSource.getMessage(key, args, locale);
	}
}

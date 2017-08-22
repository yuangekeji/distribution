package com.distribution.common.constant;

/**
 * 错误代码常量类
 * 
 */
public class ErrorCodes {

	/** 系统级别错误代码E1 */

	// 系统内部错误
	public static final String SYSTEM_INTERNAL_ERROR = "E101001";
	// 参数错误，请参考API文档
	public static final String SYSTEM_PARAM_ERROR = "E101002";
	// 请求的HTTP METHOD不支持，请检查是否选择了正确的POST/GET方式
	public static final String SYSTEM_UNSUPPORT_METHOD = "E101003";
	// 权限不足
	public static final String SYSTEM_PERMISSION_DENY = "E101004";
	// 没有登录
	public static final String SYSTEM_SIGNIN_REQUIRED = "E101005";
	// 警告,执行了非法操作
	public static final String SYSTEM_ILLEGAL_OPERATION = "E101006";
	// 用户的恶意行为
	public static final String USER_ILLEGAL_OPERATION = "E101007";

	/** 业务级别错误代码E2 */
	// 201 用户相关
	// 用户名不存在
	public static final String USER_NOT_FOUND = "E201001";
	// 用户名与密码不匹配
	public static final String USER_WRONG_PASSWORD = "E201002";
}

package com.distribution.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 用枚举方式来用单例模式，不用Spring托管方式的单例模式是因为User对象中会用到这个类
 *
 */
public enum PropertiesUtil {
	INSTANCE;

	private Properties pro;

	PropertiesUtil() {
		pro = new Properties();
		try {
			InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("properties/application.properties");
			pro.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Properties getProperties() {
		return pro;
	}

	public String getProperties(String key) {
		return getProperties().getProperty(key);
	}


	public String getLocalFileRootDir() {
		return getProperties().getProperty("local.file.root.dir");
	}
	public String getExcelFileUploadRootDir(){
		return getProperties().getProperty("excel.file.upload.root.dir");
	}

	public String getLocalTmpDir() {
		return getLocalFileRootDir() + getProperties().getProperty("local.file.temp.dir");
	}

	public String getHttpUrl(){
		return getProperties().getProperty("local.http.url");
	}

	public String getRelativeLocalTmpDir() {
		return getProperties().getProperty("local.file.temp.dir");
	}

	public String getHttpTmpPath() {
		return getResourceServer() + getProperties().getProperty("http.file.path") + getProperties().getProperty("local.file.temp.dir");
	}

	public String getResourceServer() {
		return "http://" + getProperties().getProperty("file.server.name");
	}

	public String getHttpFilePath() {
		return getProperties().getProperty("http.file.path");
	}

	public String getCtxPath(){
		return getProperties().getProperty("local.ctx.path");
	}

	public String getLoginRedirectPath(){
		return getProperties().getProperty("login.redirect.path");
	}


}

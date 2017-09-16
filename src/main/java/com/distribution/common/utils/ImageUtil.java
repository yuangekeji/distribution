package com.distribution.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * description: 图片处理工具
 * author: zhiming.dong
 * version: 1.0
 * creationTime: 2016-10-14 09:10:04
 */
public class ImageUtil {

    private static final Log loger = LogFactory.getLog(ImageUtil.class);

    /**
     * description: 将网络图片下载到服务器
     * author: zhiming.dong
     * version: 1.0
     * creationTime: 2016-10-14 09:12:03
     * */
    public static String downloadPicFromUrl(String picUrl, String moduleName) throws IOException {
        URL url = new URL(picUrl);
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(5*1000);
        InputStream inputStream = connection.getInputStream();
        byte[] bytes = new byte[1024];
        int length;
        String fileDir = PropertiesUtil.INSTANCE.getLocalFileRootDir() + File.separator + moduleName + "Pic" + File.separator;
        File file = new File(fileDir);
        if(!file.exists()) {
            file.mkdirs();
        }
        String fileName = moduleName.toUpperCase() + String.valueOf(System.currentTimeMillis()) + ".jpg";
        OutputStream outputStream = new FileOutputStream(file.getPath() + File.separator + fileName);
        try {
            while ((length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes,0,length);
            }
        } catch (IOException e) {
            loger.error("上传图片出错!");
            loger.error(e.getMessage());
            e.printStackTrace();
            throw new IOException("上传图片出错!");
        } finally {
            outputStream.close();
            inputStream.close();
        }
        return moduleName + "Pic" + File.separator + fileName;
    }

    /**
     * description: 显示图片
     * author: zhiming.dong
     * version: 1.0
     * creationTime: 2016-10-17 11:26:20
     * */
    public static void showPic(String picUrl, HttpServletResponse response, String fileName) throws IOException{
        loger.debug("showPic========="+PropertiesUtil.INSTANCE.getLocalFileRootDir());
        if(null==fileName || "".equals(fileName)) {
            fileName = "wechat";
        }
        try {
//            String filePath =PropertiesUtil.INSTANCE.getLocalFileRootDir() + File.separator +  picUrl;
            String filePath = picUrl;
            File file = new File(filePath);
            if(!file.exists()) {
                filePath = PropertiesUtil.INSTANCE.getLocalFileRootDir() + File.separator + "default.jpg";
            }
            FileInputStream fileInputStream = new FileInputStream(filePath);
            int i = fileInputStream.available();
            byte size[] = new byte[i];
            fileInputStream.read(size);
            response.setContentType("image*//**//*");
            response.setHeader("Content-disposition", "attachment; filename=" +new String(fileName.getBytes("GBK"), "ISO-8859-1")+".jpg");
            OutputStream outStream = response.getOutputStream();
            outStream.write(size);
            outStream.flush();
            outStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            loger.error("图片展示出错!");
            loger.error(e.toString());
            e.printStackTrace();
            throw new IOException("图片展示出错");
        }
    }
}

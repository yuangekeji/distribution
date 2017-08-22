package com.distribution.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;


public class Base64Util {
    //图片转化成base64字符串
    public static String GetImageStr(String path) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = path;//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    //base64字符串转化成图片
    public static String GenerateImage(String imgStr, String moduleName) throws IOException {
        //对字节数组字符串进行Base64解码并生成图片
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes  = decoder.decodeBuffer(imgStr);
        for(int i=0;i<bytes.length;++i) {
            if(bytes[i]<0)
            {//调整异常数据
                bytes[i]+=256;
            }
        }

        String fileDir = PropertiesUtil.INSTANCE.getLocalFileRootDir() + File.separator + moduleName  + File.separator;
        File file = new File(fileDir);
        if(!file.exists()) {
            file.mkdirs();
        }
        String fileName = moduleName.toUpperCase() + String.valueOf(System.currentTimeMillis()) + ".jpg";
        OutputStream outputStream = new FileOutputStream(file.getPath() + File.separator + fileName);
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
        return moduleName  + File.separator + fileName;
    }

    //base64字符串转化成图片
    public static byte[] GenerateImageByteArray(String imgStr) throws IOException {
        //对字节数组字符串进行Base64解码并生成图片
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(imgStr);
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {//调整异常数据
                bytes[i] += 256;
            }
        }
        return bytes;
    }
}

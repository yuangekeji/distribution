package com.distribution.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by P0067929 on 2016/11/2.
 */
public class FileUploadUtil {

    /**
     * 上传文件
     * @param mpfile 文件
     * @param folder 文件夹名称/文件前部分名
     * @return
     * @throws IOException
     */
    public static String FileUpload(MultipartFile mpfile,String folder) throws IOException {
        byte[] bytes = mpfile.getBytes();
        String fileDir = PropertiesUtil.INSTANCE.getLocalFileRootDir() + File.separator + folder  + File.separator;
        File file = new File(fileDir);
        if(!file.exists()) {
            file.mkdirs();
        }
        String suffix = mpfile.getOriginalFilename().substring(mpfile.getOriginalFilename().lastIndexOf(".")+1);
        String fileName = folder.toUpperCase() + String.valueOf(System.currentTimeMillis()) + "." + suffix;
        OutputStream outputStream = new FileOutputStream(file.getPath() + File.separator + fileName);
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
        return folder  + File.separator + fileName;
    }

}

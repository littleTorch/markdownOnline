package com.torch.document.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import java.io.*;

public final class FilesUtil {

    public static JSONArray fileToJson(File[] files){
        JSONArray array= JSONUtil.createArray();
        for (File file : files) {
            //判断是文件还是目录
            if (file.isDirectory()) {
                JSONObject obj = JSONUtil.createObj();
                obj.set(file.getName(),fileToJson(cn.hutool.core.io.FileUtil.ls(file.getPath())));
                array.add(obj);
            }else{
                array.add(file.getName());
            }
        }
        return array;
    }

    //#region MultipartFile 转 File
    /**
     * MultipartFile 转 File
     *
     * @chancelai file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if ("".equals(file) || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

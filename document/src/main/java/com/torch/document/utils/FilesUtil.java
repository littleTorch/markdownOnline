package com.torch.document.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.File;

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
}

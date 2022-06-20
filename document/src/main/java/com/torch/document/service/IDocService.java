package com.torch.document.service;

import cn.hutool.json.JSONObject;
import com.torch.document.domain.UserInfo;

import java.io.File;
import java.nio.file.Path;

public interface IDocService {

    //获取用户文件夹下的目录JOSN
    public JSONObject getUserDocMsg(String username);

    //新增目录
    public Boolean addDir(String path);

    //新增文件
    public Boolean addDoc(String path);

    //删除文档/目录
    public Boolean delFile(String path);

    //修改文档名/目录名
    public Boolean setFileName(String path,String newName);

    //修改文档
    public Boolean setDoc(String path,File file);

    //查看文档
    public File selDoc(String path);

    //
}

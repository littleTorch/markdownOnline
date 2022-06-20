package com.torch.document.service;

import cn.hutool.json.JSONObject;

import java.io.File;

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
    public Boolean setDoc(String path,String file);

    //查看文档
    public String selDoc(String path);

    //分享文档
    public String share(String path);

    //保存分享文档
    public Boolean saveShare(String username,String path);

    //举报文档
    public Boolean ban(String username,String code,String content);

}

package com.torch.document.service.Impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.torch.document.dao.UserDao;
import com.torch.document.domain.UserInfo;
import com.torch.document.service.IDocService;
import com.torch.document.utils.FilesUtil;
import com.torch.document.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.UntypedExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class DocServiec implements IDocService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisUtil redisUtil;

    private String root= FileUtil.getUserHomePath();

    @Override
    public JSONObject getUserDocMsg(String username) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        UserInfo user = userDao.findOne(Example.of(userInfo, UntypedExampleMatcher.matching())).get();
        String documentation = user.getDocumentation();
        String userHomePath = FileUtil.getUserHomePath();
        File[] ls = FileUtil.ls(userHomePath + "\\" + documentation);
        JSONObject obj = JSONUtil.createObj();
        obj.set(user.getId(), FilesUtil.fileToJson(ls));
        return obj;
    }

    @Override
    public Boolean addDir(String path) {
        return FileUtil.mkdir(root+"\\"+path)!=null;
    }

    @Override
    public Boolean addDoc(String path) {
        return FileUtil.touch(root+"\\"+path)!=null;
    }

    @Override
    public Boolean delFile(String path) {
        return FileUtil.del(root+"\\"+path);
    }

    @Override
    public Boolean setFileName(String path, String newName) {
        return FileUtil.rename(FileUtil.file(root+"\\"+path),newName,true)!=null;
    }

    @Override
    public Boolean setDoc(String path, File file) {
        return FileUtil.copy(file,FileUtil.file(path),true)!=null;
    }

    @Override
    public File selDoc(String path) {
        if(redisUtil.hasKey(root+"\\"+path)){
            return (File) redisUtil.get(root+"\\"+path);
        }else{
            File file = FileUtil.file(root + "\\" + path);
            redisUtil.set(root+"\\"+path,file,60*60*24);
            return file;
        }
    }

}

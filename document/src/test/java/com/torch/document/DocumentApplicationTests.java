package com.torch.document;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.torch.document.dao.UserDao;
import com.torch.document.domain.UserInfo;
import com.torch.document.service.IDocService;
import com.torch.document.utils.FilesUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.UntypedExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.File;

@SpringBootTest
class DocumentApplicationTests {

    @Autowired
    private IDocService service;

    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {


        //
        //UserInfo userInfo = new UserInfo();
        //userInfo.setUsername("zzj");
        //UserInfo info = userDao.findOne(Example.of(userInfo, UntypedExampleMatcher.matching())).get();
        //
        //System.out.println(info);

    }
    //  {"root":[]}
    //private JSONArray fileToJson(File[] files,JSONArray array){
    //    for (File file : files) {
    //        //判断是文件还是目录
    //        if (file.isDirectory()) {
    //            JSONObject obj = JSONUtil.createObj();
    //            obj.set(file.getName(),fileToJson(FileUtil.ls(file.getPath()),JSONUtil.createArray()));
    //            array.add(obj);
    //        }else{
    //            array.add(file.getName());
    //        }
    //    }
    //    return array;
    //}


}

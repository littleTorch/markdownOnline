package com.torch.document.service.Impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.torch.document.dao.UserDao;
import com.torch.document.domain.UserInfo;
import com.torch.document.service.IDocService;
import com.torch.document.utils.FilesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.UntypedExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DocServiec implements IDocService {

    @Autowired
    private UserDao userDao;

    @Override
    public JSONObject getUserDocMsg(String username) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        UserInfo user = userDao.findOne(Example.of(userInfo, UntypedExampleMatcher.matching())).get();
        String documentation = user.getDocumentation();
        String userHomePath = FileUtil.getUserHomePath();
        File[] ls = FileUtil.ls(userHomePath + "\\" + documentation);
        JSONObject obj = JSONUtil.createObj();
        obj.set("root", FilesUtil.fileToJson(ls));
        return obj;
    }

}

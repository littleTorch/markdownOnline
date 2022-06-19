package com.torch.document.service;

import cn.hutool.json.JSONObject;
import com.torch.document.domain.UserInfo;

import java.io.File;

public interface IDocService {

    public JSONObject getUserDocMsg(String username);

}

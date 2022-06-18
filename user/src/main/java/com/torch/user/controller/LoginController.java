package com.torch.user.controller;

import com.torch.user.service.IUserService;
import com.torch.user.utils.argEntity.Register;
import com.torch.user.utils.result.ResultUtils;
import com.torch.user.utils.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IUserService userService;

    @PostMapping()
    public ResultVo login(@RequestBody Register register){
        return ResultUtils.success("登录测试成功！！！");
    }
}

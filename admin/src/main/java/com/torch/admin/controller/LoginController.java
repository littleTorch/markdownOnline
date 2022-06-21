package com.torch.admin.controller;

import com.torch.admin.service.IAdminSerivce;
import com.torch.admin.utils.argEntity.Register;
import com.torch.admin.utils.result.ResultUtils;
import com.torch.admin.utils.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adminLogin")
public class LoginController {

    @Autowired
    private IAdminSerivce adminSerivce;

    @GetMapping("/login")
    public ResultVo login(@RequestBody Register register){
        String login = adminSerivce.login(register.getUsername(), register.getPassword());
        if (login.equals("登录成功")){
            return ResultUtils.success(login,register.getUsername());
        }else{
            return ResultUtils.error(login);
        }

    }
}

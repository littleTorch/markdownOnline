package com.torch.user.controller;

import com.torch.user.service.IUserService;
import com.torch.user.utils.argEntity.Register;
import com.torch.user.utils.result.ResultUtils;
import com.torch.user.utils.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResultVo login(@RequestBody Register register){
        String login = userService.Login(register.getUsername(), register.getPassword());
        if (login.equals("登录成功")){
            return ResultUtils.success(login,userService.getUser(register.getUsername()));
        }else{
            return ResultUtils.error(login);
        }

    }

    @GetMapping("/emailCode")
    public ResultVo emailCode(@RequestParam String email){
        return userService.emailCode(email) ? ResultUtils.success("验证码已发送！") : ResultUtils.error("验证码发送失败，请稍后再试！");

    }

    @PostMapping("/register")
    public ResultVo register(@RequestBody Register register){
        String msg = userService.register(register);
        if (msg.equals("注册成功")){
            return ResultUtils.success(msg);
        }else{
            return ResultUtils.error(msg);
        }
    }
}

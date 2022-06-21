package com.torch.feignclient.controller;

import com.torch.feignclient.domain.UserInfo;
import com.torch.feignclient.service.UserService;
import com.torch.feignclient.utils.argEntity.Register;
import com.torch.feignclient.utils.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultVo login(@RequestBody Register register){
        return userService.login(register);
    };

    @GetMapping("/emailCode")
    public ResultVo emailCode(@RequestParam String email){
        return userService.emailCode(email);
    }

    @PostMapping("/register")
    public ResultVo register(@RequestBody Register register){
        return userService.register(register);
    }

    @GetMapping("/getUser")
    public UserInfo getUser(@RequestParam String username){
        return userService.getUser(username);
    }
}

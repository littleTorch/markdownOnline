package com.torch.document.service;


import com.torch.document.domain.UserInfo;
import com.torch.document.utils.argEntity.Register;
import com.torch.document.utils.result.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name = "user")
public interface IUserService {

    @PostMapping("/login")
    public ResultVo login(@RequestBody Register register);

    @GetMapping("/emailCode")
    public ResultVo emailCode(@RequestParam String email);

    @PostMapping("/register")
    public ResultVo register(@RequestBody Register register);

    @GetMapping("/getUser")
    public UserInfo getUser(@RequestParam String username);
}

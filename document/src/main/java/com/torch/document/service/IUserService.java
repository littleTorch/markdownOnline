package com.torch.document.service;


import com.torch.document.domain.UserInfo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name = "user")
public interface IUserService {

    @GetMapping("/login/getUser")
    public UserInfo getUser(@RequestParam String username);
}

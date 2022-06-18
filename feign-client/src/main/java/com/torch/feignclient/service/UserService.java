package com.torch.feignclient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@FeignClient(name = "user")
public interface UserService {

    @PostMapping("/login")
    public Object login();
}

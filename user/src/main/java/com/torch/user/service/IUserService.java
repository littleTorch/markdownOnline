package com.torch.user.service;

import com.torch.user.domain.UserInfo;
import com.torch.user.utils.argEntity.Register;

public interface IUserService{

    public String register(Register register);

    public String Login(String username,String password);

    public Boolean emailCode(String email);

    public UserInfo getUser(String username);
}

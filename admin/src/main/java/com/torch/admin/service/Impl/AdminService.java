package com.torch.admin.service.Impl;

import com.torch.admin.dao.AdminDao;
import com.torch.admin.domain.Admin;
import com.torch.admin.service.IAdminSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.UntypedExampleMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminSerivce {

    @Autowired
    private AdminDao adminDao;

    @Override
    public String login(String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        //判断用户名是否存在
        if (adminDao.exists(Example.of(admin, UntypedExampleMatcher.matching()))){
            Admin user = adminDao.findOne(Example.of(admin, UntypedExampleMatcher.matching())).get();
            //判断密码是否正确
            if (new BCryptPasswordEncoder().matches(password,user.getPassword())){
                return "登录成功";
            }else{
                return "密码错误";
            }
        }else{
            return "用户名不存在";
        }
    }
}

package com.torch.user.service.Impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.system.SystemUtil;
import com.torch.user.dao.UserDao;
import com.torch.user.domain.UserInfo;
import com.torch.user.service.IUserService;
import com.torch.user.utils.RedisUtil;
import com.torch.user.utils.argEntity.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String register(Register register) {
        int code = (int) redisUtil.get(register.getEmail() + "code");
//        判断验证码
        if (register.getCode().equals(code + "")) {
//            判断用户是否已存在
            UserInfo user = new UserInfo();
            user.setBan("0");
            user.setUsername(register.getUsername());
            user.setPassword(new BCryptPasswordEncoder().encode(register.getPassword()));
            user.setEmail(register.getEmail());
/////////////////////////////////////////////////////////////////////////////////(检查是否存在？)
            if (userDao.exists(Example.of(user,ExampleMatcher.matching()))) {
                userDao.save(user);
                return "注册成功";
            } else {
                return "用户名已存在";
            }
        } else {
            return "验证码错误";
        }
    }

}

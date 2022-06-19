package com.torch.user.service.Impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.torch.user.dao.UserDao;
import com.torch.user.domain.UserInfo;
import com.torch.user.service.IUserService;
import com.torch.user.utils.RedisUtil;
import com.torch.user.utils.argEntity.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.UntypedExampleMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String register(Register register) {
        String code = redisUtil.get(register.getEmail() + "code").toString();
//        判断验证码
        if (register.getCode().equals(code)) {
            UserInfo user = new UserInfo();
            user.setUsername(register.getUsername());
//            判断用户是否已存在
            if (!userDao.exists(Example.of(user, UntypedExampleMatcher.matching()))){
                user.setBan("0");
                user.setPassword(new BCryptPasswordEncoder().encode(register.getPassword()));
                user.setEmail(register.getEmail());
                userDao.save(user);
                //获取id为文件夹名,重新保存
                UserInfo userInfo1 = new UserInfo();
                userInfo1.setUsername(register.getUsername());
                UserInfo userInfo = userDao.findOne(Example.of(userInfo1, UntypedExampleMatcher.matching())).get();
                File mkdir = FileUtil.mkdir(FileUtil.getUserHomePath() + "\\" + userInfo.getId());
                userInfo.setDocumentation(mkdir.getName());
                userDao.save(userInfo);
                return "注册成功";
            } else {
                return "用户名已存在";
            }
        } else {
            return "验证码错误";
        }
    }

    @Override
    public String Login(String username, String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        //判断用户名是否存在
        if (userDao.exists(Example.of(userInfo, UntypedExampleMatcher.matching()))){
            UserInfo user = userDao.findOne(Example.of(userInfo, UntypedExampleMatcher.matching())).get();
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

    @Override
    public Boolean emailCode(String email) {
        String code = RandomUtil.randomInt(100000, 999999)+"";
        redisUtil.set(email+"code",code,60*10);
        MailAccount mailAccount = new MailAccount();
        mailAccount.setFrom("1444504585@qq.com");
        mailAccount.setPass("ffsxjowrffptghic");
        MailUtil.send(mailAccount,email,"markdown在线文档系统","验证码为："+code,false);
        return true;
    }

    @Override
    public UserInfo getUser(String username) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        return userDao.findOne(Example.of(userInfo, UntypedExampleMatcher.matching())).get();
    }


}

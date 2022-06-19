package com.torch.user;

import cn.hutool.core.io.FileUtil;
import com.torch.user.dao.UserDao;
import com.torch.user.domain.UserInfo;
import com.torch.user.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.UntypedExampleMatcher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserApplicationTests {

    @Autowired
    private UserDao userDao;
    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private IUserService userService;

    @Test
    void contextLoads() {
        //List<UserInfo> all = userDao.findAll();
        //for (UserInfo userInfo : all) {
        //    System.out.println(userInfo);
        //}
        //ValueOperations valueOperations = redisTemplate.opsForValue();
        //valueOperations.set("test","ok");
        //System.out.println(valueOperations.get("test"));

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zzj");
        UserInfo info = userDao.findOne(Example.of(userInfo, UntypedExampleMatcher.matching())).get();

        System.out.println(info);
        //userService.emailCode("q1444504585@163.com");
        //System.out.println(FileUtil.getUserHomeDir());
        //String encode = new BCryptPasswordEncoder().encode("123456");
        //System.out.println(encode);
    }

}

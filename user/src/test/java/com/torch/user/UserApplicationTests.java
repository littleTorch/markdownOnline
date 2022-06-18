package com.torch.user;

import com.torch.user.dao.UserDao;
import com.torch.user.domain.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class UserApplicationTests {

    @Autowired
    private UserDao userDao;
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        //List<UserInfo> all = userDao.findAll();
        //for (UserInfo userInfo : all) {
        //    System.out.println(userInfo);
        //}
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("test","ok");
        System.out.println(valueOperations.get("test"));

    }

}

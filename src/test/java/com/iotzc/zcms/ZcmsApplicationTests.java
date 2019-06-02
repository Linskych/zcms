package com.iotzc.zcms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.iotzc.zcms.model.User;
import com.iotzc.zcms.redis.RedisCacheManager;
import com.iotzc.zcms.service.UserService;

@RunWith(SpringRunner.class)
@MapperScan("com.iotzc.zcms.dao")
@SpringBootTest
public class ZcmsApplicationTests {

    @Autowired
    public RedisCacheManager redisCacheManager;
    @Autowired
    public UserService userSerice;
    
    @Test
    public void contextLoads() {
        User user = new User();
        user.setPhone("123456789");
        user.setStatus(0);
        userSerice.saveUser(user);
    }

}

package com.icecream.shares;

import com.icecream.shares.pojo.User;
import com.icecream.shares.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthControllerTest {
    @Autowired
    public UserService userService;

    @Test
    public void testSelectUser(){
        User user = userService.findUserByPhone("18959121464");
        System.out.println(user);
        
    }
}

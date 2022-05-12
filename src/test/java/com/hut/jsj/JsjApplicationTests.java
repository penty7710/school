package com.hut.jsj;


import com.hut.jsj.dto.UserLogin;
import com.hut.jsj.service.impl.InfoServiceImpl;
import com.hut.jsj.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;


@SpringBootTest
class JsjApplicationTests {
    @Autowired
    InfoServiceImpl infoService;

    @Autowired
    UserServiceImpl userService;
    @Test
    void contextLoads()  {

    }

}

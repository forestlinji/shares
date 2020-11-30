package com.icecream.shares;

import com.baomidou.mybatisplus.core.toolkit.EncryptUtils;
import com.icecream.shares.utils.MD5Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
class SharesApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(MD5Utils.cptry("1" + "123456" + "1"));
    }

}

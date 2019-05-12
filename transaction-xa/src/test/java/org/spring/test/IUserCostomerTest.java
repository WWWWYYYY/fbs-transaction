package org.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.App;
import org.spring.service.IUserCostomerService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = App.class)//一个项目中可能写了多个启动类，建议指定启动类
@RunWith(SpringRunner.class)
public class IUserCostomerTest {

    @Resource
    private  IUserCostomerService iUserCostomerService;

    @Test
    public void testAdd() {
        iUserCostomerService.add();
    }
}

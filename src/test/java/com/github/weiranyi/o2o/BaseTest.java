package com.github.weiranyi.o2o;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author https://github.com/weiranyi/
 * @ClassName: BaseTest
 * @Description: TODO(配置spring和junit整合 ， junit启动时加载springIOC容器 spring - test, junit)
 * @date 2021/8/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class BaseTest {
    @Test
    public void test() {

    }
}

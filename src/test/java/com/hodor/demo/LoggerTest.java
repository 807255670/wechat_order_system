package com.hodor.demo;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
    //private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test(){
//        logger.debug("debug...");
//        logger.info("info...");
//        logger.error("error...");
        String username = "root";
        String password = "123456";
        log.debug("debug..");
        log.info("username: {},password:{}",username,password);
        log.error("error...");
    }
}

package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateLog implements Runnable{
    Logger logger = LoggerFactory.getLogger(CreateLog.class);

    @Override public void run() {
        for(int i = 0 ; i< 10000 ; i++)
        logger.info("asas年恢复大师多发阿达水电费阿斯蒂芬阿斯蒂芬阿萨德发发: {}", System.currentTimeMillis());
    }
}

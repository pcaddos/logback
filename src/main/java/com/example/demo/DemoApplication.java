package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);


        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 60, TimeUnit.MICROSECONDS, queue);
        for (int i=0; i<7; i++){
            Runnable task = new CreateLog();
            pool.execute(task);
        }
        pool.shutdown();
    }

}

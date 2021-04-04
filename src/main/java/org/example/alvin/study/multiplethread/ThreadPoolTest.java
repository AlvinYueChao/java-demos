package org.example.alvin.study.multiplethread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author AlvinPower
 * @date 2021/4/4
 */
@Slf4j
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService pool1 = Executors.newFixedThreadPool(1);

        pool1.execute(() -> {
            log.info("1");
        });

        pool1.execute(() -> {
            log.info("2");
            int k = 1/0;
        });

        pool1.execute(() -> {
            log.info("3");
        });

        /*
        ExecutorService pool2 = Executors.newCachedThreadPool();
        ExecutorService pool3 = Executors.newSingleThreadExecutor();
        ScheduledExecutorService pool4 = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService pool5 = Executors.newSingleThreadScheduledExecutor();
        */
    }
}

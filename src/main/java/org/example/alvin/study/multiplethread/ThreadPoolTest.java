package org.example.alvin.study.multiplethread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author AlvinPower
 * @date 2021/4/4
 */
@Slf4j
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService pool1 = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1));

        log.info("main start");

        pool1.execute(() -> {
            log.info("1 start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            log.info("1 end");
        });

        pool1.execute(() -> {
            log.info("2 start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            log.info("2 end");
        });

        pool1.execute(() -> {
            log.info("3 start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            log.info("3 end");
        });

        log.info("main end");
        pool1.shutdown();
        pool1.shutdownNow();

        /*
        ExecutorService pool1 = Executors.newFixedThreadPool(1);
        ExecutorService pool2 = Executors.newCachedThreadPool();
        ExecutorService pool3 = Executors.newSingleThreadExecutor();
        ScheduledExecutorService pool4 = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService pool5 = Executors.newSingleThreadScheduledExecutor();
        */
    }
}

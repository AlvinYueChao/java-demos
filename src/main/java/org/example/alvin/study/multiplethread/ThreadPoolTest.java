package org.example.alvin.study.multiplethread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author AlvinPower
 * @date 2021/4/4
 */
@Slf4j
public class ThreadPoolTest {
    private static final String MAIN_START = "main start";
    private static final String MAIN_END = "main end";

    public static void main(String[] args) {
        testScheduleWithFixedDelay();
        /*
        ExecutorService pool1 = Executors.newFixedThreadPool(1);
        ExecutorService pool2 = Executors.newCachedThreadPool();
        ExecutorService pool3 = Executors.newSingleThreadExecutor();
        ScheduledExecutorService pool4 = Executors.newScheduledThreadPool(1);
        */
    }

    /**
     * scheduleWithFixedDelay is more safe than scheduleAtFixedDelay, because it can make sure the execution times and interval between each
     * task execution ending and the next starting
     */
    private static void testScheduleWithFixedDelay() {
        log.info(MAIN_START);
        ScheduledExecutorService pool5 = Executors.newSingleThreadScheduledExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        pool5.scheduleWithFixedDelay(() -> {
            log.info("task start");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            log.info("task end");
            countDownLatch.countDown();
        }, 2, 2, TimeUnit.SECONDS);

        try {
            countDownLatch.await();
            pool5.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            log.info(MAIN_END);
        }
    }

    private static void testSchedule() {
        log.info(MAIN_START);
        ScheduledExecutorService pool5 = Executors.newSingleThreadScheduledExecutor();
        pool5.schedule(() -> {
            log.info("execute task");
        }, 2, TimeUnit.SECONDS);
        pool5.shutdown();
        log.info(MAIN_END);
    }

    private static void testShutdown() {
        ExecutorService pool1 = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1));

        log.info(MAIN_START);

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

        log.info(MAIN_END);
        pool1.shutdown();
    }
}

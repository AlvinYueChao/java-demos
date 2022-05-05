package org.example.alvin.study.multiplethread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CDLVsCBTest {

  public static void main(String[] args) {
    countDownLatchTest();
  }

  private static void countDownLatchTest() {
    CountDownLatch latch = new CountDownLatch(3);
    ExecutorService pool = Executors.newFixedThreadPool(8);
    for (int i = 0; i < 3; i++) {
      int finalI = i;
      pool.execute(() -> {
        log.info("执行任务-{}", finalI);
        try {
          Thread.sleep((finalI + 1) * 1000L);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          log.warn("任务-{}被打断", finalI, e);
        }
        latch.countDown();
      });
    }
    log.info("等待所有任务完成...");
    try {
      latch.await();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.info("等待被打断", e);
    }
    log.info("所有任务完成!");
    pool.shutdown();
  }

  private static void cyclicBarrierTest() {

  }
}

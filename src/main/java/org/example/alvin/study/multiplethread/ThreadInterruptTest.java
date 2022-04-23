package org.example.alvin.study.multiplethread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadInterruptTest {

  public static void main(String[] args) {
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        log.info("Current interrupted status: {}", Thread.currentThread().isInterrupted());
        log.info("{}", i);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          // InterruptedException, set isInterrupted = false
          log.warn("Current interrupted status: {}", Thread.currentThread().isInterrupted());
          // set isInterrupted = true.
          Thread.currentThread().interrupt();
        }
        if (Thread.currentThread().isInterrupted()) {
          log.info("======");
        }
      }
    });
    t1.start();
    t1.interrupt();
  }
}

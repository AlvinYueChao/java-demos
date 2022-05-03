package org.example.alvin.study.multiplethread;

import java.util.concurrent.Semaphore;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SemaphoreTest {

  public static void main(String[] args) {
    Semaphore windows = new Semaphore(3);
    for (int i = 0; i < 5; i++) {
      new Thread(() -> {
        try {
          windows.acquire();
          log.info("{}: 开始买票.", Thread.currentThread().getName());
          Thread.sleep(2000);
          log.info("{}: 买票成功", Thread.currentThread().getName());
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          log.warn("无法正常买票.", e);
        } finally {
          windows.release();
        }
      }, "thread-" + i).start();
    }
  }
}

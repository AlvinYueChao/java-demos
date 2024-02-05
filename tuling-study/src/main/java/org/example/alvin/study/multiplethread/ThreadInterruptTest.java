package org.example.alvin.study.multiplethread;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadInterruptTest {

  public static void main(String[] args) {
    ReentrantLock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    AtomicBoolean suitCondition = new AtomicBoolean(false);
    new Thread(
            () -> {
              lock.lock();
              try {
                log.info("{}: started", Thread.currentThread().getName());
                while (!suitCondition.get()) {
                  condition1.await();
                }
                log.info("{}: ended", Thread.currentThread().getName());
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("{} waiting has been interrupted.", Thread.currentThread().getName());
              } finally {
                lock.unlock();
              }
            },
            "thread1")
        .start();
    new Thread(
            () -> {
              lock.lock();
              try {
                log.info("{}: started", Thread.currentThread().getName());
                while (!suitCondition.get()) {
                  condition1.await();
                }
                log.info("{}: ended", Thread.currentThread().getName());
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("{} waiting has been interrupted.", Thread.currentThread().getName());
              } finally {
                lock.unlock();
              }
            },
            "thread2")
        .start();

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    lock.lock();
    try {
      log.info("Release the condition after 3s...");
      suitCondition.compareAndExchange(false, true);
      condition1.signalAll();
    } finally {
      lock.unlock();
    }
  }

  private static void test1() {
    Thread t1 =
        new Thread(
            () -> {
              for (int i = 0; i < 10; i++) {
                log.info("Current interrupted status: {}", Thread.currentThread().isInterrupted());
                log.info("{}", i);
                try {
                  Thread.sleep(1000);
                } catch (InterruptedException e) {
                  // InterruptedException, set isInterrupted = false
                  log.warn(
                      "Current interrupted status: {}", Thread.currentThread().isInterrupted());
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

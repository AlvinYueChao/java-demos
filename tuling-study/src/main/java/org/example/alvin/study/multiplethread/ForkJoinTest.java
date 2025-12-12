package org.example.alvin.study.multiplethread;

import java.util.concurrent.ForkJoinPool;
import lombok.extern.slf4j.Slf4j;

/**
 * @author AlvinPower
 * @date 2021/4/18
 */
@Slf4j
public class ForkJoinTest {

  public static void main(String[] args) {
    ForkJoinPool pool = new ForkJoinPool(4);
    pool.submit(
        () -> {
          log.info("{} executing task", Thread.currentThread().getName());
        });
    pool.submit(
        () -> {
          log.info("{} executing task", Thread.currentThread().getName());
        });
    pool.submit(
        () -> {
          log.info("{} executing task", Thread.currentThread().getName());
        });
    pool.submit(
        () -> {
          log.info("{} executing task", Thread.currentThread().getName());
        });
    pool.submit(
        () -> {
          log.info("{} executing task", Thread.currentThread().getName());
        });
    log.info("Main completed");
  }
}

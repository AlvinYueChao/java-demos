package org.example.alvin.demo.schedulerandwebflux;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @author AlvinPower
 * @date 2021/6/20
 */
@Slf4j
public class SchedulerDemo {

  private static final String EXECUTE_TASK_ONE = "execute task #1";
  private static final String EXECUTE_TASK_TWO = "execute task #2";
  private static final String EXECUTED_COMPLETELY = "executed completely";

  public static void main(String[] args) {
//    elasticDemo();
//    boundedElasticDemo();
    parallelDemo();
//    singleDemo();
//    immediateDemo();
//    fromExecutorDemo();
//    fromExecutorServiceDemo();
  }

  private static void fromExecutorServiceDemo() {
    Scheduler fromExecutorService = Schedulers.fromExecutorService(Executors.newSingleThreadExecutor());
  }

  private static void fromExecutorDemo() {
    Scheduler fromExecutor = Schedulers.fromExecutor(Executors.newSingleThreadExecutor());
  }

  private static void immediateDemo() {
    Scheduler immediate = Schedulers.immediate();
  }

  private static void singleDemo() {
    CountDownLatch taskCount = new CountDownLatch(2);
    Scheduler single = Schedulers.single();
    single.schedule(() -> {
      log.info("execute task #1");
      taskCount.countDown();
    }, 1, TimeUnit.SECONDS);
    single.schedule(() -> {
      log.info("execute task #2");
      taskCount.countDown();
    }, 1, TimeUnit.SECONDS);

    try {
      taskCount.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    log.info("Executed completely");
  }

  private static void parallelDemo() {
    CountDownLatch taskCount = new CountDownLatch(2);
    /**
     * {@link Schedulers#PARALLEL_SUPPLIER}
     * {@link ParallelScheduler#ParallelScheduler(int, java.util.concurrent.ThreadFactory)}
     */
    Scheduler parallel = Schedulers.parallel();
    parallel.schedule(() -> {
      log.info(EXECUTE_TASK_ONE);
      taskCount.countDown();
    }, 1, TimeUnit.SECONDS);
    parallel.schedule(() -> {
      log.info(EXECUTE_TASK_TWO);
      taskCount.countDown();
    }, 1, TimeUnit.SECONDS);

    try {
      taskCount.await();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
    log.info(EXECUTED_COMPLETELY);
  }

  private static void boundedElasticDemo() {
    int taskCount = 401;
    CountDownLatch countDownLatch = new CountDownLatch(taskCount);
    /**
     * VM options: -Dreactor.schedulers.defaultBoundedElasticSize=40 -Dreactor.schedulers.defaultBoundedElasticQueueSize=10
     * corePoolSize=1, maximumPoolSize=Integer.MAX_VALUE, keepAliveTime=10L,
     * {@link Schedulers#BOUNDED_ELASTIC_SUPPLIER}
     * {@link Schedulers#DEFAULT_BOUNDED_ELASTIC_SIZE}: maximum threads
     * {@link Schedulers#DEFAULT_BOUNDED_ELASTIC_QUEUESIZE}: maximum tasks queued for per-thread.
     * {@link BoundedElasticScheduler#BoundedElasticScheduler(int, int, java.util.concurrent.ThreadFactory, long, java.time.Clock)}
     */
    Scheduler boundedElastic = Schedulers.boundedElastic();
    for (int i = 0; i < taskCount; i++) {
      int finalI = i;
      boundedElastic.schedule(() -> {
        log.info("executing task #{}", finalI);
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        countDownLatch.countDown();
      }, 1, TimeUnit.SECONDS);
    }
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
    log.info(EXECUTED_COMPLETELY);
  }

  private static void elasticDemo() {
    int taskCount = 101;
    CountDownLatch countDownLatch = new CountDownLatch(taskCount);
    /**
     * corePoolSize=1, maximumPoolSize=Integer.MAX_VALUE, keepAliveTime=10L, timeUnit=MILLISECONDS, workQueue=BlockingQueue
     * {@link Schedulers#ELASTIC_SUPPLIER}
     * {@link reactor.core.scheduler.ElasticScheduler#ElasticScheduler(java.util.concurrent.ThreadFactory, int)}
     * {@link ScheduledThreadPoolExecutor#ScheduledThreadPoolExecutor(int, java.util.concurrent.ThreadFactory)}
     */
    Scheduler elastic = Schedulers.elastic();
    for (int i = 0; i < taskCount; i++) {
      int finalI = i;
      elastic.schedule(() -> {
        log.info("executing task #{}", finalI);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        countDownLatch.countDown();
      }, 1, TimeUnit.SECONDS);
    }

    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
    log.info(EXECUTED_COMPLETELY);
  }
}

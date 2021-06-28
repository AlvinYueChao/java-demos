package org.example.alvin.demo.scheduler;

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

  private static final String START_EXECUTING = "start executing";
  private static final String EXECUTED_COMPLETELY = "executed completely";

  public static void main(String[] args) {
//    elasticDemo();
//    boundedElasticDemo();
//    parallelDemo();
//    singleDemo();
//    immediateDemo();
//    fromExecutorDemo();
    fromExecutorServiceDemo();
  }

  private static void fromExecutorServiceDemo() {
    int taskCount = 10;
    CountDownLatch countDownLatch = new CountDownLatch(taskCount);
    Scheduler fromExecutorService = Schedulers.fromExecutorService(Executors.newCachedThreadPool());
    log.info(START_EXECUTING);
    for (int i = 0; i < taskCount; i++) {
      int finalI = i;
      fromExecutorService.schedule(() -> {
        log.info("executing task #{}", finalI);
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          e.printStackTrace();
        }
        countDownLatch.countDown();
      });
    }

    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
    log.info(EXECUTED_COMPLETELY);
  }

  private static void fromExecutorDemo() {
    int taskCount = 10;
    CountDownLatch countDownLatch = new CountDownLatch(taskCount);
    Scheduler fromExecutor = Schedulers.fromExecutor(command -> {
      log.info("{} executing task", Thread.currentThread().getName());
      command.run();
    });
    log.info(START_EXECUTING);
    for (int i = 0; i < taskCount; i++) {
      int finalI = i;
      fromExecutor.schedule(() -> {
        log.info("executing task #{}", finalI);
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          e.printStackTrace();
        }
        countDownLatch.countDown();
      });
    }

    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
    log.info(EXECUTED_COMPLETELY);
  }

  private static void immediateDemo() {
    int taskCount = 10;
    CountDownLatch countDownLatch = new CountDownLatch(taskCount);
    Scheduler immediate = Schedulers.immediate();
    log.info(START_EXECUTING);
    for (int i = 0; i < taskCount; i++) {
      int finalI = i;
      immediate.schedule(() -> {
        log.info("executing task #{}", finalI);
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          e.printStackTrace();
        }
        countDownLatch.countDown();
      });
    }

    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
    log.info(EXECUTED_COMPLETELY);
  }

  private static void singleDemo() {
    int taskCount = 10;
    CountDownLatch countDownLatch = new CountDownLatch(taskCount);
    Scheduler single = Schedulers.single();
    log.info(START_EXECUTING);
    for (int i = 0; i < taskCount; i++) {
      int finalI = i;
      single.schedule(() -> {
        log.info("execute task #{}", finalI);
        try {
          Thread.sleep(12000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
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

  private static void parallelDemo() {
    int taskCount = 5;
    CountDownLatch countDownLatch = new CountDownLatch(taskCount);
    /**
     * VM options: -Dreactor.schedulers.defaultPoolSize=4
     * {@link Schedulers#PARALLEL_SUPPLIER}
     * {@link ParallelScheduler#ParallelScheduler(int, java.util.concurrent.ThreadFactory)}
     */
    Scheduler parallel = Schedulers.parallel();
    log.info(START_EXECUTING);
    for (int i = 0; i < taskCount; i++) {
      int finalI = i;
      parallel.schedule(() -> {
        log.info("executing task #{}", finalI);
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
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

  private static void boundedElasticDemo() {
    int taskCount = 40;
    CountDownLatch countDownLatch = new CountDownLatch(taskCount);
    /**
     * VM options: -Dreactor.schedulers.defaultBoundedElasticSize=4 -Dreactor.schedulers.defaultBoundedElasticQueueSize=10
     * corePoolSize=1, maximumPoolSize=Integer.MAX_VALUE, keepAliveTime=10L,
     * {@link Schedulers#BOUNDED_ELASTIC_SUPPLIER}
     * {@link Schedulers#DEFAULT_BOUNDED_ELASTIC_SIZE}: maximum threads
     * {@link Schedulers#DEFAULT_BOUNDED_ELASTIC_QUEUESIZE}: maximum tasks queued for per-thread.
     * {@link BoundedElasticScheduler#BoundedElasticScheduler(int, int, java.util.concurrent.ThreadFactory, long, java.time.Clock)}
     */
    Scheduler boundedElastic = Schedulers.boundedElastic();
    log.info(EXECUTED_COMPLETELY);
    for (int i = 0; i < taskCount; i++) {
      int finalI = i;
      boundedElastic.schedule(() -> {
        log.info("executing task #{}", finalI);
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
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
          Thread.currentThread().interrupt();
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

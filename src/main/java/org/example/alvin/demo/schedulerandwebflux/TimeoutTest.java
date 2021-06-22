package org.example.alvin.demo.schedulerandwebflux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @author AlvinPower
 * @date 2021/6/22
 */
@Slf4j
public class TimeoutTest {

  public static void main(String[] args) {
    boSubscribeDemo();
  }

  private static void boSubscribeDemo() {
    Mono<String> resultMono1 = mockRestfulRequest1();
    Mono<String> resultMono2 = mockRestfulRequest2();

  }

  private static void subscribeLast_withElasticSchedulerDemo() {
    Scheduler elastic = Schedulers.elastic();
    Mono<String> resultMono1 = mockRestfulRequest1();
    Mono<String> resultMono2 = mockRestfulRequest2();

  }

  private static Mono<String> mockRestfulRequest1() {
    log.info("Working on processing request...");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
    return Mono.just("successful result #1");
  }

  private static Mono<String> mockRestfulRequest2() {
    log.info("Working on processing request...");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
    return Mono.just("successful result #2");
  }
}

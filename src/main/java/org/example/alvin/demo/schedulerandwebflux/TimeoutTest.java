package org.example.alvin.demo.schedulerandwebflux;

import java.time.Duration;
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
    subscribeLast_withElasticSchedulerDemo();
  }

  private static void subscribeLast_withElasticSchedulerDemo() {
    Scheduler elastic = Schedulers.elastic();
    TimeoutTest instance = new TimeoutTest();
    Mono<String> startMono = Mono.just("start parameters");
    startMono.flatMap(instance::mockRestfulRequest1)
        .subscribeOn(elastic)
        .timeout(Duration.ofSeconds(3))
        .flatMap(instance::mockRestfulRequest2)
        .subscribeOn(elastic)
        .timeout(Duration.ofSeconds(4))
        .subscribe();
  }

  private Mono<String> mockRestfulRequest1(String value) {
    log.info("Working on processing request #1...");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
    return Mono.just("successful result #1, result: " + value);
  }

  private Mono<String> mockRestfulRequest2(String value) {
    log.info("Working on processing request #2...");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
    return Mono.just("successful result #2, result: " + value);
  }
}

package org.example.alvin.demo.webflux.mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author AlvinPower
 * @date 2021/6/29
 */
@Slf4j
public class MonoDemo {

  public static void main(String[] args) {
    monoMultipleThreadsTimeoutDemo();
  }

  private static void monoMultipleThreadsTimeoutDemo() {
    Mono<String> startMono = Mono.just("start");
    String result = startMono
        .flatMap(x -> {
          log.info("received message: {}", x);
          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
          return Mono.just("#1 enriched: " + x);
        })
        .timeout(Duration.ofSeconds(3))
        .onErrorResume(throwable -> {
          log.warn("Caught exception, apply fallback behavior #1", throwable);
          return Mono.just("item from backup #1");
        })
        .publishOn(Schedulers.elastic())
        .flatMap(y -> {
          log.info("received message: {}", y);
          try {
            Thread.sleep(3000);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
          return Mono.just("#2 enriched: " + y);
        })
        .timeout(Duration.ofSeconds(4))
        .onErrorResume(throwable -> {
          log.warn("Caught exception, apply fallback behavior #2", throwable);
          return Mono.just("item from backup #2");
        })
        .block();
    log.info("result: {}", result);
  }

  private static void monoSingleThreadTimeoutDemo() {
    Mono<String> startMono = Mono.just("start");
    String result = startMono
        .map(x -> {
          log.info("received message: {}", x);
          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
          return "#1 enriched: " + x;
        })
        .timeout(Duration.ofSeconds(3))
        .onErrorResume(throwable -> {
          log.warn("Caught exception, apply fallback behavior #1", throwable);
          return Mono.just("item from backup #1");
        })
        .map(y -> {
          log.info("received message: {}", y);
          try {
            Thread.sleep(3000);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
          return "#2 enriched: " + y;
        })
        .timeout(Duration.ofSeconds(4))
        // there is no timeoutException thrown if I set the second timeout to 6s (6s > 2s + 3s)
//        .timeout(Duration.ofSeconds(6))
        .onErrorResume(throwable -> {
          log.warn("Caught exception, apply fallback behavior #2", throwable);
          return Mono.just("item from backup #2");
        })
        .block();
    log.info("result: {}", result);
  }

  private static void justDemo() {
    Mono<String> result = Mono.just("start");
    String resultStr = result.block();
    log.info(resultStr);
  }

  private static void justOrEmptyDemo() {
    Mono<String> directValue = Mono.justOrEmpty("direct value");
    String resultStr1 = directValue.block();
    log.info("justOrEmpty with direct value: {}", resultStr1);

    Mono<Object> nullValue = Mono.justOrEmpty(Optional.ofNullable(null));
    Object nullResult = nullValue.block();
    log.info("justOrEmpty with null value: {}", nullResult);
  }

  private static void fromSupplierDemo() {
    Mono<List<String>> listMono = Mono.fromSupplier(() -> {
      log.info("Supplier started...");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      return Arrays.asList("1", "2", "3");
    });
    listMono.subscribe(strings -> {
      log.info("{}", strings.toString());
    });
  }
}

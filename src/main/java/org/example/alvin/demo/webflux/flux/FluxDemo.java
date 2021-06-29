package org.example.alvin.demo.webflux.flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * @author AlvinPower
 * @date 2021/6/29
 */
@Slf4j
public class FluxDemo {

  public static void main(String[] args) {
    fluxFromStreamDemo();
  }

  private static void fluxJustWithArraysDemo() {
    Flux<String> arrayFlux = Flux.just("1", "2", "3");
    arrayFlux.subscribe(s -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      log.info("result: {}", s);
    });
  }

  private static void fluxFromArrayDemo() {
    Flux<String> arrayFlux = Flux.fromArray(new String[]{"1", "2", "3"});
    arrayFlux.subscribe(item -> {
      log.info("subscribe started...");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      log.info("Item: {}", item);
    });
  }

  private static void fluxFromIterableDemo() {
    List<String> strArrays = Arrays.asList("1", "2", "3");
    Flux<String> stringFlux = Flux.fromIterable(strArrays);
    stringFlux.subscribe(item -> {
      log.info("subscribe started...");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      log.info("Item: {}", item);
    });
  }

  private static void fluxRangeDemo() {
    Flux<Integer> range = Flux.range(1, 10);
    range.subscribe(integer -> {
      log.info("subscribe started...");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      log.info("Item: {}", integer);
    });
  }

  private static void fluxFromStreamDemo() {
    Stream<String> stringStream = Arrays.asList("1", "2", "3").stream();
    Flux<String> stringFlux = Flux.fromStream(() -> stringStream);
    stringFlux.subscribe(integer -> {
      log.info("subscribe started...");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      log.info("Item: {}", integer);
    });
  }
}

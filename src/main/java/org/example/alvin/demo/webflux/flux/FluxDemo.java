package org.example.alvin.demo.webflux.flux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * @author AlvinPower
 * @date 2021/6/29
 */
@Slf4j
public class FluxDemo {

  public static void main(String[] args) {
    fluxFromArrayDemo();
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
}

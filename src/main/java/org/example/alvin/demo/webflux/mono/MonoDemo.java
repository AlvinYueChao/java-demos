package org.example.alvin.demo.webflux.mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @author AlvinPower
 * @date 2021/6/29
 */
@Slf4j
public class MonoDemo {

  public static void main(String[] args) {
    fromSupplierDemo();
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

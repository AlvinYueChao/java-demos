package org.example.alvin.demo.webflux.mono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @author AlvinPower
 * @date 2021/6/29
 */
@Slf4j
public class MonoDemo {

  public static void main(String[] args) {
    justOrEmptyDemo();
  }

  private static void justDemo() {
    Mono<String> result = Mono.just("start");
    String resultStr = result.block();
    log.info(resultStr);
  }

  private static void justOrEmptyDemo() {
    Mono<String> direct_value = Mono.justOrEmpty("direct value");
    String resultStr1 = direct_value.block();
    log.info("justOrEmpty with direct value: {}", resultStr1);

    Mono<Object> nullValue = Mono.justOrEmpty(null);
    Object nullResult = nullValue.block();
    log.info("justOrEmpty with null value: {}", nullResult);
  }
}

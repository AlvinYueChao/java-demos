package org.example.alvin.demo;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author AlvinPower
 * @date 2021/4/29
 */
@Slf4j
public class HashMapTest {

  public static void main(String[] args) {
    Map<MyObj, String> map = new HashMap<>();
    Map<MyObj, MyObj> map2 = new HashMap<>();
    MyObj obj = new MyObj(1, "name1");
    MyObj obj2 = new MyObj(2, "name2");
    map.put(obj, "value1");
    map2.put(obj2, obj);
    String value1 = map.get(obj);
    log.info("get result: {}", value1);
    map.put(obj, "value2");
    String value2 = map.get(map2.get(obj2));
    log.info("get result: {}", value2);
  }

  @Data
  @AllArgsConstructor
  private static class MyObj {
    private long id;
    private String name;
  }
}

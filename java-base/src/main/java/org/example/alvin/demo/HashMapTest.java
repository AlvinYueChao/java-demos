package org.example.alvin.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
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
    /*Map<MyObj, String> map = new HashMap<>();
    Map<MyObj, MyObj> map2 = new HashMap<>();
    MyObj obj = new MyObj(1, "name1");
    MyObj obj2 = new MyObj(2, "name2");
    map.put(obj, "value1");
    map2.put(obj2, obj);
    String value1 = map.get(obj);
    log.info("get result: {}", value1);
    map.put(obj, "value2");
    String value2 = map.get(map2.get(obj2));
    log.info("get result: {}", value2);*/
    int[] nums = {1, 3, 3};
    int result = new HashMapTest().majorityElement2(nums);
    log.info("result: {}", result);
  }

  public int majorityElement(int[] nums) {
    Map<Integer, Integer> countMap = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      countMap.compute(nums[i], (k, v) -> v == null ? 1 : ++v);
    }

    AtomicReference<Integer> result = new AtomicReference<>(0);
    countMap.forEach(
        (k, v) -> {
          if (v > nums.length / 2) {
            result.set(k);
          }
        });

    return result.get();
  }

  public int majorityElement2(int[] nums) {
    int count = 0;
    Integer candidate = null;

    for (int num : nums) {
      if (count == 0) {
        candidate = num;
      }
      count += (num == candidate) ? 1 : -1;
    }

    return candidate;
  }

  @Data
  @AllArgsConstructor
  private static class MyObj {
    private long id;
    private String name;
  }
}

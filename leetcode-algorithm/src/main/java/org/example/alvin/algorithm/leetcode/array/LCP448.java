package org.example.alvin.algorithm.leetcode.array;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class LCP448 {
  public static void main(String[] args) {
    int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
//    int[] nums = {1};
    List<Integer> result = new LCP448().findDisappearedNumbers(nums);
    log.info(result.toString());
  }

  public List<Integer> findDisappearedNumbers(int[] nums) {
    int[] tmp = new int[nums.length + 1];
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      tmp[nums[i]] = 1;
    }
    for (int i = 1; i < tmp.length; i++) {
      if (tmp[i] != 1) {
        result.add(i);
      }
    }
    return result;
  }
}

package org.example.alvin.algorithm.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LCP207 {
  public static void main(String[] args) {

  }

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    Map<Integer, Integer> map1 = new HashMap<>(); // 课程-课程的前置课程数，当且仅当前置课程数为0时，可学习该课程
    Map<Integer, List<Integer>> map2 = new HashMap<>(); // 课程-以该课程作为前置课程的所有课程
    List<Integer> res = new ArrayList<>(); // 已完成课程

    for (int i = 0; i < numCourses; i++) {
      map1.put(i, 0);
      map2.put(i, new ArrayList<>());
    }

    for (int[] coursePair : prerequisites) {
      // 前置课程数加一
      Integer preCount = map1.get(coursePair[0]);
      map1.put(coursePair[0], ++preCount);
      // 记录后置课程
      map2.get(coursePair[1]).add(coursePair[0]);
    }

    int nextCourse = 0;
    while (!map1.isEmpty()) {
      for(Map.Entry<Integer, Integer> entry : map1.entrySet()) {
        if (entry.getValue() == 0) {
          res.add(entry.getKey());
        }
      }
      if (nextCourse == res.size()) {
        return false;
      }
      for(int i = nextCourse; i < res.size(); i++) {
        map1.remove(res.get(i));
        for(Integer course : map2.get(res.get(i))) {
          map1.put(course, map1.get(course) - 1);
        }
      }
      nextCourse = res.size();
    }

    return res.size() == numCourses;
  }
}

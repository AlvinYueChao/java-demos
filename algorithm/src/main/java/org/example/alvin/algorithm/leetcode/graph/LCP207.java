package org.example.alvin.algorithm.leetcode.graph;

import java.util.*;

public class LCP207 {
  public static void main(String[] args) {}

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    return bfsImpl(numCourses, prerequisites);
  }

  private static boolean bfsImpl(int numCourses, int[][] prerequisites) {
    List<List<Integer>> courseAndItsPostCourses = new ArrayList<>();
    int[] preCourseCount = new int[numCourses];
    for (int i = 0; i < numCourses; i++) {
      courseAndItsPostCourses.add(new ArrayList<>());
    }
    for (int[] prerequisite : prerequisites) {
      courseAndItsPostCourses.get(prerequisite[1]).add(prerequisite[0]);
      preCourseCount[prerequisite[0]]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (preCourseCount[i] == 0) {
        queue.offer(i);
      }
    }

    int visited = 0;
    while (!queue.isEmpty()) {
      visited++;

      int course = queue.poll();
      for (int postCourse : courseAndItsPostCourses.get(course)) {
        preCourseCount[postCourse]--;
        if (preCourseCount[postCourse] == 0) {
          queue.offer(postCourse);
        }
      }
    }

    return visited == numCourses;
  }
}

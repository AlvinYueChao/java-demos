package org.example.alvin.algorithm.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class LCP71 {
  public static void main(String[] args) {
    String path1 = "/home/";
    String path2 = "/home//foo/";
    String path3 = "/home/user/Documents/../Pictures";
    String path4 = "/../";
    String path5 = "/.../a/../b/c/../d/./";
    String path6 = "/../..ga/b/.f..d/..../e.baaeeh./.a";

    LCP71 lcp71 = new LCP71();
    System.out.println(lcp71.simplifyPath(path1));
    System.out.println(lcp71.simplifyPath(path2));
    System.out.println(lcp71.simplifyPath(path3));
    System.out.println(lcp71.simplifyPath(path4));
    System.out.println(lcp71.simplifyPath(path5));
    System.out.println(lcp71.simplifyPath(path6));
  }

  public String simplifyPath(String path) {
    Deque<String> deque = new ArrayDeque<>();
    String[] pathItems = path.split("/");
    for(String pathItem : pathItems) {
      if(pathItem.isEmpty() || pathItem.equals(".")) {
        continue;
      }
      if(pathItem.equals("..")) {
        if(!deque.isEmpty()) {
          deque.pollLast();
        }
      } else {
        deque.offer(pathItem);
      }
    }
    StringBuilder sb = new StringBuilder("/");
    while (!deque.isEmpty()) {
      if(sb.length() == 1) {
        sb.append(deque.poll());
      } else {
        sb.append("/").append(deque.poll());
      }
    }
    return sb.toString();
  }
}

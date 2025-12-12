package org.example.alvin.algorithm.leetcode.stack;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LCP155 {
  public static void main(String[] args) {
    String[] operations = {
      "MinStack",
      "push",
      "push",
      "push",
      "top",
      "pop",
      "getMin",
      "pop",
      "getMin",
      "pop",
      "push",
      "top",
      "getMin",
      "push",
      "top",
      "getMin",
      "pop",
      "getMin"
    };
    int[][] inputs = {
      {},
      {2147483646},
      {2147483646},
      {2147483647},
      {},
      {},
      {},
      {},
      {},
      {},
      {2147483647},
      {},
      {},
      {-2147483648},
      {},
      {},
      {},
      {}
    };
    List<Integer> results = new ArrayList<>();
    MinStack instance = null;
    for (int i = 0; i < operations.length; i++) {
      try {
        String operation = operations[i];
        if ("MinStack".equals(operation)) {
          instance = new LCP155.MinStack();
          results.add(null);
        } else {
          // 使用反射调用方法
          Class<?> clazz = instance.getClass();
          Method method;
          Object result = null;

          switch (operation) {
            case "push" -> {
              method = clazz.getMethod(operation, int.class);
              result = method.invoke(instance, inputs[i][0]);
            }
            case "pop", "top", "getMin" -> {
              method = clazz.getMethod(operation);
              result = method.invoke(instance);
            }
            default -> throw new IllegalArgumentException("Invalid operation: " + operation);
          }

          // 如果返回类型是void，返回值为null
          if (result == null) {
            results.add(null);
          } else {
            results.add((Integer) result);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    System.out.println(results);
  }

  static class MinStack {

    private final List<Node> arr;

    public MinStack() {
      arr = new ArrayList<>();
    }

    public void push(int val) {
      Node curr = new Node();
      Node top = null;
      if (!arr.isEmpty()) {
        top = arr.getLast();
      }
      int currentMinVal = top == null ? val : top.currentMinVal;

      curr.val = val;
      curr.currentMinVal = Math.min(val, currentMinVal);
      arr.add(curr);
    }

    public void pop() {
      arr.removeLast();
    }

    public int top() {
      Node top = arr.getLast();
      return top.val;
    }

    public int getMin() {
      Node top = arr.getLast();
      return top.currentMinVal;
    }

    static class Node {
      int val;
      int currentMinVal;

      Node() {}
    }
  }
}

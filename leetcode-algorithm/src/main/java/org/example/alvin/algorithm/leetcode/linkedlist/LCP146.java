package org.example.alvin.algorithm.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LCP146 {
  public static void main(String[] args) {
    String[] operations = {"LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"};
    int[][] params = {{2}, {1, 1}, {2, 2}, {1}, {3, 3}, {2}, {4, 4}, {1}, {3}, {4}};
    LCP146 lcp146 = new LCP146();
    LRUCache lruCache = null;
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < operations.length; i++) {
      switch (operations[i]) {
        case "LRUCache" -> {
          lruCache = lcp146.new LRUCache(params[i][0]);
          res.add(null);
        }
        case "put" -> {
          lruCache.put(params[i][0], params[i][1]);
          res.add(null);
        }
        case "get" -> {
          int result = lruCache.get(params[i][0]);
          res.add(result);
        }
      }
    }
    System.out.println(res);
  }

  class LRUCache {

    class Node {
      int key;
      int value;
      Node pre;
      Node next;

      public Node(int key, int value) {
        this.key = key;
        this.value = value;
      }
    }

    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;
    private int currentSize;
    private int maxSize;

    public LRUCache(int capacity) {
      cache = new HashMap<>();
      currentSize = 0;
      maxSize = capacity;
      head = new Node(-1, -1);
      tail = new Node(-1, -1);
      head.next = tail;
      tail.pre = head;
    }

    public int get(int key) {
      Node node = cache.get(key);
      if (node != null) {
        removeNode(node);
        addNodeToHead(node);
        return node.value;
      } else {
        return -1;
      }
    }

    public void put(int key, int value) {
      Node node = cache.get(key);
      if (node != null) {
        node.value = value;
        removeNode(node);
        addNodeToHead(node);
      } else {
        node = new Node(key, value);
        if (currentSize == maxSize) {
          cache.remove(tail.pre.key);
          removeNode(tail.pre);
        }
        cache.put(key, node);
        addNodeToHead(node);
      }
    }

    private void removeNode(Node node) {
      node.pre.next = node.next;
      node.next.pre = node.pre;
      currentSize--;
    }

    private void addNodeToHead(Node node) {
      node.pre = head;
      node.next = head.next;
      node.pre.next = node;
      node.next.pre = node;
      currentSize++;
    }
  }
}

package org.example.alvin.algorithm.leetcode.graph;

import java.util.*;

public class LCP133 {
  public static void main(String[] args) {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    node1.neighbors = new ArrayList<>();
    node1.neighbors.add(node2);
    node1.neighbors.add(node4);
    node2.neighbors = new ArrayList<>();
    node2.neighbors.add(node1);
    node2.neighbors.add(node3);
    node3.neighbors = new ArrayList<>();
    node3.neighbors.add(node2);
    node3.neighbors.add(node4);
    node4.neighbors = new ArrayList<>();
    node4.neighbors.add(node1);
    node4.neighbors.add(node3);

    LCP133 lcp133 = new LCP133();
    System.out.println(lcp133.cloneGraph(node1));
  }

  public Node cloneGraph(Node node) {
    return fastImpl(node);
  }

  private Node fastImpl(Node node) {
    Map<Node, Node> map = new HashMap<>();
    Node ans = fastImplHelper(node, map);
    return ans;
  }

  private Node fastImplHelper(Node node, Map<Node, Node> visitedMap) {
    if (node == null) {
      return null;
    }
    if (visitedMap.containsKey(node)) {
      return visitedMap.get(node);
    } else {
      Node clonedNode = new Node(node.val);
      visitedMap.put(node, clonedNode);
      for (Node neighbor : node.neighbors) {
        clonedNode.neighbors.add(fastImplHelper(neighbor, visitedMap));
      }
      return clonedNode;
    }
  }

  private Node slowImpl(Node node) {
    // Map 记录visited node，Queue记录图遍历过程，Queue为空则克隆结束
    Map<Integer, Node> map = new HashMap<>();
    Queue<Node> queue = new LinkedList<>();
    Queue<Node> queue1 = new LinkedList<>();

    if (node == null) {
      return null;
    } else {
      Node ans = new Node(node.val);
      map.put(node.val, ans);
      queue.offer(node);
      queue1.offer(ans);

      while (!queue.isEmpty()) {
        Node curr = queue.poll(); // 原节点
        Node curr1 = queue1.poll(); // 新节点

        List<Node> neighbors = curr.neighbors;
        curr1.neighbors = new ArrayList<>();
        for (Node node1 : neighbors) {
          if (map.containsKey(node1.val)) {
            curr1.neighbors.add(map.get(node1.val));
          } else {
            Node clonedNode = new Node(node1.val);
            curr1.neighbors.add(clonedNode);

            map.put(node1.val, clonedNode);
            queue.offer(node1);
            queue1.offer(clonedNode);
          }
        }
      }

      return ans;
    }
  }
}

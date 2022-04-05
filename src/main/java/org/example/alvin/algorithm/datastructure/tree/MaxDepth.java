package org.example.alvin.algorithm.datastructure.tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class MaxDepth {
  public static void main(String[] args) {
    TreeBin<String> root = new TreeBin<>("root");
    TreeBin<String> child1_1 = new TreeBin<>("child1_1");
    TreeBin<String> child1_2 = new TreeBin<>("child1_2");
    TreeBin<String> child2_1 = new TreeBin<>("child2_1");
    TreeBin<String> child2_2 = new TreeBin<>("child2_2");
    TreeBin<String> child2_3 = new TreeBin<>("child2_3");
    TreeBin<String> child3_1 = new TreeBin<>("child3_1");
    TreeBin<String> child4_1 = new TreeBin<>("child4_1");

    root.left = child1_1;
    root.right = child1_2;
    child1_1.left = child2_1;
    child1_1.right = child2_2;
    child1_2.right = child2_3;
    child2_2.right = child3_1;
    child3_1.left = child4_1;

    System.out.println("二叉树的最大深度问题:");
    System.out.println("递归实现: " + recursiveImpl(root));
    System.out.println("非递归实现: " + iterativeImpl(root));
  }

  private static int recursiveImpl(TreeBin<String> node) {
    int maxDepth;
    if (node == null) {
      maxDepth = 0;
    } else {
      maxDepth = Math.max(recursiveImpl(node.left), recursiveImpl(node.right)) + 1;
    }

    return maxDepth;
  }

  private static int iterativeImpl(TreeBin<String> root) {
    if (root == null) {
      return 0;
    }

    Queue<TreeBin<String>> binQueue = new ArrayDeque<>();
    binQueue.add(root);
    int maxDepth = 0;

    while (!binQueue.isEmpty()) {
      int last = binQueue.size();
      int current = 0;

      while (current < last) {
        TreeBin<String> currentNode = binQueue.poll();
        if (currentNode != null && currentNode.left != null) {
          binQueue.add(currentNode.left);
        }
        if (currentNode != null && currentNode.right != null) {
          binQueue.add(currentNode.right);
        }
        current++;
      }
      maxDepth++;
    }

    return maxDepth;
  }
}

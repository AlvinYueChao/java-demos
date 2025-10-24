package org.example.alvin.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LCP129 {
  public static void main(String[] args) {
    Integer[] nums = new Integer[]{4, 9, 0, 5, null, 1};
    TreeNode root = TreeNode.convertToTreeNode(nums);
    LCP129 lcp129 = new LCP129();
    System.out.println(lcp129.sumNumbers(root));
  }

  public int sumNumbers(TreeNode root) {
//    return dfs(root, 0);
    return bfs(root);
  }

  private int dfs(TreeNode root, int sum) {
    if (root == null) {
      return 0;
    }
    sum = sum * 10 + root.val;
    if (root.left == null && root.right == null) {
      return sum;
    }
    return dfs(root.left, sum) + dfs(root.right, sum);
  }

  private int bfs(TreeNode root) {
    Queue<Integer> numQueue = new LinkedList<>();
    Queue<TreeNode> nodeQueue = new LinkedList<>();
    if (root != null) {
      numQueue.offer(root.val);
      nodeQueue.offer(root);
    }
    int sum = 0;
    while (!nodeQueue.isEmpty()) {
      TreeNode currentNode = nodeQueue.poll();
      int currentNum = numQueue.poll();
      if (currentNode.left == null && currentNode.right == null) {
        sum += currentNum;
      }
      if (currentNode.left != null) {
        numQueue.offer(currentNum * 10 + currentNode.left.val);
        nodeQueue.offer(currentNode.left);
      }
      if (currentNode.right != null) {
        numQueue.offer(currentNum * 10 + currentNode.right.val);
        nodeQueue.offer(currentNode.right);
      }
    }
    return sum;
  }
}

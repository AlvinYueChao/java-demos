package org.example.alvin.algorithm.leetcode.tree;

import java.util.*;

public class LCP236 {
  public static void main(String[] args) {
    Integer[] arr = new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
    TreeNode root = TreeNode.convertToTreeNode(arr);
    int pVal = 6;
    int qVal = 7;
    TreeNode p = null;
    TreeNode q = null;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      if (node.val == pVal) {
        p = node;
      } else if (node.val == qVal) {
        q = node;
      }
      if (node.left != null) {
        queue.offer(node.left);
      }
      if (node.right != null) {
        queue.offer(node.right);
      }
    }
    TreeNode ans = new LCP236().lowestCommonAncestor(root, p, q);
    System.out.println(ans.val);
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    return slowImpl(root, p, q);
  }

  private static TreeNode slowImpl(TreeNode root, TreeNode p, TreeNode q) {
    Map<TreeNode, TreeNode> childToParentMap = new HashMap<>();
    Map<TreeNode, Boolean> visitedMap = new HashMap<>();

    // 先序遍历记录每个节点及其父节点
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode curr = stack.pop();
      if (curr.right != null) {
        stack.push(curr.right);
        childToParentMap.put(curr.right, curr);
      }
      if (curr.left != null) {
        stack.push(curr.left);
        childToParentMap.put(curr.left, curr);
      }
    }

    while (p != null) {
      visitedMap.put(p, true);
      p = childToParentMap.get(p);
    }

    while (q != null) {
      Boolean visited = visitedMap.get(q);
      if (visited != null && visited) {
        return q;
      }
      q = childToParentMap.get(q);
    }

    return null;
  }

  private static TreeNode fastImpl(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) {
      return root;
    }

    TreeNode left = fastImpl(root.left, p, q);
    TreeNode right = fastImpl(root.right, p, q);
    if (left != null && right != null) {
      return root;
    }
    return left != null ? left : right;
  }
}

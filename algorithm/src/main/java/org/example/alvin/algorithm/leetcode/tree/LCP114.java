package org.example.alvin.algorithm.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode 114
 */
public class LCP114 {
  public static void main(String[] args) {
    Integer[] nums = new Integer[]{1, 2, 5, 3, 4, null, 6};
    TreeNode root = TreeNode.convertToTreeNode(nums);
    LCP114 lcp114 = new LCP114();
    lcp114.flatten2(root);
    TreeNode.printTreeNodeWithNull(root);
  }

  public void flatten(TreeNode root) {
    while (root != null) {
      if (root.left == null) {
        root = root.right;
      } else {
        TreeNode newRootLeft = root.left;
        while (newRootLeft.right != null) {
          newRootLeft = newRootLeft.right;
        }
        newRootLeft.right = root.right;
        root.right = root.left;
        root.left = null;
      }
    }
  }

  public void flatten2(TreeNode root) {
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode curr = root;
    while (curr != null) {
      if (curr.right != null) {
        stack.push(curr.right);
      }
      if (curr.left != null) {
        curr.right = curr.left;
        curr.left = null;
      } else if (!stack.isEmpty()) {
        curr.right = stack.pop();
      } else {
        curr.right = null;
      }
      curr = curr.right;
    }
  }
}


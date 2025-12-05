package org.example.alvin.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode() {}

  public TreeNode(int val) {
    this.val = val;
  }

  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  public static TreeNode convertToTreeNode(Integer[] nums) {
    TreeNode root = null;
    if (nums == null || nums.length == 0) {
      return root;
    } else {
      root = new TreeNode(nums[0]);
      Queue<TreeNode> queue = new LinkedList<>();
      queue.offer(root);
      int index = 1;
      while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        TreeNode left = null;
        if (index < nums.length) {
          Integer value = nums[index++];
          left = value != null ? new TreeNode(value) : null;
        }
        node.left = left;
        if (left != null) {
          queue.offer(left);
        }
        TreeNode right = null;
        if (index < nums.length) {
          Integer value = nums[index++];
          right = value != null ? new TreeNode(value) : null;
        }
        node.right = right;
        if (right != null) {
          queue.offer(right);
        }
      }
    }
    return root;
  }

  public static void printTreeNodeWithNull(TreeNode root) {
    TreeNodeIteration.BinaryTreeTraversalBreadthFirst bfs =
        new TreeNodeIteration.BinaryTreeTraversalBreadthFirst();
    System.out.println(bfs.levelOrderFlatWithNull(root));
  }
}

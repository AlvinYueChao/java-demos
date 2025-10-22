package org.example.alvin.algorithm.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

public class LCP106 {
  public static void main(String[] args) {
    int[] inorder = new int[]{9, 3, 15, 20, 7};
    int[] postorder = new int[]{9, 15, 7, 20, 3};
    TreeNode root = new LCP106().buildTree(inorder, postorder);
    TreeNodeIteration.BinaryTreeTraversalBreadthFirst bfs = new TreeNodeIteration.BinaryTreeTraversalBreadthFirst();
    System.out.println(bfs.levelOrderFlatWithNull(root));
  }

  public TreeNode buildTree(int[] inorder, int[] postorder) {
    Map<Integer, Integer> inorderIdxes = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      inorderIdxes.put(inorder[i], i);
    }
    return buildTreeHelper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, inorderIdxes);
  }

  private TreeNode buildTreeHelper(int[] inorder, int inLeft, int inRight, int[] postorder, int postLeft,
                                   int postRight, Map<Integer, Integer> inorderIdxes) {
    if (postLeft > postRight) {
      return null;
    }
    TreeNode root = new TreeNode(postorder[postRight]);
    int index = inorderIdxes.get(root.val);
    root.left = buildTreeHelper(inorder, inLeft, index - 1, postorder, postLeft, postLeft + index - inLeft - 1,
      inorderIdxes);
    root.right = buildTreeHelper(inorder, index + 1, inRight, postorder, postLeft + index - inLeft, postRight - 1,
      inorderIdxes);
    return root;
  }
}

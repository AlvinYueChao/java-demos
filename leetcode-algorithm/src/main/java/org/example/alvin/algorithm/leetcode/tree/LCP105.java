package org.example.alvin.algorithm.leetcode.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LCP105 {
  public static void main(String[] args) {
    int[] preorder = new int[]{3, 9, 20, 15, 7};
    int[] inorder = new int[]{9, 3, 15, 20, 7};
//    int[] preorder = new int[]{1, 2};
//    int[] inorder = new int[]{2, 1};
    TreeNode root = new LCP105().buildTree(preorder, inorder);
    TreeNodeIteration.BinaryTreeTraversalBreadthFirst bfs = new TreeNodeIteration.BinaryTreeTraversalBreadthFirst();
    System.out.println(bfs.levelOrderFlatWithNull(root));
  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
//    return slowerImpl(preorder, inorder);

    Map<Integer, Integer> inorderIdxes = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      inorderIdxes.put(inorder[i], i);
    }
    return fasterImpl(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inorderIdxes);
  }

  private TreeNode fasterImpl(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight, Map<Integer, Integer> inorderIdxes) {
    if (preLeft > preRight) {
      return null;
    }
    TreeNode root = new TreeNode(preorder[preLeft]);
    int index = inorderIdxes.get(root.val);
    root.left = fasterImpl(preorder, preLeft + 1, preLeft + index - inLeft, inorder, inLeft, index - 1, inorderIdxes);
    root.right = fasterImpl(preorder, preLeft + index - inLeft + 1, preRight, inorder, index + 1, inRight, inorderIdxes);
    return root;
  }

  private TreeNode slowerImpl(int[] preorder, int[] inorder) {
    if (preorder.length == 0) {
      return null;
    } else if (preorder.length == 1) {
      return new TreeNode(preorder[0]);
    } else {
      TreeNode root = new TreeNode(preorder[0]);
      int index = 0;
      for (int i = 0; i < inorder.length; i++) {
        if (inorder[i] == preorder[0]) {
          index = i;
          break;
        }
      }
      root.left = buildTree(Arrays.copyOfRange(preorder, 1, index + 1), Arrays.copyOfRange(inorder, 0, index));
      root.right = buildTree(Arrays.copyOfRange(preorder, index + 1, preorder.length), Arrays.copyOfRange(inorder, index + 1, inorder.length));
      return root;
    }
  }

}

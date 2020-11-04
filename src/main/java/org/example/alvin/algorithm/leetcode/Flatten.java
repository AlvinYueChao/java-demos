package org.example.alvin.algorithm.leetcode;

import java.util.Stack;

/**
 * leetcode 114
 */
public class Flatten {
    public static void main(String[] args) {

    }

    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            }
            else {
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

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}

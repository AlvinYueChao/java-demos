package org.example.alvin.algorithm.datastructure.tree;

import java.util.Stack;

public class PostOrderTraversal {
  public static void main(String[] args) {
    TreeBin<String> root = new TreeBin<>("root");
    TreeBin<String> child1_1 = new TreeBin<>("child1_1");
    TreeBin<String> child1_2 = new TreeBin<>("child1_2");
    TreeBin<String> child2_1 = new TreeBin<>("child2_1");
    TreeBin<String> child2_2 = new TreeBin<>("child2_2");
    TreeBin<String> child2_3 = new TreeBin<>("child2_3");
    root.left = child1_1;
    root.right = child1_2;
    child1_1.left = child2_1;
    child1_1.right = child2_2;
    child1_2.right = child2_3;

    System.out.println("后序遍历递归实现：");
    recursiveImplement(root);
    System.out.println("\n后序遍历非递归实现：");
    iterativeImplement(root);
  }

  private static void recursiveImplement(TreeBin<String> root) {
    if (root != null) {
      recursiveImplement(root.left);
      recursiveImplement(root.right);
      System.out.print(root.value + ", ");
    }
  }

  private static void iterativeImplement(TreeBin<String> root) {
    if (root != null) {
      Stack<TreeBin<String>> binStack = new Stack<>();
      Stack<TreeBin<String>> markStack = new Stack<>();
      binStack.push(root);
      while (!binStack.isEmpty()) {
        root = binStack.pop();
        markStack.push(root);
        if (root.left != null) {
          binStack.push(root.left);
        }
        if (root.right != null) {
          binStack.push(root.right);
        }
      }
      while (!markStack.isEmpty()) {
        System.out.print(markStack.pop().value + ", ");
      }
    }
  }
}

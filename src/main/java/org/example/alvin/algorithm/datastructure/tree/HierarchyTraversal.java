package org.example.alvin.algorithm.datastructure.tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class HierarchyTraversal {
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

        System.out.println("层序遍历：");
        hierarchyTraversal(root);
    }

    private static void hierarchyTraversal(TreeBin<String> root) {
        if (root == null) {
            return;
        }

        Queue<TreeBin<String>> binQueue = new ArrayDeque<>();
        binQueue.add(root);
        while (!binQueue.isEmpty()) {
            TreeBin<String> currentNode = binQueue.poll();
            System.out.print(currentNode.value + ", ");
            if (currentNode.left != null) {
                binQueue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                binQueue.add(currentNode.right);
            }
        }
    }
}

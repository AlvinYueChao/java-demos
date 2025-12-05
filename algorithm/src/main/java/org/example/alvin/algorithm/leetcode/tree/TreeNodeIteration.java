package org.example.alvin.algorithm.leetcode.tree;

import java.util.*;

/** 树的遍历总体分两类：深度优先(DFS)和广度优先(BFS) 常见DFS: 先序遍历，中序遍历，后序遍历 常见BFS: 层序遍历 */
public class TreeNodeIteration {

  // 深度优先遍历
  /** 递归实现 */
  static class BinaryTreeTraversalRecursive {
    private List<Integer> resultList;

    /** 前序遍历：根-左-右 */
    List<Integer> preorderTraversal(TreeNode root) {
      resultList = new ArrayList<>();
      preorderHelper(root);
      return resultList;
    }

    private void preorderHelper(TreeNode root) {
      if (root == null) {
        return;
      }
      resultList.add(root.val);
      preorderHelper(root.left);
      preorderHelper(root.right);
    }

    /** 中序遍历：左-根-右 */
    List<Integer> inorderTraversal(TreeNode root) {
      resultList = new ArrayList<>();
      inorderHelper(root);
      return resultList;
    }

    private void inorderHelper(TreeNode root) {
      if (root == null) {
        return;
      }
      inorderHelper(root.left);
      resultList.add(root.val);
      inorderHelper(root.right);
    }

    /** 后序遍历：左-右-根 */
    List<Integer> postorderTraversal(TreeNode root) {
      resultList = new ArrayList<>();
      postorderHelper(root);
      return resultList;
    }

    private void postorderHelper(TreeNode root) {
      if (root == null) {
        return;
      }
      postorderHelper(root.left);
      postorderHelper(root.right);
      resultList.add(root.val);
    }
  }

  /** 迭代实现 */
  static class BinaryTreeTraversalIterative {
    /**
     * 前序遍历：根-左-右 迭代思路： 1. 使用一个栈，将根节点压入。 2. 当栈不为空时，弹出一个节点 (curr)。 3. 访问该节点 (curr.val)。 4. [关键] 先将其
     * 右子节点 压入栈 (如果存在)。 5. [关键] 再将其 左子节点 压入栈 (如果存在)。 (因为栈是后进先出 LIFO，所以先压右再压左，才能保证左先于右被访问)
     */
    List<Integer> preorderTraversal(TreeNode root) {
      List<Integer> resultList = new ArrayList<>();

      Stack<TreeNode> stack = new Stack<>();
      if (root != null) {
        stack.push(root);
      }

      while (!stack.isEmpty()) {
        TreeNode curr = stack.pop();
        resultList.add(curr.val);

        if (curr.right != null) {
          stack.push(curr.right);
        }

        if (curr.left != null) {
          stack.push(curr.left);
        }
      }

      return resultList;
    }

    /**
     * 中序遍历 (左 -> 根 -> 右) 迭代思路： 1. 使用一个栈和一个指针 (curr)。 2. 指针 curr 指向 root。 3. [循环] 当 curr 不为 null 或
     * 栈不为空时： a. [一路向左] 当 curr 不为 null 时，将 curr 压入栈，并令 curr = curr.left。 b. [访问] 当 curr 为 null 时
     * (说明左边到底了)，从栈中弹出一个节点 (node)。 c. 访问该节点 (node.val)。 d. [转向右] 令 curr = node.right (准备处理右子树)。
     */
    List<Integer> inorderTraversal(TreeNode root) {
      List<Integer> resultList = new ArrayList<>();

      Stack<TreeNode> stack = new Stack<>();
      TreeNode curr = root;
      while (curr != null || !stack.isEmpty()) {
        while (curr != null) {
          stack.push(curr);
          curr = curr.left;
        }

        curr = stack.pop();
        resultList.add(curr.val);

        curr = curr.right;
      }

      return resultList;
    }

    /**
     * 后序遍历 (左 -> 右 -> 根) 迭代思路 (巧妙法)： 后序是 "左 -> 右 -> 根"。 我们可以实现一个 "根 -> 右 -> 左" 的遍历，然后将结果反转，就得到了 "左
     * -> 右 -> 根"。 "根 -> 右 -> 左" 的实现非常类似前序 ("根 -> 左 -> 右")，只需要把压栈顺序反过来 (先压左，再压右) 即可。 * 我们使用
     * LinkedList 的 addFirst() 方法，可以在添加时就实现反转效果。
     */
    List<Integer> postorderTraversal(TreeNode root) {
      // 使用 LinkedList 在头部添加，等效于最后反转
      LinkedList<Integer> resultList = new LinkedList<>();

      Stack<TreeNode> stack = new Stack<>();
      if (root != null) {
        stack.push(root);
      }
      while (!stack.isEmpty()) {
        TreeNode curr = stack.pop();
        resultList.addFirst(curr.val);

        if (curr.left != null) {
          stack.push(curr.left);
        }
        if (curr.right != null) {
          stack.push(curr.right);
        }
      }

      return resultList;
    }
  }

  // 广度优先遍历
  static class BinaryTreeTraversalBreadthFirst {

    List<List<Integer>> levelOrder(TreeNode root) {
      List<List<Integer>> results = new ArrayList<>();

      Queue<TreeNode> queue = new LinkedList<>();
      if (root != null) {
        queue.offer(root);
      }

      while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> currentLevel = new ArrayList<>();

        for (int i = 0; i < levelSize; i++) {
          TreeNode curr = queue.poll();
          currentLevel.add(curr.val);

          if (curr.left != null) {
            queue.offer(curr.left);
          }
          if (curr.right != null) {
            queue.offer(curr.right);
          }
        }
        results.add(currentLevel);
      }

      return results;
    }

    List<Integer> levelOrderFlat(TreeNode root) {
      List<Integer> resultList = new ArrayList<>();

      Queue<TreeNode> queue = new LinkedList<>();
      if (root != null) {
        queue.offer(root);
      }

      while (!queue.isEmpty()) {
        int levelSize = queue.size();

        for (int i = 0; i < levelSize; i++) {
          TreeNode curr = queue.poll();
          resultList.add(curr.val);

          if (curr.left != null) {
            queue.offer(curr.left);
          }
          if (curr.right != null) {
            queue.offer(curr.right);
          }
        }
      }

      return resultList;
    }

    List<Integer> levelOrderFlatWithNull(TreeNode root) {
      List<Integer> resultList = new ArrayList<>();
      Queue<TreeNode> queue = new LinkedList<>();
      queue.offer(root);
      while (!queue.isEmpty()) {
        int levelSize = queue.size();

        int nullCount = 0;
        for (TreeNode treeNode : queue) {
          if (treeNode == null) {
            nullCount++;
          }
        }
        if (nullCount == levelSize) {
          break;
        }

        for (int i = 0; i < levelSize; i++) {
          TreeNode curr = queue.poll();
          if (curr != null) {
            resultList.add(curr.val);
            queue.offer(curr.left);
            queue.offer(curr.right);
          } else {
            resultList.add(null);
          }
        }
      }
      return resultList;
    }
  }

  public static void main(String[] args) {
    /*
     * 构造一个二叉树:
     * 1
     * / \
     * 2   3
     * / \
     * 4   5
     */
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);

    System.out.println("--- 递归实现 ---");
    BinaryTreeTraversalRecursive recursive = new BinaryTreeTraversalRecursive();

    // 前序: 1 -> 2 -> 4 -> 5 -> 3
    System.out.println("前序 (递归): " + recursive.preorderTraversal(root));

    // 中序: 4 -> 2 -> 5 -> 1 -> 3
    System.out.println("中序 (递归): " + recursive.inorderTraversal(root));

    // 后序: 4 -> 5 -> 2 -> 3 -> 1
    System.out.println("后序 (递归): " + recursive.postorderTraversal(root));

    System.out.println("\n--- 迭代实现 ---");
    BinaryTreeTraversalIterative iterative = new BinaryTreeTraversalIterative();

    // 前序: 1 -> 2 -> 4 -> 5 -> 3
    System.out.println("前序 (迭代): " + iterative.preorderTraversal(root));

    // 中序: 4 -> 2 -> 5 -> 1 -> 3
    System.out.println("中序 (迭代): " + iterative.inorderTraversal(root));

    // 后序: 4 -> 5 -> 2 -> 3 -> 1
    System.out.println("后序 (迭代): " + iterative.postorderTraversal(root));

    // --- 测试按层分组的 BFS ---
    System.out.println("--- 按层分组 (Level Order) ---");
    BinaryTreeTraversalBreadthFirst bfs = new BinaryTreeTraversalBreadthFirst();
    List<List<Integer>> levelOrderResult = bfs.levelOrder(root);
    // 预期输出: [[1], [2, 3], [4, 5]]
    System.out.println(levelOrderResult);

    // --- 测试扁平化的 BFS ---
    System.out.println("\n--- 扁平化 (BFS) ---");
    List<Integer> flatResult = bfs.levelOrderFlat(root);
    // 预期输出: [1, 2, 3, 4, 5]
    System.out.println(flatResult);
  }
}

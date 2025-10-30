package org.example.alvin.algorithm.niuke;

import java.util.*;

/**
 * 时间显示：1秒 空间限制：262MB 描述：
 * 贪吃蛇是一个经典游戏，蛇的身体有若干方格连接而成，身体随蛇头移动，蛇头触碰到食物时，蛇的长度会增加一格。蛇头和身体的任一方格或者游戏版图边界碰撞时，游戏结束。
 * 下面让我们来完成贪吃蛇游戏的模拟。给定一个N*M的数组arr，代表N*M个方格组成的版图，贪吃蛇每次移动一个方格。若arr[i][j]=='H',表示该方格为贪吃蛇的起始位置；
 * 若arr[i][j]=='F',表示该方格为食物，若arr[i][j]=='E',表示该方格为空格。
 * 贪吃蛇初始长度为1，初始移动方向为向左。为给定一系列贪吃蛇的移动操作，返回操作后蛇的长度，如果在操作执行完之前已经游戏结束，返回游戏结束时蛇的长度 贪吃蛇移动、吃食物和碰撞处理的细节见下面图示：
 * 图1：截取了贪吃蛇移动的一个中间状态，H表示蛇头，F表示食物，数字为蛇身体各节的编号，蛇为向左移动，此时蛇头和食物已经相邻
 * 图2：蛇头向左移动1格，蛇头和食物重叠，注意此时食物的格子成为了新的蛇头，第一节身体移动到蛇头位置，第二节身体移动到第一节身体的位置，以此类推，最后添加第四节身体到原来第三节身体的位置
 * 图3：蛇头继续向左移动一格，身体的各节按上述规则移动，此时蛇头已经和边界相邻，但还未碰撞 图4：蛇头继续向左移动一格，此时蛇头已经超过边界，发生碰撞，游戏结束
 * 图5和图6给出一个蛇头和身体碰撞的例子，蛇为向上移动。图5时蛇头和第7节身体相邻，但还未碰撞；图6蛇头向上移动一格，此时蛇头和第8节身体都移动到了原来第7节身体的位置，发生碰撞，游戏结束
 * 输入描述：
 * 输入第一行为空格分隔的字母，代表贪吃蛇的移动操作。字母取值为U、D、L、R和G、U、D、L、R分别表示贪吃蛇往上、下、左、右转向，转向时贪吃蛇不移动，G表示贪吃蛇按当前的方向移动一格。用例保证输入的操作正确。第二行为空格分隔的两个数，
 * 指定N和M，为数组的行和列数。余下N行每行是空格分隔的M个字母。字母取值为H、F和E，H表示贪吃蛇的起始位置，F表示食物，E表示该方格为空。用例保证有且只有一个H，而F和E会有多个 输出描述：
 * 输出一个数字，为蛇的长度 示例1： 输入： D G G 3 3 F F F F F H E F E 输出： 1 说明：
 * 贪吃蛇一开始就向下转向并且移动两步，此时蛇头已经和边界碰撞，游戏结束，蛇头没有吃任何食物，故长度为1 示例2： 输入： G G G 3 3 F F F F F H E F E 输出： 3 说明：
 * 贪吃蛇保存最初的向左方向移动三步，此时蛇头已经和边界碰撞，游戏结束，蛇头吃了两个食物，故长度为3
 */
public class Solution09283 {

  // 方向定义：0-上，1-下，2-左，3-右
  private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  // 蛇身节点类
  static class Point {
    int row;
    int col;

    Point(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      Point point = (Point) obj;
      return row == point.row && col == point.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // 读取操作指令
    String[] operations = scanner.nextLine().split(" ");

    // 读取地图尺寸
    String[] dimensions = scanner.nextLine().split(" ");
    int N = Integer.parseInt(dimensions[0]);
    int M = Integer.parseInt(dimensions[1]);

    // 读取地图
    char[][] grid = new char[N][M];
    Point head = null;

    for (int i = 0; i < N; i++) {
      String[] row = scanner.nextLine().split(" ");
      for (int j = 0; j < M; j++) {
        grid[i][j] = row[j].charAt(0);
        if (grid[i][j] == 'H') {
          head = new Point(i, j);
        }
      }
    }

    scanner.close();

    int result = simulateGame(operations, grid, head);
    System.out.println(result);
  }

  /**
   * 模拟贪吃蛇游戏
   *
   * @param operations 操作指令数组
   * @param grid 游戏地图
   * @param initialHead 蛇头初始位置
   * @return 最终蛇的长度
   */
  public static int simulateGame(String[] operations, char[][] grid, Point initialHead) {
    // 使用双端队列存储蛇身，队首为蛇头
    Deque<Point> snake = new LinkedList<>();
    snake.offerFirst(initialHead);

    // 记录蛇身占据的位置
    Set<Point> snakeBody = new HashSet<>();
    snakeBody.add(initialHead);

    // 初始方向为向左（索引2）
    int currentDirection = 2;

    // 地图尺寸
    int rows = grid.length;
    int cols = grid[0].length;

    // 执行操作
    for (String op : operations) {
      if (op.equals("U")) {
        currentDirection = 0;
      } else if (op.equals("D")) {
        currentDirection = 1;
      } else if (op.equals("L")) {
        currentDirection = 2;
      } else if (op.equals("R")) {
        currentDirection = 3;
      } else if (op.equals("G")) {
        // 获取当前蛇头位置
        Point oldHead = snake.peekFirst();

        // 计算新蛇头位置
        int newRow = oldHead.row + DIRECTIONS[currentDirection][0];
        int newCol = oldHead.col + DIRECTIONS[currentDirection][1];
        Point newHead = new Point(newRow, newCol);

        // 检查是否撞墙
        if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
          return snake.size(); // 游戏结束，返回当前长度
        }

        // 检查是否撞到自己
        if (snakeBody.contains(newHead)) {
          return snake.size(); // 游戏结束，返回当前长度
        }

        // 检查新位置是否有食物
        boolean hasFood = grid[newRow][newCol] == 'F';

        // 更新蛇身位置
        snake.offerFirst(newHead);
        snakeBody.add(newHead);

        // 如果没有吃到食物，移除蛇尾
        if (!hasFood) {
          Point tail = snake.pollLast();
          snakeBody.remove(tail);
        }
      }
    }

    // 所有操作执行完毕，返回最终长度
    return snake.size();
  }
}

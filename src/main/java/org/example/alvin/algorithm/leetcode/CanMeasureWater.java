package org.example.alvin.algorithm.leetcode;

import java.util.*;

/**
 * leetcode 365
 */
public class CanMeasureWater {
    public static void main(String[] args) {
        int x = 1, y = 2, z = 3;
        System.out.println(canMeasureWater(x, y, z));
    }

    private static boolean canMeasureWater(int x, int y, int z) {
        if (z == 0) {
            return true;
        }
        if (x + y < z) {
            return false;
        }

        Queue<Map.Entry<Integer, Integer>> queue = new ArrayDeque<>();
        Map.Entry<Integer, Integer> start = new AbstractMap.SimpleEntry<>(0, 0);
        queue.add(start);
        Set<Map.Entry<Integer, Integer>> visited = new HashSet<>();
        visited.add(start);
        while (!queue.isEmpty()) {
            Map.Entry<Integer, Integer> entry = queue.poll();
            int remainX = entry.getKey();
            int remainY = entry.getValue();
            if (remainX == z || remainY == z || remainX + remainY == z) {
                return true;
            }
            if (remainX == 0) {
                // 把第一个桶倒满
                addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(x, remainY));
            }
            if (remainY == 0) {
                // 把第二个桶倒满
                addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(remainX, y));
            }
            if (remainX < x) {
                // 把第一个桶倒空
                addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(remainX, 0));
            }
            if (remainY < y) {
                // 把第二个桶倒空
                addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(0, remainY));
            }

            // y - remainY 是第二个桶还可以再加的水的升数，但是最多只能加 remainX 升水。
            int moveSize = Math.min(remainX, y - remainY);
            // 把第一个桶里的 remainX 升水倒到第二个桶里去。
            addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(remainX - moveSize, remainY + moveSize));
            // 反过来同理，x - remainX 是第一个桶还可以再加的升数，但是最多只能加 remainY 升水。
            moveSize = Math.min(remainY, x - remainX);
            // 把第二个桶里的 remainX 升水倒到第一个桶里去。
            addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(remainX + moveSize, remainY - moveSize));
        }

        return false;
    }

    private static void addIntoQueue(Queue<Map.Entry<Integer, Integer>> queue,
                              Set<Map.Entry<Integer, Integer>> visited,
                              Map.Entry<Integer, Integer> newEntry) {
        if (!visited.contains(newEntry)) {
            visited.add(newEntry);
            queue.add(newEntry);
        }
    }
}
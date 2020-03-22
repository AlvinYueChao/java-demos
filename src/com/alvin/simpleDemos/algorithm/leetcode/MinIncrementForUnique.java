package com.alvin.simpleDemos.algorithm.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * leetcode 945
 * 当数组 A 数量较多时，超出时间限制，待改进
 */
public class MinIncrementForUnique {
    public static void main(String[] args) {
        int[] A = {3, 2, 1, 2, 1, 5, 7};
        System.out.println(minIncrementForUnique(A));
    }

    private static int minIncrementForUnique(int[] A) {
        Set<Map.Entry<Integer, Integer>> entrySet = new HashSet<>();
        List<Map.Entry<Integer, Integer>> needHandleList = new ArrayList<>();
        for (int num : A) {
            createOrAdd(entrySet, needHandleList, num);
        }


        int stepCount = 0;
        while (!needHandleList.isEmpty()) {
            Map.Entry<Integer, Integer> currentEntry = needHandleList.get(0);
            while (currentEntry.getValue() > 1) {
                remove(entrySet, needHandleList, currentEntry.getKey());
                createOrAdd(entrySet, needHandleList, currentEntry.getKey() + 1);
                // 当前元素计数值减一
                currentEntry.setValue(currentEntry.getValue() - 1);
                stepCount++;
            }
        }

        return stepCount;
    }

    private static void createOrAdd(Set<Map.Entry<Integer, Integer>> entrySet, List<Map.Entry<Integer, Integer>> needHandleList, int value) {
        Map<Integer, Integer> allValueMap = entrySet.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (allValueMap.containsKey(value)) {
            Integer newCount = allValueMap.get(value) + 1;
            // 移除旧状态
            entrySet.remove(new AbstractMap.SimpleEntry<>(value, allValueMap.get(value)));
            entrySet.add(new AbstractMap.SimpleEntry<>(value, newCount));
            // 移除旧状态
            needHandleList.remove(new AbstractMap.SimpleEntry<>(value, allValueMap.get(value)));
            needHandleList.add(new AbstractMap.SimpleEntry<>(value, newCount));
            needHandleList.sort(Map.Entry.comparingByKey());
        }
        else {
            entrySet.add(new AbstractMap.SimpleEntry<>(value, 1));
        }
    }

    private static void remove(Set<Map.Entry<Integer, Integer>> entrySet, List<Map.Entry<Integer, Integer>> needHandleList, int value) {
        Map<Integer, Integer> allValueMap = entrySet.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (allValueMap.containsKey(value)) {
            int newCount = allValueMap.get(value) - 1;
            // 移除旧状态
            entrySet.remove(new AbstractMap.SimpleEntry<>(value, allValueMap.get(value)));
            entrySet.add(new AbstractMap.SimpleEntry<>(value, newCount));
            // 移除旧状态
            needHandleList.remove(new AbstractMap.SimpleEntry<>(value, allValueMap.get(value)));
            needHandleList.add(new AbstractMap.SimpleEntry<>(value, newCount));
            needHandleList.sort(Map.Entry.comparingByKey());
            if (newCount == 1) {
                needHandleList.remove(new AbstractMap.SimpleEntry<>(value, 1));
            }
        }
    }
}

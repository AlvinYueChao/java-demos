package com.alvin.simpleDemos.algorithm.datastructure.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class JosephProblem {
    public static void main(String[] args) {
        ListNode<String> first = new ListNode<>("1");
        ListNode<String> second = new ListNode<>("2");
        ListNode<String> third = new ListNode<>("3");
        ListNode<String> fourth = new ListNode<>("4");
        ListNode<String> fifth = new ListNode<>("5");
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = first;
        System.out.println(getSequence(first));
    }

    private static String getSequence(ListNode<String> head) {
        // 当只有一个人参加约瑟夫游戏时，直接返回该人名
        if (head.next == null || head.next == head) {
            return head.val;
        }
        List<String> overPeople = new ArrayList<>();
        int count = 1;
        ListNode<String> preNode = null;
        ListNode<String> iterator = head;
        while (true) {
            while (count < 3) {
                preNode = iterator;
                iterator = iterator.next;
                count++;
            }
            // 当前报数为3的加入加入出局数组并退出游戏
            overPeople.add(iterator.val);
            preNode.next = iterator.next;

            // 本轮出局的下个人重新从1开始报数
            iterator = iterator.next;
            if (iterator == iterator.next) {
                // 当只剩一个人的时候，直接加入出局数组，并退出游系
                overPeople.add(iterator.val);
                break;
            }
            count = 1;
        }
        return overPeople.toString();
    }
}

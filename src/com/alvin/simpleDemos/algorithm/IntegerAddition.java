package com.alvin.simpleDemos.algorithm;

/**
 * LeetCode 2
 */
public class IntegerAddition {
    public static void main(String[] args) {
        ListNode l1_1 = new ListNode(1);
        ListNode l1_2 = new ListNode(2);
        ListNode l1_3 = new ListNode(3);
        ListNode l1_4 = new ListNode(4);
        l1_1.next = l1_2;
        l1_2.next = l1_3;
        l1_3.next = l1_4;

        ListNode l2_1 = new ListNode(3);
        ListNode l2_2 = new ListNode(4);
        l2_1.next = l2_2;

        IntegerAddition integerAddition = new IntegerAddition();
        ListNode result = integerAddition.addTwoNumbers(l1_1, l2_1);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sizeOfL1 = getListNodeSize(l1);
        int sizeOfL2 = getListNodeSize(l2);

        if (sizeOfL1 <= sizeOfL2) {
            int numOfEmpty = sizeOfL2 - sizeOfL1;
            l1 = fillEmpty(l1, numOfEmpty);
        } else {
            int numOfEmpty = sizeOfL1 - sizeOfL2;
            l2 = fillEmpty(l2, numOfEmpty);
        }

        ListNode reverseL1 = reverseListNode(l1);
        ListNode reverseL2 = reverseListNode(l2);

        int sum = 0;
        int promotion = 0;
        ListNode result = new ListNode(0);
        ListNode iterator = result;
        while (reverseL1 != null) {
            sum = reverseL1.val + reverseL2.val + promotion;
            promotion = sum / 10;
            sum = sum % 10;
            iterator.next = new ListNode(sum);
            iterator = iterator.next;
            reverseL1 = reverseL1.next;
            reverseL2 = reverseL2.next;
        }

        if (promotion != 0) {
            iterator = new ListNode(promotion);
        }

        result = result.next;

        return reverseListNode(result);
    }

    public int getListNodeSize(ListNode linked) {
        int size = 0;

        while (linked != null) {
            linked = linked.next;
            size++;
        }

        return size;
    }

    public ListNode fillEmpty(ListNode linked, int numOfEmpty) {
        if (linked == null || numOfEmpty <= 0) {
            return linked;
        }

        for (int i = 0; i < numOfEmpty; i++) {
            ListNode preNode = new ListNode(0);
            preNode.next = linked;
            linked = preNode;
        }

        return linked;
    }

    public ListNode reverseListNode(ListNode linked) {
        if (linked == null) {
            return linked;
        }

        ListNode preNode = null;
        while (linked != null) {
            ListNode nextNode = linked.next;
            linked.next = preNode;
            preNode = linked;
            linked = nextNode;
        }

        return preNode;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

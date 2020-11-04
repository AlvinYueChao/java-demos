package org.example.alvin.algorithm.datastructure.linkedlist;

public class GetMiddleElement {
    public static void main(String[] args) {
        ListNode<String> first = new ListNode<>("aa");
        ListNode<String> second = new ListNode<>("bb");
        ListNode<String> third = new ListNode<>("cc");
        ListNode<String> fourth = new ListNode<>("dd");
        ListNode<String> fifth = new ListNode<>("ee");
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;

        System.out.println(getMiddleElement(first).val);
    }

    private static ListNode<?> getMiddleElement(ListNode<?> head) {
        if (head == null) {
            return null;
        }

        ListNode<?> slow = head;
        ListNode<?> fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

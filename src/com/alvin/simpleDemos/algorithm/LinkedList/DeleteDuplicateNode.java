package com.alvin.simpleDemos.algorithm.LinkedList;

public class DeleteDuplicateNode {
    public static void main(String[] args) {
        DeleteDuplicateNode instance = new DeleteDuplicateNode();

        // original linkedList: 1->2->3->3->4->4->5, result: 1->2->5
        ListNode pHead = new ListNode(1);
        ListNode p1 = new ListNode(2);
        ListNode p2 = new ListNode(3);
        ListNode p3 = new ListNode(3);
        ListNode p4 = new ListNode(4);
        ListNode p5 = new ListNode(4);
        ListNode p6 = new ListNode(5);
        pHead.next = p1;
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        p5.next = p6;
        ListNode result = instance.deleteDuplicateNode(pHead);

        // original linkedList: 1->1->2, result: 2
        ListNode pHead1 = new ListNode(1);
        ListNode p11 = new ListNode(1);
        ListNode p12 = new ListNode(2);
        pHead1.next = p11;
        p11.next = p12;
        ListNode result1 = instance.deleteDuplicateNode(pHead1);

        // original linkedList: 1->1->2->2, result: null
        ListNode pHead2 = new ListNode(1);
        ListNode p21 = new ListNode(1);
        ListNode p22 = new ListNode(2);
        ListNode p23 = new ListNode(2);
        pHead2.next = p21;
        p21.next = p22;
        p22.next = p23;
        ListNode result2 = instance.deleteDuplicateNode(pHead2);
    }

    public ListNode deleteDuplicateNode(ListNode pHead){
        ListNode preNode = null;
        ListNode pNode = pHead;
        while(pNode != null){
            ListNode pNext = pNode.next;
            boolean needDelete = false;
            if(pNext != null && pNext.val == pNode.val){
                needDelete = true;
            }
            if(!needDelete){
                preNode = pNode;
                pNode = pNext;
            }
            else {
                int duplicatedValue = pNode.val;
                while(pNode != null && pNode.val == duplicatedValue){
                    pNode = pNode.next;
                }

                if(preNode != null){
                    preNode.next = pNode;
                }
                else{
                    pHead = pNode;
                }
            }
        }

        return pHead;
    }
}

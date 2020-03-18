package com.alvin.simpleDemos.algorithm.leetcode;

/**
 * leetcode 836
 */
public class IsRectangleOverlap {
    public static void main(String[] args) {
        int[] rec1 = {0,0,1,1};
        int[] rec2 = {1,0,2,1};
        System.out.println(isRectangleOverlap(rec1, rec2));
    }

    private static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        boolean xNotOverlap = rec1[0] >= rec2[2] || rec1[2] <= rec2[0];
        boolean yNotOverlap = rec1[1] >= rec2[3] || rec1[3] <= rec2[1];
        return !(xNotOverlap || yNotOverlap);
    }
}

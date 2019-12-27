package com.alvin.simpleDemos.algorithm;

import com.sun.deploy.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class FirstMissing {
    public static void main(String[] args) {
        FirstMissing firstMissing = new FirstMissing();
        int[] nums = {1, 3, 3};
        System.out.println(firstMissing.firstMissingPositive(nums));
    }

    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0){
            return 1;
        }

        Arrays.sort(nums);

        int gsp = 0;
        for(int i = 0; i < nums.length - 1; i++){
            gsp += nums[i + 1] - nums[i];
        }

        if(nums[0] > 1){
            return 1;
        }
        else if(nums[nums.length - 1] < 1){
            return 1;
        }
        else{
            if(gsp > nums.length - 1){
                ArrayList<Integer> arrs = new ArrayList<>();
                for (int num : nums) {
                    arrs.add(num);
                }
                for(int i = 1; i < nums[nums.length - 1]; i++){
                    if(!arrs.contains(i)){
                        return i;
                    }
                }
                return 1;
            }
            else{
                return nums[nums.length - 1] + 1;
            }
        }
    }
}

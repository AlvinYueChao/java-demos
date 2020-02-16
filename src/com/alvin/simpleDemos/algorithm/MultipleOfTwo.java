package com.alvin.simpleDemos.algorithm;

import java.util.Scanner;

public class MultipleOfTwo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] intList = new int[size];
        for (int i = 0; i < size; i++){
            intList[i] = scanner.nextInt();
        }

        int maxInt = intList[0];
        for (int i = 0; i < size; i++){
            maxInt = Math.max(maxInt, intList[i]);
        }

        String result = "YES";
        for (int i = 0; i < size; i++){
            if (!scalb(maxInt, intList[i])){
                result = "NO";
                break;
            }
        }
        System.out.println(result);
    }

    private static boolean scalb(int targetNum, int originalNum){
        boolean result = false;
        if (targetNum == originalNum){
            result = true;
        }
        else{
            while (targetNum > originalNum){
                originalNum = 2 * originalNum;
                if (originalNum == targetNum){
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}

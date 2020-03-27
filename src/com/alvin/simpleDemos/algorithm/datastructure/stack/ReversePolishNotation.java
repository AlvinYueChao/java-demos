package com.alvin.simpleDemos.algorithm.datastructure.stack;

import java.util.Stack;

public class ReversePolishNotation {
    public static void main(String[] args) {
        String[] exp1 = {"10", "20", "5", "+", "*", "2", "/", "5", "-"};
        System.out.println("result: " + getResult(exp1));
    }

    private static int getResult(String[] exp) {
        if (exp == null || exp.length == 0) {
            return 0;
        }

        int result = 0;
        Stack<Integer> numbers = new Stack<>();
        for (String item : exp) {
            switch (item) {
                case "+": {
                    int second = numbers.pop();
                    int first = numbers.pop();
                    result = first + second;
                    numbers.push(result);
                    break;
                }
                case "-": {
                    int second = numbers.pop();
                    int first = numbers.pop();
                    result = first - second;
                    numbers.push(result);
                    break;
                }
                case "*": {
                    int second = numbers.pop();
                    int first = numbers.pop();
                    result = first * second;
                    numbers.push(result);
                    break;
                }
                case "/": {
                    int second = numbers.pop();
                    int first = numbers.pop();
                    result = first / second;
                    numbers.push(result);
                    break;
                }
                default: {
                    int number = Integer.parseInt(item);
                    numbers.push(number);
                    break;
                }
            }
        }
        return numbers.pop();
    }
}

package com.operator;

public class Test {
    public static void main(String args[]) {
        int x = 5;
        int y = ++x;
        System.out.println("x的值为" + x);
        System.out.println("y的值为" + y);
        int a = 5;
        int b = a++;
        System.out.println("a的值为" + a);
        System.out.println("b的值为" + b);
    }
}

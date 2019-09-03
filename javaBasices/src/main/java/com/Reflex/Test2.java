package com.reflex;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Test2 {
    public static void main(String args[]) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Test2();
    }

    public static void Test2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<String> list = new ArrayList();
        list.add("hello");

        Class cls = list.getClass();
        Method add = cls.getMethod("add", Object.class);

        Test2 test2 = new Test2();
        add.invoke(list, test2);
        System.out.println(list);
    }
}

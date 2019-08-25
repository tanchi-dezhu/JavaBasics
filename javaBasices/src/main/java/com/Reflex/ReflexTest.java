package com.Reflex;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflexTest {
    public static void main(String[] args) throws Exception {
        String str = "abc";
        // ----------------字节码----------------------------------
        // 得到字节码的3种方式
        Class cls1 = str.getClass();
        Class cls2 = String.class;
        Class cls3 = Class.forName("java.lang.String");
        // PS:cls1,cls2,cls3都是同一份字节码对象.cls1=cls2=cls3
        System.out.println(cls1 == cls2);
        System.out.println(cls1 == cls3);

        System.out.println(cls1.isPrimitive());         // 返回false. String不是基本类型字节码。
        System.out.println(int.class == Integer.class);   // 返回false
        System.out.println(int.class == Integer.TYPE);  // 返回true
        // .TYPE返回其包装类型的字节码
        System.out.println(int[].class.isPrimitive());     // 返回false判断是否是基本数据类型的映射
        System.out.println(int[].class.isArray());       // 返回true 判断字节码类型是数组类型

        // -------------------------Constructor--------------------------------
        // 得到某一个构造方法： 参数类型为StringBuffer类型
        Constructor constructor = String.class.getConstructor(StringBuffer.class);
        // 通过构造方法创建实例化对象
        // 通常方式
        String str1 = new String(new StringBuffer("abc"));
        // 反射方式
        String str2 = (String) constructor.newInstance(new StringBuffer("abc"));
        System.out.println(str2.charAt(2));
        // PS:得到构造方法constructor对象时必须传递参数类型,创建实例化对象时必须是相同的参数类型

        // -----------------------Filed------------------------------------------
        ReflexTest1 rt = new ReflexTest1(2, 3);
        // 得到Java类中的成员变量
        Field fieldY = rt.getClass().getField("y");
        // fieldY不是Java对象身上的变量,而是类上,从某个Java对象中取出具体的值
        System.out.println(fieldY.get(rt));
        // 对于私有不可见变量
        Field fieldX = rt.getClass().getDeclaredField("x");
        fieldX.setAccessible(true); // 暴力反射
        System.out.println(fieldX.get(rt));

        changeValue(rt);
        System.out.println(rt);

        // --------------------------Method-----------------------------------------
        // 通过反射得到Java类方法
        Method methodCharAt = String.class.getMethod("charAt", int.class);
        // 调用方法必须在某个对象上调
        System.out.println(methodCharAt.invoke(str1, 1));

        // 反射执行类中的main方法 main方法为静态方法。传递对象可为null
        Method methodMain = Class.forName("com.Reflex.MethodTest").getMethod(
                "main", String[].class);
        System.out.println(methodMain.invoke(null, new Object[]{new String[]{
                "aaa", "bbb"}}));

        // ---------------------------数组映射-----------------------------------------
        printObject(new String[]{"aaa", "bbb"});
    }

    // 打印数组
    private static void printObject(Object obj) {
        Class clazz = obj.getClass();
        if (clazz.isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                System.out.println(Array.get(obj, i));
            }
        } else {
            System.out.println(obj);
        }
    }

    // field运用:改变类型名为String类型的成员变量的值
    private static void changeValue(Object obj) throws Exception {
        Field[] fields = obj.getClass().getFields();
        for (Field field : fields) {
            // 比较字节码用"="比较 因为是同一份字节码
            if (field.getType() == String.class) {
                String oldValue = (String) field.get(obj);
                String newValue = oldValue.replace("b", "a");
                // 重新设值
                field.set(obj, newValue);
            }
        }
    }

}

// method运用。调用类中的main方法
class MethodTest {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}

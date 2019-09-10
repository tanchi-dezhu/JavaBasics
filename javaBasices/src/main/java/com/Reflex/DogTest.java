package com.reflex;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class DogTest {
    public static void main(String[] args)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // 性别: 0:母, 1:公
        Dog dog = new Dog("点点", 0, 25, "哈士奇");
        String name = dog.getName();
        System.out.println("狗狗的名字是：" + name);
        // 由于Dog的成员变量都是私有的，并且当前Dog类没有提供set方法。
        // 在创建了对象之后，不能够改变Dog对象的属性。
        // 如果想改变对象的属性，则需要用点不常规的办法来做这件事情，即：反射
        // 我个人的理解，反射就是一种能够拿到某个类的所有公有、私有属性和方法的一种手段。
        // 接下来，看看如何改变Dog的名字、性别等属性
        Class<? extends Dog> clazz = dog.getClass();// 获取到对象对应的class对象
        Field[] fields = clazz.getDeclaredFields();//
        for (Field f : fields) {
            String fileName = f.getName();
            int m = f.getModifiers();
            String mStr = Modifier.toString(m);
            System.out.println(fileName + " 属性:" + mStr);
        }

        // 把上面已经创建好的狗狗的名字改为：大大
        Field nameField = clazz.getDeclaredField("name");// 获取私有成员变量:name
        nameField.setAccessible(true);// 设置操作权限为true
        nameField.set(dog, "大大");
        System.out.println("通过反射机制，最后修改的狗狗的名字是:" + dog.getName());

        Field typeField = clazz.getDeclaredField("type");
        typeField.setAccessible(true);
        typeField.set(dog, "金毛");

        System.out.println("狗狗的品种变为了:" + dog.getType());
    }
}

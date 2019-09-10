package com.reflex;

public class Dog {
    private String name;
    private int sex;
    private int weight;
    private String type;

    public Dog(String name, int sex, int weight, String type) {
        this.name = name;
        this.sex = sex;
        this.weight = weight;
        this.type = type;
    }

    /////////////// 只提供了get方法////////////////////
    public String getName() {
        return name;
    }

    public int getSex() {
        return sex;
    }

    public int getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }
}

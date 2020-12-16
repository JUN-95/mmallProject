package com.jun.test.factoryDesignDemo;

public class Cake implements Food{
    @Override
    public String name() {
        return "cake";
    }

    @Override
    public Integer price() {
        return 22;
    }
}

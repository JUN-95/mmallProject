package com.jun.test.factoryDesignDemo;

public class Bread implements Food {
    @Override
    public String name() {
        return "bread";
    }

    @Override
    public Integer price() {
        return 11;
    }
}

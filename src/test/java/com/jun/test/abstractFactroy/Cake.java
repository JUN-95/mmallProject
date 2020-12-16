package com.jun.test.abstractFactroy;

public class Cake implements Food {
    @Override
    public String name() {
        return "cake";
    }

    @Override
    public Integer price() {
        return 22;
    }
}

package com.jun.test.factoryDesignDemo;

public class FactroyDesign {
    public static void main(String[] args) {
        System.out.println(new FoodFactory().setFood(new Bread()).name());
        System.out.println(new FoodFactory().setFood(new Cake()).name());
    }
}



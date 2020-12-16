package com.jun.test.abstractFactroy;

import com.jun.test.factoryDesignDemo.Bread;
import com.jun.test.factoryDesignDemo.FoodFactory;


public class AbstractFactroy {
    public Object getObjectFactory(Object object){
        return object;
    }

    public static void main(String[] args){
        new AbstractFactroy().getObjectFactory(new FoodFactory().setFood(new Bread()));
    }
}

package com.jun.test.abstractFactroy;

public class Cut implements Tool {
    @Override
    public String toolName() {
        return "cut";
    }

    @Override
    public Integer toolPrice() {
        return 33;
    }
}

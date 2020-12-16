package com.jun.exception;

import com.jun.enmus.ResultEnum;

public class MMallException extends RuntimeException{
    // RuntimeException 属于可检查异常，对throw出来的exception可以进行检查，
    // 而Exception 属于不可检查异常

    public MMallException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
    }
}

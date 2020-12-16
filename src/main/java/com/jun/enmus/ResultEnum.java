package com.jun.enmus;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum ResultEnum {

    NO_ORDER_EXCEPTION(0,"订单不能为空"),

    NO_USER_EXCEPTION(1,"用户不能为空"),

    USER_SAVE_FAILD(2,"用户保存失败"),

    GENDER_VALUE_ERROR(3, "性别错误");

    ResultEnum(){

    }
    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @EnumValue
    private Integer code;
    private String msg;
}

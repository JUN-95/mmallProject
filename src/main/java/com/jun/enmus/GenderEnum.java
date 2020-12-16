package com.jun.enmus;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
public enum GenderEnum {
    FEMALE(0, "女"),
    MALE(1, "男");

    GenderEnum(){
    }
    GenderEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    @EnumValue
    private Integer code;
    private String msg;
}

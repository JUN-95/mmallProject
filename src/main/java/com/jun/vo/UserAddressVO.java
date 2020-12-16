package com.jun.vo;

import lombok.Data;

@Data
public class UserAddressVO {
    private Integer userId;
    private String address;
    private String remark;
    private boolean isdefault;
}

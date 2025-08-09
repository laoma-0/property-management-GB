package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class SysUser {
    private Long userId;
    private String username;
    private String password;
    private String realName;
    private String phone;
    private Integer userType;
    private Date createTime;
}

package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String role;
    private String name;
    private String phone;
    private String email;
    private Date createTime;
    private Date updateTime;
}

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
    private Integer userType; // 0-业主 1-物业人员 2-维修工
    private Date createTime;
    // 关联画像
    private UserProfile profile;
}
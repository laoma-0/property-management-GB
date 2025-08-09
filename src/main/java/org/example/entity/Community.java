package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Community {
    private Integer id;
    private String name;
    private String address;
    private String propertyPhone;
    private String description;
    private Date createTime;
    private Date updateTime;
}

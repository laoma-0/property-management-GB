package org.example.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Room {
    private Long roomId;
    private Integer buildingId;
    private String roomNumber;
    private Long ownerId;
    private BigDecimal area;

    // 关联字段：所属楼栋信息
    private Building building;
    // 关联字段：业主信息
    private SysUser owner;
}
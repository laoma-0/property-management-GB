package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class RepairOrder {
    private Long orderId;
    private Long userId;
    private Long roomId;
    private String repairType;
    private String description;
    private String aiLabel;
    private String imagePath;
    private Integer status;
    private Long assigneeId;
    private Date createTime;
    private Date completeTime;
}

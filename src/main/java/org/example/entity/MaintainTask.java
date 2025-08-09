package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class MaintainTask {
    private Long taskId;
    private String deviceId;
    private String predictReason;
    private Long assigneeId;
    private Integer taskStatus;
    private Date createTime;
}

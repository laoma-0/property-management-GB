package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Device {
    private String deviceId;
    private String deviceName;
    private String deviceType;
    private String location;
    private Date installDate;
    private Date lastMaintain;
}

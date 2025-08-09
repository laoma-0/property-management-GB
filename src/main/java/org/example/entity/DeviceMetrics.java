package org.example.entity;

import lombok.Data;
import java.util.Date;

@Data
public class DeviceMetrics {
    private Long metricId;
    private String deviceId;
    private Date metricTime;
    private Float vibration;
    private Float temperature;
    private Float current;
    private Boolean isAbnormal;
}

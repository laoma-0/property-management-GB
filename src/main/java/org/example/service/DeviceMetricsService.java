package org.example.service;

import org.example.entity.DeviceMetrics;
import java.util.List;

public interface DeviceMetricsService {
    DeviceMetrics getDeviceMetricsById(Long id);
    List<DeviceMetrics> getAllDeviceMetrics();
    int addDeviceMetrics(DeviceMetrics metrics);
    int updateDeviceMetrics(DeviceMetrics metrics);
    int deleteDeviceMetricsById(Long id);
}

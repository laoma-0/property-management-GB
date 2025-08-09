package org.example.service.impl;

import org.example.entity.DeviceMetrics;
import org.example.mapper.DeviceMetricsMapper;
import org.example.service.DeviceMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeviceMetricsServiceImpl implements DeviceMetricsService {
    @Autowired
    private DeviceMetricsMapper deviceMetricsMapper;

    @Override
    public DeviceMetrics getDeviceMetricsById(Long id) {
        return deviceMetricsMapper.selectById(id);
    }

    @Override
    public List<DeviceMetrics> getAllDeviceMetrics() {
        return deviceMetricsMapper.selectAll();
    }

    @Override
    public int addDeviceMetrics(DeviceMetrics metrics) {
        return deviceMetricsMapper.insert(metrics);
    }

    @Override
    public int updateDeviceMetrics(DeviceMetrics metrics) {
        return deviceMetricsMapper.update(metrics);
    }

    @Override
    public int deleteDeviceMetricsById(Long id) {
        return deviceMetricsMapper.deleteById(id);
    }
}

package org.example.controller;

import org.example.entity.DeviceMetrics;
import org.example.service.DeviceMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/device-metrics")
public class DeviceMetricsController {
    @Autowired
    private DeviceMetricsService deviceMetricsService;

    @GetMapping("/{id}")
    public DeviceMetrics getDeviceMetricsById(@PathVariable Long id) {
        return deviceMetricsService.getDeviceMetricsById(id);
    }

    @GetMapping
    public List<DeviceMetrics> getAllDeviceMetrics() {
        return deviceMetricsService.getAllDeviceMetrics();
    }

    @PostMapping
    public int addDeviceMetrics(@RequestBody DeviceMetrics metrics) {
        return deviceMetricsService.addDeviceMetrics(metrics);
    }

    @PutMapping
    public int updateDeviceMetrics(@RequestBody DeviceMetrics metrics) {
        return deviceMetricsService.updateDeviceMetrics(metrics);
    }

    @DeleteMapping("/{id}")
    public int deleteDeviceMetrics(@PathVariable Long id) {
        return deviceMetricsService.deleteDeviceMetricsById(id);
    }
}

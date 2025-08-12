package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.Result;
import org.example.entity.DeviceMetrics;
import org.example.service.DeviceMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 设备指标管理控制器
 * 处理设备运行指标数据的查询、新增、修改、删除等请求
 */
@RestController
@RequestMapping("/api/device-metrics")
@Tag(name = "设备指标管理", description = "设备运行指标相关接口（数据采集、查询等）")
public class DeviceMetricsController {
    @Autowired
    private DeviceMetricsService deviceMetricsService;

    /**
     * 根据ID查询设备指标
     * @param id 指标记录ID（长整型）
     * @return 包含指标详情的Result对象
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询设备指标", description = "通过指标记录ID获取设备运行指标详情（如温度、能耗等）")
    public Result<DeviceMetrics> getDeviceMetricsById(
            @Parameter(description = "指标记录ID", required = true)
            @PathVariable Long id) {
        DeviceMetrics metrics = deviceMetricsService.getDeviceMetricsById(id);
        return Result.success(metrics);
    }

    /**
     * 查询所有设备指标
     * @return 包含指标列表的Result对象
     */
    @GetMapping
    @Operation(summary = "查询所有设备指标", description = "获取系统中所有设备的运行指标记录")
    public Result<List<DeviceMetrics>> getAllDeviceMetrics() {
        List<DeviceMetrics> metricsList = deviceMetricsService.getAllDeviceMetrics();
        return Result.success(metricsList);
    }

    /**
     * 新增设备指标
     * @param metrics 指标实体（包含设备ID、指标值、采集时间等信息）
     * @return 包含影响行数的Result对象
     */
    @PostMapping
    @Operation(summary = "新增设备指标", description = "添加设备运行指标数据到系统")
    public Result<Integer> addDeviceMetrics(
            @Parameter(description = "设备指标信息", required = true)
            @RequestBody DeviceMetrics metrics) {
        int rows = deviceMetricsService.addDeviceMetrics(metrics);
        return Result.success(rows);
    }

    /**
     * 修改设备指标
     * @param metrics 指标实体（需包含ID和更新信息）
     * @return 包含影响行数的Result对象
     */
    @PutMapping
    @Operation(summary = "修改设备指标", description = "更新设备运行指标记录")
    public Result<Integer> updateDeviceMetrics(
            @Parameter(description = "更新后的指标信息", required = true)
            @RequestBody DeviceMetrics metrics) {
        int rows = deviceMetricsService.updateDeviceMetrics(metrics);
        return Result.success(rows);
    }

    /**
     * 根据ID删除设备指标
     * @param id 指标记录ID（长整型）
     * @return 包含影响行数的Result对象
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除设备指标", description = "通过指标记录ID删除指定指标数据")
    public Result<Integer> deleteDeviceMetrics(
            @Parameter(description = "指标记录ID", required = true)
            @PathVariable Long id) {
        int rows = deviceMetricsService.deleteDeviceMetricsById(id);
        return Result.success(rows);
    }
}
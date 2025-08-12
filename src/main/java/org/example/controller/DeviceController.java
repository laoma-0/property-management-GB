package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.Result;
import org.example.entity.Device;
import org.example.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 设备管理控制器
 * 处理设备的查询、新增、修改、删除等请求
 */
@RestController
@RequestMapping("/api/devices")
@Tag(name = "设备管理", description = "设备相关接口（CRUD操作）")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    /**
     * 根据ID查询设备
     * @param id 设备ID（字符串类型）
     * @return 包含设备信息的Result对象
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询设备", description = "通过设备ID获取设备详情信息")
    public Result<Device> getDeviceById(
            @Parameter(description = "设备ID", required = true)
            @PathVariable String id) {
        Device device = deviceService.getDeviceById(id);
        return Result.success(device);
    }

    /**
     * 查询所有设备
     * @return 包含设备列表的Result对象
     */
    @GetMapping
    @Operation(summary = "查询所有设备", description = "获取系统中所有设备的列表信息")
    public Result<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return Result.success(devices);
    }

    /**
     * 新增设备
     * @param device 设备实体（包含设备名称、型号等信息）
     * @return 包含影响行数的Result对象
     */
    @PostMapping
    @Operation(summary = "新增设备", description = "向系统中添加新设备信息")
    public Result<Integer> addDevice(
            @Parameter(description = "设备信息", required = true)
            @RequestBody Device device) {
        int rows = deviceService.addDevice(device);
        return Result.success(rows);
    }

    /**
     * 修改设备
     * @param device 设备实体（需包含ID和更新信息）
     * @return 包含影响行数的Result对象
     */
    @PutMapping
    @Operation(summary = "修改设备", description = "更新系统中的设备信息")
    public Result<Integer> updateDevice(
            @Parameter(description = "更新后的设备信息", required = true)
            @RequestBody Device device) {
        int rows = deviceService.updateDevice(device);
        return Result.success(rows);
    }

    /**
     * 根据ID删除设备
     * @param id 设备ID（字符串类型）
     * @return 包含影响行数的Result对象
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除设备", description = "通过设备ID删除指定设备")
    public Result<Integer> deleteDevice(
            @Parameter(description = "设备ID", required = true)
            @PathVariable String id) {
        int rows = deviceService.deleteDeviceById(id);
        return Result.success(rows);
    }
}
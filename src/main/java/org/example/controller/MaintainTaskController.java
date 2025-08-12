package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.Result;
import org.example.entity.MaintainTask;
import org.example.service.MaintainTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 维护任务管理控制器
 * 处理设备维护任务的查询、创建、修改、删除等请求
 */
@RestController
@RequestMapping("/api/maintain-tasks")
@Tag(name = "维护任务管理", description = "设备维护任务相关接口（创建、分配、跟踪等）")
public class MaintainTaskController {
    @Autowired
    private MaintainTaskService maintainTaskService;

    /**
     * 根据ID查询维护任务
     * @param id 任务ID（长整型）
     * @return 包含任务详情的Result对象
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询维护任务", description = "通过任务ID获取维护任务详情（内容、状态、负责人等）")
    public Result<MaintainTask> getMaintainTaskById(
            @Parameter(description = "维护任务ID", required = true)
            @PathVariable Long id) {
        MaintainTask task = maintainTaskService.getMaintainTaskById(id);
        return Result.success(task);
    }

    /**
     * 查询所有维护任务
     * @return 包含任务列表的Result对象
     */
    @GetMapping
    @Operation(summary = "查询所有维护任务", description = "获取系统中所有设备维护任务的列表")
    public Result<List<MaintainTask>> getAllMaintainTasks() {
        List<MaintainTask> tasks = maintainTaskService.getAllMaintainTasks();
        return Result.success(tasks);
    }

    /**
     * 新增维护任务
     * @param task 任务实体（包含任务内容、设备ID、负责人等信息）
     * @return 包含影响行数的Result对象
     */
    @PostMapping
    @Operation(summary = "新增维护任务", description = "创建新的设备维护任务")
    public Result<Integer> addMaintainTask(
            @Parameter(description = "维护任务信息", required = true)
            @RequestBody MaintainTask task) {
        int rows = maintainTaskService.addMaintainTask(task);
        return Result.success(rows);
    }

    /**
     * 修改维护任务
     * @param task 任务实体（需包含ID和更新信息，如状态变更）
     * @return 包含影响行数的Result对象
     */
    @PutMapping
    @Operation(summary = "修改维护任务", description = "更新维护任务信息（如状态、进度等）")
    public Result<Integer> updateMaintainTask(
            @Parameter(description = "更新后的任务信息", required = true)
            @RequestBody MaintainTask task) {
        int rows = maintainTaskService.updateMaintainTask(task);
        return Result.success(rows);
    }

    /**
     * 根据ID删除维护任务
     * @param id 任务ID（长整型）
     * @return 包含影响行数的Result对象
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除维护任务", description = "通过任务ID删除指定维护任务")
    public Result<Integer> deleteMaintainTask(
            @Parameter(description = "维护任务ID", required = true)
            @PathVariable Long id) {
        int rows = maintainTaskService.deleteMaintainTaskById(id);
        return Result.success(rows);
    }
}
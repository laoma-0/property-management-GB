package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.Result;
import org.example.entity.RepairOrder;
import org.example.service.RepairOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 报修订单管理控制器
 * 处理业主报修订单的查询、创建、修改、删除等请求
 */
@RestController
@RequestMapping("/api/repair-orders")
@Tag(name = "报修订单管理", description = "业主报修相关接口（下单、派单、完成等）")
public class RepairOrderController {
    @Autowired
    private RepairOrderService repairOrderService;

    /**
     * 根据ID查询报修订单
     * @param id 订单ID（长整型）
     * @return 包含订单详情的Result对象
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询报修订单", description = "通过订单ID获取报修详情（问题描述、状态、处理进度等）")
    public Result<RepairOrder> getRepairOrderById(
            @Parameter(description = "报修订单ID", required = true)
            @PathVariable Long id) {
        RepairOrder order = repairOrderService.getRepairOrderById(id);
        return Result.success(order);
    }

    /**
     * 查询所有报修订单
     * @return 包含订单列表的Result对象
     */
    @GetMapping
    @Operation(summary = "查询所有报修订单", description = "获取系统中所有业主报修订单的列表")
    public Result<List<RepairOrder>> getAllRepairOrders() {
        List<RepairOrder> orders = repairOrderService.getAllRepairOrders();
        return Result.success(orders);
    }

    /**
     * 新增报修订单
     * @param order 订单实体（包含业主信息、问题描述、地址等信息）
     * @return 包含影响行数的Result对象
     */
    @PostMapping
    @Operation(summary = "新增报修订单", description = "业主提交新的报修请求")
    public Result<Integer> addRepairOrder(
            @Parameter(description = "报修订单信息", required = true)
            @RequestBody RepairOrder order) {
        int rows = repairOrderService.addRepairOrder(order);
        return Result.success(rows);
    }

    /**
     * 修改报修订单
     * @param order 订单实体（需包含ID和更新信息，如状态变更）
     * @return 包含影响行数的Result对象
     */
    @PutMapping
    @Operation(summary = "修改报修订单", description = "更新报修订单状态（如已派单、已完成等）")
    public Result<Integer> updateRepairOrder(
            @Parameter(description = "更新后的订单信息", required = true)
            @RequestBody RepairOrder order) {
        int rows = repairOrderService.updateRepairOrder(order);
        return Result.success(rows);
    }

    /**
     * 根据ID删除报修订单
     * @param id 订单ID（长整型）
     * @return 包含影响行数的Result对象
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除报修订单", description = "通过订单ID删除指定报修订单")
    public Result<Integer> deleteRepairOrder(
            @Parameter(description = "报修订单ID", required = true)
            @PathVariable Long id) {
        int rows = repairOrderService.deleteRepairOrderById(id);
        return Result.success(rows);
    }
}
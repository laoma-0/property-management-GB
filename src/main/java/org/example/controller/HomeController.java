package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.Result;
import org.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页控制器
 * 提供首页统计数据和系统概览信息
 */
@RestController
@RequestMapping("/home")
@Tag(name = "首页", description = "首页统计数据相关接口")
public class HomeController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoomService roomService;
    
    @Autowired
    private BuildingService buildingService;
    
    @Autowired
    private RepairOrderService repairOrderService;
    
    @Autowired
    private PaymentBillService paymentBillService;
    
    /**
     * 获取首页统计数据
     * @return 包含各项统计数据的Result对象
     */
    @GetMapping("/statistics")
    @Operation(summary = "获取首页统计数据", description = "获取系统各项统计数据，用于首页展示")
    public Result<Map<String, Object>> getHomeStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        
        // 用户总数
        statistics.put("userCount", userService.getAllUsers().size());
        
        // 房间总数
        statistics.put("roomCount", roomService.getAllRooms().size());
        
        // 楼栋总数
        statistics.put("buildingCount", buildingService.getAllBuildings().size());
        
        // 报修订单总数
        statistics.put("repairOrderCount", repairOrderService.getAllRepairOrders().size());
        
        // 缴费账单总数
        statistics.put("paymentBillCount", paymentBillService.getAllPaymentBills().size());
        
        // 待处理报修订单数
        statistics.put("pendingRepairOrders", 
            repairOrderService.getAllRepairOrders().stream()
                .filter(order -> "pending".equals(order.getStatus())).count());
        
        // 未缴费账单数
        statistics.put("unpaidBills",
            paymentBillService.getAllPaymentBills().stream()
                .filter(bill -> "unpaid".equals(bill.getStatus())).count());
        
        return Result.success(statistics);
    }
    
    /**
     * 获取系统概览信息
     * @return 包含系统概览信息的Result对象
     */
    @GetMapping("/overview")
    @Operation(summary = "获取系统概览", description = "获取系统基本信息和运行状态")
    public Result<Map<String, Object>> getSystemOverview() {
        Map<String, Object> overview = new HashMap<>();
        
        overview.put("systemName", "智慧物业管理系统");
        overview.put("version", "1.0.0");
        overview.put("description", "一站式物业管理解决方案");
        
        return Result.success(overview);
    }
}
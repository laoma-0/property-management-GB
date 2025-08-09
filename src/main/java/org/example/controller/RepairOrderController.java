package org.example.controller;

import org.example.entity.RepairOrder;
import org.example.service.RepairOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/repair-orders")
public class RepairOrderController {
    @Autowired
    private RepairOrderService repairOrderService;

    @GetMapping("/{id}")
    public RepairOrder getRepairOrderById(@PathVariable Long id) {
        return repairOrderService.getRepairOrderById(id);
    }

    @GetMapping
    public List<RepairOrder> getAllRepairOrders() {
        return repairOrderService.getAllRepairOrders();
    }

    @PostMapping
    public int addRepairOrder(@RequestBody RepairOrder order) {
        return repairOrderService.addRepairOrder(order);
    }

    @PutMapping
    public int updateRepairOrder(@RequestBody RepairOrder order) {
        return repairOrderService.updateRepairOrder(order);
    }

    @DeleteMapping("/{id}")
    public int deleteRepairOrder(@PathVariable Long id) {
        return repairOrderService.deleteRepairOrderById(id);
    }
}

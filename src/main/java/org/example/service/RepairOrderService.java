package org.example.service;

import org.example.entity.RepairOrder;
import java.util.List;

public interface RepairOrderService {
    RepairOrder getRepairOrderById(Long id);
    List<RepairOrder> getAllRepairOrders();
    int addRepairOrder(RepairOrder order);
    int updateRepairOrder(RepairOrder order);
    int deleteRepairOrderById(Long id);
}

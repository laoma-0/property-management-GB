package org.example.service;

import com.github.pagehelper.PageInfo;
import org.example.entity.RepairOrder;
import java.util.List;

public interface RepairOrderService {
    RepairOrder getRepairOrderById(Long id);
    List<RepairOrder> getAllRepairOrders();
    PageInfo<RepairOrder> getRepairOrdersByPage(Long userId, Integer status, int pageNum, int pageSize);
    int addRepairOrder(RepairOrder order);
    int updateRepairOrder(RepairOrder order);
    int deleteRepairOrderById(Long id);
}
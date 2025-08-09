package org.example.service.impl;

import org.example.entity.RepairOrder;
import org.example.mapper.RepairOrderMapper;
import org.example.service.RepairOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RepairOrderServiceImpl implements RepairOrderService {
    @Autowired
    private RepairOrderMapper repairOrderMapper;

    @Override
    public RepairOrder getRepairOrderById(Long id) {
        return repairOrderMapper.selectById(id);
    }

    @Override
    public List<RepairOrder> getAllRepairOrders() {
        return repairOrderMapper.selectAll();
    }

    @Override
    public int addRepairOrder(RepairOrder order) {
        return repairOrderMapper.insert(order);
    }

    @Override
    public int updateRepairOrder(RepairOrder order) {
        return repairOrderMapper.update(order);
    }

    @Override
    public int deleteRepairOrderById(Long id) {
        return repairOrderMapper.deleteById(id);
    }
}

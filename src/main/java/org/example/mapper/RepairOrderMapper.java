package org.example.mapper;

import org.example.entity.RepairOrder;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RepairOrderMapper {
    RepairOrder selectById(Long orderId);
    List<RepairOrder> selectAll();
    int insert(RepairOrder order);
    int update(RepairOrder order);
    int deleteById(Long orderId);
}

package org.example.mapper;

import org.example.entity.Building;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BuildingMapper {
    Building selectById(Integer buildingId);
    List<Building> selectAll();
    int insert(Building building);
    int update(Building building);
    int deleteById(Integer buildingId);
}

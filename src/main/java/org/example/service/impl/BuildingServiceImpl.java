package org.example.service.impl;

import org.example.entity.Building;
import org.example.mapper.BuildingMapper;
import org.example.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;

    @Override
    public Building getBuildingById(Integer id) {
        return buildingMapper.selectById(id);
    }

    @Override
    public List<Building> getAllBuildings() {
        return buildingMapper.selectAll();
    }

    @Override
    public int addBuilding(Building building) {
        return buildingMapper.insert(building);
    }

    @Override
    public int updateBuilding(Building building) {
        return buildingMapper.update(building);
    }

    @Override
    public int deleteBuildingById(Integer id) {
        return buildingMapper.deleteById(id);
    }
}

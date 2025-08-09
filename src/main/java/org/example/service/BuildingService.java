package org.example.service;

import org.example.entity.Building;
import java.util.List;

public interface BuildingService {
    Building getBuildingById(Integer id);
    List<Building> getAllBuildings();
    int addBuilding(Building building);
    int updateBuilding(Building building);
    int deleteBuildingById(Integer id);
}

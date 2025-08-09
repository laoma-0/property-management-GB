package org.example.controller;

import org.example.entity.Building;
import org.example.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @GetMapping("/{id}")
    public Building getBuildingById(@PathVariable Integer id) {
        return buildingService.getBuildingById(id);
    }

    @GetMapping
    public List<Building> getAllBuildings() {
        return buildingService.getAllBuildings();
    }

    @PostMapping
    public int addBuilding(@RequestBody Building building) {
        return buildingService.addBuilding(building);
    }

    @PutMapping
    public int updateBuilding(@RequestBody Building building) {
        return buildingService.updateBuilding(building);
    }

    @DeleteMapping("/{id}")
    public int deleteBuilding(@PathVariable Integer id) {
        return buildingService.deleteBuildingById(id);
    }
}

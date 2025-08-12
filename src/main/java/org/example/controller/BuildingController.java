package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.Result;
import org.example.entity.Building;
import org.example.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 楼栋管理控制器
 * 处理小区楼栋信息的查询、新增、修改、删除等请求
 */
@RestController
@RequestMapping("/api/buildings")
@Tag(name = "楼栋管理", description = "小区楼栋信息相关接口（CRUD操作）")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    /**
     * 根据ID查询楼栋
     * @param id 楼栋ID（整型）
     * @return 包含楼栋详情的Result对象
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询楼栋", description = "通过楼栋ID获取楼栋信息（名称、单元数、所属小区等）")
    public Result<Building> getBuildingById(
            @Parameter(description = "楼栋ID", required = true)
            @PathVariable Integer id) {
        Building building = buildingService.getBuildingById(id);
        return Result.success(building);
    }

    /**
     * 查询所有楼栋
     * @return 包含楼栋列表的Result对象
     */
    @GetMapping
    @Operation(summary = "查询所有楼栋", description = "获取系统中所有小区楼栋的列表信息")
    public Result<List<Building>> getAllBuildings() {
        List<Building> buildings = buildingService.getAllBuildings();
        return Result.success(buildings);
    }

    /**
     * 新增楼栋
     * @param building 楼栋实体（包含名称、所属小区ID、单元数等信息）
     * @return 包含影响行数的Result对象
     */
    @PostMapping
    @Operation(summary = "新增楼栋", description = "添加新的楼栋信息到系统")
    public Result<Integer> addBuilding(
            @Parameter(description = "楼栋信息", required = true)
            @RequestBody Building building) {
        int rows = buildingService.addBuilding(building);
        return Result.success(rows);
    }

    /**
     * 修改楼栋
     * @param building 楼栋实体（需包含ID和更新信息）
     * @return 包含影响行数的Result对象
     */
    @PutMapping
    @Operation(summary = "修改楼栋", description = "更新楼栋信息（如单元数调整、名称变更等）")
    public Result<Integer> updateBuilding(
            @Parameter(description = "更新后的楼栋信息", required = true)
            @RequestBody Building building) {
        int rows = buildingService.updateBuilding(building);
        return Result.success(rows);
    }

    /**
     * 根据ID删除楼栋
     * @param id 楼栋ID（整型）
     * @return 包含影响行数的Result对象
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除楼栋", description = "通过楼栋ID删除指定楼栋信息")
    public Result<Integer> deleteBuilding(
            @Parameter(description = "楼栋ID", required = true)
            @PathVariable Integer id) {
        int rows = buildingService.deleteBuildingById(id);
        return Result.success(rows);
    }
}
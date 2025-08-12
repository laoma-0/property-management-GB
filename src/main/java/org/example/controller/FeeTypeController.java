package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.Result;
import org.example.entity.FeeType;
import org.example.service.FeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 费用类型管理控制器
 * 处理物业费类型（如水费、电费等）的查询、新增、修改、删除等请求
 */
@RestController
@RequestMapping("/api/fee-types")
@Tag(name = "费用类型管理", description = "物业收费类型相关接口（类型定义、费率等）")
public class FeeTypeController {
    @Autowired
    private FeeTypeService feeTypeService;

    /**
     * 根据ID查询费用类型
     * @param id 类型ID（整型）
     * @return 包含类型详情的Result对象
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询费用类型", description = "通过类型ID获取费用类型详情（名称、费率、单位等）")
    public Result<FeeType> getFeeTypeById(
            @Parameter(description = "费用类型ID", required = true)
            @PathVariable Integer id) {
        FeeType feeType = feeTypeService.getFeeTypeById(id);
        return Result.success(feeType);
    }

    /**
     * 查询所有费用类型
     * @return 包含类型列表的Result对象
     */
    @GetMapping
    @Operation(summary = "查询所有费用类型", description = "获取系统中所有物业费类型的列表")
    public Result<List<FeeType>> getAllFeeTypes() {
        List<FeeType> feeTypes = feeTypeService.getAllFeeTypes();
        return Result.success(feeTypes);
    }

    /**
     * 新增费用类型
     * @param feeType 类型实体（包含类型名称、费率标准等信息）
     * @return 包含影响行数的Result对象
     */
    @PostMapping
    @Operation(summary = "新增费用类型", description = "添加新的物业费类型到系统")
    public Result<Integer> addFeeType(
            @Parameter(description = "费用类型信息", required = true)
            @RequestBody FeeType feeType) {
        int rows = feeTypeService.addFeeType(feeType);
        return Result.success(rows);
    }

    /**
     * 修改费用类型
     * @param feeType 类型实体（需包含ID和更新信息，如费率调整）
     * @return 包含影响行数的Result对象
     */
    @PutMapping
    @Operation(summary = "修改费用类型", description = "更新费用类型信息（如费率调整、名称变更等）")
    public Result<Integer> updateFeeType(
            @Parameter(description = "更新后的费用类型信息", required = true)
            @RequestBody FeeType feeType) {
        int rows = feeTypeService.updateFeeType(feeType);
        return Result.success(rows);
    }

    /**
     * 根据ID删除费用类型
     * @param id 类型ID（整型）
     * @return 包含影响行数的Result对象
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除费用类型", description = "通过类型ID删除指定费用类型")
    public Result<Integer> deleteFeeType(
            @Parameter(description = "费用类型ID", required = true)
            @PathVariable Integer id) {
        int rows = feeTypeService.deleteFeeTypeById(id);
        return Result.success(rows);
    }
}
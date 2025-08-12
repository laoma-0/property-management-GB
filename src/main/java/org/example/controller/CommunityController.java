package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.Result;
import org.example.entity.Community;
import org.example.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 小区管理控制器
 * 处理小区信息的查询、新增、修改、删除等请求
 */
@RestController
@RequestMapping("/api/communities")
@Tag(name = "小区管理", description = "小区基础信息相关接口（CRUD操作）")
public class CommunityController {
    @Autowired
    private CommunityService communityService;

    /**
     * 根据ID查询小区
     * @param id 小区ID（整型）
     * @return 包含小区详情的Result对象
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询小区", description = "通过小区ID获取小区基础信息（名称、地址、规模等）")
    public Result<Community> getCommunityById(
            @Parameter(description = "小区ID", required = true)
            @PathVariable Integer id) {
        Community community = communityService.getCommunityById(id);
        return Result.success(community);
    }

    /**
     * 查询所有小区
     * @return 包含小区列表的Result对象
     */
    @GetMapping
    @Operation(summary = "查询所有小区", description = "获取系统中所有小区的列表信息")
    public Result<List<Community>> getAllCommunities() {
        List<Community> communities = communityService.getAllCommunities();
        return Result.success(communities);
    }

    /**
     * 新增小区
     * @param community 小区实体（包含名称、地址、总户数等信息）
     * @return 包含影响行数的Result对象
     */
    @PostMapping
    @Operation(summary = "新增小区", description = "添加新的小区信息到系统")
    public Result<Integer> addCommunity(
            @Parameter(description = "小区信息", required = true)
            @RequestBody Community community) {
        int rows = communityService.addCommunity(community);
        return Result.success(rows);
    }

    /**
     * 修改小区
     * @param community 小区实体（需包含ID和更新信息）
     * @return 包含影响行数的Result对象
     */
    @PutMapping
    @Operation(summary = "修改小区", description = "更新小区基础信息")
    public Result<Integer> updateCommunity(
            @Parameter(description = "更新后的小区信息", required = true)
            @RequestBody Community community) {
        int rows = communityService.updateCommunity(community);
        return Result.success(rows);
    }

    /**
     * 根据ID删除小区
     * @param id 小区ID（整型）
     * @return 包含影响行数的Result对象
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除小区", description = "通过小区ID删除指定小区信息")
    public Result<Integer> deleteCommunity(
            @Parameter(description = "小区ID", required = true)
            @PathVariable Integer id) {
        int rows = communityService.deleteCommunityById(id);
        return Result.success(rows);
    }
}
package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.Result;
import org.example.entity.UserProfile;
import org.example.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 用户档案管理控制器
 * 处理用户详细档案（如业主信息）的查询、新增、修改、删除等请求
 */
@RestController
@RequestMapping("/api/user-profiles")
@Tag(name = "用户档案管理", description = "用户详细信息相关接口（业主资料、联系方式等）")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    /**
     * 根据ID查询用户档案
     * @param id 档案ID（长整型）
     * @return 包含档案详情的Result对象
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询用户档案", description = "通过档案ID获取用户详细信息（姓名、电话、住址等）")
    public Result<UserProfile> getUserProfileById(
            @Parameter(description = "用户档案ID", required = true)
            @PathVariable Long id) {
        UserProfile profile = userProfileService.getUserProfileById(id);
        return Result.success(profile);
    }

    /**
     * 查询所有用户档案
     * @return 包含档案列表的Result对象
     */
    @GetMapping
    @Operation(summary = "查询所有用户档案", description = "获取系统中所有用户的详细档案列表")
    public Result<List<UserProfile>> getAllUserProfiles() {
        List<UserProfile> profiles = userProfileService.getAllUserProfiles();
        return Result.success(profiles);
    }

    /**
     * 新增用户档案
     * @param profile 档案实体（包含用户ID、姓名、联系方式等信息）
     * @return 包含影响行数的Result对象
     */
    @PostMapping
    @Operation(summary = "新增用户档案", description = "添加用户的详细档案信息")
    public Result<Integer> addUserProfile(
            @Parameter(description = "用户档案信息", required = true)
            @RequestBody UserProfile profile) {
        int rows = userProfileService.addUserProfile(profile);
        return Result.success(rows);
    }

    /**
     * 修改用户档案
     * @param profile 档案实体（需包含ID和更新信息，如联系方式变更）
     * @return 包含影响行数的Result对象
     */
    @PutMapping
    @Operation(summary = "修改用户档案", description = "更新用户的详细档案信息")
    public Result<Integer> updateUserProfile(
            @Parameter(description = "更新后的档案信息", required = true)
            @RequestBody UserProfile profile) {
        int rows = userProfileService.updateUserProfile(profile);
        return Result.success(rows);
    }

    /**
     * 根据ID删除用户档案
     * @param id 档案ID（长整型）
     * @return 包含影响行数的Result对象
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户档案", description = "通过档案ID删除指定用户的详细档案")
    public Result<Integer> deleteUserProfile(
            @Parameter(description = "用户档案ID", required = true)
            @PathVariable Long id) {
        int rows = userProfileService.deleteUserProfileById(id);
        return Result.success(rows);
    }
}
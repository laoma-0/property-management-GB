package org.example.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.example.common.PageResult;
import org.example.common.Result;
import org.example.entity.SysUser;
import org.example.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 系统用户管理控制器
 * 处理系统用户（业主、物业人员、维修工等）的查询、新增、修改、删除等请求
 */
@RestController
@RequestMapping("/sys-users")
@Tag(name = "系统用户管理", description = "系统用户相关接口（业主、物业人员、维修工等）")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据ID查询系统用户
     *
     * @param id 用户ID（长整型）
     * @return 包含用户详情的Result对象
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询系统用户", description = "通过用户ID获取用户详情信息")
    public Result<SysUser> getUserById(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id) {
        SysUser user = sysUserService.getUserById(id);
        return Result.success(user);
    }

    /**
     * 分页查询系统用户
     * @param username 用户名（可选）
     * @param realName 真实姓名（可选）
     * @param phone 手机号（可选）
     * @param userType 用户类型（可选）
     * @param pageNum 页码（从1开始）
     * @param pageSize 每页记录数
     * @return 包含分页用户列表的PageResult对象
     */
    @GetMapping
    @Operation(summary = "分页查询系统用户", description = "分页获取系统用户列表，支持按用户名、真实姓名、手机号、用户类型搜索")
    public PageResult<SysUser> getUsersByPage(
            @Parameter(description = "用户名")
            @RequestParam(required = false) String username,
            @Parameter(description = "真实姓名")
            @RequestParam(required = false) String realName,
            @Parameter(description = "手机号")
            @RequestParam(required = false) String phone,
            @Parameter(description = "用户类型（0-业主 1-物业人员 2-维修工）")
            @RequestParam(required = false) Integer userType,
            @Parameter(description = "页码（从1开始）")
            @RequestParam(defaultValue = "1") @Min(value = 1, message = "页码必须大于0") int pageNum,
            @Parameter(description = "每页记录数")
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "每页记录数必须大于0") int pageSize) {
        
        PageInfo<SysUser> pageInfo = sysUserService.getUsersByPage(username, realName, phone, userType, pageNum, pageSize);
        return PageResult.success(pageInfo.getList(), pageInfo.getTotal(), pageNum, pageSize);
    }

    /**
     * 查询所有系统用户
     *
     * @return 包含用户列表的Result对象
     */
    @GetMapping("/all")
    @Operation(summary = "查询所有系统用户", description = "获取所有系统用户列表")
    public Result<List<SysUser>> getAllUsers() {
        List<SysUser> users = sysUserService.getAllUsers();
        return Result.success(users);
    }

    /**
     * 新增系统用户
     *
     * @param user 用户实体（包含用户名、密码等信息）
     * @return 包含影响行数的Result对象
     */
    @PostMapping
    @Operation(summary = "新增系统用户", description = "创建新的系统用户")
    public Result<Integer> addUser(
            @Parameter(description = "用户信息", required = true)
            @Valid @RequestBody SysUser user) {
        // 设置创建时间
        user.setCreateTime(new Date());
        int rows = sysUserService.addUser(user);
        return Result.success(rows);
    }

    /**
     * 修改系统用户
     *
     * @param user 用户实体（需包含ID和更新信息）
     * @return 包含影响行数的Result对象
     */
    @PutMapping
    @Operation(summary = "修改系统用户", description = "更新系统用户信息")
    public Result<Integer> updateUser(
            @Parameter(description = "更新后的用户信息", required = true)
            @Valid @RequestBody SysUser user) {
        int rows = sysUserService.updateUser(user);
        return Result.success(rows);
    }

    /**
     * 根据ID删除系统用户
     *
     * @param id 用户ID（长整型）
     * @return 包含影响行数的Result对象
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除系统用户", description = "通过用户ID删除指定系统用户")
    public Result<Integer> deleteUser(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Long id) {
        int rows = sysUserService.deleteUserById(id);
        return Result.success(rows);
    }
}
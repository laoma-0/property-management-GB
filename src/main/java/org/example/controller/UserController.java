package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.common.Result;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理控制器
 * 处理系统用户（管理员、物业人员等）的查询、新增、修改、删除等请求
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "用户管理", description = "系统用户相关接口（账号管理、权限等）")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据ID查询用户
     * @param id 用户ID（整型）
     * @return 包含用户详情的Result对象
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询用户", description = "通过用户ID获取用户账号信息（用户名、角色、权限等）")
    public Result<User> getUserById(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Integer id) {
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    /**
     * 查询所有用户
     * @return 包含用户列表的Result对象
     */
    @GetMapping
    @Operation(summary = "查询所有用户", description = "获取系统中所有用户的账号列表")
    public Result<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Result.success(users);
    }

    /**
     * 新增用户
     * @param user 用户实体（包含用户名、密码、角色等信息）
     * @return 包含影响行数的Result对象
     */
    @PostMapping
    @Operation(summary = "新增用户", description = "创建新的系统用户账号")
    public Result<Integer> addUser(
            @Parameter(description = "用户信息", required = true)
            @RequestBody User user) {
        int rows = userService.addUser(user);
        return Result.success(rows);
    }

    /**
     * 修改用户
     * @param user 用户实体（需包含ID和更新信息，如密码、角色等）
     * @return 包含影响行数的Result对象
     */
    @PutMapping
    @Operation(summary = "修改用户", description = "更新用户账号信息（如密码重置、角色变更等）")
    public Result<Integer> updateUser(
            @Parameter(description = "更新后的用户信息", required = true)
            @RequestBody User user) {
        int rows = userService.updateUser(user);
        return Result.success(rows);
    }

    /**
     * 根据ID删除用户
     * @param id 用户ID（整型）
     * @return 包含影响行数的Result对象
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户", description = "通过用户ID删除指定用户账号")
    public Result<Integer> deleteUser(
            @Parameter(description = "用户ID", required = true)
            @PathVariable Integer id) {
        int rows = userService.deleteUserById(id);
        return Result.success(rows);
    }
}
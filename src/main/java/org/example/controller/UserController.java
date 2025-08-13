package org.example.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.example.common.PageResult;
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
@RequestMapping("/users")
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
            @PathVariable @Min(value = 1, message = "用户ID必须大于0") Integer id) {
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    /**
     * 查询所有用户
     * @return 包含用户列表的Result对象
     */
    @GetMapping("/all")
    @Operation(summary = "查询所有用户", description = "获取系统中所有用户的账号列表")
    public Result<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Result.success(users);
    }

    /**
     * 分页查询用户
     * @param username 用户名（可选）
     * @param name 姓名（可选）
     * @param phone 手机号（可选）
     * @param pageNum 页码（从1开始）
     * @param pageSize 每页记录数
     * @return 包含分页用户列表的PageResult对象
     */
    @GetMapping
    @Operation(summary = "分页查询用户", description = "分页获取系统中用户账号列表，支持按用户名、姓名、手机号搜索")
    public PageResult<User> getUsersByPage(
            @Parameter(description = "用户名")
            @RequestParam(required = false) String username,
            @Parameter(description = "姓名")
            @RequestParam(required = false) String name,
            @Parameter(description = "手机号")
            @RequestParam(required = false) String phone,
            @Parameter(description = "页码（从1开始）")
            @RequestParam(defaultValue = "1") @Min(value = 1, message = "页码必须大于0") int pageNum,
            @Parameter(description = "每页记录数")
            @RequestParam(defaultValue = "10") @Min(value = 1, message = "每页记录数必须大于0") int pageSize) {
        
        PageInfo<User> pageInfo;
        // 如果有搜索条件，则按条件搜索
        if ((username != null && !username.isEmpty()) || 
            (name != null && !name.isEmpty()) || 
            (phone != null && !phone.isEmpty())) {
            pageInfo = userService.getUsersByPageAndCondition(username, name, phone, pageNum, pageSize);
        } else {
            // 否则查询所有用户
            pageInfo = userService.getUsersByPage(pageNum, pageSize);
        }
        
        return PageResult.success(pageInfo.getList(), pageInfo.getTotal(), pageNum, pageSize);
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
            @Valid @RequestBody User user) {
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
            @Valid @RequestBody User user) {
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
            @PathVariable @Min(value = 1, message = "用户ID必须大于0") Integer id) {
        int rows = userService.deleteUserById(id);
        return Result.success(rows);
    }
}
package org.example.controller;

import org.example.common.Result;
import org.example.entity.SysUser;
import org.example.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sys-users")
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
    public Result<SysUser> getUserById(@PathVariable Long id) {
        SysUser user = sysUserService.getUserById(id);
        return Result.success(user);
    }

    /**
     * 查询所有系统用户
     *
     * @return 包含用户列表的Result对象
     */
    @GetMapping
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
    public Result<Integer> addUser(@RequestBody SysUser user) {
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
    public Result<Integer> updateUser(@RequestBody SysUser user) {
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
    public Result<Integer> deleteUser(@PathVariable Long id) {
        int rows = sysUserService.deleteUserById(id);
        return Result.success(rows);
    }
}
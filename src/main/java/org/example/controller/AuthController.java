package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.common.Result;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "认证管理", description = "用户登录认证相关接口")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录认证，成功后返回token和用户信息")
    public Result<Map<String, Object>> login(
            @Parameter(description = "登录信息", required = true)
            @Valid @RequestBody LoginRequest loginRequest) {
        
        try {
            // 验证用户凭据
            User user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            
            if (user == null) {
                return Result.error(401, "用户名或密码错误");
            }
            
            // 生成token（这里简化处理，实际项目中应该使用JWT或其他安全的token机制）
            String token = "token_" + System.currentTimeMillis() + "_" + user.getId();
            
            // 构造返回数据
            Map<String, Object> result = new HashMap<>();
            result.put("token", token);
            result.put("user", user);
            
            return Result.success(result);
        } catch (Exception e) {
            return Result.error(500, "登录失败：" + e.getMessage());
        }
    }

    @GetMapping("/info")
    @Operation(summary = "获取用户信息", description = "根据token获取当前登录用户的信息")
    public Result<User> getUserInfo() {
        // 这里应该从token中解析用户信息
        // 简化处理，实际项目中应该验证token并获取对应用户信息
        return Result.success(null);
    }

    @PostMapping("/logout")
    @Operation(summary = "用户退出登录", description = "清除用户登录状态")
    public Result<Void> logout() {
        // 清除token信息
        return Result.success(null);
    }

    // 登录请求数据传输对象
    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
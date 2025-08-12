// frontend/frontend/src/api/user.js
import request from "./request";

// 获取所有用户列表
export function getAllUsers() {
    return request({
        url: "/api/users", // 对应后端UserController的@RequestMapping("/api/users")
        method: "get"
    });
}

// 根据ID获取单个用户
export function getUserById(id) {
    return request({
        url: `/api/users/${id}`,
        method: "get"
    });
}

// 新增用户
export function addUser(data) {
    return request({
        url: "/api/users",
        method: "post",
        data
    });
}

// 更新用户
export function updateUser(data) {
    return request({
        url: "/api/users",
        method: "put",
        data
    });
}

// 删除用户
export function deleteUser(id) {
    return request({
        url: `/api/users/${id}`,
        method: "delete"
    });
}
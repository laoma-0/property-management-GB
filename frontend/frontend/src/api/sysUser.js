import request from './request'

// 获取系统用户列表（分页）
export function getUsers(params) {
  return request.get('/sys-users', { params })
}

// 获取所有系统用户
export function getAllUsers() {
  return request.get('/sys-users/all')
}

// 根据ID获取系统用户
export function getUserById(id) {
  return request.get(`/sys-users/${id}`)
}

// 新增系统用户
export function addUser(data) {
  return request.post('/sys-users', data)
}

// 修改系统用户
export function updateUser(data) {
  return request.put('/sys-users', data)
}

// 删除系统用户
export function deleteUser(id) {
  return request.delete(`/sys-users/${id}`)
}
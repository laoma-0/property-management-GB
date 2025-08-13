import request from './request'

// 获取楼栋列表
export function getBuildings() {
  return request.get('/buildings')
}

// 根据ID获取楼栋
export function getBuildingById(id) {
  return request.get(`/buildings/${id}`)
}

// 新增楼栋
export function addBuilding(data) {
  return request.post('/buildings', data)
}

// 修改楼栋
export function updateBuilding(data) {
  return request.put('/buildings', data)
}

// 删除楼栋
export function deleteBuilding(id) {
  return request.delete(`/buildings/${id}`)
}

// 获取房间列表
export function getRooms(params) {
  return request.get('/rooms', { params })
}

// 获取所有房间
export function getAllRooms() {
  return request.get('/rooms/all')
}

// 根据ID获取房间
export function getRoomById(id) {
  return request.get(`/rooms/${id}`)
}

// 新增房间
export function addRoom(data) {
  return request.post('/rooms', data)
}

// 修改房间
export function updateRoom(data) {
  return request.put('/rooms', data)
}

// 删除房间
export function deleteRoom(id) {
  return request.delete(`/rooms/${id}`)
}
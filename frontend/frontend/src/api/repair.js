import request from './request'

// 获取报修订单列表（分页）
export function getRepairOrders(params) {
  return request.get('/repair-orders', { params })
}

// 获取所有报修订单
export function getAllRepairOrders() {
  return request.get('/repair-orders/all')
}

// 根据ID获取报修订单
export function getRepairOrderById(id) {
  return request.get(`/repair-orders/${id}`)
}

// 新增报修订单
export function addRepairOrder(data) {
  return request.post('/repair-orders', data)
}

// 修改报修订单
export function updateRepairOrder(data) {
  return request.put('/repair-orders', data)
}

// 删除报修订单
export function deleteRepairOrder(id) {
  return request.delete(`/repair-orders/${id}`)
}
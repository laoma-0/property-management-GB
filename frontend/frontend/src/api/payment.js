import request from './request'

// 获取缴费账单列表（分页）
export function getPaymentBills(params) {
  return request.get('/payment-bills', { params })
}

// 获取所有缴费账单
export function getAllPaymentBills() {
  return request.get('/payment-bills/all')
}

// 根据ID获取缴费账单
export function getPaymentBillById(id) {
  return request.get(`/payment-bills/${id}`)
}

// 新增缴费账单
export function addPaymentBill(data) {
  return request.post('/payment-bills', data)
}

// 修改缴费账单
export function updatePaymentBill(data) {
  return request.put('/payment-bills', data)
}

// 删除缴费账单
export function deletePaymentBill(id) {
  return request.delete(`/payment-bills/${id}`)
}

// 获取费用类型列表
export function getFeeTypes() {
  return request.get('/fee-types')
}

// 获取房间列表
export function getRooms() {
  return request.get('/rooms/all')
}
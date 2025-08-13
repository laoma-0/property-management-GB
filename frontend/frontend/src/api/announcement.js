import request from './request'

// 获取公告列表（分页）
export function getAnnouncements(params) {
  return request.get('/announcements', { params })
}

// 获取所有公告
export function getAllAnnouncements() {
  return request.get('/announcements/all')
}

// 根据ID获取公告
export function getAnnouncementById(id) {
  return request.get(`/announcements/${id}`)
}

// 新增公告
export function addAnnouncement(data) {
  return request.post('/announcements', data)
}

// 修改公告
export function updateAnnouncement(data) {
  return request.put('/announcements', data)
}

// 删除公告
export function deleteAnnouncement(id) {
  return request.delete(`/announcements/${id}`)
}
import request from './request'

// 获取首页统计数据
export function getHomeStatistics() {
  return request.get('/home/statistics')
}

// 获取系统概览信息
export function getSystemOverview() {
  return request.get('/home/overview')
}
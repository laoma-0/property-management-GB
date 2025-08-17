import { describe, it, expect } from 'vitest'

describe('HeaderBar', () => {
  it('应该能够正确导入', async () => {
    // 简单测试确保组件能够正确导入
    const module = await import('./HeaderBar.vue')
    expect(module).toBeDefined()
  })
})
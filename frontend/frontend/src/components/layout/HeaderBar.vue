<template>
  <div class="header-container">
    <!-- 左侧：系统名称和面包屑 -->
    <div class="header-left">
      <div class="system-name">物业管理系统</div>
      <Breadcrumb />
    </div>

    <!-- 右侧：用户信息和操作 -->
    <div class="header-right">
      <!-- 通知图标 -->
      <el-badge :value="5" class="header-icon">
        <el-icon :size="20">
          <Bell />
        </el-icon>
      </el-badge>

      <!-- 用户信息 -->
      <el-dropdown trigger="click">
        <div class="user-info">
          <el-avatar :size="36" :src="userAvatar" />
          <span class="username">{{ username }}</span>
          <el-icon><ArrowDown /></el-icon>
        </div>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>
              <el-icon><User /></el-icon> 个人中心
            </el-dropdown-item>
            <el-dropdown-item @click="handleLogout">
              <el-icon><SwitchButton /></el-icon> 退出登录
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/userStore'
import { Bell, User, ArrowDown, SwitchButton } from '@element-plus/icons-vue'
import Breadcrumb from './Breadcrumb.vue'
import { ElMessage } from 'element-plus'

const router = useRouter()
const userStore = useUserStore() // 添加这行来实际创建userStore实例

// 用户头像 - 使用默认头像
const userAvatar = ref('https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png')

// 用户名显示
const username = computed(() => {
  // 确保userStore存在且有userInfo
  if (userStore && userStore.userInfo) {
    return userStore.userInfo.username || userStore.userInfo.name || '管理员'
  }
  return '管理员'
})

// 退出登录
const handleLogout = () => {
  userStore.logout()
  ElMessage.success('退出登录成功')
  router.push('/login')
}
</script>

<style scoped>
.header-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 100%;
  padding: 0 20px;
  background-color: #08aeae;
  color: white;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 20px;
}

.system-name {
  font-size: 20px;
  font-weight: bold;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 20px;
}

.header-icon {
  cursor: pointer;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
}

.username {
  font-size: 14px;
}
</style>
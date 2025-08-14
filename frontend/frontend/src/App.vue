<template>
  <el-config-provider :locale="zhCn">
    <router-view />
  </el-config-provider>
</template>

<script setup>
import { onMounted } from 'vue'
import { useUserStore } from '@/store/userStore'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import { useRouter } from 'vue-router'

const userStore = useUserStore()
const router = useRouter()

// 检查登录状态
const checkLoginStatus = async () => {
  // 检查是否过期
  if (userStore.isLoginExpired()) {
    userStore.logout()
    // 如果当前不在登录页，则跳转到登录页
    if (router.currentRoute.value.path !== '/login') {
      router.push('/login')
    }
    return
  }
  
  // 如果有token但没有用户信息，尝试获取用户信息
  if (userStore.token && !userStore.userInfo) {
    try {
      await userStore.getUserInfo()
    } catch (error) {
      console.error('获取用户信息失败:', error)
      userStore.logout()
      if (router.currentRoute.value.path !== '/login') {
        router.push('/login')
      }
    }
  }
}

onMounted(() => {
  checkLoginStatus()
})
</script>

<style>
#app {
  height: 100%;
}
</style>
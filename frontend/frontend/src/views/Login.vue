<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2 class="login-title">智慧物业管理系统</h2>
      <el-form
          ref="loginFormRef"
          :model="loginForm"
          :rules="loginRules"
          label-width="80px"
      >
        <el-form-item label="用户名" prop="username">
          <el-input
              v-model="loginForm.username"
              placeholder="请输入用户名"
              prefix-icon="User"
          />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
              v-model="loginForm.password"
              type="password"
              placeholder="请输入密码"
              prefix-icon="Lock"
          />
        </el-form-item>
        <el-form-item>
          <el-button
              type="primary"
              style="width: 100%"
              @click="handleLogin"
              :loading="loading"
          >
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/userStore'
import { ElMessage } from 'element-plus'

const router = useRouter()
const userStore = useUserStore()
const loading = ref(false)
const loginFormRef = ref(null)

const loginForm = reactive({
  username: '',
  password: ''
})

const loginRules = reactive({
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码长度至少6位', trigger: 'blur' }
  ]
})

const handleLogin = async () => {
  try {
    await loginFormRef.value.validate()
    loading.value = true
    // 调用登录接口
    await userStore.login(loginForm.username, loginForm.password)
    ElMessage.success('登录成功')
    // 跳转至来源页或首页
    const redirect = router.currentRoute.value.query.redirect || '/'
    router.push(redirect)
  } catch (error) {
    if (error.name !== 'ValidationError') {
      ElMessage.error(error.message || '登录失败')
    }
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f7fa;
}

.login-card {
  width: 400px;
  padding: 30px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.login-title {
  text-align: center;
  margin-bottom: 20px;
  color: #1f2329;
}
</style>
<template>
  <div class="login-container">
    <div class="login-background"></div>
    <div class="login-box">
      <div class="login-left">
        <div class="welcome-text">
          <h1>欢迎使用</h1>
          <h2>智慧物业管理系统</h2>
          <p>高效、智能、便捷的物业管理解决方案</p>
        </div>
      </div>
      <div class="login-right">
        <el-card class="login-card" shadow="never">
          <div class="card-header">
            <h2>用户登录</h2>
            <p>请输入您的账号和密码</p>
          </div>
          <el-form
              ref="loginFormRef"
              :model="loginForm"
              :rules="loginRules"
              class="login-form"
          >
            <el-form-item prop="username">
              <el-input
                  v-model="loginForm.username"
                  placeholder="请输入用户名"
                  prefix-icon="User"
                  size="large"
                  class="login-input"
              />
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                  v-model="loginForm.password"
                  type="password"
                  placeholder="请输入密码"
                  prefix-icon="Lock"
                  size="large"
                  class="login-input"
                  @keyup.enter="handleLogin"
              />
            </el-form-item>
            <el-form-item>
              <el-button
                  type="primary"
                  size="large"
                  class="login-button"
                  @click="handleLogin"
                  :loading="loading"
                  round
              >
                登录系统
              </el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
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

// 检查是否已登录
const checkLoggedIn = () => {
  if (userStore.token && !userStore.isLoginExpired()) {
    router.push('/')
  }
}

const handleLogin = async () => {
  try {
    await loginFormRef.value.validate()
    loading.value = true
    // 调用登录接口
    await userStore.login(loginForm.username, loginForm.password)
    ElMessage.success('登录成功')
    // 登录成功后直接跳转到主页，不使用redirect参数
    router.push('/')
  } catch (error) {
    if (error.name !== 'ValidationError') {
      ElMessage.error(error.message || '登录失败')
    }
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  checkLoggedIn()
})
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  position: relative;
  overflow: hidden;
}

.login-background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: linear-gradient(135deg, #667eea 0%, #08aeae 100%);
  background-size: 400% 400%;
  animation: gradientBG 15s ease infinite;
  z-index: 1;
}

@keyframes gradientBG {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}

.login-box {
  display: flex;
  width: 900px;
  height: 500px;
  border-radius: 20px;
  overflow: hidden;
  box-shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.25);
  z-index: 2;
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
}

.login-left {
  flex: 1;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px;
  color: white;
}

.welcome-text h1 {
  font-size: 36px;
  font-weight: 700;
  margin-bottom: 10px;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.welcome-text h2 {
  font-size: 28px;
  font-weight: 600;
  margin-bottom: 20px;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.welcome-text p {
  font-size: 16px;
  opacity: 0.9;
  line-height: 1.6;
}

.login-right {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px;
}

.login-card {
  width: 100%;
  max-width: 350px;
  border: none;
  background: transparent;
}

.card-header {
  text-align: center;
  margin-bottom: 30px;
}

.card-header h2 {
  font-size: 24px;
  font-weight: 600;
  color: #333;
  margin-bottom: 8px;
}

.card-header p {
  font-size: 14px;
  color: #666;
  margin: 0;
}

.login-form {
  width: 100%;
}

:deep(.el-form-item) {
  margin-bottom: 24px;
}

.login-input {
  height: 48px;
}

:deep(.el-input__wrapper) {
  border-radius: 8px;
  box-shadow: 0 0 0 1px #dcdfe6 inset;
  transition: box-shadow 0.3s ease;
}

:deep(.el-input__wrapper.is-focus) {
  box-shadow: 0 0 0 1px #08aeae inset;
}

:deep(.el-input__prefix) {
  padding-left: 12px;
}

.login-button {
  width: 100%;
  height: 48px;
  margin-top: 10px;
  font-size: 16px;
  font-weight: 500;
  background: linear-gradient(135deg, #08aeae 0%, #764ba2 100%);
  border: none;
  transition: all 0.3s ease;
}

.login-button:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(102, 126, 234, 0.4);
}

/* 响应式设计 */
@media (max-width: 768px) {
  .login-box {
    flex-direction: column;
    width: 90%;
    height: auto;
    margin: 20px;
  }
  
  .login-left {
    padding: 30px 20px;
  }
  
  .login-right {
    padding: 30px 20px;
  }
  
  .welcome-text h1 {
    font-size: 28px;
  }
  
  .welcome-text h2 {
    font-size: 22px;
  }
}

@media (max-width: 480px) {
  .login-box {
    width: 95%;
    margin: 10px;
  }
  
  .login-left,
  .login-right {
    padding: 20px 15px;
  }
  
  .card-header h2 {
    font-size: 20px;
  }
}
</style>
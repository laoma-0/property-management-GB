<template>
  <div class="user-container">
    <el-card class="user-card">
      <template #header>
        <div class="card-header">
          <span>用户管理</span>
          <el-button type="primary" @click="handleAddUser">新增用户</el-button>
        </div>
      </template>

      <!-- 用户搜索 -->
      <el-form :model="searchForm" label-width="80px" class="search-form">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="用户名">
              <el-input v-model="searchForm.username" placeholder="请输入用户名" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="姓名">
              <el-input v-model="searchForm.realName" placeholder="请输入姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="手机号">
              <el-input v-model="searchForm.phone" placeholder="请输入手机号" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="用户类型">
              <el-select v-model="searchForm.userType" placeholder="请选择用户类型" clearable style="width: 100%">
                <el-option label="业主" :value="0" />
                <el-option label="物业人员" :value="1" />
                <el-option label="维修工" :value="2" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24" style="text-align: right;">
            <el-form-item>
              <el-button type="primary" @click="handleSearch">搜索</el-button>
              <el-button @click="handleReset">重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <!-- 用户列表 -->
      <div class="table-container">
        <el-table 
          :data="userList" 
          border 
          style="width: 100%" 
          v-loading="loading"
          height="100%"
          max-height="100%">
          <el-table-column prop="userId" label="用户ID" width="80" />
          <el-table-column prop="username" label="用户名" />
          <el-table-column prop="realName" label="姓名" />
          <el-table-column prop="userType" label="用户类型">
            <template #default="scope">
              <el-tag :type="getUserTypeTag(scope.row.userType)">
                {{ getUserTypeName(scope.row.userType) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="phone" label="手机号" />
          <el-table-column prop="createTime" label="创建时间" width="180">
            <template #default="scope">
              {{ formatDate(scope.row.createTime) }}
            </template>
          </el-table-column>
          <el-table-column label="操作" width="200" fixed="right">
            <template #default="scope">
              <div class="operation-buttons">
                <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
                <el-button size="small" type="danger" @click="handleDelete(scope.row)">删除</el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <!-- 分页 -->
      <el-pagination
        v-model:current-page="pagination.pageNum"
        v-model:page-size="pagination.pageSize"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pagination.total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        class="pagination"
      />

      <!-- 用户编辑对话框 -->
      <el-dialog v-model="dialogVisible" :title="dialogTitle" width="500px" @close="handleDialogClose">
        <el-form :model="userForm" :rules="userRules" ref="userFormRef" label-width="80px">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="userForm.username" :disabled="!!userForm.userId" />
          </el-form-item>
          <el-form-item label="密码" prop="password" v-if="!userForm.userId">
            <el-input v-model="userForm.password" type="password" show-password />
          </el-form-item>
          <el-form-item label="姓名" prop="realName">
            <el-input v-model="userForm.realName" />
          </el-form-item>
          <el-form-item label="用户类型" prop="userType">
            <el-select v-model="userForm.userType" style="width: 100%">
              <el-option label="业主" :value="0" />
              <el-option label="物业人员" :value="1" />
              <el-option label="维修工" :value="2" />
            </el-select>
          </el-form-item>
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="userForm.phone" />
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="submitUserForm">确定</el-button>
          </span>
        </template>
      </el-dialog>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { 
  getUsers, 
  addUser, 
  updateUser, 
  deleteUser 
} from '@/api/sysUser'

// 用户列表相关
const userList = ref([])
const loading = ref(false)

// 搜索表单
const searchForm = reactive({
  username: '',
  realName: '',
  phone: '',
  userType: ''
})

// 分页参数
const pagination = reactive({
  pageNum: 1,
  pageSize: 10,
  total: 0
})

// 对话框相关
const dialogVisible = ref(false)
const dialogTitle = ref('')
const isEdit = ref(false)

// 用户表单
const userForm = reactive({
  userId: null,
  username: '',
  password: '',
  realName: '',
  userType: '',
  phone: ''
})

// 表单验证规则
const userRules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码长度至少6位', trigger: 'blur' }
  ],
  realName: [
    { required: true, message: '请输入姓名', trigger: 'blur' }
  ],
  userType: [
    { required: true, message: '请选择用户类型', trigger: 'change' }
  ],
  phone: [
    { required: true, message: '请输入手机号', trigger: 'blur' },
    { pattern: /^1[3-9]\d{9}$/, message: '手机号格式不正确', trigger: 'blur' }
  ]
}

// 表单引用
const userFormRef = ref(null)

// 获取用户类型名称
const getUserTypeName = (type) => {
  switch (type) {
    case 0: return '业主'
    case 1: return '物业人员'
    case 2: return '维修工'
    default: return '未知'
  }
}

// 获取用户类型标签样式
const getUserTypeTag = (type) => {
  switch (type) {
    case 0: return 'success'  // 业主 - 绿色
    case 1: return 'primary'  // 物业人员 - 蓝色
    case 2: return 'warning'  // 维修工 - 橙色
    default: return 'info'    // 未知 - 灰色
  }
}

// 格式化日期
const formatDate = (date) => {
  if (!date) return ''
  const d = new Date(date)
  return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, '0')}-${String(d.getDate()).padStart(2, '0')} ${String(d.getHours()).padStart(2, '0')}:${String(d.getMinutes()).padStart(2, '0')}:${String(d.getSeconds()).padStart(2, '0')}`
}

// 获取用户列表
const getUserList = async () => {
  try {
    loading.value = true
    const res = await getUsers({
      pageNum: pagination.pageNum,
      pageSize: pagination.pageSize,
      ...searchForm
    })
    
    if (res.code === 200) {
      userList.value = res.data
      pagination.total = res.total
    } else {
      ElMessage.error(res.msg || '获取用户列表失败')
    }
  } catch (error) {
    ElMessage.error('获取用户列表失败: ' + error.message)
  } finally {
    loading.value = false
  }
}

// 处理搜索
const handleSearch = () => {
  pagination.pageNum = 1
  getUserList()
}

// 处理重置
const handleReset = () => {
  searchForm.username = ''
  searchForm.realName = ''
  searchForm.phone = ''
  searchForm.userType = ''
  pagination.pageNum = 1
  getUserList()
}

// 处理分页大小变化
const handleSizeChange = (val) => {
  pagination.pageSize = val
  pagination.pageNum = 1
  getUserList()
}

// 处理页码变化
const handleCurrentChange = (val) => {
  pagination.pageNum = val
  getUserList()
}

// 处理新增用户
const handleAddUser = () => {
  dialogTitle.value = '新增用户'
  isEdit.value = false
  // 重置表单
  Object.assign(userForm, {
    userId: null,
    username: '',
    password: '',
    realName: '',
    userType: '',
    phone: ''
  })
  
  // 恢复验证规则，新增时密码必填
  userRules.password = [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码长度至少6位', trigger: 'blur' }
  ]
  
  dialogVisible.value = true
}

// 处理编辑用户
const handleEdit = (row) => {
  dialogTitle.value = '编辑用户'
  isEdit.value = true
  // 填充表单数据
  Object.assign(userForm, {
    userId: row.userId,
    username: row.username,
    password: '', // 编辑时不显示原密码
    realName: row.realName,
    userType: row.userType,
    phone: row.phone
  })
  
  // 修改验证规则，编辑时密码非必填
  userRules.password = [
    { min: 6, message: '密码长度至少6位', trigger: 'blur' }
  ]
  
  dialogVisible.value = true
}

// 处理删除用户
const handleDelete = (row) => {
  ElMessageBox.confirm(
    `确定要删除用户"${row.username}"吗？`,
    '确认删除',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(async () => {
    try {
      const res = await deleteUser(row.userId)
      if (res.code === 200) {
        ElMessage.success('删除成功')
        getUserList()
      } else {
        ElMessage.error(res.msg || '删除失败')
      }
    } catch (error) {
      ElMessage.error('删除失败: ' + error.message)
    }
  }).catch(() => {
    // 用户取消删除
  })
}

// 处理对话框关闭
const handleDialogClose = () => {
  userFormRef.value?.resetFields()
}

// 提交用户表单
const submitUserForm = async () => {
  try {
    await userFormRef.value.validate()
    
    // 处理表单数据
    const formData = { ...userForm }
    
    if (isEdit.value) {
      // 编辑用户
      // 如果密码字段为空且未修改密码，则删除该字段
      if (!formData.password) {
        delete formData.password
      }
      
      const res = await updateUser(formData)
      if (res.code === 200) {
        ElMessage.success('更新成功')
        dialogVisible.value = false
        getUserList()
      } else {
        ElMessage.error(res.msg || '更新失败')
      }
    } else {
      // 新增用户，必须填写密码
      const res = await addUser(formData)
      if (res.code === 200) {
        ElMessage.success('新增成功')
        dialogVisible.value = false
        getUserList()
      } else {
        ElMessage.error(res.msg || '新增失败')
      }
    }
  } catch (error) {
    if (error.name !== 'ValidationError') {
      ElMessage.error((isEdit.value ? '更新' : '新增') + '失败: ' + error.message)
    }
  }
}

// 组件挂载时获取用户列表
onMounted(() => {
  getUserList()
})
</script>

<style scoped>
.user-container {
  height: 100%;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.user-card {
  flex: 1;
  display: flex;
  flex-direction: column;
  height: 100%;
  overflow: hidden;
}

.user-card :deep(.el-card__body) {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.search-form {
  margin-bottom: 20px;
  flex-shrink: 0;
}

.table-container {
  flex: 1;
  overflow: hidden;
  position: relative;
}

.table-container :deep(.el-table) {
  height: 100%;
}

.pagination {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  flex-shrink: 0;
}

.operation-buttons {
  display: flex;
  gap: 8px;
  flex-wrap: nowrap;
}

.operation-buttons .el-button {
  flex: 1;
  min-width: 0;
}
</style>
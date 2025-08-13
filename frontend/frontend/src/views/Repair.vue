<template>
  <div class="repair-container">
    <el-card class="repair-card">
      <template #header>
        <div class="card-header">
          <span>报修管理</span>
          <el-button type="primary" @click="handleAddRepairOrder">新增报修</el-button>
        </div>
      </template>

      <!-- 报修订单搜索 -->
      <el-form :model="searchForm" label-width="80px" class="search-form">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="用户ID">
              <el-input v-model="searchForm.userId" placeholder="请输入用户ID" type="number" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="状态">
              <el-select v-model="searchForm.status" placeholder="请选择状态" clearable style="width: 100%">
                <el-option label="待处理" :value="0" />
                <el-option label="处理中" :value="1" />
                <el-option label="已完成" :value="2" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item>
              <el-button type="primary" @click="handleSearch">搜索</el-button>
              <el-button @click="handleReset">重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <!-- 报修订单列表 -->
      <div class="table-container">
        <el-table 
          :data="repairOrderList" 
          border 
          style="width: 100%" 
          v-loading="loading"
          height="100%">
          <el-table-column prop="orderId" label="订单ID" width="100" />
          <el-table-column prop="user.realName" label="报修人" width="120" />
          <el-table-column prop="room.roomNumber" label="房间号" width="100" />
          <el-table-column prop="repairType" label="报修类型" width="120" />
          <el-table-column prop="description" label="问题描述" show-overflow-tooltip />
          <el-table-column prop="aiLabel" label="AI标签" width="120" />
          <el-table-column prop="status" label="状态" width="100">
            <template #default="scope">
              <el-tag :type="getStatusTagType(scope.row.status)">
                {{ getStatusText(scope.row.status) }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="assignee.realName" label="处理人" width="120" />
          <el-table-column prop="createTime" label="创建时间" width="180">
            <template #default="scope">
              {{ formatDate(scope.row.createTime) }}
            </template>
          </el-table-column>
          <el-table-column prop="completeTime" label="完成时间" width="180">
            <template #default="scope">
              {{ scope.row.completeTime ? formatDate(scope.row.completeTime) : '-' }}
            </template>
          </el-table-column>
          <el-table-column label="操作" width="200" fixed="right">
            <template #default="scope">
              <div class="operation-buttons">
                <el-button size="small" @click="handleView(scope.row)">查看</el-button>
                <el-button 
                  size="small" 
                  :type="scope.row.status === 0 ? 'primary' : scope.row.status === 1 ? 'success' : 'info'"
                  @click="handleProcess(scope.row)">
                  {{ scope.row.status === 0 ? '派单' : scope.row.status === 1 ? '完成' : '已完成' }}
                </el-button>
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

      <!-- 报修订单查看对话框 -->
      <el-dialog v-model="viewDialogVisible" title="查看报修订单" width="600px">
        <el-form :model="currentOrder" label-width="100px">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="订单ID">
                <span>{{ currentOrder.orderId }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="报修人">
                <span>{{ currentOrder.user?.realName || '-' }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="房间号">
                <span>{{ currentOrder.room?.roomNumber || '-' }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="报修类型">
                <span>{{ currentOrder.repairType || '-' }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-form-item label="问题描述">
            <span>{{ currentOrder.description || '-' }}</span>
          </el-form-item>
          
          <el-form-item label="AI标签">
            <span>{{ currentOrder.aiLabel || '-' }}</span>
          </el-form-item>
          
          <el-form-item label="状态">
            <el-tag :type="getStatusTagType(currentOrder.status)">
              {{ getStatusText(currentOrder.status) }}
            </el-tag>
          </el-form-item>
          
          <el-form-item label="处理人">
            <span>{{ currentOrder.assignee?.realName || '-' }}</span>
          </el-form-item>
          
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="创建时间">
                <span>{{ currentOrder.createTime ? formatDate(currentOrder.createTime) : '-' }}</span>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="完成时间">
                <span>{{ currentOrder.completeTime ? formatDate(currentOrder.completeTime) : '-' }}</span>
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-form-item label="图片">
            <el-image
              v-if="currentOrder.imagePath"
              :src="currentOrder.imagePath"
              :preview-src-list="[currentOrder.imagePath]"
              style="width: 100px; height: 100px"
              fit="cover"
            />
            <span v-else>-</span>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="viewDialogVisible = false">关闭</el-button>
          </span>
        </template>
      </el-dialog>

      <!-- 报修订单处理对话框 -->
      <el-dialog v-model="processDialogVisible" :title="processDialogTitle" width="500px" @close="handleProcessDialogClose">
        <el-form :model="processForm" :rules="processRules" ref="processFormRef" label-width="100px">
          <el-form-item label="状态">
            <el-select v-model="processForm.status" style="width: 100%">
              <el-option 
                v-for="item in statusOptions" 
                :key="item.value" 
                :label="item.label" 
                :value="item.value"
                :disabled="shouldDisableStatus(item.value)" />
            </el-select>
          </el-form-item>
          
          <el-form-item v-if="processForm.status > 0" label="处理人" prop="assigneeId">
            <el-select v-model="processForm.assigneeId" placeholder="请选择处理人" style="width: 100%">
              <el-option
                v-for="item in repairWorkers"
                :key="item.userId"
                :label="item.realName"
                :value="item.userId"
              />
            </el-select>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="processDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="submitProcessForm">确定</el-button>
          </span>
        </template>
      </el-dialog>

      <!-- 报修订单新增/编辑对话框 -->
      <el-dialog v-model="editDialogVisible" :title="editDialogTitle" width="600px" @close="handleEditDialogClose">
        <el-form :model="editForm" :rules="editRules" ref="editFormRef" label-width="100px">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="报修人ID" prop="userId">
                <el-input v-model.number="editForm.userId" type="number" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="房间ID" prop="roomId">
                <el-input v-model.number="editForm.roomId" type="number" />
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="报修类型" prop="repairType">
                <el-input v-model="editForm.repairType" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="AI标签" prop="aiLabel">
                <el-input v-model="editForm.aiLabel" />
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-form-item label="问题描述" prop="description">
            <el-input 
              v-model="editForm.description" 
              type="textarea" 
              :rows="3" 
              placeholder="请输入问题描述" 
            />
          </el-form-item>
          
          <el-form-item label="图片路径" prop="imagePath">
            <el-input v-model="editForm.imagePath" placeholder="请输入图片路径" />
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="editDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="submitEditForm">确定</el-button>
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
  getRepairOrders, 
  addRepairOrder, 
  updateRepairOrder, 
  deleteRepairOrder,
  getRepairOrderById
} from '@/api/repair'

// 报修订单列表相关
const repairOrderList = ref([])
const loading = ref(false)

// 搜索表单
const searchForm = reactive({
  userId: '',
  status: ''
})

// 分页参数
const pagination = reactive({
  pageNum: 1,
  pageSize: 10,
  total: 0
})

// 查看对话框相关
const viewDialogVisible = ref(false)
const currentOrder = ref({})

// 处理对话框相关
const processDialogVisible = ref(false)
const processDialogTitle = ref('')
const processForm = reactive({
  orderId: null,
  status: 0,
  assigneeId: null
})

// 编辑对话框相关
const editDialogVisible = ref(false)
const editDialogTitle = ref('')
const isEdit = ref(false)
const editForm = reactive({
  orderId: null,
  userId: '',
  roomId: '',
  repairType: '',
  description: '',
  aiLabel: '',
  imagePath: ''
})

// 维修工列表（模拟数据）
const repairWorkers = ref([
  { userId: 6, realName: '维修工A' },
  { userId: 7, realName: '维修工B' }
])

// 状态选项
const statusOptions = [
  { label: '待处理', value: 0 },
  { label: '处理中', value: 1 },
  { label: '已完成', value: 2 }
]

// 处理表单验证规则
const processRules = {
  assigneeId: [
    { required: true, message: '请选择处理人', trigger: 'change' }
  ]
}

// 编辑表单验证规则
const editRules = {
  userId: [
    { required: true, message: '请输入报修人ID', trigger: 'blur' }
  ],
  roomId: [
    { required: true, message: '请输入房间ID', trigger: 'blur' }
  ],
  repairType: [
    { required: true, message: '请输入报修类型', trigger: 'blur' }
  ],
  description: [
    { required: true, message: '请输入问题描述', trigger: 'blur' }
  ]
}

// 表单引用
const processFormRef = ref(null)
const editFormRef = ref(null)

// 获取状态文本
const getStatusText = (status) => {
  switch (status) {
    case 0: return '待处理'
    case 1: return '处理中'
    case 2: return '已完成'
    default: return '未知'
  }
}

// 获取状态标签类型
const getStatusTagType = (status) => {
  switch (status) {
    case 0: return 'info'    // 待处理 - 灰色
    case 1: return 'warning' // 处理中 - 橙色
    case 2: return 'success' // 已完成 - 绿色
    default: return 'info'   // 未知 - 灰色
  }
}

// 判断是否应禁用状态选项
const shouldDisableStatus = (status) => {
  // 如果当前订单状态为已完成，则不能选择待处理或处理中
  if (currentOrder.value.status === 2 && status !== 2) {
    return true
  }
  // 如果当前订单状态为处理中，不能选择待处理
  if (currentOrder.value.status === 1 && status === 0) {
    return true
  }
  return false
}

// 格式化日期
const formatDate = (date) => {
  if (!date) return ''
  const d = new Date(date)
  return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, '0')}-${String(d.getDate()).padStart(2, '0')} ${String(d.getHours()).padStart(2, '0')}:${String(d.getMinutes()).padStart(2, '0')}:${String(d.getSeconds()).padStart(2, '0')}`
}

// 获取报修订单列表
const getRepairOrderList = async () => {
  try {
    loading.value = true
    const res = await getRepairOrders({
      pageNum: pagination.pageNum,
      pageSize: pagination.pageSize,
      userId: searchForm.userId || undefined,
      status: searchForm.status !== '' ? searchForm.status : undefined
    })
    
    if (res.code === 200) {
      repairOrderList.value = res.data
      pagination.total = res.total
    } else {
      ElMessage.error(res.msg || '获取报修订单列表失败')
    }
  } catch (error) {
    ElMessage.error('获取报修订单列表失败: ' + error.message)
  } finally {
    loading.value = false
  }
}

// 处理搜索
const handleSearch = () => {
  pagination.pageNum = 1
  getRepairOrderList()
}

// 处理重置
const handleReset = () => {
  searchForm.userId = ''
  searchForm.status = ''
  pagination.pageNum = 1
  getRepairOrderList()
}

// 处理分页大小变化
const handleSizeChange = (val) => {
  pagination.pageSize = val
  pagination.pageNum = 1
  getRepairOrderList()
}

// 处理页码变化
const handleCurrentChange = (val) => {
  pagination.pageNum = val
  getRepairOrderList()
}

// 处理查看报修订单
const handleView = (row) => {
  currentOrder.value = { ...row }
  viewDialogVisible.value = true
}

// 处理处理报修订单
const handleProcess = (row) => {
  currentOrder.value = { ...row }
  processDialogTitle.value = '处理报修订单'
  
  // 填充处理表单数据
  Object.assign(processForm, {
    orderId: row.orderId,
    status: row.status + 1 > 2 ? 2 : row.status + 1, // 状态只能向前推进
    assigneeId: row.assigneeId || null
  })
  
  processDialogVisible.value = true
}

// 处理处理对话框关闭
const handleProcessDialogClose = () => {
  processFormRef.value?.resetFields()
}

// 提交处理表单
const submitProcessForm = async () => {
  try {
    await processFormRef.value?.validate()
    
    // 构造更新数据（只传递需要更新的字段）
    const updateData = {
      orderId: processForm.orderId,
      status: processForm.status
    }
    
    // 只有在状态不是待处理时才传递处理人ID
    if (processForm.status > 0) {
      updateData.assigneeId = processForm.assigneeId
    }
    
    const res = await updateRepairOrder(updateData)
    if (res.code === 200) {
      ElMessage.success('处理成功')
      processDialogVisible.value = false
      getRepairOrderList()
    } else {
      ElMessage.error(res.msg || '处理失败')
    }
  } catch (error) {
    if (error.name !== 'ValidationError') {
      ElMessage.error('处理失败: ' + error.message)
    }
  }
}

// 处理新增报修订单
const handleAddRepairOrder = () => {
  editDialogTitle.value = '新增报修订单'
  isEdit.value = false
  // 重置表单
  Object.assign(editForm, {
    orderId: null,
    userId: '',
    roomId: '',
    repairType: '',
    description: '',
    aiLabel: '',
    imagePath: ''
  })
  editDialogVisible.value = true
}

// 处理编辑报修订单
const handleEdit = (row) => {
  editDialogTitle.value = '编辑报修订单'
  isEdit.value = true
  // 填充表单数据
  Object.assign(editForm, {
    orderId: row.orderId,
    userId: row.userId,
    roomId: row.roomId,
    repairType: row.repairType,
    description: row.description,
    aiLabel: row.aiLabel,
    imagePath: row.imagePath
  })
  editDialogVisible.value = true
}

// 处理编辑对话框关闭
const handleEditDialogClose = () => {
  editFormRef.value?.resetFields()
}

// 提交编辑表单
const submitEditForm = async () => {
  try {
    await editFormRef.value.validate()
    
    // 处理表单数据（只传递非空字段）
    const formData = { ...editForm }
    
    if (isEdit.value) {
      // 编辑报修订单
      const res = await updateRepairOrder(formData)
      if (res.code === 200) {
        ElMessage.success('更新成功')
        editDialogVisible.value = false
        getRepairOrderList()
      } else {
        ElMessage.error(res.msg || '更新失败')
      }
    } else {
      // 新增报修订单
      const res = await addRepairOrder(formData)
      if (res.code === 200) {
        ElMessage.success('新增成功')
        editDialogVisible.value = false
        getRepairOrderList()
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

// 处理删除报修订单
const handleDelete = (row) => {
  ElMessageBox.confirm(
    `确定要删除报修订单"${row.orderId}"吗？`,
    '确认删除',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(async () => {
    try {
      const res = await deleteRepairOrder(row.orderId)
      if (res.code === 200) {
        ElMessage.success('删除成功')
        getRepairOrderList()
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

// 组件挂载时获取报修订单列表
onMounted(() => {
  getRepairOrderList()
})
</script>

<style scoped>
.repair-container {
  height: 100%;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.repair-card {
  flex: 1;
  display: flex;
  flex-direction: column;
  height: 100%;
  overflow: hidden;
}

.repair-card :deep(.el-card__body) {
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
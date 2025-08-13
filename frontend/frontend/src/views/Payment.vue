<template>
  <div class="payment-container">
    <el-card class="payment-card">
      <template #header>
        <div class="card-header">
          <span>缴费管理</span>
          <el-button type="primary" @click="handleAddPayment">新增账单</el-button>
        </div>
      </template>

      <!-- 搜索条件 -->
      <el-form :model="searchForm" label-width="80px" class="search-form">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="房间号">
              <el-input v-model="searchForm.roomId" placeholder="请输入房间ID" />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="费用类型">
              <el-select v-model="searchForm.feeId" placeholder="请选择费用类型" clearable style="width: 100%">
                <el-option
                  v-for="item in feeTypes"
                  :key="item.feeId"
                  :label="item.feeName"
                  :value="item.feeId"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="缴费状态">
              <el-select v-model="searchForm.status" placeholder="请选择状态" clearable style="width: 100%">
                <el-option label="未缴费" :value="0" />
                <el-option label="已缴费" :value="1" />
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

      <!-- 缴费账单列表 -->
      <div class="table-container">
        <el-table 
          :data="paymentList" 
          border 
          style="width: 100%" 
          v-loading="loading"
          height="100%">
          <el-table-column prop="billId" label="账单ID" width="100" />
          <el-table-column prop="roomId" label="房间ID" width="100" />
          <el-table-column prop="feeType.feeName" label="费用类型" width="120" />
          <el-table-column prop="period" label="缴费周期" width="120" />
          <el-table-column prop="amount" label="金额(元)" width="120">
            <template #default="scope">
              ¥{{ scope.row.amount }}
            </template>
          </el-table-column>
          <el-table-column prop="dueDate" label="截止日期" width="120">
            <template #default="scope">
              {{ formatDate(scope.row.dueDate) }}
            </template>
          </el-table-column>
          <el-table-column prop="paymentTime" label="缴费时间" width="120">
            <template #default="scope">
              {{ scope.row.paymentTime ? formatDate(scope.row.paymentTime) : '-' }}
            </template>
          </el-table-column>
          <el-table-column prop="status" label="状态" width="100">
            <template #default="scope">
              <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
                {{ scope.row.status === 1 ? '已缴费' : '未缴费' }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" width="180">
            <template #default="scope">
              {{ formatDate(scope.row.createTime) }}
            </template>
          </el-table-column>
          <el-table-column label="操作" width="300" fixed="right">
            <template #default="scope">
              <div class="operation-buttons">
                <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
                <el-button 
                  size="small" 
                  :type="scope.row.status === 0 ? 'success' : 'warning'"
                  @click="handlePay(scope.row)">
                  {{ scope.row.status === 0 ? '缴费' : '取消缴费' }}
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

      <!-- 缴费账单编辑对话框 -->
      <el-dialog v-model="dialogVisible" :title="dialogTitle" width="600px" @close="handleDialogClose">
        <el-form :model="paymentForm" :rules="paymentRules" ref="paymentFormRef" label-width="100px">
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="房间ID" prop="roomId">
                <el-input v-model.number="paymentForm.roomId" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="费用类型" prop="feeId">
                <el-select v-model="paymentForm.feeId" placeholder="请选择费用类型" style="width: 100%">
                  <el-option
                    v-for="item in feeTypes"
                    :key="item.feeId"
                    :label="item.feeName"
                    :value="item.feeId"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="缴费周期" prop="period">
                <el-input v-model="paymentForm.period" placeholder="例如：2023-01" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="金额(元)" prop="amount">
                <el-input v-model.number="paymentForm.amount" type="number" />
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-row :gutter="20">
            <el-col :span="12">
              <el-form-item label="截止日期" prop="dueDate">
                <el-date-picker
                  v-model="paymentForm.dueDate"
                  type="date"
                  placeholder="请选择截止日期"
                  style="width: 100%"
                  value-format="YYYY-MM-DD"
                />
              </el-form-item>
            </el-col>
            <el-col :span="12" >
              <el-form-item label="缴费状态" prop="status">
                <el-select v-model="paymentForm.status" placeholder="请选择状态" style="width: 100%">
                  <el-option label="未缴费" :value="0" />
                  <el-option label="已缴费" :value="1" />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          
          <el-form-item label="缴费时间" prop="paymentTime">
            <el-date-picker
              v-model="paymentForm.paymentTime"
              type="datetime"
              placeholder="请选择缴费时间"
              style="width: 100%"
              value-format="YYYY-MM-DD HH:mm:ss"
            />
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="submitPaymentForm">确定</el-button>
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
  getPaymentBills, 
  addPaymentBill, 
  updatePaymentBill, 
  deletePaymentBill,
  getFeeTypes,
  getRooms
} from '@/api/payment'

// 缴费账单列表相关
const paymentList = ref([])
const loading = ref(false)

// 搜索表单
const searchForm = reactive({
  roomId: '',
  feeId: '',
  status: ''
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

// 缴费账单表单
const paymentForm = reactive({
  billId: null,
  roomId: '',
  feeId: '',
  period: '',
  amount: '',
  status: 0,
  dueDate: '',
  paymentTime: ''
})

// 表单验证规则
const paymentRules = {
  roomId: [
    { required: true, message: '请输入房间ID', trigger: 'blur' }
  ],
  feeId: [
    { required: true, message: '请选择费用类型', trigger: 'change' }
  ],
  period: [
    { required: true, message: '请输入缴费周期', trigger: 'blur' }
  ],
  amount: [
    { required: true, message: '请输入金额', trigger: 'blur' }
  ],
  dueDate: [
    { required: true, message: '请选择截止日期', trigger: 'change' }
  ]
}

// 费用类型列表
const feeTypes = ref([])

// 房间列表
const rooms = ref([])

// 表单引用
const paymentFormRef = ref(null)

// 格式化日期
const formatDate = (date) => {
  if (!date) return ''
  const d = new Date(date)
  return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, '0')}-${String(d.getDate()).padStart(2, '0')} ${String(d.getHours()).padStart(2, '0')}:${String(d.getMinutes()).padStart(2, '0')}:${String(d.getSeconds()).padStart(2, '0')}`
}

// 获取缴费账单列表
const getPaymentList = async () => {
  try {
    loading.value = true
    const res = await getPaymentBills({
      pageNum: pagination.pageNum,
      pageSize: pagination.pageSize,
      ...searchForm
    })
    
    if (res.code === 200) {
      paymentList.value = res.data
      pagination.total = res.total
    } else {
      ElMessage.error(res.msg || '获取缴费账单列表失败')
    }
  } catch (error) {
    ElMessage.error('获取缴费账单列表失败: ' + error.message)
  } finally {
    loading.value = false
  }
}

// 获取费用类型列表
const getFeeTypeList = async () => {
  try {
    const res = await getFeeTypes()
    if (res.code === 200) {
      feeTypes.value = res.data
    } else {
      ElMessage.error(res.msg || '获取费用类型失败')
    }
  } catch (error) {
    ElMessage.error('获取费用类型失败: ' + error.message)
  }
}

// 获取房间列表
const getRoomList = async () => {
  try {
    const res = await getRooms()
    if (res.code === 200) {
      rooms.value = res.data
    } else {
      ElMessage.error(res.msg || '获取房间列表失败')
    }
  } catch (error) {
    ElMessage.error('获取房间列表失败: ' + error.message)
  }
}

// 处理搜索
const handleSearch = () => {
  pagination.pageNum = 1
  getPaymentList()
}

// 处理重置
const handleReset = () => {
  searchForm.roomId = ''
  searchForm.feeId = ''
  searchForm.status = ''
  pagination.pageNum = 1
  getPaymentList()
}

// 处理分页大小变化
const handleSizeChange = (val) => {
  pagination.pageSize = val
  pagination.pageNum = 1
  getPaymentList()
}

// 处理页码变化
const handleCurrentChange = (val) => {
  pagination.pageNum = val
  getPaymentList()
}

// 处理新增缴费账单
const handleAddPayment = () => {
  dialogTitle.value = '新增缴费账单'
  isEdit.value = false
  // 重置表单
  Object.assign(paymentForm, {
    billId: null,
    roomId: '',
    feeId: '',
    period: '',
    amount: '',
    status: 0,
    dueDate: '',
    paymentTime: ''
  })
  dialogVisible.value = true
}

// 处理编辑缴费账单
const handleEdit = (row) => {
  dialogTitle.value = '编辑缴费账单'
  isEdit.value = true
  // 填充表单数据
  Object.assign(paymentForm, row)
  dialogVisible.value = true
}

// 处理缴费/取消缴费
const handlePay = async (row) => {
  try {
    const action = row.status === 0 ? '缴费' : '取消缴费'
    const newStatus = row.status === 0 ? 1 : 0
    
    // 构造更新数据
    const updateData = {
      ...row,
      status: newStatus,
      paymentTime: newStatus === 1 ? new Date() : null
    }
    
    const res = await updatePaymentBill(updateData)
    if (res.code === 200) {
      ElMessage.success(`${action}成功`)
      getPaymentList()
    } else {
      ElMessage.error(`${action}失败: ${res.msg}`)
    }
  } catch (error) {
    ElMessage.error(`${row.status === 0 ? '缴费' : '取消缴费'}失败: ` + error.message)
  }
}

// 处理删除缴费账单
const handleDelete = (row) => {
  ElMessageBox.confirm(
    `确定要删除账单"${row.billId}"吗？`,
    '确认删除',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(async () => {
    try {
      const res = await deletePaymentBill(row.billId)
      if (res.code === 200) {
        ElMessage.success('删除成功')
        getPaymentList()
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
  paymentFormRef.value?.resetFields()
}

// 提交缴费账单表单
const submitPaymentForm = async () => {
  try {
    await paymentFormRef.value.validate()
    
    // 处理表单数据
    const formData = { ...paymentForm }
    
    if (isEdit.value) {
      // 编辑缴费账单
      const res = await updatePaymentBill(formData)
      if (res.code === 200) {
        ElMessage.success('更新成功')
        dialogVisible.value = false
        getPaymentList()
      } else {
        ElMessage.error(res.msg || '更新失败')
      }
    } else {
      // 新增缴费账单
      const res = await addPaymentBill(formData)
      if (res.code === 200) {
        ElMessage.success('新增成功')
        dialogVisible.value = false
        getPaymentList()
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

// 组件挂载时获取数据
onMounted(() => {
  getPaymentList()
  getFeeTypeList()
  getRoomList()
})
</script>

<style scoped>
.payment-container {
  height: 100%;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.payment-card {
  flex: 1;
  display: flex;
  flex-direction: column;
  height: 100%;
  overflow: hidden;
}

.payment-card :deep(.el-card__body) {
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
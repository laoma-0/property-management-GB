<template>
  <div class="announcement-container">
    <el-card class="announcement-card">
      <template #header>
        <div class="card-header">
          <span>公告管理</span>
          <el-button type="primary" @click="handleAddAnnouncement">新增公告</el-button>
        </div>
      </template>

      <!-- 公告搜索 -->
      <el-form :model="searchForm" label-width="80px" class="search-form">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-form-item label="公告标题">
              <el-input v-model="searchForm.title" placeholder="请输入公告标题" />
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

      <!-- 公告列表 -->
      <div class="table-container">
        <el-table 
          :data="announcementList" 
          border 
          style="width: 100%" 
          v-loading="loading"
          height="100%">
          <el-table-column prop="announceId" label="公告ID" width="100" />
          <el-table-column prop="title" label="标题" show-overflow-tooltip />
          <el-table-column prop="content" label="内容" show-overflow-tooltip>
            <template #default="scope">
              <el-tooltip :content="scope.row.content" placement="top">
                <span>{{ scope.row.content.substring(0, 50) }}{{ scope.row.content.length > 50 ? '...' : '' }}</span>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column prop="publisherId" label="发布人ID" width="120" />
          <el-table-column prop="publishTime" label="发布时间" width="180">
            <template #default="scope">
              {{ formatDate(scope.row.publishTime) }}
            </template>
          </el-table-column>
          <el-table-column prop="isTop" label="置顶" width="80">
            <template #default="scope">
              <el-tag :type="scope.row.isTop ? 'success' : 'info'">
                {{ scope.row.isTop ? '是' : '否' }}
              </el-tag>
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

      <!-- 公告编辑对话框 -->
      <el-dialog v-model="dialogVisible" :title="dialogTitle" width="600px" @close="handleDialogClose">
        <el-form :model="announcementForm" :rules="announcementRules" ref="announcementFormRef" label-width="80px">
          <el-form-item label="标题" prop="title">
            <el-input v-model="announcementForm.title" />
          </el-form-item>
          <el-form-item label="内容" prop="content">
            <el-input 
              v-model="announcementForm.content" 
              type="textarea" 
              :rows="4" 
              placeholder="请输入公告内容" 
            />
          </el-form-item>
          <el-form-item label="发布人ID" prop="publisherId">
            <el-input v-model.number="announcementForm.publisherId" type="number" />
          </el-form-item>
          <el-form-item label="是否置顶" prop="isTop">
            <el-switch 
              v-model="announcementForm.isTop" 
              :active-value="true" 
              :inactive-value="false"
            />
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="submitAnnouncementForm">确定</el-button>
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
  getAnnouncements, 
  addAnnouncement, 
  updateAnnouncement, 
  deleteAnnouncement 
} from '@/api/announcement'

// 公告列表相关
const announcementList = ref([])
const loading = ref(false)

// 搜索表单
const searchForm = reactive({
  title: ''
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

// 公告表单
const announcementForm = reactive({
  announceId: null,
  title: '',
  content: '',
  publisherId: '',
  isTop: false
})

// 表单验证规则
const announcementRules = {
  title: [
    { required: true, message: '请输入公告标题', trigger: 'blur' }
  ],
  content: [
    { required: true, message: '请输入公告内容', trigger: 'blur' }
  ],
  publisherId: [
    { required: true, message: '请输入发布人ID', trigger: 'blur' }
  ]
}

// 表单引用
const announcementFormRef = ref(null)

// 格式化日期
const formatDate = (date) => {
  if (!date) return ''
  const d = new Date(date)
  return `${d.getFullYear()}-${String(d.getMonth() + 1).padStart(2, '0')}-${String(d.getDate()).padStart(2, '0')} ${String(d.getHours()).padStart(2, '0')}:${String(d.getMinutes()).padStart(2, '0')}:${String(d.getSeconds()).padStart(2, '0')}`
}

// 获取公告列表
const getAnnouncementList = async () => {
  try {
    loading.value = true
    const res = await getAnnouncements({
      pageNum: pagination.pageNum,
      pageSize: pagination.pageSize,
      title: searchForm.title
    })
    
    if (res.code === 200) {
      announcementList.value = res.data
      pagination.total = res.total
    } else {
      ElMessage.error(res.msg || '获取公告列表失败')
    }
  } catch (error) {
    ElMessage.error('获取公告列表失败: ' + error.message)
  } finally {
    loading.value = false
  }
}

// 处理搜索
const handleSearch = () => {
  pagination.pageNum = 1
  getAnnouncementList()
}

// 处理重置
const handleReset = () => {
  searchForm.title = ''
  pagination.pageNum = 1
  getAnnouncementList()
}

// 处理分页大小变化
const handleSizeChange = (val) => {
  pagination.pageSize = val
  pagination.pageNum = 1
  getAnnouncementList()
}

// 处理页码变化
const handleCurrentChange = (val) => {
  pagination.pageNum = val
  getAnnouncementList()
}

// 处理新增公告
const handleAddAnnouncement = () => {
  dialogTitle.value = '新增公告'
  isEdit.value = false
  // 重置表单
  Object.assign(announcementForm, {
    announceId: null,
    title: '',
    content: '',
    publisherId: '',
    isTop: false
  })
  dialogVisible.value = true
}

// 处理编辑公告
const handleEdit = (row) => {
  dialogTitle.value = '编辑公告'
  isEdit.value = true
  // 填充表单数据
  Object.assign(announcementForm, {
    announceId: row.announceId,
    title: row.title,
    content: row.content,
    publisherId: row.publisherId,
    isTop: row.isTop
  })
  dialogVisible.value = true
}

// 处理删除公告
const handleDelete = (row) => {
  ElMessageBox.confirm(
    `确定要删除公告"${row.title}"吗？`,
    '确认删除',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(async () => {
    try {
      const res = await deleteAnnouncement(row.announceId)
      if (res.code === 200) {
        ElMessage.success('删除成功')
        getAnnouncementList()
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
  announcementFormRef.value?.resetFields()
}

// 提交公告表单
const submitAnnouncementForm = async () => {
  try {
    await announcementFormRef.value.validate()
    
    // 处理表单数据
    const formData = { ...announcementForm }
    
    if (isEdit.value) {
      // 编辑公告
      const res = await updateAnnouncement(formData)
      if (res.code === 200) {
        ElMessage.success('更新成功')
        dialogVisible.value = false
        getAnnouncementList()
      } else {
        ElMessage.error(res.msg || '更新失败')
      }
    } else {
      // 新增公告
      const res = await addAnnouncement(formData)
      if (res.code === 200) {
        ElMessage.success('新增成功')
        dialogVisible.value = false
        getAnnouncementList()
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

// 组件挂载时获取公告列表
onMounted(() => {
  getAnnouncementList()
})
</script>

<style scoped>
.announcement-container {
  height: 100%;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.announcement-card {
  flex: 1;
  display: flex;
  flex-direction: column;
  height: 100%;
  overflow: hidden;
}

.announcement-card :deep(.el-card__body) {
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
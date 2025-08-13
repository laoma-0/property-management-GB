<template>
  <div class="property-container">
    <el-tabs v-model="activeTab" class="property-tabs">
      <!-- 楼栋管理标签页 -->
      <el-tab-pane label="楼栋管理" name="building">
        <el-card class="property-card">
          <template #header>
            <div class="card-header">
              <span>楼栋管理</span>
              <el-button type="primary" @click="handleAddBuilding">新增楼栋</el-button>
            </div>
          </template>

          <!-- 楼栋列表 -->
          <div class="table-container">
            <el-table 
              :data="buildingList" 
              border 
              style="width: 100%" 
              v-loading="buildingLoading">
              <el-table-column prop="buildingId" label="楼栋ID" width="300" />
              <el-table-column prop="buildingName" label="楼栋名称" width="300" />
              <el-table-column prop="floorCount" label="总层数" width="300" />
              <el-table-column label="操作" width="500" fixed="right">
                <template #default="scope">
                  <div class="operation-buttons">
                    <el-button size="small" @click="handleEditBuilding(scope.row)">编辑</el-button>
                    <el-button size="small" type="danger" @click="handleDeleteBuilding(scope.row)">删除</el-button>
                  </div>
                </template>
              </el-table-column>
            </el-table>
          </div>

          <!-- 楼栋编辑对话框 -->
          <el-dialog v-model="buildingDialogVisible" :title="buildingDialogTitle" width="500px" @close="handleBuildingDialogClose">
            <el-form :model="buildingForm" :rules="buildingRules" ref="buildingFormRef" label-width="100px">
              <el-form-item label="楼栋名称" prop="buildingName">
                <el-input v-model="buildingForm.buildingName" />
              </el-form-item>
              <el-form-item label="总层数" prop="floorCount">
                <el-input v-model.number="buildingForm.floorCount" type="number" />
              </el-form-item>
            </el-form>
            <template #footer>
              <span class="dialog-footer">
                <el-button @click="buildingDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="submitBuildingForm">确定</el-button>
              </span>
            </template>
          </el-dialog>
        </el-card>
      </el-tab-pane>

      <!-- 房间管理标签页 -->
      <el-tab-pane label="房间管理" name="room">
        <el-card class="property-card">
          <template #header>
            <div class="card-header">
              <span>房间管理</span>
              <el-button type="primary" @click="handleAddRoom">新增房间</el-button>
            </div>
          </template>

          <!-- 房间搜索 -->
          <el-form :model="roomSearchForm" label-width="80px" class="search-form">
            <el-row :gutter="20">
              <el-col :span="6">
                <el-form-item label="楼栋">
                  <el-select v-model="roomSearchForm.buildingId" placeholder="请选择楼栋" clearable style="width: 100%">
                    <el-option
                      v-for="item in buildingList"
                      :key="item.buildingId"
                      :label="item.buildingName"
                      :value="item.buildingId"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="房号">
                  <el-input v-model="roomSearchForm.roomNumber" placeholder="请输入房号" />
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item>
                  <el-button type="primary" @click="handleRoomSearch">搜索</el-button>
                  <el-button @click="handleRoomReset">重置</el-button>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>

          <!-- 房间列表 -->
          <div class="table-container">
            <el-table 
              :data="roomList" 
              border 
              style="width: 100%" 
              v-loading="roomLoading">
              <el-table-column prop="roomId" label="房间ID" width="150" />
              <el-table-column prop="building.buildingName" label="所属楼栋" width="150" />
              <el-table-column prop="roomNumber" label="房号" width="150" />
              <el-table-column prop="area" label="面积(㎡)" width="150" />
              <el-table-column prop="owner.realName" label="业主" width="150" />
              <el-table-column prop="owner.phone" label="联系电话" width="150" />
              <el-table-column label="操作" width="500" fixed="right">
                <template #default="scope">
                  <div class="operation-buttons">
                    <el-button size="small" @click="handleEditRoom(scope.row)">编辑</el-button>
                    <el-button size="small" type="danger" @click="handleDeleteRoom(scope.row)">删除</el-button>
                  </div>
                </template>
              </el-table-column>
            </el-table>
          </div>

          <!-- 分页 -->
          <el-pagination
            v-model:current-page="roomPagination.pageNum"
            v-model:page-size="roomPagination.pageSize"
            :page-sizes="[10, 20, 50, 100]"
            layout="total, sizes, prev, pager, next, jumper"
            :total="roomPagination.total"
            @size-change="handleRoomSizeChange"
            @current-change="handleRoomCurrentChange"
            class="pagination"
          />

          <!-- 房间编辑对话框 -->
          <el-dialog v-model="roomDialogVisible" :title="roomDialogTitle" width="600px" @close="handleRoomDialogClose">
            <el-form :model="roomForm" :rules="roomRules" ref="roomFormRef" label-width="100px">
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="所属楼栋" prop="buildingId">
                    <el-select v-model="roomForm.buildingId" placeholder="请选择楼栋" style="width: 100%">
                      <el-option
                        v-for="item in buildingList"
                        :key="item.buildingId"
                        :label="item.buildingName"
                        :value="item.buildingId"
                      />
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="房号" prop="roomNumber">
                    <el-input v-model="roomForm.roomNumber" />
                  </el-form-item>
                </el-col>
              </el-row>
              
              <el-row :gutter="20">
                <el-col :span="12">
                  <el-form-item label="面积(㎡)" prop="area">
                    <el-input v-model.number="roomForm.area" type="number" />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="业主ID" prop="ownerId">
                    <el-input v-model.number="roomForm.ownerId" type="number" />
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
            <template #footer>
              <span class="dialog-footer">
                <el-button @click="roomDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="submitRoomForm">确定</el-button>
              </span>
            </template>
          </el-dialog>
        </el-card>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { 
  getBuildings, 
  addBuilding, 
  updateBuilding, 
  deleteBuilding,
  getRooms,
  addRoom,
  updateRoom,
  deleteRoom
} from '@/api/property'

// 活动标签页
const activeTab = ref('building')

// 楼栋相关数据
const buildingList = ref([])
const buildingLoading = ref(false)

// 房间相关数据
const roomList = ref([])
const roomLoading = ref(false)

// 楼栋对话框相关
const buildingDialogVisible = ref(false)
const buildingDialogTitle = ref('')
const isEditBuilding = ref(false)

// 房间对话框相关
const roomDialogVisible = ref(false)
const roomDialogTitle = ref('')
const isEditRoom = ref(false)

// 楼栋表单
const buildingForm = reactive({
  buildingId: null,
  buildingName: '',
  floorCount: ''
})

// 房间表单
const roomForm = reactive({
  roomId: null,
  buildingId: '',
  roomNumber: '',
  ownerId: '',
  area: ''
})

// 房间搜索表单
const roomSearchForm = reactive({
  buildingId: '',
  roomNumber: ''
})

// 分页参数
const roomPagination = reactive({
  pageNum: 1,
  pageSize: 10,
  total: 0
})

// 楼栋表单验证规则
const buildingRules = {
  buildingName: [
    { required: true, message: '请输入楼栋名称', trigger: 'blur' }
  ],
  floorCount: [
    { required: true, message: '请输入总层数', trigger: 'blur' }
  ]
}

// 房间表单验证规则
const roomRules = {
  buildingId: [
    { required: true, message: '请选择所属楼栋', trigger: 'change' }
  ],
  roomNumber: [
    { required: true, message: '请输入房号', trigger: 'blur' }
  ],
  area: [
    { required: true, message: '请输入面积', trigger: 'blur' }
  ]
}

// 表单引用
const buildingFormRef = ref(null)
const roomFormRef = ref(null)

// 获取楼栋列表
const getBuildingList = async () => {
  try {
    buildingLoading.value = true
    const res = await getBuildings()
    if (res.code === 200) {
      buildingList.value = res.data
    } else {
      ElMessage.error(res.msg || '获取楼栋列表失败')
    }
  } catch (error) {
    ElMessage.error('获取楼栋列表失败: ' + error.message)
  } finally {
    buildingLoading.value = false
  }
}

// 获取房间列表
const getRoomList = async () => {
  try {
    roomLoading.value = true
    const res = await getRooms({
      pageNum: roomPagination.pageNum,
      pageSize: roomPagination.pageSize,
      buildingId: roomSearchForm.buildingId,
      roomNumber: roomSearchForm.roomNumber
    })
    
    if (res.code === 200) {
      roomList.value = res.data
      roomPagination.total = res.total
    } else {
      ElMessage.error(res.msg || '获取房间列表失败')
    }
  } catch (error) {
    ElMessage.error('获取房间列表失败: ' + error.message)
  } finally {
    roomLoading.value = false
  }
}

// 处理新增楼栋
const handleAddBuilding = () => {
  buildingDialogTitle.value = '新增楼栋'
  isEditBuilding.value = false
  // 重置表单
  Object.assign(buildingForm, {
    buildingId: null,
    buildingName: '',
    floorCount: ''
  })
  buildingDialogVisible.value = true
}

// 处理编辑楼栋
const handleEditBuilding = (row) => {
  buildingDialogTitle.value = '编辑楼栋'
  isEditBuilding.value = true
  // 填充表单数据
  Object.assign(buildingForm, row)
  buildingDialogVisible.value = true
}

// 处理删除楼栋
const handleDeleteBuilding = (row) => {
  ElMessageBox.confirm(
    `确定要删除楼栋"${row.buildingName}"吗？`,
    '确认删除',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(async () => {
    try {
      const res = await deleteBuilding(row.buildingId)
      if (res.code === 200) {
        ElMessage.success('删除成功')
        getBuildingList()
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

// 处理楼栋对话框关闭
const handleBuildingDialogClose = () => {
  buildingFormRef.value?.resetFields()
}

// 提交楼栋表单
const submitBuildingForm = async () => {
  try {
    await buildingFormRef.value.validate()
    
    if (isEditBuilding.value) {
      // 编辑楼栋
      const res = await updateBuilding(buildingForm)
      if (res.code === 200) {
        ElMessage.success('更新成功')
        buildingDialogVisible.value = false
        getBuildingList()
      } else {
        ElMessage.error(res.msg || '更新失败')
      }
    } else {
      // 新增楼栋
      const res = await addBuilding(buildingForm)
      if (res.code === 200) {
        ElMessage.success('新增成功')
        buildingDialogVisible.value = false
        getBuildingList()
      } else {
        ElMessage.error(res.msg || '新增失败')
      }
    }
  } catch (error) {
    if (error.name !== 'ValidationError') {
      ElMessage.error((isEditBuilding.value ? '更新' : '新增') + '失败: ' + error.message)
    }
  }
}

// 处理新增房间
const handleAddRoom = () => {
  roomDialogTitle.value = '新增房间'
  isEditRoom.value = false
  // 重置表单
  Object.assign(roomForm, {
    roomId: null,
    buildingId: '',
    roomNumber: '',
    ownerId: '',
    area: ''
  })
  roomDialogVisible.value = true
}

// 处理编辑房间
const handleEditRoom = (row) => {
  roomDialogTitle.value = '编辑房间'
  isEditRoom.value = true
  // 填充表单数据
  Object.assign(roomForm, row)
  roomDialogVisible.value = true
}

// 处理删除房间
const handleDeleteRoom = (row) => {
  ElMessageBox.confirm(
    `确定要删除房间"${row.roomNumber}"吗？`,
    '确认删除',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(async () => {
    try {
      const res = await deleteRoom(row.roomId)
      if (res.code === 200) {
        ElMessage.success('删除成功')
        getRoomList()
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

// 处理房间对话框关闭
const handleRoomDialogClose = () => {
  roomFormRef.value?.resetFields()
}

// 提交房间表单
const submitRoomForm = async () => {
  try {
    await roomFormRef.value.validate()
    
    if (isEditRoom.value) {
      // 编辑房间
      const res = await updateRoom(roomForm)
      if (res.code === 200) {
        ElMessage.success('更新成功')
        roomDialogVisible.value = false
        getRoomList()
      } else {
        ElMessage.error(res.msg || '更新失败')
      }
    } else {
      // 新增房间
      const res = await addRoom(roomForm)
      if (res.code === 200) {
        ElMessage.success('新增成功')
        roomDialogVisible.value = false
        getRoomList()
      } else {
        ElMessage.error(res.msg || '新增失败')
      }
    }
  } catch (error) {
    if (error.name !== 'ValidationError') {
      ElMessage.error((isEditRoom.value ? '更新' : '新增') + '失败: ' + error.message)
    }
  }
}

// 处理房间搜索
const handleRoomSearch = () => {
  roomPagination.pageNum = 1
  getRoomList()
}

// 处理房间重置
const handleRoomReset = () => {
  roomSearchForm.buildingId = ''
  roomSearchForm.roomNumber = ''
  roomPagination.pageNum = 1
  getRoomList()
}

// 处理房间分页大小变化
const handleRoomSizeChange = (val) => {
  roomPagination.pageSize = val
  roomPagination.pageNum = 1
  getRoomList()
}

// 处理房间页码变化
const handleRoomCurrentChange = (val) => {
  roomPagination.pageNum = val
  getRoomList()
}

// 组件挂载时获取数据
onMounted(() => {
  getBuildingList()
  getRoomList()
})
</script>

<style scoped>
.property-container {
  height: 100%;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.property-tabs {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.property-tabs :deep(.el-tabs__content) {
  flex: 1;
  overflow: hidden;
}

.property-card {
  height: 100%;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.property-card :deep(.el-card__body) {
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
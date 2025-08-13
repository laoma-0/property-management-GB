<template>
  <div class="home-container">
    <!-- 系统概览 -->
    <el-card class="overview-card">
      <template #header>
        <div class="card-header">
          <span>系统概览</span>
        </div>
      </template>
      <div class="overview-content">
        <h2>{{ systemInfo.systemName }}</h2>
        <p>{{ systemInfo.description }}</p>
        <el-tag type="success">版本: {{ systemInfo.version }}</el-tag>
      </div>
    </el-card>

    <!-- 统计数据 -->
    <el-row :gutter="20" class="stats-row">
      <el-col :span="8">
        <el-card class="stat-card">
          <div class="stat-content">
            <div class="stat-icon users">
              <el-icon><User /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-title">用户总数</div>
              <div class="stat-number">{{ statistics.userCount }}</div>
            </div>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="8">
        <el-card class="stat-card">
          <div class="stat-content">
            <div class="stat-icon rooms">
              <el-icon><HomeFilled /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-title">房间总数</div>
              <div class="stat-number">{{ statistics.roomCount }}</div>
            </div>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="8">
        <el-card class="stat-card">
          <div class="stat-content">
            <div class="stat-icon buildings">
              <el-icon><OfficeBuilding /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-title">楼栋总数</div>
              <div class="stat-number">{{ statistics.buildingCount }}</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    
    <el-row :gutter="20" class="stats-row">
      <el-col :span="8">
        <el-card class="stat-card">
          <div class="stat-content">
            <div class="stat-icon repairs">
              <el-icon><Tools /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-title">报修订单</div>
              <div class="stat-number">{{ statistics.repairOrderCount }}</div>
              <div class="stat-detail">待处理: {{ statistics.pendingRepairOrders }}</div>
            </div>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="8">
        <el-card class="stat-card">
          <div class="stat-content">
            <div class="stat-icon payments">
              <el-icon><Money /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-title">缴费账单</div>
              <div class="stat-number">{{ statistics.paymentBillCount }}</div>
              <div class="stat-detail">未缴费: {{ statistics.unpaidBills }}</div>
            </div>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="8">
        <el-card class="stat-card">
          <div class="stat-content">
            <div class="stat-icon info">
              <el-icon><InfoFilled /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-title">系统状态</div>
              <div class="stat-number">
                <el-tag type="success">运行正常</el-tag>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 快捷操作 -->
    <el-card class="actions-card">
      <template #header>
        <div class="card-header">
          <span>快捷操作</span>
        </div>
      </template>
      <div class="actions-content">
        <el-row :gutter="20">
          <el-col :span="6">
            <el-button type="primary" @click="$router.push('/user')" class="action-button">
              <el-icon><User /></el-icon>
              <span>用户管理</span>
            </el-button>
          </el-col>
          <el-col :span="6">
            <el-button type="success" @click="$router.push('/property')" class="action-button">
              <el-icon><OfficeBuilding /></el-icon>
              <span>物业管理</span>
            </el-button>
          </el-col>
          <el-col :span="6">
            <el-button type="warning" @click="$router.push('/repair')" class="action-button">
              <el-icon><Tools /></el-icon>
              <span>报修管理</span>
            </el-button>
          </el-col>
          <el-col :span="6">
            <el-button type="danger" @click="$router.push('/payment')" class="action-button">
              <el-icon><Money /></el-icon>
              <span>缴费管理</span>
            </el-button>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { User, HomeFilled, OfficeBuilding, Tools, Money, InfoFilled } from '@element-plus/icons-vue'
import { getHomeStatistics, getSystemOverview } from '@/api/home'

// 系统信息
const systemInfo = ref({
  systemName: '智慧物业管理系统',
  version: '1.0.0',
  description: '一站式物业管理解决方案'
})

// 统计数据
const statistics = ref({
  userCount: 0,
  roomCount: 0,
  buildingCount: 0,
  repairOrderCount: 0,
  paymentBillCount: 0,
  pendingRepairOrders: 0,
  unpaidBills: 0
})

// 获取系统概览信息
const fetchSystemOverview = async () => {
  try {
    const res = await getSystemOverview()
    if (res.code === 200) {
      systemInfo.value = { ...systemInfo.value, ...res.data }
    } else {
      ElMessage.error(res.msg || '获取系统信息失败')
    }
  } catch (error) {
    console.error('获取系统信息失败:', error)
  }
}

// 获取首页统计数据
const fetchHomeStatistics = async () => {
  try {
    const res = await getHomeStatistics()
    if (res.code === 200) {
      statistics.value = { ...statistics.value, ...res.data }
    } else {
      ElMessage.error(res.msg || '获取统计数据失败')
    }
  } catch (error) {
    console.error('获取统计数据失败:', error)
    ElMessage.error('获取统计数据失败: ' + error.message)
  }
}

// 页面加载时获取数据
onMounted(() => {
  fetchSystemOverview()
  fetchHomeStatistics()
})
</script>

<style scoped>
.home-container {
  height: 100%;
  display: flex;
  flex-direction: column;
  background-color: #f5f7fa;
  padding: 20px;
  overflow-y: auto;
}

.overview-card {
  margin-bottom: 20px;
}

.overview-content {
  text-align: center;
}

.overview-content h2 {
  margin-bottom: 10px;
  color: #409eff;
}

.overview-content p {
  margin-bottom: 15px;
  color: #606266;
}

.stats-row {
  margin-bottom: 20px;
}

.stat-card {
  height: 150px;
}

.stat-content {
  display: flex;
  align-items: center;
  height: 100%;
}

.stat-icon {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 20px;
  font-size: 32px;
}

.stat-icon.users {
  background-color: #ecf5ff;
  color: #409eff;
}

.stat-icon.rooms {
  background-color: #f0f9eb;
  color: #67c23a;
}

.stat-icon.buildings {
  background-color: #fdf6ec;
  color: #e6a23c;
}

.stat-icon.repairs {
  background-color: #fef0f0;
  color: #f56c6c;
}

.stat-icon.payments {
  background-color: #f0f9eb;
  color: #67c23a;
}

.stat-icon.info {
  background-color: #ecf5ff;
  color: #409eff;
}

.stat-info {
  flex: 1;
}

.stat-title {
  font-size: 14px;
  color: #909399;
  margin-bottom: 5px;
}

.stat-number {
  font-size: 24px;
  font-weight: bold;
  color: #303133;
  margin-bottom: 5px;
}

.stat-detail {
  font-size: 12px;
  color: #909399;
}

.actions-card {
  margin-top: 20px;
}

.actions-content {
  padding: 20px 0;
}

.action-button {
  width: 100%;
  height: 60px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.action-button .el-icon {
  font-size: 20px;
  margin-bottom: 5px;
}
</style>
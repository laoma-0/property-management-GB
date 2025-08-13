<template>
  <div class="repair-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>报修管理</span>
        </div>
      </template>
      <div class="content">
        <p>报修管理功能正在开发中...</p>
      </div>
    </el-card>
  </div>
</template>

<script setup></script>

<style scoped>
.repair-container {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.repair-container :deep(.el-card) {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.repair-container :deep(.el-card__body) {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.content {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
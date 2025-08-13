<template>
  <div class="property-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>物业管理</span>
        </div>
      </template>
      <div class="content">
        <p>物业管理功能正在开发中...</p>
      </div>
    </el-card>
  </div>
</template>

<script setup></script>

<style scoped>
.property-container {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.property-container :deep(.el-card) {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.property-container :deep(.el-card__body) {
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
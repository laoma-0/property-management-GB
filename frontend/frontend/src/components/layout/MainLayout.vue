<template>
  <el-container class="main-container">
    <!-- 顶部导航栏 -->
    <el-header height="60px">
      <HeaderBar />
    </el-header>

    <el-container class="main-content">
      <!-- 侧边栏 -->
      <Sidebar :active-menu="activeMenu" />

      <!-- 主内容区 -->
      <el-main class="main-area">
        <Breadcrumb />
        <div class="content-wrapper">
          <router-view />
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { ref, computed } from "vue";
import { useRoute } from "vue-router";
import HeaderBar from "./HeaderBar.vue";
import Sidebar from "./Sidebar.vue";
import Breadcrumb from "./Breadcrumb.vue";

const route = useRoute();
// 直接用 computed 保证刷新和切换时都能正确高亮
const activeMenu = computed(() => route.name);
</script>

<style scoped>
.main-container {
  height: 98vh;
  width: 98vw;
  display: flex;
  flex-direction: column;
  overflow: hidden;
background: #1f2329;
}

.main-content {
  flex: 1;
  overflow: hidden;
  display: flex;
}

.el-header {
  padding: 0;
  background-color: #409eff;
  z-index: 1000;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  flex-shrink: 0;
}

.el-aside {
  background-color: #eef1f6;
  height: 100%;
  overflow-y: auto;
  border-right: 1px solid #e6e6e6;
  flex-shrink: 0;
}

.main-area {
  flex: 1;
  display: flex;
  flex-direction: column;
  padding: 20px;
  background-color: #f5f7fa;
  overflow: hidden;
}

.main-area :deep(.el-breadcrumb) {
  margin-bottom: 16px;
  flex-shrink: 0;
}

.content-wrapper {
  flex: 1;
  overflow-y: auto;
  background-color: white;
  border-radius: 4px;
  padding: 20px;
  display: flex;
  flex-direction: column;
}
</style>
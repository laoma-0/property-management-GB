<template>
  <el-container class="main-container">
    <!-- 顶部导航栏 -->
    <el-header height="60px">
      <HeaderBar />
    </el-header>

    <el-container>
      <!-- 侧边栏 -->
      <Sidebar :active-menu="activeMenu" />

      <!-- 主内容区 -->
      <el-main>
        <Breadcrumb />
        <router-view />
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
  height: 100vh;
  display: flex;
  flex-direction: column;
}

.el-header {
  padding: 0;
  background-color: #409eff;
  z-index: 1000;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.el-aside {
  background-color: #eef1f6;
  height: calc(100vh - 60px);
  overflow-y: auto;
  border-right: 1px solid #e6e6e6;
}

.el-main {
  padding: 20px;
  height: calc(100vh - 60px);
  overflow-y: auto;
  background-color: #f5f7fa;
}
</style>

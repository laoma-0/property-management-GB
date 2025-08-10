// 侧边栏
<template>
  <el-aside width="168px">
    <el-menu :default-active="$route.path" router>
      <el-menu-item index="/">
        <el-icon><House /></el-icon>
        <span>首页</span>
      </el-menu-item>
      <el-menu-item index="/user">
        <el-icon><User /></el-icon>
        <span>用户管理</span>
      </el-menu-item>
      <el-menu-item index="/property">
        <el-icon><OfficeBuilding /></el-icon>
        <span>物业管理</span>
      </el-menu-item>
      <el-menu-item index="/repair">
        <el-icon><Tools /></el-icon>
        <span>报修管理</span>
      </el-menu-item>
      <el-menu-item index="/payment">
        <el-icon><Money /></el-icon>
        <span>缴费管理</span>
      </el-menu-item>
      <el-menu-item index="/announcement">
        <el-icon><HomeFilled /></el-icon>
        <span>公告管理</span>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<script setup>
import { useRouter } from "vue-router";
import {
  House,
  HomeFilled,
  User,
  OfficeBuilding,
  Tools,
  Money,
} from "@element-plus/icons-vue";

defineProps({
  activeMenu: String,
});
</script>

<style scoped></style>

<template>
  <div class="announcement-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>公告管理</span>
          <el-button type="primary" @click="fetchList">刷新</el-button>
        </div>
      </template>
      <el-table :data="list" style="width: 100%">
        <el-table-column prop="announceId" label="ID" width="80" />
        <el-table-column prop="title" label="标题" />
        <el-table-column prop="content" label="内容" />
        <el-table-column prop="publisherId" label="发布人ID" width="100" />
        <el-table-column prop="publishTime" label="发布时间" width="180" />
        <el-table-column prop="isTop" label="置顶" width="80">
          <template #default="scope">
            <el-tag v-if="scope.row.isTop" type="success">是</el-tag>
            <el-tag v-else type="info">否</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { getAnnouncementList } from "../api/announcement";

const list = ref([]);
const fetchList = async () => {
  try {
    const res = await getAnnouncementList();
    list.value = Array.isArray(res) ? res : res.data || [];
  } catch (e) {
    list.value = [];
  }
};
onMounted(fetchList);
</script>

<style scoped>
.announcement-container {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.announcement-container :deep(.el-card) {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.announcement-container :deep(.el-card__body) {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>

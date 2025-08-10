import request from "./request";

// 获取公告列表
export function getAnnouncementList() {
  return request({
    url: "/announcement",
    method: "get",
  });
}

// 新增公告
export function addAnnouncement(data) {
  return request({
    url: "/announcement",
    method: "post",
    data,
  });
}

// 删除公告
export function deleteAnnouncement(id) {
  return request({
    url: `/announcement/${id}`,
    method: "delete",
  });
}

// 编辑公告
export function updateAnnouncement(data) {
  return request({
    url: `/announcement/${data.announceId}`,
    method: "put",
    data,
  });
}

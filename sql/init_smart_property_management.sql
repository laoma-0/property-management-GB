/*
 Navicat MySQL Dump SQL

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 90001 (9.0.1)
 Source Host           : localhost:3306
 Source Schema         : smart_property_management

 Target Server Type    : MySQL
 Target Server Version : 90001 (9.0.1)
 File Encoding         : 65001

 Date: 13/08/2025 15:00:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building`  (
  `building_id` int NOT NULL AUTO_INCREMENT,
  `building_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '楼栋名称',
  `floor_count` int NULL DEFAULT NULL COMMENT '总层数',
  PRIMARY KEY (`building_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '楼栋信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES (1, 'A栋', 18);
INSERT INTO `building` VALUES (2, 'B栋', 24);
INSERT INTO `building` VALUES (3, 'C栋', 12);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '加密密码',
  `real_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `user_type` tinyint NOT NULL DEFAULT 0 COMMENT '0-业主 1-物业人员 2-维修工',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '张三', '13800138001', 0, '2025-08-09 23:04:36');
INSERT INTO `sys_user` VALUES (2, 'lisi', 'e10adc3949ba59abbe56e057f20f883e', '李四', '13900139002', 0, '2025-08-09 23:04:36');
INSERT INTO `sys_user` VALUES (3, 'wangwu', 'e10adc3949ba59abbe56e057f20f883e', '王五', '13700137003', 0, '2025-08-09 23:04:36');
INSERT INTO `sys_user` VALUES (4, 'zhaoliu', 'e10adc3949ba59abbe56e057f20f883e', '赵六', '13600136004', 0, '2025-08-09 23:04:36');
INSERT INTO `sys_user` VALUES (5, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '13500135000', 1, '2025-08-09 23:04:36');
INSERT INTO `sys_user` VALUES (6, 'repair1', 'e10adc3949ba59abbe56e057f20f883e', '维修工A', '13400134001', 2, '2025-08-09 23:04:36');
INSERT INTO `sys_user` VALUES (7, 'repair2', 'e10adc3949ba59abbe56e057f20f883e', '维修工B', '13300133002', 2, '2025-08-09 23:04:36');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '加密存储的密码',
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色：admin(管理员)/owner(业主)',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '真实姓名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE,
  INDEX `idx_role`(`role` ASC) USING BTREE COMMENT '按角色查询索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '系统管理员', '13800138000', 'admin@propmanage.com', '2025-08-09 23:04:36', '2025-08-09 23:04:36');
INSERT INTO `user` VALUES (2, 'manager', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '物业经理', '13900139001', 'manager@propmanage.com', '2025-08-09 23:04:36', '2025-08-09 23:04:36');
INSERT INTO `user` VALUES (3, 'owner001', 'e10adc3949ba59abbe56e057f20f883e', 'owner', '张三', '13700137001', 'zhangsan@owner.com', '2025-08-09 23:04:36', '2025-08-09 23:04:36');
INSERT INTO `user` VALUES (4, 'owner002', 'e10adc3949ba59abbe56e057f20f883e', 'owner', '李四', '13700137002', 'lisi@owner.com', '2025-08-09 23:04:36', '2025-08-09 23:04:36');
INSERT INTO `user` VALUES (5, 'owner003', 'e10adc3949ba59abbe56e057f20f883e', 'owner', '王五', '13700137003', 'wangwu@owner.com', '2025-08-09 23:04:36', '2025-08-09 23:04:36');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `room_id` bigint NOT NULL AUTO_INCREMENT,
  `building_id` int NOT NULL,
  `room_number` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '房号',
  `owner_id` bigint NULL DEFAULT NULL COMMENT '业主ID',
  `area` decimal(10, 2) NULL DEFAULT NULL COMMENT '面积(㎡)',
  PRIMARY KEY (`room_id`) USING BTREE,
  INDEX `building_id`(`building_id` ASC) USING BTREE,
  INDEX `owner_id`(`owner_id` ASC) USING BTREE,
  CONSTRAINT `room_ibfk_1` FOREIGN KEY (`building_id`) REFERENCES `building` (`building_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `room_ibfk_2` FOREIGN KEY (`owner_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 302 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '房屋信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (101, 1, '101', 1, 89.50);
INSERT INTO `room` VALUES (102, 1, '102', 2, 75.00);
INSERT INTO `room` VALUES (201, 2, '201', 3, 105.00);
INSERT INTO `room` VALUES (301, 3, '301', 4, 92.30);

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `announce_id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `publisher_id` bigint NOT NULL COMMENT '发布人',
  `publish_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `is_top` tinyint(1) NULL DEFAULT 0 COMMENT '是否置顶',
  PRIMARY KEY (`announce_id`) USING BTREE,
  INDEX `publisher_id`(`publisher_id` ASC) USING BTREE,
  CONSTRAINT `announcement_ibfk_1` FOREIGN KEY (`publisher_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES (1, '停水通知', '6月16日9:00-17:00进行水管维护，A栋暂停供水', 5, '2023-06-14 15:00:00', 1);
INSERT INTO `announcement` VALUES (2, '消防演习', '本周五下午3点举行消防演习，请全体业主参与', 5, '2023-06-12 10:30:00', 0);

-- ----------------------------
-- Table structure for community
-- ----------------------------
DROP TABLE IF EXISTS `community`;
CREATE TABLE `community`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '小区ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '小区名称',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '小区地址',
  `property_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '物业联系电话',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '小区描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name` ASC) USING BTREE COMMENT '小区名称唯一'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '小区表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of community
-- ----------------------------

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `device_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '设备编号',
  `device_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '设备名称',
  `device_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电梯/水泵/配电箱',
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '安装位置',
  `install_date` date NULL DEFAULT NULL COMMENT '安装日期',
  `last_maintain` date NULL DEFAULT NULL COMMENT '上次维护日期',
  PRIMARY KEY (`device_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备档案表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('ELEC-202', '配电箱202', '配电设备', 'C栋2楼', '2019-11-05', '2023-03-25');
INSERT INTO `device` VALUES ('ELEV-001', '1号电梯', '电梯', 'A栋单元门', '2020-05-01', '2023-05-15');
INSERT INTO `device` VALUES ('PUMP-101', '地下室水泵', '水泵', 'B栋地下室', '2021-03-10', '2023-04-20');

-- ----------------------------
-- Table structure for device_metrics
-- ----------------------------
DROP TABLE IF EXISTS `device_metrics`;
CREATE TABLE `device_metrics`  (
  `metric_id` bigint NOT NULL AUTO_INCREMENT,
  `device_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `metric_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `vibration` float NULL DEFAULT NULL COMMENT '振动值',
  `temperature` float NULL DEFAULT NULL COMMENT '温度值',
  `current` float NULL DEFAULT NULL COMMENT '电流值',
  `is_abnormal` tinyint(1) NULL DEFAULT 0 COMMENT '是否异常（创新）',
  PRIMARY KEY (`metric_id`) USING BTREE,
  INDEX `idx_device_time`(`device_id` ASC, `metric_time` ASC) USING BTREE,
  CONSTRAINT `device_metrics_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `device` (`device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备监控指标表（创新）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_metrics
-- ----------------------------
INSERT INTO `device_metrics` VALUES (1, 'ELEV-001', '2023-06-15 08:00:00', 5.2, 28.5, 15.3, 0);
INSERT INTO `device_metrics` VALUES (2, 'ELEV-001', '2023-06-15 08:05:00', 7.8, 29.1, 15.1, 1);
INSERT INTO `device_metrics` VALUES (3, 'ELEV-001', '2023-06-15 08:10:00', 8.1, 29.5, 15.4, 1);
INSERT INTO `device_metrics` VALUES (4, 'PUMP-101', '2023-06-15 09:30:00', 3.5, 35, 22.8, 0);
INSERT INTO `device_metrics` VALUES (5, 'ELEC-202', '2023-06-15 10:00:00', 0, 41.2, 18.5, 1);

-- ----------------------------
-- Table structure for fee_type
-- ----------------------------
DROP TABLE IF EXISTS `fee_type`;
CREATE TABLE `fee_type`  (
  `fee_id` int NOT NULL AUTO_INCREMENT,
  `fee_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '物业费/水费/电费',
  `unit_price` decimal(10, 2) NOT NULL COMMENT '单价',
  PRIMARY KEY (`fee_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '费用类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fee_type
-- ----------------------------
INSERT INTO `fee_type` VALUES (1, '物业管理费', 2.50);
INSERT INTO `fee_type` VALUES (2, '水费', 4.00);
INSERT INTO `fee_type` VALUES (3, '电费', 0.65);

-- ----------------------------
-- Table structure for maintain_task
-- ----------------------------
DROP TABLE IF EXISTS `maintain_task`;
CREATE TABLE `maintain_task`  (
  `task_id` bigint NOT NULL AUTO_INCREMENT,
  `device_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `predict_reason` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '预测原因（创新）',
  `assignee_id` bigint NULL DEFAULT NULL COMMENT '负责人',
  `task_status` tinyint NULL DEFAULT 0 COMMENT '0-待处理 1-已完成',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`task_id`) USING BTREE,
  INDEX `device_id`(`device_id` ASC) USING BTREE,
  INDEX `assignee_id`(`assignee_id` ASC) USING BTREE,
  CONSTRAINT `maintain_task_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `device` (`device_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `maintain_task_ibfk_2` FOREIGN KEY (`assignee_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备维护任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of maintain_task
-- ----------------------------
INSERT INTO `maintain_task` VALUES (1, 'ELEV-001', '振动值连续2次>7.5', 6, 0, '2025-08-09 23:04:36');

-- ----------------------------
-- Table structure for payment_bill
-- ----------------------------
DROP TABLE IF EXISTS `payment_bill`;
CREATE TABLE `payment_bill`  (
  `bill_id` bigint NOT NULL AUTO_INCREMENT,
  `room_id` bigint NOT NULL COMMENT '关联房屋',
  `fee_id` int NOT NULL COMMENT '费用类型',
  `period` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账期:2023-06',
  `amount` decimal(10, 2) NOT NULL COMMENT '金额',
  `status` tinyint NULL DEFAULT 0 COMMENT '0-未缴 1-已缴',
  `due_date` date NULL DEFAULT NULL COMMENT '缴费截止日',
  `payment_time` datetime NULL DEFAULT NULL COMMENT '实际缴费时间',
  PRIMARY KEY (`bill_id`) USING BTREE,
  INDEX `room_id`(`room_id` ASC) USING BTREE,
  INDEX `fee_id`(`fee_id` ASC) USING BTREE,
  CONSTRAINT `payment_bill_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `payment_bill_ibfk_2` FOREIGN KEY (`fee_id`) REFERENCES `fee_type` (`fee_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '缴费账单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment_bill
-- ----------------------------
INSERT INTO `payment_bill` VALUES (1, 101, 1, '2023-06', 223.75, 1, '2023-06-20', '2023-06-18 14:30:00');
INSERT INTO `payment_bill` VALUES (2, 101, 2, '2023-06', 85.60, 1, '2023-06-20', '2023-06-18 14:30:00');
INSERT INTO `payment_bill` VALUES (3, 102, 1, '2023-06', 187.50, 0, '2023-06-20', NULL);
INSERT INTO `payment_bill` VALUES (4, 201, 1, '2023-06', 262.50, 0, '2023-06-20', NULL);
INSERT INTO `payment_bill` VALUES (5, 301, 3, '2023-06', 120.35, 1, '2023-06-20', '2023-06-19 10:15:00');

-- ----------------------------
-- Table structure for repair_order
-- ----------------------------
DROP TABLE IF EXISTS `repair_order`;
CREATE TABLE `repair_order`  (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '报修人',
  `room_id` bigint NULL DEFAULT NULL COMMENT '关联房间',
  `repair_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '报修类型:水电/公共设施',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '问题描述',
  `ai_label` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'AI识别标签（创新）',
  `image_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片存储路径',
  `status` tinyint NULL DEFAULT 0 COMMENT '0-待处理 1-处理中 2-已完成',
  `assignee_id` bigint NULL DEFAULT NULL COMMENT '指派维修工',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `complete_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `assignee_id`(`assignee_id` ASC) USING BTREE,
  CONSTRAINT `repair_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `repair_order_ibfk_2` FOREIGN KEY (`assignee_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '报修工单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair_order
-- ----------------------------
INSERT INTO `repair_order` VALUES (1, 1, 101, '水电', '厨房水管漏水', '水管破裂', '/static/repair/pipe_101.jpg', 2, 6, '2023-06-01 09:30:00', '2023-06-01 14:00:00');
INSERT INTO `repair_order` VALUES (2, 2, 102, '电路', '卧室插座无电', '电路故障', '/static/repair/elec_102.jpg', 1, 7, '2023-06-05 10:15:00', NULL);
INSERT INTO `repair_order` VALUES (3, 3, 201, '公共区域', '电梯按钮失灵', NULL, NULL, 0, NULL, '2023-06-10 15:20:00', NULL);
INSERT INTO `repair_order` VALUES (4, 4, 301, '墙面', '卫生间墙面渗水', '墙面渗水', '/static/repair/wall_301.jpg', 0, NULL, '2023-06-12 11:05:00', NULL);

-- ----------------------------
-- Table structure for user_profile
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile`  (
  `profile_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '关联用户ID',
  `payment_habit` tinyint NULL DEFAULT NULL COMMENT '缴费习惯:0-准时 1-偶尔逾期 2-经常逾期',
  `preferred_channel` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '偏好渠道:短信/APP',
  `last_payment_date` date NULL DEFAULT NULL COMMENT '最近缴费日期',
  PRIMARY KEY (`profile_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `user_profile_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户画像表（创新）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_profile
-- ----------------------------
INSERT INTO `user_profile` VALUES (1, 1, 0, 'APP', '2023-05-28');
INSERT INTO `user_profile` VALUES (2, 2, 1, '短信', '2023-05-30');
INSERT INTO `user_profile` VALUES (3, 3, 2, 'APP', '2023-04-15');
INSERT INTO `user_profile` VALUES (4, 4, 0, '短信', '2023-06-01');

SET FOREIGN_KEY_CHECKS = 1;
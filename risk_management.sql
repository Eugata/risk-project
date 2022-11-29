/*
 Navicat Premium Data Transfer

 Source Server         : dev
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 172.31.24.107:3306
 Source Schema         : risk_management

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 29/11/2022 15:44:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for entry_logs
-- ----------------------------
DROP TABLE IF EXISTS `entry_logs`;
CREATE TABLE `entry_logs`  (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `entry_id` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `audit_id` int NULL DEFAULT NULL,
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `field_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `before_value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `updated_value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_date` datetime NULL DEFAULT NULL,
  `updated_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4966 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------

-- ----------------------------
-- Table structure for risk_entry
-- ----------------------------
DROP TABLE IF EXISTS `risk_entry`;
CREATE TABLE `risk_entry`  (
  `entry_id` int NOT NULL AUTO_INCREMENT,
  `member_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `member_account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `depositor_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bank_account_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `entry_reason` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `risk_id` int NOT NULL,
  `contact_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `remarks` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `updated_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`entry_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of risk_entry
-- ----------------------------
INSERT INTO `risk_entry` VALUES (1, 'Kira', '1025565', 'Kira', '10002355698', 'sample data 2', 2, '09662486689', '2022-06-03 15:38:58', 'sample test 2', 'jskingston', '2022-10-29 17:00:28', 'temp_user', 1);
INSERT INTO `risk_entry` VALUES (2, 'Leilani', '2023565', 'Kira', '10000254639', 'sample data 3', 4, '09177462150', '2022-06-03 15:38:58', 'sample test 3', 'jskingston', '2022-10-29 17:57:51', 'temp_user', 0);
INSERT INTO `risk_entry` VALUES (3, 'Barn', '2029995', 'Kira', '20552266985', 'sample data 3', 4, '09221590662', '2022-06-03 15:38:58', 'sample test 45', 'jskingston', '2022-10-29 17:51:19', 'temp_user', 0);
INSERT INTO `risk_entry` VALUES (4, 'Herm', '3033321', 'Kira', '10000258588', 'sample data 3', 4, '09959821738', '2022-06-01 15:38:58', 'sample test 3', 'jskingston', NULL, NULL, 0);

-- ----------------------------
-- Table structure for risk_log
-- ----------------------------
DROP TABLE IF EXISTS `risk_log`;
CREATE TABLE `risk_log`  (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `entry_id` int NOT NULL,
  `operation_type` smallint NULL DEFAULT NULL COMMENT '\"1 - add 2- edit\"',
  `operator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `operation_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_at` datetime NULL DEFAULT NULL,
  `field_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `before_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `audit_id` int(10) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of risk_log
-- ----------------------------
INSERT INTO `risk_log` VALUES (1, 1, 1, 'temp_log_user', 'Added Entry', '2022-10-29 16:58:12', NULL, NULL, 0000000001);
INSERT INTO `risk_log` VALUES (2, 1, 2, 'temp_log_user', 'Edited: member_name', '2022-10-29 17:00:28', 'member_name', 'Barney', 0000000002);
INSERT INTO `risk_log` VALUES (3, 1, 2, 'temp_log_user', 'Edited: entry_reason', '2022-10-29 17:00:28', 'entry_reason', 'sample data', 0000000002);
INSERT INTO `risk_log` VALUES (4, 1, 2, 'temp_log_user', 'Edited: remarks', '2022-10-29 17:00:28', 'remarks', 'sample test', 0000000002);
INSERT INTO `risk_log` VALUES (5, 2, 1, 'temp_log_user', 'Added Entry', '2022-10-29 17:02:17', NULL, NULL, 0000000003);
INSERT INTO `risk_log` VALUES (6, 3, 1, 'temp_log_user', 'Added Entry', '2022-10-29 17:46:36', NULL, NULL, 0000000004);
INSERT INTO `risk_log` VALUES (7, 3, 2, 'temp_log_user', 'Edited: risk_id', '2022-10-29 17:47:58', 'risk_id', '2', 0000000005);
INSERT INTO `risk_log` VALUES (8, 3, 2, 'temp_log_user', 'Edited: remarks', '2022-10-29 17:47:58', 'remarks', 'sample test 3', 0000000005);
INSERT INTO `risk_log` VALUES (9, 3, 2, 'temp_log_user', 'Edited: risk_id', '2022-10-29 17:51:19', 'risk_id', '1', 0000000006);
INSERT INTO `risk_log` VALUES (10, 2, 2, 'temp_log_user', 'Edited: risk_id', '2022-10-29 17:57:51', 'risk_id', '2', 0000000007);
INSERT INTO `risk_log` VALUES (11, 2, 2, 'temp_log_user', 'Edited: member_name', '2022-10-29 17:57:51', 'member_name', 'Kurosh', 0000000007);
INSERT INTO `risk_log` VALUES (12, 4, 1, 'temp_log_user', 'Added Entry', '2022-10-29 17:59:47', NULL, NULL, 0000000008);

-- ----------------------------
-- Table structure for risk_type
-- ----------------------------
DROP TABLE IF EXISTS `risk_type`;
CREATE TABLE `risk_type`  (
  `risk_id` int NOT NULL AUTO_INCREMENT,
  `risk_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remarks` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `created_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `updated_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`risk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of risk_type
-- ----------------------------
INSERT INTO `risk_type` VALUES (1, 'Maximum Risk Significant', 'Maximum Risk Significant', '2022-05-28 14:00:05', '2022-05-28 14:00:08', 'admin', 'admin');
INSERT INTO `risk_type` VALUES (2, 'High Risk', 'High Risk', '2022-05-28 14:00:40', '2022-05-28 14:00:43', 'admin', 'admin');
INSERT INTO `risk_type` VALUES (3, 'Medium Risk', 'Medium Risk', '2022-05-28 14:01:21', '2022-05-28 14:01:23', 'admin', 'admin');
INSERT INTO `risk_type` VALUES (4, 'Low Risk', 'Low Risk', '2022-05-28 14:02:12', '2022-05-28 14:02:15', 'admin', 'admin');

-- ----------------------------
-- Table structure for training_table
-- ----------------------------
DROP TABLE IF EXISTS `training_table`;
CREATE TABLE `training_table`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of training_table
-- ----------------------------
INSERT INTO `training_table` VALUES (1, 'data');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime NULL DEFAULT NULL,
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_deleted` int NULL DEFAULT NULL,
  `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_type` enum('Y','S','N') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, '2022-11-04 19:23:12', '$2a$10$xKf4v5tMQ0r7Bxi3sENQoe45JmshKDTEeQGIYjZVUqZS6U8NCKReC', NULL, 'admin', 'anonymousUser', 0, NULL, 'admin@itcom.com', NULL);
INSERT INTO `user` VALUES (3, '2022-11-29 13:00:43', '$2a$10$IOBphrjmn6fY52qNMrd9JOC.XbHlUU3xOb.hKIaNUxGfxbb6poxSa', NULL, 'admin2', 'anonymousUser', 0, NULL, 'admin@itcom.com', NULL);
INSERT INTO `user` VALUES (4, '2022-11-29 13:07:10', '$2a$10$GQY4op3GzY4WO8pJtMRQlON7DiVsbcH4VroM0sSQ29.jqC3KceqoS', NULL, 'admin2', 'anonymousUser', 0, NULL, 'admin@itcom666.com', NULL);

-- ----------------------------
-- Table structure for user_login
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` datetime NULL DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_deleted` tinyint(1) NULL DEFAULT 0,
  `user_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 260 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user_permission_detail
-- ----------------------------
DROP TABLE IF EXISTS `user_permission_detail`;
CREATE TABLE `user_permission_detail`  (
  `detail_id` int NOT NULL AUTO_INCREMENT,
  `header_id` int NULL DEFAULT NULL,
  `detail_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `created_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `updated_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 141 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_permission_detail
-- ----------------------------
INSERT INTO `user_permission_detail` VALUES (1, 1, '录入', '2022-03-28 00:00:00', '2022-06-09 16:27:40', 'admin', 'admin');
INSERT INTO `user_permission_detail` VALUES (2, 1, '批量录入', '2022-03-28 00:00:00', '2022-03-29 00:00:00', 'admin', 'admin');
INSERT INTO `user_permission_detail` VALUES (3, 1, '查看log', '2022-03-28 00:00:00', '2022-03-29 00:00:00', 'admin', 'admin');
INSERT INTO `user_permission_detail` VALUES (4, 1, '编辑', '2022-03-28 00:00:00', '2022-03-29 00:00:00', 'admin', 'admin');
INSERT INTO `user_permission_detail` VALUES (5, 1, '删除', '2022-03-28 00:00:00', '2022-03-29 00:00:00', 'admin', 'admin');
INSERT INTO `user_permission_detail` VALUES (6, 1, '批量删除', '2022-03-28 00:00:00', '2022-03-29 00:00:00', 'admin', 'admin');
INSERT INTO `user_permission_detail` VALUES (7, 1, '导出', '2022-03-28 00:00:00', '2022-03-29 00:00:00', 'admin', 'admin');
INSERT INTO `user_permission_detail` VALUES (8, 2, '新增', '2022-03-28 00:00:00', '2022-03-29 00:00:00', 'admin', 'admin');
INSERT INTO `user_permission_detail` VALUES (9, 2, '编辑', '2022-03-28 00:00:00', '2022-03-29 00:00:00', 'admin', 'admin');
INSERT INTO `user_permission_detail` VALUES (10, 2, '设置权限', '2022-03-28 00:00:00', '2022-03-29 00:00:00', 'admin', 'admin');
INSERT INTO `user_permission_detail` VALUES (11, 2, '删除', '2022-03-28 00:00:00', '2022-03-29 00:00:00', 'admin', 'admin');

-- ----------------------------
-- Table structure for user_permission_header
-- ----------------------------
DROP TABLE IF EXISTS `user_permission_header`;
CREATE TABLE `user_permission_header`  (
  `header_id` int NOT NULL AUTO_INCREMENT,
  `header_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `created_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `updated_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`header_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_permission_header
-- ----------------------------
INSERT INTO `user_permission_header` VALUES (1, '风控录入', '2022-05-04 00:00:00', '2022-05-05 00:00:00', 'Jiwon', 'admin', NULL);
INSERT INTO `user_permission_header` VALUES (2, '系统账号', '2022-05-04 00:00:00', '2022-05-05 00:00:00', 'admin', 'admin', NULL);

-- ----------------------------
-- Table structure for user_permissions
-- ----------------------------
DROP TABLE IF EXISTS `user_permissions`;
CREATE TABLE `user_permissions`  (
  `permission_id` int NOT NULL AUTO_INCREMENT,
  `detail_id` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `is_allowed` bit(1) NULL DEFAULT NULL,
  `created_at` datetime NULL DEFAULT NULL,
  `created_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  `updated_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 258 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_permissions
-- ----------------------------
INSERT INTO `user_permissions` VALUES (1, 1, 1, b'0', '2022-05-04 00:00:00', 'admin', '2022-05-04 00:00:00', 'admin');
INSERT INTO `user_permissions` VALUES (2, 2, 1, b'0', '2022-05-04 00:00:00', 'admin', '2022-05-04 00:00:00', 'admin');
INSERT INTO `user_permissions` VALUES (3, 3, 1, b'0', '2022-05-04 00:00:00', 'admin', '2022-05-04 00:00:00', 'admin');
INSERT INTO `user_permissions` VALUES (4, 4, 1, b'0', '2022-05-04 00:00:00', 'admin', '2022-05-04 00:00:00', 'admin');
INSERT INTO `user_permissions` VALUES (5, 5, 1, b'0', '2022-05-04 00:00:00', 'admin', '2022-05-04 00:00:00', 'admin');
INSERT INTO `user_permissions` VALUES (6, 6, 1, b'0', '2022-05-04 00:00:00', 'admin', '2022-05-04 00:00:00', 'admin');
INSERT INTO `user_permissions` VALUES (7, 7, 1, b'1', '2022-05-04 00:00:00', 'admin', '2022-05-04 00:00:00', 'admin');
INSERT INTO `user_permissions` VALUES (8, 8, 1, b'0', '2022-05-04 00:00:00', 'admin', '2022-05-04 00:00:00', 'admin');
INSERT INTO `user_permissions` VALUES (9, 9, 1, b'0', '2022-05-04 00:00:00', 'admin', '2022-05-04 00:00:00', 'admin');
INSERT INTO `user_permissions` VALUES (10, 10, 1, b'0', '2022-05-04 00:00:00', 'admin', '2022-05-04 00:00:00', 'admin');
INSERT INTO `user_permissions` VALUES (11, 11, 1, b'1', '2022-05-04 00:00:00', 'admin', '2022-05-04 00:00:00', 'admin');
INSERT INTO `user_permissions` VALUES (18, 1, 2, b'1', '2022-06-09 18:49:33', 'admin', '2022-06-09 18:49:33', 'admin');
INSERT INTO `user_permissions` VALUES (19, 2, 2, b'1', '2022-06-09 18:49:33', 'admin', '2022-06-09 18:49:33', 'admin');
INSERT INTO `user_permissions` VALUES (20, 3, 2, b'0', '2022-06-09 18:49:33', 'admin', '2022-06-09 18:49:33', 'admin');
INSERT INTO `user_permissions` VALUES (21, 4, 2, b'1', '2022-06-09 18:49:33', 'admin', '2022-06-09 18:49:33', 'admin');
INSERT INTO `user_permissions` VALUES (22, 5, 2, b'1', '2022-06-09 18:49:33', 'admin', '2022-06-09 18:49:33', 'admin');
INSERT INTO `user_permissions` VALUES (23, 6, 2, b'1', '2022-06-09 18:49:33', 'admin', '2022-06-09 18:49:33', 'admin');
INSERT INTO `user_permissions` VALUES (24, 7, 2, b'1', '2022-06-09 18:49:33', 'admin', '2022-06-09 18:49:33', 'admin');
INSERT INTO `user_permissions` VALUES (25, 8, 2, b'1', '2022-06-09 18:49:33', 'admin', '2022-06-09 18:49:33', 'admin');
INSERT INTO `user_permissions` VALUES (26, 9, 2, b'1', '2022-06-09 18:49:33', 'admin', '2022-06-09 18:49:33', 'admin');
INSERT INTO `user_permissions` VALUES (27, 10, 2, b'1', '2022-06-09 18:49:33', 'admin', '2022-06-09 18:49:33', 'admin');
INSERT INTO `user_permissions` VALUES (28, 11, 2, b'1', '2022-06-09 18:49:33', 'admin', '2022-06-09 18:49:33', 'admin');
INSERT INTO `user_permissions` VALUES (29, 1, 68, b'0', '2022-06-09 18:50:13', 'admin', '2022-06-09 18:50:13', 'admin');
INSERT INTO `user_permissions` VALUES (30, 2, 68, b'0', '2022-06-09 18:50:13', 'admin', '2022-06-09 18:50:13', 'admin');
INSERT INTO `user_permissions` VALUES (31, 3, 68, b'0', '2022-06-09 18:50:13', 'admin', '2022-06-09 18:50:13', 'admin');
INSERT INTO `user_permissions` VALUES (32, 4, 68, b'1', '2022-06-09 18:50:13', 'admin', '2022-06-09 18:50:13', 'admin');
INSERT INTO `user_permissions` VALUES (33, 5, 68, b'0', '2022-06-09 18:50:13', 'admin', '2022-06-09 18:50:13', 'admin');
INSERT INTO `user_permissions` VALUES (34, 6, 68, b'0', '2022-06-09 18:50:13', 'admin', '2022-06-09 18:50:13', 'admin');
INSERT INTO `user_permissions` VALUES (35, 7, 68, b'1', '2022-06-09 18:50:13', 'admin', '2022-06-09 18:50:13', 'admin');
INSERT INTO `user_permissions` VALUES (36, 8, 68, b'0', '2022-06-09 18:50:13', 'admin', '2022-06-09 18:50:13', 'admin');
INSERT INTO `user_permissions` VALUES (37, 9, 68, b'0', '2022-06-09 18:50:13', 'admin', '2022-06-09 18:50:13', 'admin');
INSERT INTO `user_permissions` VALUES (38, 10, 68, b'1', '2022-06-09 18:50:13', 'admin', '2022-06-09 18:50:13', 'admin');
INSERT INTO `user_permissions` VALUES (39, 11, 68, b'1', '2022-06-09 18:50:13', 'admin', '2022-06-09 18:50:13', 'admin');
INSERT INTO `user_permissions` VALUES (40, 1, 69, b'1', '2022-06-10 14:23:00', 'admin', '2022-06-10 14:23:00', 'admin');
INSERT INTO `user_permissions` VALUES (41, 2, 69, b'1', '2022-06-10 14:23:00', 'admin', '2022-06-10 14:23:00', 'admin');
INSERT INTO `user_permissions` VALUES (42, 3, 69, b'1', '2022-06-10 14:23:00', 'admin', '2022-06-10 14:23:00', 'admin');
INSERT INTO `user_permissions` VALUES (43, 4, 69, b'1', '2022-06-10 14:23:00', 'admin', '2022-06-10 14:23:00', 'admin');
INSERT INTO `user_permissions` VALUES (44, 5, 69, b'1', '2022-06-10 14:23:00', 'admin', '2022-06-10 14:23:00', 'admin');
INSERT INTO `user_permissions` VALUES (45, 6, 69, b'1', '2022-06-10 14:23:00', 'admin', '2022-06-10 14:23:00', 'admin');
INSERT INTO `user_permissions` VALUES (46, 7, 69, b'1', '2022-06-10 14:23:00', 'admin', '2022-06-10 14:23:00', 'admin');
INSERT INTO `user_permissions` VALUES (69, 1, 72, b'1', '2022-06-11 13:41:57', 'admin', '2022-06-11 13:41:57', 'admin');
INSERT INTO `user_permissions` VALUES (70, 2, 72, b'1', '2022-06-11 13:41:57', 'admin', '2022-06-11 13:41:57', 'admin');
INSERT INTO `user_permissions` VALUES (71, 3, 72, b'1', '2022-06-11 13:41:57', 'admin', '2022-06-11 13:41:57', 'admin');
INSERT INTO `user_permissions` VALUES (72, 4, 72, b'1', '2022-06-11 13:41:57', 'admin', '2022-06-11 13:41:57', 'admin');
INSERT INTO `user_permissions` VALUES (73, 5, 72, b'1', '2022-06-11 13:41:57', 'admin', '2022-06-11 13:41:57', 'admin');
INSERT INTO `user_permissions` VALUES (74, 6, 72, b'1', '2022-06-11 13:41:57', 'admin', '2022-06-11 13:41:57', 'admin');
INSERT INTO `user_permissions` VALUES (75, 7, 72, b'1', '2022-06-11 13:41:57', 'admin', '2022-06-11 13:41:57', 'admin');
INSERT INTO `user_permissions` VALUES (76, 8, 69, b'1', '2022-06-30 13:23:46', 'admin1', '2022-06-30 13:23:46', 'admin1');
INSERT INTO `user_permissions` VALUES (77, 9, 69, b'1', '2022-06-30 13:23:46', 'admin1', '2022-06-30 13:23:46', 'admin1');
INSERT INTO `user_permissions` VALUES (78, 10, 69, b'1', '2022-06-30 13:23:46', 'admin1', '2022-06-30 13:23:46', 'admin1');
INSERT INTO `user_permissions` VALUES (79, 11, 69, b'1', '2022-06-30 13:23:46', 'admin1', '2022-06-30 13:23:46', 'admin1');
INSERT INTO `user_permissions` VALUES (80, 1, 77, b'1', '2022-07-02 11:38:38', 'admin1', '2022-07-02 11:38:38', 'admin1');
INSERT INTO `user_permissions` VALUES (81, 2, 77, b'1', '2022-07-02 11:38:38', 'admin1', '2022-07-02 11:38:38', 'admin1');
INSERT INTO `user_permissions` VALUES (82, 3, 77, b'0', '2022-07-02 11:38:38', 'admin1', '2022-07-02 11:38:38', 'admin1');
INSERT INTO `user_permissions` VALUES (83, 4, 77, b'0', '2022-07-02 11:38:38', 'admin1', '2022-07-02 11:38:38', 'admin1');
INSERT INTO `user_permissions` VALUES (84, 5, 77, b'0', '2022-07-02 11:38:38', 'admin1', '2022-07-02 11:38:38', 'admin1');
INSERT INTO `user_permissions` VALUES (85, 6, 77, b'0', '2022-07-02 11:38:38', 'admin1', '2022-07-02 11:38:38', 'admin1');
INSERT INTO `user_permissions` VALUES (86, 7, 77, b'0', '2022-07-02 11:38:38', 'admin1', '2022-07-02 11:38:38', 'admin1');
INSERT INTO `user_permissions` VALUES (87, 1, 105, b'0', '2022-07-06 15:59:35', 'user', '2022-07-06 15:59:35', 'user');
INSERT INTO `user_permissions` VALUES (88, 2, 105, b'0', '2022-07-06 15:59:35', 'user', '2022-07-06 15:59:35', 'user');
INSERT INTO `user_permissions` VALUES (89, 3, 105, b'0', '2022-07-06 15:59:35', 'user', '2022-07-06 15:59:35', 'user');
INSERT INTO `user_permissions` VALUES (90, 4, 105, b'0', '2022-07-06 15:59:35', 'user', '2022-07-06 15:59:35', 'user');
INSERT INTO `user_permissions` VALUES (91, 5, 105, b'0', '2022-07-06 15:59:35', 'user', '2022-07-06 15:59:35', 'user');
INSERT INTO `user_permissions` VALUES (92, 6, 105, b'0', '2022-07-06 15:59:35', 'user', '2022-07-06 15:59:35', 'user');
INSERT INTO `user_permissions` VALUES (93, 7, 105, b'0', '2022-07-06 15:59:35', 'user', '2022-07-06 15:59:35', 'user');
INSERT INTO `user_permissions` VALUES (94, 1, 79, b'1', '2022-07-06 16:22:29', 'luslec', '2022-07-06 16:22:29', 'luslec');
INSERT INTO `user_permissions` VALUES (95, 2, 79, b'1', '2022-07-06 16:22:29', 'luslec', '2022-07-06 16:22:29', 'luslec');
INSERT INTO `user_permissions` VALUES (96, 3, 79, b'1', '2022-07-06 16:22:29', 'luslec', '2022-07-06 16:22:29', 'luslec');
INSERT INTO `user_permissions` VALUES (97, 4, 79, b'1', '2022-07-06 16:22:29', 'luslec', '2022-07-06 16:22:29', 'luslec');
INSERT INTO `user_permissions` VALUES (98, 5, 79, b'1', '2022-07-06 16:22:29', 'luslec', '2022-07-06 16:22:29', 'luslec');
INSERT INTO `user_permissions` VALUES (99, 6, 79, b'1', '2022-07-06 16:22:29', 'luslec', '2022-07-06 16:22:29', 'luslec');
INSERT INTO `user_permissions` VALUES (100, 7, 79, b'1', '2022-07-06 16:22:29', 'luslec', '2022-07-06 16:22:29', 'luslec');
INSERT INTO `user_permissions` VALUES (101, 8, 77, b'0', '2022-07-08 14:01:42', 'user', '2022-07-08 14:01:42', 'user');
INSERT INTO `user_permissions` VALUES (102, 9, 77, b'0', '2022-07-08 14:01:42', 'user', '2022-07-08 14:01:42', 'user');
INSERT INTO `user_permissions` VALUES (103, 10, 77, b'0', '2022-07-08 14:01:42', 'user', '2022-07-08 14:01:42', 'user');
INSERT INTO `user_permissions` VALUES (104, 11, 77, b'0', '2022-07-08 14:01:42', 'user', '2022-07-08 14:01:42', 'user');
INSERT INTO `user_permissions` VALUES (105, 1, 92, b'1', '2022-07-11 10:58:07', 'joestar', '2022-07-11 10:58:07', 'joestar');
INSERT INTO `user_permissions` VALUES (106, 2, 92, b'1', '2022-07-11 10:58:07', 'joestar', '2022-07-11 10:58:07', 'joestar');
INSERT INTO `user_permissions` VALUES (107, 3, 92, b'1', '2022-07-11 10:58:07', 'joestar', '2022-07-11 10:58:07', 'joestar');
INSERT INTO `user_permissions` VALUES (108, 4, 92, b'1', '2022-07-11 10:58:07', 'joestar', '2022-07-11 10:58:07', 'joestar');
INSERT INTO `user_permissions` VALUES (109, 5, 92, b'1', '2022-07-11 10:58:07', 'joestar', '2022-07-11 10:58:07', 'joestar');
INSERT INTO `user_permissions` VALUES (110, 6, 92, b'1', '2022-07-11 10:58:07', 'joestar', '2022-07-11 10:58:07', 'joestar');
INSERT INTO `user_permissions` VALUES (111, 7, 92, b'1', '2022-07-11 10:58:07', 'joestar', '2022-07-11 10:58:07', 'joestar');
INSERT INTO `user_permissions` VALUES (112, 8, 92, b'1', '2022-07-11 10:58:07', 'joestar', '2022-07-11 10:58:07', 'joestar');
INSERT INTO `user_permissions` VALUES (113, 9, 92, b'1', '2022-07-11 10:58:07', 'joestar', '2022-07-11 10:58:07', 'joestar');
INSERT INTO `user_permissions` VALUES (114, 10, 92, b'1', '2022-07-11 10:58:07', 'joestar', '2022-07-11 10:58:07', 'joestar');
INSERT INTO `user_permissions` VALUES (115, 11, 92, b'1', '2022-07-11 10:58:07', 'joestar', '2022-07-11 10:58:07', 'joestar');
INSERT INTO `user_permissions` VALUES (116, 137, 69, b'0', '2022-07-18 18:49:00', 'admin1', '2022-07-18 18:49:00', 'admin1');
INSERT INTO `user_permissions` VALUES (117, 137, 1, b'1', '2022-07-18 18:55:13', 'joestar', '2022-07-18 18:55:13', 'joestar');
INSERT INTO `user_permissions` VALUES (118, 8, 75, b'0', '2022-07-18 18:55:21', 'joestar', '2022-07-18 18:55:21', 'joestar');
INSERT INTO `user_permissions` VALUES (119, 9, 75, b'0', '2022-07-18 18:55:21', 'joestar', '2022-07-18 18:55:21', 'joestar');
INSERT INTO `user_permissions` VALUES (120, 10, 75, b'0', '2022-07-18 18:55:21', 'joestar', '2022-07-18 18:55:21', 'joestar');
INSERT INTO `user_permissions` VALUES (121, 11, 75, b'0', '2022-07-18 18:55:21', 'joestar', '2022-07-18 18:55:21', 'joestar');
INSERT INTO `user_permissions` VALUES (122, 8, 79, b'1', '2022-07-19 11:50:01', 'user', '2022-07-19 11:50:01', 'user');
INSERT INTO `user_permissions` VALUES (123, 9, 79, b'1', '2022-07-19 11:50:01', 'user', '2022-07-19 11:50:01', 'user');
INSERT INTO `user_permissions` VALUES (124, 10, 79, b'0', '2022-07-19 11:50:01', 'user', '2022-07-19 11:50:01', 'user');
INSERT INTO `user_permissions` VALUES (125, 11, 79, b'0', '2022-07-19 11:50:01', 'user', '2022-07-19 11:50:01', 'user');
INSERT INTO `user_permissions` VALUES (181, 1, 191, b'0', '2022-07-20 15:49:33', 'joestar', '2022-07-20 15:49:33', 'joestar');
INSERT INTO `user_permissions` VALUES (182, 2, 191, b'0', '2022-07-20 15:49:33', 'joestar', '2022-07-20 15:49:33', 'joestar');
INSERT INTO `user_permissions` VALUES (183, 3, 191, b'0', '2022-07-20 15:49:33', 'joestar', '2022-07-20 15:49:33', 'joestar');
INSERT INTO `user_permissions` VALUES (184, 4, 191, b'0', '2022-07-20 15:49:33', 'joestar', '2022-07-20 15:49:33', 'joestar');
INSERT INTO `user_permissions` VALUES (185, 5, 191, b'0', '2022-07-20 15:49:33', 'joestar', '2022-07-20 15:49:33', 'joestar');
INSERT INTO `user_permissions` VALUES (186, 6, 191, b'0', '2022-07-20 15:49:33', 'joestar', '2022-07-20 15:49:33', 'joestar');
INSERT INTO `user_permissions` VALUES (187, 7, 191, b'0', '2022-07-20 15:49:33', 'joestar', '2022-07-20 15:49:33', 'joestar');
INSERT INTO `user_permissions` VALUES (188, 8, 191, b'0', '2022-07-20 15:49:33', 'joestar', '2022-07-20 15:49:33', 'joestar');
INSERT INTO `user_permissions` VALUES (189, 9, 191, b'0', '2022-07-20 15:49:33', 'joestar', '2022-07-20 15:49:33', 'joestar');
INSERT INTO `user_permissions` VALUES (190, 10, 191, b'0', '2022-07-20 15:49:33', 'joestar', '2022-07-20 15:49:33', 'joestar');
INSERT INTO `user_permissions` VALUES (191, 11, 191, b'0', '2022-07-20 15:49:33', 'joestar', '2022-07-20 15:49:33', 'joestar');
INSERT INTO `user_permissions` VALUES (192, 1, 190, b'0', '2022-07-20 15:58:56', 'joestar', '2022-07-20 15:58:56', 'joestar');
INSERT INTO `user_permissions` VALUES (193, 2, 190, b'0', '2022-07-20 15:58:56', 'joestar', '2022-07-20 15:58:56', 'joestar');
INSERT INTO `user_permissions` VALUES (194, 3, 190, b'0', '2022-07-20 15:58:56', 'joestar', '2022-07-20 15:58:56', 'joestar');
INSERT INTO `user_permissions` VALUES (195, 4, 190, b'0', '2022-07-20 15:58:56', 'joestar', '2022-07-20 15:58:56', 'joestar');
INSERT INTO `user_permissions` VALUES (196, 5, 190, b'0', '2022-07-20 15:58:56', 'joestar', '2022-07-20 15:58:56', 'joestar');
INSERT INTO `user_permissions` VALUES (197, 6, 190, b'0', '2022-07-20 15:58:56', 'joestar', '2022-07-20 15:58:56', 'joestar');
INSERT INTO `user_permissions` VALUES (198, 7, 190, b'0', '2022-07-20 15:58:56', 'joestar', '2022-07-20 15:58:56', 'joestar');
INSERT INTO `user_permissions` VALUES (199, 8, 190, b'0', '2022-07-20 15:58:56', 'joestar', '2022-07-20 15:58:56', 'joestar');
INSERT INTO `user_permissions` VALUES (200, 9, 190, b'0', '2022-07-20 15:58:56', 'joestar', '2022-07-20 15:58:56', 'joestar');
INSERT INTO `user_permissions` VALUES (201, 10, 190, b'0', '2022-07-20 15:58:56', 'joestar', '2022-07-20 15:58:56', 'joestar');
INSERT INTO `user_permissions` VALUES (202, 11, 190, b'0', '2022-07-20 15:58:56', 'joestar', '2022-07-20 15:58:56', 'joestar');
INSERT INTO `user_permissions` VALUES (203, 1, 192, b'0', '2022-07-20 16:00:42', 'joestar', '2022-07-20 16:00:42', 'joestar');
INSERT INTO `user_permissions` VALUES (204, 2, 192, b'0', '2022-07-20 16:00:42', 'joestar', '2022-07-20 16:00:42', 'joestar');
INSERT INTO `user_permissions` VALUES (205, 3, 192, b'0', '2022-07-20 16:00:42', 'joestar', '2022-07-20 16:00:42', 'joestar');
INSERT INTO `user_permissions` VALUES (206, 4, 192, b'0', '2022-07-20 16:00:42', 'joestar', '2022-07-20 16:00:42', 'joestar');
INSERT INTO `user_permissions` VALUES (207, 5, 192, b'0', '2022-07-20 16:00:42', 'joestar', '2022-07-20 16:00:42', 'joestar');
INSERT INTO `user_permissions` VALUES (208, 6, 192, b'0', '2022-07-20 16:00:42', 'joestar', '2022-07-20 16:00:42', 'joestar');
INSERT INTO `user_permissions` VALUES (209, 7, 192, b'0', '2022-07-20 16:00:42', 'joestar', '2022-07-20 16:00:42', 'joestar');
INSERT INTO `user_permissions` VALUES (210, 8, 192, b'0', '2022-07-20 16:00:42', 'joestar', '2022-07-20 16:00:42', 'joestar');
INSERT INTO `user_permissions` VALUES (211, 9, 192, b'0', '2022-07-20 16:00:42', 'joestar', '2022-07-20 16:00:42', 'joestar');
INSERT INTO `user_permissions` VALUES (212, 10, 192, b'0', '2022-07-20 16:00:42', 'joestar', '2022-07-20 16:00:42', 'joestar');
INSERT INTO `user_permissions` VALUES (213, 11, 192, b'0', '2022-07-20 16:00:42', 'joestar', '2022-07-20 16:00:42', 'joestar');
INSERT INTO `user_permissions` VALUES (214, 1, 187, b'1', '2022-07-20 16:02:45', 'joestar', '2022-07-20 16:02:45', 'joestar');
INSERT INTO `user_permissions` VALUES (215, 2, 187, b'1', '2022-07-20 16:02:45', 'joestar', '2022-07-20 16:02:45', 'joestar');
INSERT INTO `user_permissions` VALUES (216, 3, 187, b'1', '2022-07-20 16:02:45', 'joestar', '2022-07-20 16:02:45', 'joestar');
INSERT INTO `user_permissions` VALUES (217, 4, 187, b'1', '2022-07-20 16:02:45', 'joestar', '2022-07-20 16:02:45', 'joestar');
INSERT INTO `user_permissions` VALUES (218, 5, 187, b'0', '2022-07-20 16:02:45', 'joestar', '2022-07-20 16:02:45', 'joestar');
INSERT INTO `user_permissions` VALUES (219, 6, 187, b'1', '2022-07-20 16:02:45', 'joestar', '2022-07-20 16:02:45', 'joestar');
INSERT INTO `user_permissions` VALUES (220, 7, 187, b'1', '2022-07-20 16:02:45', 'joestar', '2022-07-20 16:02:45', 'joestar');
INSERT INTO `user_permissions` VALUES (221, 8, 187, b'1', '2022-07-20 16:02:45', 'joestar', '2022-07-20 16:02:45', 'joestar');
INSERT INTO `user_permissions` VALUES (222, 9, 187, b'0', '2022-07-20 16:02:45', 'joestar', '2022-07-20 16:02:45', 'joestar');
INSERT INTO `user_permissions` VALUES (223, 10, 187, b'1', '2022-07-20 16:02:45', 'joestar', '2022-07-20 16:02:45', 'joestar');
INSERT INTO `user_permissions` VALUES (224, 11, 187, b'0', '2022-07-20 16:02:45', 'joestar', '2022-07-20 16:02:45', 'joestar');
INSERT INTO `user_permissions` VALUES (225, 1, 75, b'1', '2022-07-20 17:56:55', 'joestar', '2022-07-20 17:56:55', 'joestar');
INSERT INTO `user_permissions` VALUES (226, 2, 75, b'1', '2022-07-20 17:56:55', 'joestar', '2022-07-20 17:56:55', 'joestar');
INSERT INTO `user_permissions` VALUES (227, 3, 75, b'0', '2022-07-20 17:56:55', 'joestar', '2022-07-20 17:56:55', 'joestar');
INSERT INTO `user_permissions` VALUES (228, 4, 75, b'0', '2022-07-20 17:56:55', 'joestar', '2022-07-20 17:56:55', 'joestar');
INSERT INTO `user_permissions` VALUES (229, 5, 75, b'0', '2022-07-20 17:56:55', 'joestar', '2022-07-20 17:56:55', 'joestar');
INSERT INTO `user_permissions` VALUES (230, 6, 75, b'0', '2022-07-20 17:56:55', 'joestar', '2022-07-20 17:56:55', 'joestar');
INSERT INTO `user_permissions` VALUES (231, 7, 75, b'0', '2022-07-20 17:56:55', 'joestar', '2022-07-20 17:56:55', 'joestar');
INSERT INTO `user_permissions` VALUES (232, 1, 195, b'1', '2022-07-21 16:58:47', 'joestar', '2022-07-21 16:58:47', 'joestar');
INSERT INTO `user_permissions` VALUES (233, 2, 195, b'1', '2022-07-21 16:58:47', 'joestar', '2022-07-21 16:58:47', 'joestar');
INSERT INTO `user_permissions` VALUES (234, 3, 195, b'1', '2022-07-21 16:58:47', 'joestar', '2022-07-21 16:58:47', 'joestar');
INSERT INTO `user_permissions` VALUES (235, 4, 195, b'1', '2022-07-21 16:58:47', 'joestar', '2022-07-21 16:58:47', 'joestar');
INSERT INTO `user_permissions` VALUES (236, 5, 195, b'1', '2022-07-21 16:58:47', 'joestar', '2022-07-21 16:58:47', 'joestar');
INSERT INTO `user_permissions` VALUES (237, 6, 195, b'1', '2022-07-21 16:58:47', 'joestar', '2022-07-21 16:58:47', 'joestar');
INSERT INTO `user_permissions` VALUES (238, 7, 195, b'1', '2022-07-21 16:58:47', 'joestar', '2022-07-21 16:58:47', 'joestar');
INSERT INTO `user_permissions` VALUES (239, 8, 195, b'1', '2022-07-23 11:09:47', 'joestar', '2022-07-23 11:09:47', 'joestar');
INSERT INTO `user_permissions` VALUES (240, 9, 195, b'1', '2022-07-23 11:09:47', 'joestar', '2022-07-23 11:09:47', 'joestar');
INSERT INTO `user_permissions` VALUES (241, 10, 195, b'0', '2022-07-23 11:09:47', 'joestar', '2022-07-23 11:09:47', 'joestar');
INSERT INTO `user_permissions` VALUES (242, 11, 195, b'1', '2022-07-23 11:09:47', 'joestar', '2022-07-23 11:09:47', 'joestar');
INSERT INTO `user_permissions` VALUES (243, 8, 219, b'1', '2022-07-30 14:41:39', 'joestar', '2022-07-30 14:41:39', 'joestar');
INSERT INTO `user_permissions` VALUES (244, 9, 219, b'1', '2022-07-30 14:41:39', 'joestar', '2022-07-30 14:41:39', 'joestar');
INSERT INTO `user_permissions` VALUES (245, 10, 219, b'0', '2022-07-30 14:41:39', 'joestar', '2022-07-30 14:41:39', 'joestar');
INSERT INTO `user_permissions` VALUES (246, 11, 219, b'1', '2022-07-30 14:41:39', 'joestar', '2022-07-30 14:41:39', 'joestar');
INSERT INTO `user_permissions` VALUES (247, 1, 220, b'1', '2022-07-30 14:44:01', 'joestar', '2022-07-30 14:44:01', 'joestar');
INSERT INTO `user_permissions` VALUES (248, 2, 220, b'1', '2022-07-30 14:44:01', 'joestar', '2022-07-30 14:44:01', 'joestar');
INSERT INTO `user_permissions` VALUES (249, 3, 220, b'1', '2022-07-30 14:44:01', 'joestar', '2022-07-30 14:44:01', 'joestar');
INSERT INTO `user_permissions` VALUES (250, 4, 220, b'1', '2022-07-30 14:44:01', 'joestar', '2022-07-30 14:44:01', 'joestar');
INSERT INTO `user_permissions` VALUES (251, 5, 220, b'1', '2022-07-30 14:44:01', 'joestar', '2022-07-30 14:44:01', 'joestar');
INSERT INTO `user_permissions` VALUES (252, 6, 220, b'1', '2022-07-30 14:44:01', 'joestar', '2022-07-30 14:44:01', 'joestar');
INSERT INTO `user_permissions` VALUES (253, 7, 220, b'1', '2022-07-30 14:44:01', 'joestar', '2022-07-30 14:44:01', 'joestar');
INSERT INTO `user_permissions` VALUES (254, 8, 220, b'1', '2022-07-30 14:44:27', 'joestar', '2022-07-30 14:44:27', 'joestar');
INSERT INTO `user_permissions` VALUES (255, 9, 220, b'1', '2022-07-30 14:44:27', 'joestar', '2022-07-30 14:44:27', 'joestar');
INSERT INTO `user_permissions` VALUES (256, 10, 220, b'0', '2022-07-30 14:44:27', 'joestar', '2022-07-30 14:44:27', 'joestar');
INSERT INTO `user_permissions` VALUES (257, 11, 220, b'1', '2022-07-30 14:44:27', 'joestar', '2022-07-30 14:44:27', 'joestar');

-- ----------------------------
-- View structure for vwuserpermissions
-- ----------------------------
DROP VIEW IF EXISTS `vwuserpermissions`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `vwuserpermissions` AS select `ul`.`user_name` AS `username2`,`up`.`detail_id` AS `detailid2` from (`user_login` `ul` join `user_permissions` `up` on((`ul`.`user_id` = `up`.`user_id`)));

-- ----------------------------
-- Procedure structure for doWhile
-- ----------------------------
DROP PROCEDURE IF EXISTS `doWhile`;
delimiter ;;
CREATE PROCEDURE `doWhile`()
BEGIN
DECLARE i INT DEFAULT 1; 
WHILE (i <= 10000000) DO
    INSERT INTO `training_table` (name) values (CONCAT('japs',i));
    SET i = i+1;
END WHILE;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
/*
Navicat MySQL Data Transfer

Source Server         : shop
Source Server Version : 50638
Source Host           : 172.16.10.81:3306
Source Database       : myshop

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-06-21 14:44:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_button
-- ----------------------------
DROP TABLE IF EXISTS `tb_button`;
CREATE TABLE `tb_button` (
  `idx` bigint(19) NOT NULL COMMENT '主键idx，分布式架构，全局唯一递增',
  `idx_code` bigint(19) DEFAULT NULL COMMENT '主键idx生成的code##@Long2S(targetField = "idxCode")##',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注，不能为Null',
  `status` smallint(1) DEFAULT NULL COMMENT '状态，1：启用，2：停用，取值范围为0-9，不可以为null，必须手动设置为0或者1',
  `version` bigint(19) DEFAULT NULL COMMENT '版本号，高并发，乐观锁的解决方案，不可以为null，必须赋值',
  `create_by_member_idx` bigint(19) DEFAULT NULL COMMENT '被哪位成员（member_idx）创建的，不可以为null创建成员idx，不可以为##@DynamicS##',
  `update_by_member_idx` bigint(19) DEFAULT NULL COMMENT '被哪位成员（member_idx）修改的，不可以为null创建成员idx，不可以为##@DynamicS##',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间，后续不可以再更新时间##@EnYyyyMMddHHmmss##',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间，每一次都要更新##@EnYyyyMMddHHmmss##',
  `ext` varchar(100) DEFAULT NULL COMMENT '扩展字段，不可以为null，默认为空字符串',
  `menu_idx_code` bigint(19) DEFAULT NULL COMMENT '菜单idxCode',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '按钮名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '按钮编码',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限按钮表';

-- ----------------------------
-- Table structure for tb_member
-- ----------------------------
DROP TABLE IF EXISTS `tb_member`;
CREATE TABLE `tb_member` (
  `idx` bigint(19) NOT NULL DEFAULT '0' COMMENT '主键idx',
  `idx_code` bigint(19) DEFAULT NULL COMMENT '外部关联idxCode',
  `name` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `telphone` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `email` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `pwd` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_by_member_idx_code` bigint(19) DEFAULT NULL,
  `update_by_member_idx_code` bigint(19) DEFAULT NULL,
  `version` bigint(19) DEFAULT NULL,
  `status` smallint(2) DEFAULT NULL,
  `ext` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='成员表';

-- ----------------------------
-- Table structure for tb_member_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_member_role`;
CREATE TABLE `tb_member_role` (
  `idx` bigint(19) NOT NULL COMMENT '主键idx，分布式架构，全局唯一递增',
  `idx_code` bigint(19) DEFAULT NULL COMMENT '主键idx生成的code##@Long2S(targetField = "idxCode")##',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注，不能为Null',
  `status` smallint(1) DEFAULT NULL COMMENT '状态，1：启用，2：停用，取值范围为0-9，不可以为null，必须手动设置为0或者1',
  `version` bigint(19) DEFAULT NULL COMMENT '版本号，高并发，乐观锁的解决方案，不可以为null，必须赋值',
  `create_by_member_idx` bigint(19) DEFAULT NULL COMMENT '被哪位成员（member_idx）创建的，不可以为null创建成员idx，不可以为##@DynamicS##',
  `update_by_member_idx` bigint(19) DEFAULT NULL COMMENT '被哪位成员（member_idx）修改的，不可以为null创建成员idx，不可以为##@DynamicS##',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间，后续不可以再更新时间##@EnYyyyMMddHHmmss##',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间，每一次都要更新##@EnYyyyMMddHHmmss##',
  `ext` varchar(100) DEFAULT NULL COMMENT '扩展字段，不可以为null，默认为空字符串',
  `member_idx_code` bigint(19) DEFAULT NULL COMMENT '成员idxCode',
  `role_idx_code` bigint(19) DEFAULT NULL COMMENT '角色idxCode',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_menu_p
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu_p`;
CREATE TABLE `tb_menu_p` (
  `idx` bigint(19) NOT NULL DEFAULT '0' COMMENT '菜单idx',
  `idx_code` bigint(19) DEFAULT NULL COMMENT '菜单idxCode',
  `name` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单名称',
  `zindex` smallint(3) DEFAULT NULL COMMENT '序号',
  `level` smallint(2) DEFAULT NULL COMMENT '菜单级别',
  `pidx` bigint(19) DEFAULT NULL COMMENT '父菜单Idx',
  `pidx_code` bigint(19) DEFAULT NULL COMMENT '父菜单idxCode',
  `status` smallint(2) DEFAULT NULL COMMENT '状态',
  `code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单编码',
  `is_top` smallint(1) DEFAULT NULL COMMENT '是否顶部显示 0 ：不显示 1：显示',
  `url` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单路径',
  `img` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单图片',
  `version` bigint(19) DEFAULT NULL COMMENT '版本号',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `create_by_member_idx_code` bigint(19) DEFAULT NULL COMMENT '创建人',
  `update_by_member_idx_code` bigint(19) DEFAULT NULL COMMENT '修改人',
  `remark` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `ext` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '扩展字段',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单表';

-- ----------------------------
-- Table structure for tb_resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource` (
  `idx` bigint(19) NOT NULL COMMENT '主键idx，分布式架构，全局唯一递增',
  `idx_code` bigint(19) DEFAULT NULL COMMENT '主键idx生成的code##@Long2S(targetField = "idxCode")##',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注，不能为Null',
  `status` smallint(1) DEFAULT NULL COMMENT '状态，1：启用，2：停用，取值范围为0-9，不可以为null，必须手动设置为0或者1',
  `version` bigint(19) DEFAULT NULL COMMENT '版本号，高并发，乐观锁的解决方案，不可以为null，必须赋值',
  `create_by_member_idx` bigint(19) DEFAULT NULL COMMENT '被哪位成员（member_idx）创建的，不可以为null创建成员idx，不可以为##@DynamicS##',
  `update_by_member_idx` bigint(19) DEFAULT NULL COMMENT '被哪位成员（member_idx）修改的，不可以为null创建成员idx，不可以为##@DynamicS##',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间，后续不可以再更新时间##@EnYyyyMMddHHmmss##',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间，每一次都要更新##@EnYyyyMMddHHmmss##',
  `ext` varchar(100) DEFAULT NULL COMMENT '扩展字段，不可以为null，默认为空字符串',
  `resource_group_idx_code` bigint(19) DEFAULT NULL COMMENT '资源组idxCode',
  `resource_type` smallint(2) DEFAULT NULL COMMENT '资源类型 1：菜单 2：按钮',
  `menu_button_idx_code` bigint(19) DEFAULT NULL COMMENT '菜单或者按钮idxCode',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源表';

-- ----------------------------
-- Table structure for tb_resource_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource_group`;
CREATE TABLE `tb_resource_group` (
  `idx` bigint(19) NOT NULL COMMENT '主键idx，分布式架构，全局唯一递增',
  `idx_code` bigint(19) DEFAULT NULL COMMENT '主键idx生成的code##@Long2S(targetField = "idxCode")##',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注，不能为Null',
  `status` smallint(1) DEFAULT NULL COMMENT '状态，1：启用，2：停用，取值范围为0-9，不可以为null，必须手动设置为0或者1',
  `version` bigint(19) DEFAULT NULL COMMENT '版本号，高并发，乐观锁的解决方案，不可以为null，必须赋值',
  `create_by_member_idx` bigint(19) DEFAULT NULL COMMENT '被哪位成员（member_idx）创建的，不可以为null创建成员idx，不可以为##@DynamicS##',
  `update_by_member_idx` bigint(19) DEFAULT NULL COMMENT '被哪位成员（member_idx）修改的，不可以为null创建成员idx，不可以为##@DynamicS##',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间，后续不可以再更新时间##@EnYyyyMMddHHmmss##',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间，每一次都要更新##@EnYyyyMMddHHmmss##',
  `ext` varchar(100) DEFAULT NULL COMMENT '扩展字段，不可以为null，默认为空字符串',
  `name` varchar(100) DEFAULT NULL COMMENT '资源组',
  `code` varchar(100) DEFAULT NULL COMMENT '资源组编码',
  `zindex` smallint(3) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源组';

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `idx` bigint(19) NOT NULL COMMENT '主键idx，分布式架构，全局唯一递增',
  `idx_code` bigint(19) DEFAULT NULL COMMENT '主键idx生成的code##@Long2S(targetField = "idxCode")##',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注，不能为Null',
  `status` smallint(1) DEFAULT NULL COMMENT '状态，1：启用，2：停用，取值范围为0-9，不可以为null，必须手动设置为0或者1',
  `version` bigint(19) DEFAULT NULL COMMENT '版本号，高并发，乐观锁的解决方案，不可以为null，必须赋值',
  `create_by_member_idx` bigint(19) DEFAULT NULL COMMENT '被哪位成员（member_idx）创建的，不可以为null创建成员idx，不可以为##@DynamicS##',
  `update_by_member_idx` bigint(19) DEFAULT NULL COMMENT '被哪位成员（member_idx）修改的，不可以为null创建成员idx，不可以为##@DynamicS##',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间，后续不可以再更新时间##@EnYyyyMMddHHmmss##',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间，每一次都要更新##@EnYyyyMMddHHmmss##',
  `ext` varchar(100) DEFAULT NULL COMMENT '扩展字段，不可以为null，默认为空字符串',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '编码',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Table structure for tb_role_resource_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_resource_group`;
CREATE TABLE `tb_role_resource_group` (
  `idx` bigint(19) NOT NULL COMMENT '主键idx，分布式架构，全局唯一递增',
  `idx_code` bigint(19) DEFAULT NULL COMMENT '主键idx生成的code##@Long2S(targetField = "idxCode")##',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注，不能为Null',
  `status` smallint(1) DEFAULT NULL COMMENT '状态，1：启用，2：停用，取值范围为0-9，不可以为null，必须手动设置为0或者1',
  `version` bigint(19) DEFAULT NULL COMMENT '版本号，高并发，乐观锁的解决方案，不可以为null，必须赋值',
  `create_by_member_idx` bigint(19) DEFAULT NULL COMMENT '被哪位成员（member_idx）创建的，不可以为null创建成员idx，不可以为##@DynamicS##',
  `update_by_member_idx` bigint(19) DEFAULT NULL COMMENT '被哪位成员（member_idx）修改的，不可以为null创建成员idx，不可以为##@DynamicS##',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间，后续不可以再更新时间##@EnYyyyMMddHHmmss##',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间，每一次都要更新##@EnYyyyMMddHHmmss##',
  `ext` varchar(100) DEFAULT NULL COMMENT '扩展字段，不可以为null，默认为空字符串',
  `role_idx_code` bigint(19) DEFAULT NULL COMMENT '角色idxCode',
  `resource_group_idx_code` bigint(19) DEFAULT NULL COMMENT '资源组idxCode',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_transcation
-- ----------------------------
DROP TABLE IF EXISTS `tb_transcation`;
CREATE TABLE `tb_transcation` (
  `idx` bigint(19) NOT NULL,
  `idx_code` bigint(19) DEFAULT NULL,
  `transcation_body` varchar(5000) DEFAULT NULL COMMENT '事务体（多个表的封装数据）',
  `cause` varchar(1000) DEFAULT NULL COMMENT '异常信息',
  `tag` varchar(30) DEFAULT NULL COMMENT '事务标签，用于分类',
  `result_msg` varchar(100) DEFAULT '' COMMENT '返回结果',
  `result_statu` smallint(3) DEFAULT NULL COMMENT '返回状态码（400， 500， 其它）',
  `mq_send_status` smallint(2) DEFAULT NULL COMMENT 'mq 发送状态 1：成功 0：失败',
  `mq_consume_status` smallint(2) DEFAULT NULL COMMENT 'mq 消费状态（1：成功 2：失败）',
  `transcation_status` smallint(2) DEFAULT NULL COMMENT '事务状态（1：成功 2：失败）',
  `version` bigint(19) DEFAULT NULL,
  `ext` varchar(100) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_member_idx_code` bigint(19) DEFAULT NULL,
  `update_member_idx_code` bigint(19) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='事务通用表， 走es查询，需要实现的建新表，加入搜索条件';

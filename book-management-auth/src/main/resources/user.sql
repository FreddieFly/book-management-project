-- 创建包含全部字段的user表
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL COMMENT '主键',
  `delete_status` boolean DEFAULT NULL COMMENT '删除状态',
  `create_time` timestamp DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp DEFAULT NULL COMMENT '更新时间',
  `create_user` varchar(255) DEFAULT NULL COMMENT '创建者',
  `update_user` varchar(255) DEFAULT NULL COMMENT '更新者',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `role` varchar(255) DEFAULT 'common' COMMENT '角色（admin/common）',
  PRIMARY KEY (`id`)
);

-- 插入10条记录（两条role为admin，其他为common）
INSERT INTO `user` (
  `id`, `delete_status`, `create_time`, `update_time`,
  `create_user`, `update_user`, `username`, `password`,
  `name`, `email`, `phone`, `role`
) VALUES
  (1, false, NOW(), NOW(), 'system', 'system', 'system', 'system', '管理员', 'admin@example.com', '13800000001', 'admin'),
  (2, false, NOW(), NOW(), 'system', 'system', 'user01', 'user01_pass', '用户01', 'user01@example.com', '13800000002', 'admin'),
  (3, false, NOW(), NOW(), 'system', 'system', 'user02', 'user02_pass', '用户02', 'user02@example.com', '13800000003', 'common'),
  (4, false, NOW(), NOW(), 'system', 'system', 'user03', 'user03_pass', '用户03', 'user03@example.com', '13800000004', 'common'),
  (5, false, NOW(), NOW(), 'system', 'system', 'user04', 'user04_pass', '用户04', 'user04@example.com', '13800000005', 'common'),
  (6, false, NOW(), NOW(), 'system', 'system', 'user05', 'user05_pass', '用户05', 'user05@example.com', '13800000006', 'common'),
  (7, false, NOW(), NOW(), 'system', 'system', 'user06', 'user06_pass', '用户06', 'user06@example.com', '13800000007', 'common'),
  (8, false, NOW(), NOW(), 'system', 'system', 'user07', 'user07_pass', '用户07', 'user07@example.com', '13800000008', 'common'),
  (9, false, NOW(), NOW(), 'system', 'system', 'user08', 'user08_pass', '用户08', 'user08@example.com', '13800000009', 'admin'),
  (10, false, NOW(), NOW(), 'system', 'system', 'user09', 'user09_pass', '用户09', 'user09@example.com', '13800000010', 'common');
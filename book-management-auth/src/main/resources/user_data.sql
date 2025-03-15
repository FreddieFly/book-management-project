-- 转储表 auth_user
DROP TABLE IF EXISTS `auth_user`;

CREATE TABLE `auth_user` (
  `id` bigint NOT NULL, -- 主键
  `delete_status` boolean DEFAULT NULL, -- 删除状态
  `create_time` timestamp DEFAULT NULL, -- 创建时间
  `update_time` timestamp DEFAULT NULL, -- 更新时间
  `create_user` varchar(255) DEFAULT NULL, -- 创建数据的用户
  `update_user` varchar(255) DEFAULT NULL, -- 更新数据的用户
  `username` varchar(255) DEFAULT NULL, -- 用户名
  `password` varchar(255) DEFAULT NULL, -- 密码
  `status` varchar(255) DEFAULT NULL, -- 账户状态
  `name` varchar(255) DEFAULT NULL, -- 姓名
  PRIMARY KEY (`id`)
);

-- 插入数据
INSERT INTO `auth_user` (`id`, `delete_status`, `create_time`, `update_time`, `create_user`, `update_user`, `username`, `password`, `status`, `name`)
VALUES
  (1, NULL, NULL, NULL,NULL ,NULL , 'admin', 'admin',NULL , '管理员'),
  (2, NULL, NULL, NULL, NULL, NULL, 'user01', 'user01',NULL , '用户01');
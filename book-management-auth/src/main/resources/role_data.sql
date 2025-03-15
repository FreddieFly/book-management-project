-- 转储表 auth_role
DROP TABLE IF EXISTS `auth_role`;

CREATE TABLE `auth_role` (
  `id` bigint NOT NULL,
  `delete_status` boolean DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- 插入数据
INSERT INTO `auth_role` (`id`, `delete_status`, `create_time`, `update_time`, `create_user`, `update_user`, `name`, `description`)
VALUES
  (1, NULL, NULL, NULL, NULL, NULL, '图书管理员', '拥有所有菜单权限'),
  (2, NULL, NULL, NULL, NULL, NULL, '普通用户', '拥有部分权限');
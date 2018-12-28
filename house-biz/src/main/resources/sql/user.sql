
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名称',
  `phone` char(13) NOT NULL DEFAULT '' COMMENT '手机号码',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '电子邮箱',
  `about_me` varchar(250) NOT NULL DEFAULT '' COMMENT '自我介绍',
  `passwd` varchar(512) NOT NULL DEFAULT '' COMMENT '密码 md5加密',
  `avatar` varchar(512) NOT NULL DEFAULT '' COMMENT '头像图片',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1-普通用户 2-经纪人',
  `create_time` datetime NOT NULL DEFAULT '1994-05-17 00:00:00' COMMENT '创建时间',
  `enable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否启用 1-启用 0-禁用',
  `agency_id` int(11) NOT NULL DEFAULT '0' COMMENT '经纪人id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户表';

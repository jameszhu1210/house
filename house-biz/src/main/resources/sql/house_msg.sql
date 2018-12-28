
CREATE TABLE `house_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `msg` varchar(1024) NOT NULL DEFAULT '' COMMENT '消息',
  `create_time` datetime NOT NULL DEFAULT '1994-05-17 00:00:00' COMMENT '创建时间',
  `agent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '经纪人id',
  `house_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '房屋id',
  `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房屋信息表';

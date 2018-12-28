
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(512) NOT NULL DEFAULT '' COMMENT '内容',
  `house_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '房屋id',
  `create_time` datetime NOT NULL DEFAULT '1994-05-17 00:00:00' COMMENT '创建时间',
  `blog_id` int(11) NOT NULL DEFAULT '0' COMMENT '博客id',
  `type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1-房产  2-博客',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

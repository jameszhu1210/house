CREATE TABLE `agency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '经纪人名称',
  `address` varchar(100) NOT NULL DEFAULT '' COMMENT '地址',
  `phone` char(13) NOT NULL DEFAULT '' COMMENT '手机号码',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱',
  `about_me` varchar(100) NOT NULL DEFAULT '' COMMENT '自我介绍',
  `mobile` varchar(11) NOT NULL DEFAULT '0' COMMENT '座机',
  `web_site` varchar(20) NOT NULL DEFAULT '' COMMENT '网站',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经济机构表';

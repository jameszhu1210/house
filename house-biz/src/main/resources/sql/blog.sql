
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tags` varchar(20) NOT NULL DEFAULT '' COMMENT '标签',
  `content` text NOT NULL COMMENT '内容',
  `create_time` datetime NOT NULL DEFAULT '1994-05-17 00:00:00' COMMENT '创建时间',
  `title` varchar(20) NOT NULL DEFAULT '' COMMENT '标题',
  `cat` int(11) NOT NULL DEFAULT '0' COMMENT '分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='博客表';



CREATE TABLE `menu` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `title` varchar(255) DEFAULT NULL,
    `icon` varchar(255) DEFAULT NULL,
    `path` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `parent_id` int(11) DEFAULT NULL,
    `type` int(11) DEFAULT NULL,
    `create_time` datetime DEFAULT NULL,
    `component` varchar(255) DEFAULT NULL,
    `state` int(11) DEFAULT NULL,
    `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `role` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `remark` varchar(255) DEFAULT NULL,
    `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `role_menu` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `role_id` int(11) DEFAULT NULL,
    `menu_id` varchar(255) DEFAULT NULL,
    `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `uid` varchar(255) DEFAULT NULL,
    `username` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `mobile` varchar(255) DEFAULT NULL,
    `nick_name` varchar(255) DEFAULT NULL,
    `create_time` datetime DEFAULT NULL,
    `state` int(11) DEFAULT NULL,
    `type` int(11) DEFAULT NULL,
    `salt` varchar(255) DEFAULT NULL,
    `pwd` varchar(255) DEFAULT NULL,
    `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `user_role` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` int(11) DEFAULT NULL,
    `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


# Dump of table user
# ------------------------------------------------------------
create database snsdb;

use snsdb;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` char(20) NOT NULL,
  `email` varchar(128) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `create_date` datetime DEFAULT current_timestamp(),
  `birth` datetime DEFAULT NULL,
  `gender` varchar(8) NOT NULL,
  `selfintroduce` varchar(100) default Null,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `user_idx_unique_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



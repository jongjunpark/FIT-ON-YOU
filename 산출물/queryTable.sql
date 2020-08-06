
create database snsdb;

use snsdb;

CREATE TABLE `user` (
  `nickname` varchar(20) not null,
  `email` varchar(128) NOT NULL,
  `password` varchar(128),
  `create_date` datetime DEFAULT current_timestamp(),
  `birth` datetime DEFAULT NULL,
  `gender` varchar(8),
  `profile_img` varchar(1000) default Null,
  `selfintroduce` varchar(100) default Null,
  PRIMARY KEY (`nickname`),
  UNIQUE KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table `influencer`(
	`nickname` varchar(20) ,
    `profile_img` varchar(1000),
    primary key(nickname)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table `Board` (
	`articleNo` int auto_increment not null,
    `content` varchar(500),
    `articleDate` datetime default current_timestamp(),
    `articleUser` varchar(20),
    `influeUser` varchar(20),
    `favoriteCnt` int default 0,
    PRIMARY KEY(articleNo),
	FOREIGN KEY(articleUser) REFERENCES user(nickname) on delete cascade on update cascade,
	FOREIGN KEY(influeUser) REFERENCES influencer(nickname) on delete cascade on update cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table `likes`(
	`likeNo` int auto_increment not null,
	`nickname` varchar(20),
    `articleNo` int,
	primary key(likeNo),
    foreign key (nickname) references user(nickname) on delete cascade on update cascade,
    foreign key (articleNo) references Board(articleNo) on delete cascade
)Engine=InnoDB Default Charset = utf8mb4;

create table `imageStore`(
	`imageNo` int auto_increment not null,
	`articleNo` int,
    `imageUrl` varchar(300) not null,
    primary key(imageNo),
    foreign key (articleNo) references Board(articleNo) on delete cascade
    )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
    
create table `bookmark`(
	`bookmarkNo` int not null auto_increment,
    `bookedArticle` int not null,
    `bookUser` varchar(20) not null,
    primary key(bookmarkNo),
    index (bookmarkNo),
    foreign key(bookedArticle) references Board(articleNo) on delete cascade,
    foreign key(bookUser) references user(nickname) on delete cascade on update cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table `follow`(
	`followNo` int auto_increment not null,
    `followingUser` varchar(20) not null,
    `followedUser` varchar(20) not null,
     `check` boolean,
    primary key(followNo),
    foreign key (followingUser) references user(nickname) on delete cascade on update cascade,
     foreign key (followedUser) references user(nickname) on delete cascade on update cascade
    )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table `recellboard`(
	`recellNo` int auto_increment not null,
    `recellContent` varchar(1000),
    `recellImage` varchar(1000) not null,
    `recellDate` datetime default current_timestamp(),
    `recellUser` varchar(20) not null,
    `recellPrice` varchar(100) unique key,
    primary key(recellNo),
    foreign key(recellUser) references user(nickname) on delete cascade
    )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table `directMessage`(
	`recevier` varchar(20) not null,
    `caller` varchar(20) not null,
    `content` varchar(1000),
    `directNo` int auto_increment not null,
    primary key(directNo),
    foreign key(recevier) references user(nickname) on delete cascade on update cascade,
    foreign key(caller) references user(nickname) on delete cascade on update cascade
    )ENGINE=InnoDB default Charset =utf8mb4;

create table `alarm` (
   `alramNo` int auto_increment not null,
    `type` varchar(30),
    `recevier` varchar(20) not null,
    `follower` varchar(20) ,
    `articleNo` int,
    `isRead` tinyint(1),
    `createAt` datetime default current_timestamp(),
    primary key(alramNo),
    foreign key (recevier) references user(nickname)  on delete cascade on update cascade,
    foreign key (follower) references user(nickname) on delete cascade on update cascade,
    foreign key (articleNo) references Board(ArticleNo) on delete cascade
    )engine=InnoDB default Charset = utf8mb4;
    
create table `comment` (
	`commentNo` int auto_increment not null,
    `writer` varchar(20) not null,
    `articleNo` int not null,
    `content` varchar(100),
	primary key(commentNo),
    foreign key (writer) references user(nickname) on delete cascade on update cascade,
    foreign key (articleNo) references Board(articleNo) on delete cascade
    ) Engine=InnoDB default Charset = utf8mb4;

create table `tag` (
	`tagName` varchar(100) not null,
    primary key(tagName)
    )Engine = InnoDB default Charset = utf8mb4;
    

create table `articleTag`(
	`tagNo` int auto_increment not null,
    `tagName` varchar(100),
    `articleNo` int,
    primary key(tagNo),
    foreign key(tagName) references tag(tagName),
    foreign key(articleNo) references Board(articleNo)
    )Engine = InnoDB default charset = utf8mb4


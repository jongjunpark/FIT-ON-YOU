drop schema snsdb;

create database snsdb;

use snsdb;

CREATE TABLE `user` (
  `nickname` char(20) NOT NULL,
  `email` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `create_date` datetime DEFAULT current_timestamp(),
  `birth` datetime DEFAULT NULL,
  `gender` varchar(8) NOT NULL,
  `profile_img` varchar(1000) default Null,
  `selfintroduce` varchar(100) default Null,
  PRIMARY KEY (`nickname`),
  UNIQUE KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `influencer`(
	`nickname` varchar(20) not null,
    `profile_img` varchar(1000),
    primary key(nickname)
);
create table `board` (
	`articleNo` int auto_increment not null,
    `content` varchar(1000),
    `articleDate` datetime default current_timestamp(),
    `articleUser` varchar(20) not null,
    `favoriteCnt` int,
    `img` varchar(1000) not null,
    PRIMARY KEY(articleNo),
	INDEX(articleNo),
	FOREIGN KEY(articleUser) REFERENCES User(nickname) on delete cascade on update cascade,
	FOREIGN KEY(articleUser) REFERENCES influencer(nickname) on delete cascade on update cascade

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `bookmark`(
	`bookmarkNo` int not null auto_increment,
    `bookedArticle` int not null,
    `bookUser` varchar(20) not null,
    primary key(bookmarkNo),
    index (bookmarkNo),
    foreign key(bookedArticle) references board(articleNo) on delete cascade,
    foreign key(bookUser) references User(nickname) on delete cascade on update cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `follow`(
	`followNo` int auto_increment not null,
    `followingUser` varchar(20) not null,
    `followedUser` varchar(20) not null,
    primary key(followNo),
    foreign key (followingUser) references User(nickname) on delete cascade on update cascade,
    foreign key (followedUser) references User(nickname) on delete cascade on update cascade
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `recellboard`(
	`recellNo` int auto_increment not null,
    `recellContent` varchar(1000),
    `recellImage` varchar(1000) not null,
    `recellDate` datetime default current_timestamp(),
    `recellUser` varchar(20) not null,
    `recellPrice` varchar(100) unique key,
    primary key(recellNo),
    foreign key(recellUser) references User(nickname) on delete cascade
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `directMessage`(
	`recevier` varchar(20) not null,
    `caller` varchar(20) not null,
    `content` varchar(1000),
    `directNo` int auto_increment not null,
    primary key(directNo),
    foreign key(recevier) references User(nickname) on delete cascade on update cascade,
    foreign key(caller) references User(nickname) on delete cascade on update cascade
    )ENGINE=InnoDB default Charset =utf8;

create table `alarm` (
	`alramNo` int auto_increment not null,
    `content` varchar(100),
    `recevier` varchar(20) not null,
    primary key(alramNo),
    foreign key (recevier) references User(nickname)  on delete cascade on update cascade
    )engine=InnoDB default Charset = uft8;
    
create table `comment` (
	`commentNo` int auto_increment not null,
    `writer` varchar(20) not null,
    `articleNo` int not null,
    `content` varchar(100),
	primary key(commentNo),
    foreign key (writer) references User(nickname) on delete cascade on update cascade
    ) Engine=InnoDB default Charset = utf8;

create table `tag` (
	`tagName` varchar(20) not null,
    primary key(tagName)
    )Engine = InnoDB default Charset = utf8;
    
create table `like`(
	`likeNo` int auto_increment not null,
    `articleNo` int not null,
    `likeuser` varchar(20) not null,
    primary key(likeNo),
    foreign key(articleNo) references board(articleNo) on delete cascade,
    foreign key(likeuser) references User(nickname) on delete cascade on update cascade
    ) engine=InnoDB default charset=utf8;
    
    



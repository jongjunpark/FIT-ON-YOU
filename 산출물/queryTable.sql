use snsdb;

CREATE TABLE `user` (
  `nickname` char(20) NOT NULL,
  `email` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `create_date` datetime DEFAULT current_timestamp(),
  `birth` datetime DEFAULT NULL,
  `gender` varchar(8) NOT NULL,
  `selfintroduce` varchar(100) default Null,
  PRIMARY KEY (`nickname`),
  UNIQUE KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `board` (
	`articleNo` int auto_increment not null,
    `content` varchar(1000),
    `articleDate` datetime default current_timestamp(),
    `articleUser` varchar(20) not null,
    `favoriteCnt` int,
    `img` mediumblob,
    PRIMARY KEY(articleNo),
	INDEX(articleNo),
	FOREIGN KEY(articleUser) REFERENCES User(nickname) on delete cascade on update cascade
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
    `recellImage` mediumblob,
    `recellDate` datetime default current_timestamp(),
    `recellUser` varchar(20) not null,
    `recellPrice` varchar(100) unique key,
    primary key(recellNo),
    foreign key(recellUser) references User(nickname) on delete cascade
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;




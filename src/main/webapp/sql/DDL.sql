use playdata;

drop table if exists Member;

drop table if exists Bookmark;

create table Member (
	   member_id        VARCHAR(20) PRIMARY KEY,
       name             VARCHAR(20) NOT NULL,
       password         	VARCHAR(20) NOT NULL
);

create table Bookmark (
	   bookmarkId       INT AUTO_INCREMENT PRIMARY KEY,
       coinId           VARCHAR(20) NOT NULL,
       memberId         	VARCHAR(20) NOT NULL
);

ALTER TABLE Bookmark  ADD FOREIGN KEY (memberId) REFERENCES Member (member_id);
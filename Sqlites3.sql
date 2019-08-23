# 删除表
DROP TABLE account;


# 添加新字段 邮箱
ALTER TABLE account ADD email text; 


INSERT INTO account (acname,email,gender,password,salt,birth,reg_time) VALUES 
('administrator','javaguide@qq.com',0,'123123','QWERTYUI','2013-03-07','2013-04-17');


SELECT acid,acname,email,gender,password,salt,birth,reg_time AS regTime FROM account WHERE acname=?;


# 账户表
# '性别:0-female,1-male,2-secret';'出生年月日';'账号注册时间[年月日]';'盐值'
CREATE TABLE IF NOT EXISTS account( 
					acid Integer PRIMARY KEY AUTOINCREMENT,  
					acname varchar(255) NOT NULL UNIQUE,  
					gender int(1) DEFAULT '2' NOT NULL,
					email text,
					birth date NOT NULL,
					reg_time date NOT NULL,
					password varchar(52) NOT NULL,
					salt varchar(20) NOT NULL
				);


# insert into an trans_letter 
INSERT INTO transmitLetter (transmitter,receiver,title,content,composeTime,launchTime,lastEditTime,moveInRecycleTime,status,attachmentFileName) 
VALUES (?,?,?,?,?,?,?,?,?,?);


# ...
SELECT id,transmitter,receiver,title,content,composeTime,launchTime,lastEditTime,moveInRecycleTime,status,attachmentFileName FROM transmitLetter;

SELECT id,transmitter,receiver,title,content,composeTime,launchTime,lastEditTime,moveInRecycleTime,status,attachmentFileName 
FROM transmitLetter WHERE id=?;


# 发件表
# transmitter发信人；receiver收信人; title标题; content信件正文; composeTime撰写创建之时; launchTime发送之时; lastEditTime上次编辑时间
# moveInRecycleTime 移入回收站之时; status ("0-回收站,1-已发送(发件箱),2-未发送(草稿)");　attachmentFileName 附件文件名；
CREATE TABLE IF NOT EXISTS transmitLetter(
	ID Integer PRIMARY KEY AUTOINCREMENT,
	transmitter Char(100) not null,
	receiver Char(100) not null,
	title VarChar(200) not null,
	content text not null,
	composeTime datetime,
	launchTime datetime,
	lastEditTime datetime,
	moveInRecycleTime datetime,
	status int(1),
	attachmentFileName Char(255)
);


# 收件表
# transmitter发信人; receiver收信人; title信件标题; content信件内容; moveInRecycleTime移入回收站之时; status 状态(0-回收站,1-已阅,2-未阅);
# receiveTime 收信时间　; attachmentFileName 附件文件名;
CREATE TABLE IF NOT EXISTS receiveLetter(
	ID Integer PRIMARY KEY AUTOINCREMENT,
	transmitter Char(100) not null,
	receiver Char(100) not null,
	title VarChar(200) not null,
	content text not null,
	receiveTime datetime,
	moveInRecycleTime datetime,
	status int(1),
	attachmentFileName Char(255)
);


# ......
SELECT id,transmitter,receiver,title,content,composeTime,launchTime,lastEditTime,moveInRecycleTime,status,attachmentFileName 
FROM transmitLetter WHERE transmitter=?;

# update
UPDATE transmitLetter SET status=? WHERE id IN (?,?);

# update
UPDATE transmitLetter SET receiver=?,title=?,content=?,launchTime=?,lastEditTime=?,attachmentFileName=?,status=? WHERE id=?;

# 特征
ALTER TABLE transmitLetter ADD COLUMN feature int DEFAULT 1;

ALTER TABLE receiveLetter ADD COLUMN feature int DEFAULT 0;

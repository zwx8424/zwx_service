CREATE DATABASE mytest DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE USER 'mytest'@'%' IDENTIFIED BY 'mytest';

GRANT ALL ON mytest.* TO 'mytest'@'%';
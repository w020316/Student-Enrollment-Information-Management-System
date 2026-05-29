CREATE DATABASE IF NOT EXISTS stu_manage DEFAULT CHARACTER SET utf8;

USE stu_manage;

CREATE TABLE IF NOT EXISTS student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(10) NOT NULL,
    age INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS teacher (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(10) NOT NULL,
    age INT,
    salary DOUBLE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO student (name, age) VALUES ('张三', 20);
INSERT INTO student (name, age) VALUES ('李四', 21);
INSERT INTO student (name, age) VALUES ('王五', 22);
INSERT INTO student (name, age) VALUES ('赵六', 20);
INSERT INTO student (name, age) VALUES ('钱七', 23);
INSERT INTO student (name, age) VALUES ('孙八', 19);
INSERT INTO student (name, age) VALUES ('周九', 21);
INSERT INTO student (name, age) VALUES ('吴十', 22);

INSERT INTO teacher (name, age, salary) VALUES ('刘老师', 35, 8000.0);
INSERT INTO teacher (name, age, salary) VALUES ('陈老师', 40, 9500.0);
INSERT INTO teacher (name, age, salary) VALUES ('杨老师', 38, 8800.0);
INSERT INTO teacher (name, age, salary) VALUES ('黄老师', 45, 10200.0);
INSERT INTO teacher (name, age, salary) VALUES ('林老师', 33, 7500.0);

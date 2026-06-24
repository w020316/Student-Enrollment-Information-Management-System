CREATE DATABASE IF NOT EXISTS stu_manage DEFAULT CHARACTER SET utf8;

USE stu_manage;

CREATE TABLE IF NOT EXISTS student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_no VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(10) NOT NULL,
    gender VARCHAR(4) DEFAULT '男',
    age INT,
    status VARCHAR(10) DEFAULT '在读'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS teacher (
    id INT PRIMARY KEY AUTO_INCREMENT,
    teacher_no VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(10) NOT NULL,
    gender VARCHAR(4) DEFAULT '男',
    age INT,
    salary DOUBLE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS course (
    id INT PRIMARY KEY AUTO_INCREMENT,
    course_no VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(20) NOT NULL,
    credit DOUBLE,
    hours INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS student_course (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    score DOUBLE,
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO student (student_no, name, gender, age, status) VALUES ('2024001', '张三', '男', 20, '在读');
INSERT INTO student (student_no, name, gender, age, status) VALUES ('2024002', '李四', '男', 21, '在读');
INSERT INTO student (student_no, name, gender, age, status) VALUES ('2024003', '王五', '男', 22, '休学');
INSERT INTO student (student_no, name, gender, age, status) VALUES ('2024004', '赵六', '男', 20, '在读');
INSERT INTO student (student_no, name, gender, age, status) VALUES ('2024005', '钱七', '男', 23, '毕业');
INSERT INTO student (student_no, name, gender, age, status) VALUES ('2024006', '孙八', '男', 19, '在读');
INSERT INTO student (student_no, name, gender, age, status) VALUES ('2024007', '周九', '女', 21, '退学');
INSERT INTO student (student_no, name, gender, age, status) VALUES ('2024008', '吴十', '女', 22, '在读');

INSERT INTO teacher (teacher_no, name, gender, age, salary) VALUES ('T1001', '刘老师', '男', 35, 8000.0);
INSERT INTO teacher (teacher_no, name, gender, age, salary) VALUES ('T1002', '陈老师', '男', 40, 9500.0);
INSERT INTO teacher (teacher_no, name, gender, age, salary) VALUES ('T1003', '杨老师', '女', 38, 8800.0);
INSERT INTO teacher (teacher_no, name, gender, age, salary) VALUES ('T1004', '黄老师', '男', 45, 10200.0);
INSERT INTO teacher (teacher_no, name, gender, age, salary) VALUES ('T1005', '林老师', '女', 33, 7500.0);

INSERT INTO course (course_no, name, credit, hours) VALUES ('C001', '高等数学', 4.0, 64);
INSERT INTO course (course_no, name, credit, hours) VALUES ('C002', '线性代数', 3.0, 48);
INSERT INTO course (course_no, name, credit, hours) VALUES ('C003', '大学英语', 3.5, 56);
INSERT INTO course (course_no, name, credit, hours) VALUES ('C004', 'Java程序设计', 4.0, 64);
INSERT INTO course (course_no, name, credit, hours) VALUES ('C005', '数据结构', 3.5, 56);

INSERT INTO student_course (student_id, course_id, score) VALUES (1, 1, 85.0);
INSERT INTO student_course (student_id, course_id, score) VALUES (1, 4, 92.0);
INSERT INTO student_course (student_id, course_id, score) VALUES (2, 1, 78.0);
INSERT INTO student_course (student_id, course_id, score) VALUES (2, 2, 88.0);
INSERT INTO student_course (student_id, course_id, score) VALUES (3, 3, 90.0);
INSERT INTO student_course (student_id, course_id, score) VALUES (3, 5, 82.0);
INSERT INTO student_course (student_id, course_id, score) VALUES (4, 4, 95.0);
INSERT INTO student_course (student_id, course_id, score) VALUES (5, 1, 76.0);
INSERT INTO student_course (student_id, course_id, score) VALUES (5, 5, 88.0);
INSERT INTO student_course (student_id, course_id, score) VALUES (6, 2, 91.0);
INSERT INTO student_course (student_id, course_id, score) VALUES (7, 3, 85.0);
INSERT INTO student_course (student_id, course_id, score) VALUES (8, 4, 89.0);

package com.stu.entity;

import com.stu.annotation.Column;
import com.stu.annotation.Entity;
import com.stu.annotation.ID;

@Entity("student")
public class Student {
    @ID
    @Column(value = "id", label = "编号")
    private Integer id;

    @Column(value = "student_no", nullable = false, maxLength = 20, label = "学号")
    private String studentNo;

    @Column(value = "name", nullable = false, maxLength = 10, label = "姓名")
    private String name;

    @Column(value = "gender", label = "性别")
    private String gender;

    @Column(value = "age", label = "年龄")
    private Integer age;

    @Column(value = "status", label = "在籍信息")
    private String status;

    public Student() {}

    public Student(Integer id, String studentNo, String name, String gender, Integer age, String status) {
        this.id = id;
        this.studentNo = studentNo;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.status = status;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getStudentNo() { return studentNo; }
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Student{id=" + id + ", studentNo='" + studentNo + "', name='" + name + "', gender='" + gender + "', age=" + age + ", status='" + status + "'}";
    }
}

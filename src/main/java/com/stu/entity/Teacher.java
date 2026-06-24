package com.stu.entity;

import com.stu.annotation.Column;
import com.stu.annotation.Entity;
import com.stu.annotation.ID;

@Entity("teacher")
public class Teacher {
    @ID
    @Column(value = "id", label = "编号")
    private Integer id;

    @Column(value = "teacher_no", nullable = false, maxLength = 20, label = "工号")
    private String teacherNo;

    @Column(value = "name", nullable = false, maxLength = 10, label = "姓名")
    private String name;

    @Column(value = "gender", label = "性别")
    private String gender;

    @Column(value = "age", label = "年龄")
    private Integer age;

    @Column(value = "salary", label = "薪资")
    private Double salary;

    public Teacher() {}

    public Teacher(Integer id, String teacherNo, String name, String gender, Integer age, Double salary) {
        this.id = id;
        this.teacherNo = teacherNo;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTeacherNo() { return teacherNo; }
    public void setTeacherNo(String teacherNo) { this.teacherNo = teacherNo; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return "Teacher{id=" + id + ", teacherNo='" + teacherNo + "', name='" + name + "', gender='" + gender + "', age=" + age + ", salary=" + salary + "}";
    }
}

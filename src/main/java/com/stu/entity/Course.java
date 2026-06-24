package com.stu.entity;

import com.stu.annotation.Column;
import com.stu.annotation.Entity;
import com.stu.annotation.ID;

@Entity("course")
public class Course {
    @ID
    @Column(value = "id", label = "编号")
    private Integer id;

    @Column(value = "course_no", nullable = false, maxLength = 20, label = "课程编号")
    private String courseNo;

    @Column(value = "name", nullable = false, maxLength = 20, label = "课程名称")
    private String name;

    @Column(value = "credit", label = "学分")
    private Double credit;

    @Column(value = "hours", label = "学时")
    private Integer hours;

    public Course() {}

    public Course(Integer id, String courseNo, String name, Double credit, Integer hours) {
        this.id = id;
        this.courseNo = courseNo;
        this.name = name;
        this.credit = credit;
        this.hours = hours;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCourseNo() { return courseNo; }
    public void setCourseNo(String courseNo) { this.courseNo = courseNo; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getCredit() { return credit; }
    public void setCredit(Double credit) { this.credit = credit; }
    public Integer getHours() { return hours; }
    public void setHours(Integer hours) { this.hours = hours; }

    @Override
    public String toString() {
        return "Course{id=" + id + ", courseNo='" + courseNo + "', name='" + name + "', credit=" + credit + ", hours=" + hours + "}";
    }
}

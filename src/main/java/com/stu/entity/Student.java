package com.stu.entity;

import com.stu.annotation.Column;
import com.stu.annotation.Entity;
import com.stu.annotation.ID;

@Entity("student")
public class Student {
    @ID
    @Column(value = "id", label = "编号")
    private Integer id;

    @Column(value = "name", nullable = false, maxLength = 10, label = "姓名")
    private String name;

    @Column(value = "age", label = "年龄")
    private Integer age;

    public Student() {}

    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', age=" + age + "}";
    }
}

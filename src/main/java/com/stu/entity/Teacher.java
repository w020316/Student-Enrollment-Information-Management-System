package com.stu.entity;

import com.stu.annotation.Column;
import com.stu.annotation.Entity;
import com.stu.annotation.ID;

@Entity("teacher")
public class Teacher {
    @ID
    @Column(value = "id", label = "编号")
    private Integer id;

    @Column(value = "name", nullable = false, maxLength = 10, label = "姓名")
    private String name;

    @Column(value = "age", label = "年龄")
    private Integer age;

    @Column(value = "salary", label = "薪资")
    private Double salary;

    public Teacher() {}

    public Teacher(Integer id, String name, Integer age, Double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return "Teacher{id=" + id + ", name='" + name + "', age=" + age + ", salary=" + salary + "}";
    }
}

package com.stu.service;

import com.stu.entity.Course;
import java.util.List;

public interface CourseService {
    List<Course> findAll();
    List<Course> findByPage(int page, int size);
    List<Course> findByName(String name);
    Course findById(Integer id);
    int count();
    void insert(Course course);
    void update(Course course);
    void delete(Integer id);
}

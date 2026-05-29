package com.stu.service;

import com.stu.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> findAll();
    List<Student> findByPage(int page, int size);
    Student findById(Integer id);
    int count();
    void insert(Student student);
    void update(Student student);
    void delete(Integer id);
}

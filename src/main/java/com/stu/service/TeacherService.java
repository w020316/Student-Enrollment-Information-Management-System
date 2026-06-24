package com.stu.service;

import com.stu.entity.Teacher;
import java.util.List;

public interface TeacherService {
    List<Teacher> findAll();
    List<Teacher> findByPage(int page, int size);
    List<Teacher> findByName(String name);
    Teacher findById(Integer id);
    int count();
    void insert(Teacher teacher);
    void update(Teacher teacher);
    void delete(Integer id);
}

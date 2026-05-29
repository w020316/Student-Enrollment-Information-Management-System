package com.stu.dao;

import com.stu.entity.Teacher;
import java.util.List;

public interface TeacherDao {
    List<Teacher> findAll();
    List<Teacher> findByPage(int page, int size);
    Teacher findById(Integer id);
    int count();
    void insert(Teacher teacher);
    void update(Teacher teacher);
    void delete(Integer id);
}

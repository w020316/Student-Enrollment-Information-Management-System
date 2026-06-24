package com.stu.service;

import com.stu.dao.TeacherDao;
import com.stu.entity.Teacher;
import com.stu.factory.DaoFactory;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private TeacherDao teacherDao;

    public TeacherServiceImpl() {
        this.teacherDao = DaoFactory.getInstance().getTeacherDao();
    }

    @Override
    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }

    @Override
    public List<Teacher> findByPage(int page, int size) {
        return teacherDao.findByPage(page, size);
    }

    @Override
    public List<Teacher> findByName(String name) {
        return teacherDao.findByName(name);
    }

    @Override
    public Teacher findById(Integer id) {
        return teacherDao.findById(id);
    }

    @Override
    public int count() {
        return teacherDao.count();
    }

    @Override
    public void insert(Teacher teacher) {
        teacherDao.insert(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        teacherDao.update(teacher);
    }

    @Override
    public void delete(Integer id) {
        teacherDao.delete(id);
    }
}

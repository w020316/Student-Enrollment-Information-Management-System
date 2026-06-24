package com.stu.service;

import com.stu.dao.CourseDao;
import com.stu.entity.Course;
import com.stu.factory.DaoFactory;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;

    public CourseServiceImpl() {
        this.courseDao = DaoFactory.getInstance().getCourseDao();
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public List<Course> findByPage(int page, int size) {
        return courseDao.findByPage(page, size);
    }

    @Override
    public List<Course> findByName(String name) {
        return courseDao.findByName(name);
    }

    @Override
    public Course findById(Integer id) {
        return courseDao.findById(id);
    }

    @Override
    public int count() {
        return courseDao.count();
    }

    @Override
    public void insert(Course course) {
        courseDao.insert(course);
    }

    @Override
    public void update(Course course) {
        courseDao.update(course);
    }

    @Override
    public void delete(Integer id) {
        courseDao.delete(id);
    }
}

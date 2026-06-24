package com.stu.dao;

import com.stu.entity.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CourseMemoryDao implements CourseDao {

    private static final List<Course> data = new ArrayList<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    static {
        data.add(new Course(idGenerator.getAndIncrement(), "C001", "高等数学", 4.0, 64));
        data.add(new Course(idGenerator.getAndIncrement(), "C002", "线性代数", 3.0, 48));
        data.add(new Course(idGenerator.getAndIncrement(), "C003", "大学英语", 3.5, 56));
        data.add(new Course(idGenerator.getAndIncrement(), "C004", "Java程序设计", 4.0, 64));
        data.add(new Course(idGenerator.getAndIncrement(), "C005", "数据结构", 3.5, 56));
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public List<Course> findByPage(int page, int size) {
        int start = (page - 1) * size;
        int end = Math.min(start + size, data.size());
        if (start >= data.size()) return new ArrayList<>();
        return new ArrayList<>(data.subList(start, end));
    }

    @Override
    public List<Course> findByName(String name) {
        List<Course> result = new ArrayList<>();
        for (Course c : data) {
            if (c.getName() != null && c.getName().contains(name)) {
                result.add(c);
            }
        }
        return result;
    }

    @Override
    public Course findById(Integer id) {
        for (Course c : data) {
            if (c.getId().equals(id)) return c;
        }
        return null;
    }

    @Override
    public int count() {
        return data.size();
    }

    @Override
    public void insert(Course course) {
        course.setId(idGenerator.getAndIncrement());
        data.add(course);
    }

    @Override
    public void update(Course course) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(course.getId())) {
                data.set(i, course);
                return;
            }
        }
    }

    @Override
    public void delete(Integer id) {
        data.removeIf(c -> c.getId().equals(id));
    }
}

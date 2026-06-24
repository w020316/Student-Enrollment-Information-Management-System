package com.stu.dao;

import com.stu.entity.Teacher;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TeacherMemoryDao implements TeacherDao {

    private static final List<Teacher> data = new ArrayList<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    static {
        data.add(new Teacher(idGenerator.getAndIncrement(), "T1001", "刘老师", "男", 35, 8000.0));
        data.add(new Teacher(idGenerator.getAndIncrement(), "T1002", "陈老师", "男", 40, 9500.0));
        data.add(new Teacher(idGenerator.getAndIncrement(), "T1003", "杨老师", "女", 38, 8800.0));
        data.add(new Teacher(idGenerator.getAndIncrement(), "T1004", "黄老师", "男", 45, 10200.0));
        data.add(new Teacher(idGenerator.getAndIncrement(), "T1005", "林老师", "女", 33, 7500.0));
    }

    @Override
    public List<Teacher> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public List<Teacher> findByPage(int page, int size) {
        int start = (page - 1) * size;
        int end = Math.min(start + size, data.size());
        if (start >= data.size()) return new ArrayList<>();
        return new ArrayList<>(data.subList(start, end));
    }

    @Override
    public List<Teacher> findByName(String name) {
        List<Teacher> result = new ArrayList<>();
        for (Teacher t : data) {
            if (t.getName() != null && t.getName().contains(name)) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public Teacher findById(Integer id) {
        for (Teacher t : data) {
            if (t.getId().equals(id)) return t;
        }
        return null;
    }

    @Override
    public int count() {
        return data.size();
    }

    @Override
    public void insert(Teacher teacher) {
        teacher.setId(idGenerator.getAndIncrement());
        data.add(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(teacher.getId())) {
                data.set(i, teacher);
                return;
            }
        }
    }

    @Override
    public void delete(Integer id) {
        data.removeIf(t -> t.getId().equals(id));
    }
}

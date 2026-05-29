package com.stu.dao;

import com.stu.entity.Teacher;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TeacherMemoryDao implements TeacherDao {

    private static final List<Teacher> data = new ArrayList<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    static {
        data.add(new Teacher(idGenerator.getAndIncrement(), "刘老师", 35, 8000.0));
        data.add(new Teacher(idGenerator.getAndIncrement(), "陈老师", 40, 9500.0));
        data.add(new Teacher(idGenerator.getAndIncrement(), "杨老师", 38, 8800.0));
        data.add(new Teacher(idGenerator.getAndIncrement(), "黄老师", 45, 10200.0));
        data.add(new Teacher(idGenerator.getAndIncrement(), "林老师", 33, 7500.0));
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

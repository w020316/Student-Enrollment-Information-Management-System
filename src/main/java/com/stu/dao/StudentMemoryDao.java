package com.stu.dao;

import com.stu.entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentMemoryDao implements StudentDao {

    private static final List<Student> data = new ArrayList<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    static {
        data.add(new Student(idGenerator.getAndIncrement(), "张三", 20));
        data.add(new Student(idGenerator.getAndIncrement(), "李四", 21));
        data.add(new Student(idGenerator.getAndIncrement(), "王五", 22));
        data.add(new Student(idGenerator.getAndIncrement(), "赵六", 20));
        data.add(new Student(idGenerator.getAndIncrement(), "钱七", 23));
        data.add(new Student(idGenerator.getAndIncrement(), "孙八", 19));
        data.add(new Student(idGenerator.getAndIncrement(), "周九", 21));
        data.add(new Student(idGenerator.getAndIncrement(), "吴十", 22));
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public List<Student> findByPage(int page, int size) {
        int start = (page - 1) * size;
        int end = Math.min(start + size, data.size());
        if (start >= data.size()) return new ArrayList<>();
        return new ArrayList<>(data.subList(start, end));
    }

    @Override
    public Student findById(Integer id) {
        for (Student s : data) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    @Override
    public int count() {
        return data.size();
    }

    @Override
    public void insert(Student student) {
        student.setId(idGenerator.getAndIncrement());
        data.add(student);
    }

    @Override
    public void update(Student student) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(student.getId())) {
                data.set(i, student);
                return;
            }
        }
    }

    @Override
    public void delete(Integer id) {
        data.removeIf(s -> s.getId().equals(id));
    }
}

package com.stu.dao;

import com.stu.entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StudentMemoryDao implements StudentDao {

    private static final List<Student> data = new ArrayList<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    static {
        data.add(new Student(idGenerator.getAndIncrement(), "2024001", "张三", "男", 20, "在读"));
        data.add(new Student(idGenerator.getAndIncrement(), "2024002", "李四", "男", 21, "在读"));
        data.add(new Student(idGenerator.getAndIncrement(), "2024003", "王五", "男", 22, "休学"));
        data.add(new Student(idGenerator.getAndIncrement(), "2024004", "赵六", "男", 20, "在读"));
        data.add(new Student(idGenerator.getAndIncrement(), "2024005", "钱七", "男", 23, "毕业"));
        data.add(new Student(idGenerator.getAndIncrement(), "2024006", "孙八", "男", 19, "在读"));
        data.add(new Student(idGenerator.getAndIncrement(), "2024007", "周九", "女", 21, "退学"));
        data.add(new Student(idGenerator.getAndIncrement(), "2024008", "吴十", "女", 22, "在读"));
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
    public List<Student> findByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student s : data) {
            if (s.getName() != null && s.getName().contains(name)) {
                result.add(s);
            }
        }
        return result;
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

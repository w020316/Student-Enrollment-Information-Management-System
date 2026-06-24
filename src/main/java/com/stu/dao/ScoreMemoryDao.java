package com.stu.dao;

import com.stu.entity.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ScoreMemoryDao implements ScoreDao {

    private static final List<Score> data = new ArrayList<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    static {
        data.add(new Score(idGenerator.getAndIncrement(), 1, 1, 85.0));
        data.add(new Score(idGenerator.getAndIncrement(), 1, 4, 92.0));
        data.add(new Score(idGenerator.getAndIncrement(), 2, 1, 78.0));
        data.add(new Score(idGenerator.getAndIncrement(), 2, 2, 88.0));
        data.add(new Score(idGenerator.getAndIncrement(), 3, 3, 90.0));
        data.add(new Score(idGenerator.getAndIncrement(), 3, 5, 82.0));
        data.add(new Score(idGenerator.getAndIncrement(), 4, 4, 95.0));
        data.add(new Score(idGenerator.getAndIncrement(), 5, 1, 76.0));
        data.add(new Score(idGenerator.getAndIncrement(), 5, 5, 88.0));
        data.add(new Score(idGenerator.getAndIncrement(), 6, 2, 91.0));
        data.add(new Score(idGenerator.getAndIncrement(), 7, 3, 85.0));
        data.add(new Score(idGenerator.getAndIncrement(), 8, 4, 89.0));
    }

    @Override
    public List<Score> findAll() { return new ArrayList<>(data); }

    @Override
    public List<Score> findByPage(int page, int size) {
        int start = (page - 1) * size;
        int end = Math.min(start + size, data.size());
        if (start >= data.size()) return new ArrayList<>();
        return new ArrayList<>(data.subList(start, end));
    }

    @Override
    public List<Score> findByStudentId(Integer studentId) {
        return data.stream().filter(s -> s.getStudentId().equals(studentId)).collect(Collectors.toList());
    }

    @Override
    public List<Score> findByCourseId(Integer courseId) {
        return data.stream().filter(s -> s.getCourseId().equals(courseId)).collect(Collectors.toList());
    }

    @Override
    public Score findById(Integer id) {
        for (Score s : data) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    @Override
    public int count() { return data.size(); }

    @Override
    public void insert(Score score) {
        score.setId(idGenerator.getAndIncrement());
        data.add(score);
    }

    @Override
    public void update(Score score) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(score.getId())) {
                data.set(i, score);
                return;
            }
        }
    }

    @Override
    public void delete(Integer id) {
        data.removeIf(s -> s.getId().equals(id));
    }
}

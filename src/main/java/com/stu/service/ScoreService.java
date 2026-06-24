package com.stu.service;

import com.stu.entity.Score;
import java.util.List;

public interface ScoreService {
    List<Score> findAll();
    List<Score> findByPage(int page, int size);
    List<Score> findByStudentId(Integer studentId);
    List<Score> findByCourseId(Integer courseId);
    Score findById(Integer id);
    int count();
    void insert(Score score);
    void update(Score score);
    void delete(Integer id);
}

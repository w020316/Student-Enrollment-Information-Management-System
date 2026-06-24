package com.stu.service;

import com.stu.dao.ScoreDao;
import com.stu.entity.Score;
import com.stu.factory.DaoFactory;

import java.util.List;

public class ScoreServiceImpl implements ScoreService {

    private ScoreDao scoreDao;

    public ScoreServiceImpl() {
        this.scoreDao = DaoFactory.getInstance().getScoreDao();
    }

    @Override
    public List<Score> findAll() {
        return scoreDao.findAll();
    }

    @Override
    public List<Score> findByPage(int page, int size) {
        return scoreDao.findByPage(page, size);
    }

    @Override
    public List<Score> findByStudentId(Integer studentId) {
        return scoreDao.findByStudentId(studentId);
    }

    @Override
    public List<Score> findByCourseId(Integer courseId) {
        return scoreDao.findByCourseId(courseId);
    }

    @Override
    public Score findById(Integer id) {
        return scoreDao.findById(id);
    }

    @Override
    public int count() {
        return scoreDao.count();
    }

    @Override
    public void insert(Score score) {
        scoreDao.insert(score);
    }

    @Override
    public void update(Score score) {
        scoreDao.update(score);
    }

    @Override
    public void delete(Integer id) {
        scoreDao.delete(id);
    }
}

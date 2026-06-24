package com.stu.factory;

import com.stu.service.StudentService;
import com.stu.service.StudentServiceImpl;
import com.stu.service.TeacherService;
import com.stu.service.TeacherServiceImpl;
import com.stu.service.CourseService;
import com.stu.service.CourseServiceImpl;
import com.stu.service.ScoreService;
import com.stu.service.ScoreServiceImpl;

public class ServiceFactory {

    private static ServiceFactory instance = new ServiceFactory();

    // 缓存Service实例
    private StudentService studentService;
    private TeacherService teacherService;
    private CourseService courseService;
    private ScoreService scoreService;

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public StudentService getStudentService() {
        if (studentService == null) {
            studentService = new StudentServiceImpl();
        }
        return studentService;
    }

    public TeacherService getTeacherService() {
        if (teacherService == null) {
            teacherService = new TeacherServiceImpl();
        }
        return teacherService;
    }

    public CourseService getCourseService() {
        if (courseService == null) {
            courseService = new CourseServiceImpl();
        }
        return courseService;
    }

    public ScoreService getScoreService() {
        if (scoreService == null) {
            scoreService = new ScoreServiceImpl();
        }
        return scoreService;
    }
}

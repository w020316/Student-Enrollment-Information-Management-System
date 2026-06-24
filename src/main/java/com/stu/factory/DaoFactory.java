package com.stu.factory;

import com.stu.dao.*;

public class DaoFactory {

    private static DaoFactory instance = new DaoFactory();
    private String daoType = "memory";

    // 缓存DAO实例，避免重复创建
    private StudentDao studentDao;
    private TeacherDao teacherDao;
    private CourseDao courseDao;
    private ScoreDao scoreDao;

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return instance;
    }

    public void setDaoType(String daoType) {
        if (!this.daoType.equals(daoType)) {
            this.daoType = daoType;
            // DAO类型变更时清除缓存
            this.studentDao = null;
            this.teacherDao = null;
            this.courseDao = null;
            this.scoreDao = null;
        }
    }

    public StudentDao getStudentDao() {
        if (studentDao == null) {
            switch (daoType) {
                case "db":
                    studentDao = new StudentDBDao();
                    break;
                case "dbReflect":
                    studentDao = new StudentDBReflectDao();
                    break;
                case "remote":
                    studentDao = new StudentRemoteDao();
                    break;
                default:
                    studentDao = new StudentMemoryDao();
                    break;
            }
        }
        return studentDao;
    }

    public TeacherDao getTeacherDao() {
        if (teacherDao == null) {
            switch (daoType) {
                case "db":
                    teacherDao = new TeacherDBDao();
                    break;
                case "dbReflect":
                    teacherDao = new TeacherDBReflectDao();
                    break;
                case "remote":
                    teacherDao = new TeacherRemoteDao();
                    break;
                default:
                    teacherDao = new TeacherMemoryDao();
                    break;
            }
        }
        return teacherDao;
    }

    public CourseDao getCourseDao() {
        if (courseDao == null) {
            switch (daoType) {
                case "db":
                    courseDao = new CourseDBDao();
                    break;
                default:
                    courseDao = new CourseMemoryDao();
                    break;
            }
        }
        return courseDao;
    }

    public ScoreDao getScoreDao() {
        if (scoreDao == null) {
            switch (daoType) {
                case "db":
                    scoreDao = new ScoreDBDao();
                    break;
                default:
                    scoreDao = new ScoreMemoryDao();
                    break;
            }
        }
        return scoreDao;
    }
}

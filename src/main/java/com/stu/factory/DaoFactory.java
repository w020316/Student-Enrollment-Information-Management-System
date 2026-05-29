package com.stu.factory;

import com.stu.dao.*;

public class DaoFactory {

    private static DaoFactory instance = new DaoFactory();
    private String daoType = "memory";

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return instance;
    }

    public void setDaoType(String daoType) {
        this.daoType = daoType;
    }

    public StudentDao getStudentDao() {
        switch (daoType) {
            case "db":
                return new StudentDBDao();
            case "dbReflect":
                return new StudentDBReflectDao();
            case "remote":
                return new StudentRemoteDao();
            default:
                return new StudentMemoryDao();
        }
    }

    public TeacherDao getTeacherDao() {
        switch (daoType) {
            case "db":
                return new TeacherDBDao();
            case "dbReflect":
                return new TeacherDBReflectDao();
            case "remote":
                return new TeacherRemoteDao();
            default:
                return new TeacherMemoryDao();
        }
    }
}

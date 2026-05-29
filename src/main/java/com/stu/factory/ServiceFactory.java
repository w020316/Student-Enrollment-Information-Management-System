package com.stu.factory;

import com.stu.service.StudentService;
import com.stu.service.StudentServiceImpl;
import com.stu.service.TeacherService;
import com.stu.service.TeacherServiceImpl;

public class ServiceFactory {

    private static ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public StudentService getStudentService() {
        return new StudentServiceImpl();
    }

    public TeacherService getTeacherService() {
        return new TeacherServiceImpl();
    }
}

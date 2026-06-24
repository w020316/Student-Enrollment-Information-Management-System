package com.stu.servlet;

import com.stu.entity.Student;
import com.stu.factory.ServiceFactory;
import com.stu.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/add")
public class StudentAddServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/student/StudentAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name;
        try {
            name = getRequiredStringParam(req, "name", "姓名");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/student/StudentAdd.jsp");
            return;
        }

        String gender = getStringParam(req, "gender");
        int age = getIntParam(req, "age", 0);

        if (age < 1 || age > 150) {
            errorAndForward(req, resp, "年龄必须在1-150之间", "/student/StudentAdd.jsp");
            return;
        }

        Student student = new Student();
        student.setStudentNo(getStringParam(req, "studentNo"));
        student.setName(name);

        if (student.getStudentNo() == null || student.getStudentNo().isEmpty()) {
            errorAndForward(req, resp, "学号不能为空", "/student/StudentAdd.jsp");
            return;
        }
        student.setGender(gender.isEmpty() ? "男" : gender);
        student.setAge(age);
        student.setStatus(getStringParam(req, "status"));

        StudentService service = ServiceFactory.getInstance().getStudentService();
        service.insert(student);

        logOperation(req, "新增学生", "新增学生：" + name);
        successAndRedirect(req, resp, "学生添加成功", "/student/list");
    }
}

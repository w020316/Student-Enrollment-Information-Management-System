package com.stu.servlet;

import com.stu.entity.Student;
import com.stu.factory.ServiceFactory;
import com.stu.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/update")
public class StudentUpdateServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try {
            id = getRequiredIntParam(req, "id", "ID");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/student/StudentList.jsp");
            return;
        }

        StudentService service = ServiceFactory.getInstance().getStudentService();
        Student student = service.findById(id);
        req.setAttribute("student", student);
        req.getRequestDispatcher("/student/StudentEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try {
            id = getRequiredIntParam(req, "id", "ID");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/student/StudentEdit.jsp");
            return;
        }

        String name;
        try {
            name = getRequiredStringParam(req, "name", "姓名");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/student/StudentEdit.jsp");
            return;
        }

        String gender = getStringParam(req, "gender");
        int age = getIntParam(req, "age", 0);

        if (age < 1 || age > 150) {
            errorAndForward(req, resp, "年龄必须在1-150之间", "/student/StudentEdit.jsp");
            return;
        }

        Student student = new Student();
        student.setId(id);
        student.setStudentNo(getStringParam(req, "studentNo"));
        student.setName(name);
        student.setGender(gender.isEmpty() ? "男" : gender);
        student.setAge(age);
        student.setStatus(getStringParam(req, "status"));

        StudentService service = ServiceFactory.getInstance().getStudentService();
        service.update(student);

        logOperation(req, "修改学生", "修改学生ID：" + id);
        successAndRedirect(req, resp, "学生信息更新成功", "/student/list");
    }
}

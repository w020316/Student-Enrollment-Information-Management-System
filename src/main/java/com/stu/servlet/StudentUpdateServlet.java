package com.stu.servlet;

import com.stu.entity.Student;
import com.stu.factory.ServiceFactory;
import com.stu.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/update")
public class StudentUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);

        StudentService service = ServiceFactory.getInstance().getStudentService();
        Student student = service.findById(id);
        req.setAttribute("student", student);
        req.getRequestDispatcher("/student/StudentEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String ageStr = req.getParameter("age");

        Student student = new Student();
        student.setId(Integer.parseInt(idStr));
        student.setName(name);
        try { student.setAge(Integer.parseInt(ageStr)); } catch (Exception ignored) {}

        StudentService service = ServiceFactory.getInstance().getStudentService();
        service.update(student);

        resp.sendRedirect(req.getContextPath() + "/student/list");
    }
}

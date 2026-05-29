package com.stu.servlet;

import com.stu.entity.Teacher;
import com.stu.factory.ServiceFactory;
import com.stu.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacher/add")
public class TeacherAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/teacher/TeacherAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String ageStr = req.getParameter("age");
        String salaryStr = req.getParameter("salary");

        Teacher teacher = new Teacher();
        teacher.setName(name);
        try { teacher.setAge(Integer.parseInt(ageStr)); } catch (Exception ignored) {}
        try { teacher.setSalary(Double.parseDouble(salaryStr)); } catch (Exception ignored) {}

        TeacherService service = ServiceFactory.getInstance().getTeacherService();
        service.insert(teacher);

        resp.sendRedirect(req.getContextPath() + "/teacher/list");
    }
}

package com.stu.servlet;

import com.stu.entity.Student;
import com.stu.factory.ServiceFactory;
import com.stu.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/view")
public class StudentViewServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try {
            id = getRequiredIntParam(req, "id", "ID");
        } catch (ServletException e) {
            resp.sendRedirect(req.getContextPath() + "/student/list");
            return;
        }

        StudentService service = ServiceFactory.getInstance().getStudentService();
        Student student = service.findById(id);
        req.setAttribute("student", student);
        req.getRequestDispatcher("/student/StudentView.jsp").forward(req, resp);
    }
}

package com.stu.servlet;

import com.stu.factory.ServiceFactory;
import com.stu.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/delete")
public class StudentDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);

        StudentService service = ServiceFactory.getInstance().getStudentService();
        service.delete(id);

        resp.sendRedirect(req.getContextPath() + "/student/list");
    }
}

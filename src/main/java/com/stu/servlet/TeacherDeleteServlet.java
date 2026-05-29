package com.stu.servlet;

import com.stu.factory.ServiceFactory;
import com.stu.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacher/delete")
public class TeacherDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);

        TeacherService service = ServiceFactory.getInstance().getTeacherService();
        service.delete(id);

        resp.sendRedirect(req.getContextPath() + "/teacher/list");
    }
}

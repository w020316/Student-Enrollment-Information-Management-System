package com.stu.servlet;

import com.stu.entity.Teacher;
import com.stu.factory.ServiceFactory;
import com.stu.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacher/view")
public class TeacherViewServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try {
            id = getRequiredIntParam(req, "id", "教师ID");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/teacher/TeacherList.jsp");
            return;
        }

        TeacherService service = ServiceFactory.getInstance().getTeacherService();
        Teacher teacher = service.findById(id);
        req.setAttribute("teacher", teacher);
        req.getRequestDispatcher("/teacher/TeacherView.jsp").forward(req, resp);
    }
}

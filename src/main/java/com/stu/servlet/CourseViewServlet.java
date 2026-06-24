package com.stu.servlet;

import com.stu.entity.Course;
import com.stu.factory.ServiceFactory;
import com.stu.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/course/view")
public class CourseViewServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try {
            id = getRequiredIntParam(req, "id", "课程ID");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/course/CourseList.jsp");
            return;
        }

        CourseService service = ServiceFactory.getInstance().getCourseService();
        Course course = service.findById(id);
        req.setAttribute("course", course);
        req.getRequestDispatcher("/course/CourseView.jsp").forward(req, resp);
    }
}

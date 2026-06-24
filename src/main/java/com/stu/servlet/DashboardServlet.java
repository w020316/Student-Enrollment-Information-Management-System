package com.stu.servlet;

import com.stu.factory.ServiceFactory;
import com.stu.service.StudentService;
import com.stu.service.TeacherService;
import com.stu.service.CourseService;
import com.stu.service.ScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentService studentService = ServiceFactory.getInstance().getStudentService();
        TeacherService teacherService = ServiceFactory.getInstance().getTeacherService();
        CourseService courseService = ServiceFactory.getInstance().getCourseService();
        ScoreService scoreService = ServiceFactory.getInstance().getScoreService();

        int studentCount = studentService.count();
        int teacherCount = teacherService.count();
        int courseCount = courseService.count();
        int scoreCount = scoreService.count();

        req.setAttribute("studentCount", studentCount);
        req.setAttribute("teacherCount", teacherCount);
        req.setAttribute("courseCount", courseCount);
        req.setAttribute("scoreCount", scoreCount);

        req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }
}

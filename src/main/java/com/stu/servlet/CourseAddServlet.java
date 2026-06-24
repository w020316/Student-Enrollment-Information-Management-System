package com.stu.servlet;

import com.stu.entity.Course;
import com.stu.factory.ServiceFactory;
import com.stu.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/course/add")
public class CourseAddServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/course/CourseAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name;
        try {
            name = getRequiredStringParam(req, "name", "课程名称");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/course/CourseAdd.jsp");
            return;
        }

        String creditStr = getStringParam(req, "credit");
        double credit = 0;
        try {
            credit = Double.parseDouble(creditStr);
        } catch (NumberFormatException e) {
            errorAndForward(req, resp, "学分必须是数字", "/course/CourseAdd.jsp");
            return;
        }
        if (credit < 0 || credit > 10) {
            errorAndForward(req, resp, "学分必须在0-10之间", "/course/CourseAdd.jsp");
            return;
        }

        int hours;
        try {
            hours = getRequiredIntParam(req, "hours", "学时");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/course/CourseAdd.jsp");
            return;
        }
        if (hours < 0) {
            errorAndForward(req, resp, "学时不能为负数", "/course/CourseAdd.jsp");
            return;
        }

        Course course = new Course();
        course.setCourseNo(getStringParam(req, "courseNo"));
        if (course.getCourseNo() == null || course.getCourseNo().isEmpty()) {
            errorAndForward(req, resp, "课程编号不能为空", "/course/CourseAdd.jsp");
            return;
        }
        course.setName(name);
        course.setCredit(credit);
        course.setHours(hours);

        CourseService service = ServiceFactory.getInstance().getCourseService();
        service.insert(course);

        logOperation(req, "新增课程", "新增课程：" + course.getName());
        successAndRedirect(req, resp, "课程添加成功", "/course/list");
    }
}

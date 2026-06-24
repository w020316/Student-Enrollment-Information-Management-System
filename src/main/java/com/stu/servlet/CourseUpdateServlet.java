package com.stu.servlet;

import com.stu.entity.Course;
import com.stu.factory.ServiceFactory;
import com.stu.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/course/update")
public class CourseUpdateServlet extends BaseServlet {

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
        req.getRequestDispatcher("/course/CourseEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try {
            id = getRequiredIntParam(req, "id", "课程ID");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/course/CourseList.jsp");
            return;
        }

        String name;
        try {
            name = getRequiredStringParam(req, "name", "课程名称");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/course/CourseEdit.jsp");
            return;
        }

        String creditStr = getStringParam(req, "credit");
        double credit = 0;
        try {
            credit = Double.parseDouble(creditStr);
        } catch (NumberFormatException e) {
            errorAndForward(req, resp, "学分必须是数字", "/course/CourseEdit.jsp");
            return;
        }
        if (credit < 0 || credit > 10) {
            errorAndForward(req, resp, "学分必须在0-10之间", "/course/CourseEdit.jsp");
            return;
        }

        int hours;
        try {
            hours = getRequiredIntParam(req, "hours", "学时");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/course/CourseEdit.jsp");
            return;
        }
        if (hours < 0) {
            errorAndForward(req, resp, "学时不能为负数", "/course/CourseEdit.jsp");
            return;
        }

        Course course = new Course();
        course.setId(id);
        course.setCourseNo(getStringParam(req, "courseNo"));
        if (course.getCourseNo() == null || course.getCourseNo().isEmpty()) {
            errorAndForward(req, resp, "课程编号不能为空", "/course/CourseEdit.jsp");
            return;
        }
        course.setName(name);
        course.setCredit(credit);
        course.setHours(hours);

        CourseService service = ServiceFactory.getInstance().getCourseService();
        service.update(course);

        logOperation(req, "修改课程", "修改课程ID：" + course.getId());
        successAndRedirect(req, resp, "课程信息更新成功", "/course/list");
    }
}

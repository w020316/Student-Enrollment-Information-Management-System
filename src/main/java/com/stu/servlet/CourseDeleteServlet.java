package com.stu.servlet;

import com.stu.factory.ServiceFactory;
import com.stu.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/course/delete")
public class CourseDeleteServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = getRequiredIntParam(req, "id", "课程ID");
            CourseService service = ServiceFactory.getInstance().getCourseService();
            service.delete(id);
            logOperation(req, "删除课程", "删除课程ID：" + id);
            successAndRedirect(req, resp, "课程删除成功", "/course/list");
        } catch (ServletException e) {
            successAndRedirect(req, resp, "删除失败：" + e.getMessage(), "/course/list");
        } catch (Exception e) {
            successAndRedirect(req, resp, "删除失败：" + e.getMessage(), "/course/list");
        }
    }
}

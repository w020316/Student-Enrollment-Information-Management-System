package com.stu.servlet;

import com.stu.factory.ServiceFactory;
import com.stu.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacher/delete")
public class TeacherDeleteServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = getRequiredIntParam(req, "id", "教师ID");
            TeacherService service = ServiceFactory.getInstance().getTeacherService();
            service.delete(id);
            logOperation(req, "删除教师", "删除教师ID：" + id);
            successAndRedirect(req, resp, "教师删除成功", "/teacher/list");
        } catch (ServletException e) {
            successAndRedirect(req, resp, "删除失败：" + e.getMessage(), "/teacher/list");
        } catch (Exception e) {
            successAndRedirect(req, resp, "删除失败：" + e.getMessage(), "/teacher/list");
        }
    }
}

package com.stu.servlet;

import com.stu.factory.ServiceFactory;
import com.stu.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/delete")
public class StudentDeleteServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try {
            id = getRequiredIntParam(req, "id", "ID");
        } catch (ServletException e) {
            req.getSession().setAttribute("message", "删除失败：" + e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/student/list");
            return;
        }

        try {
            StudentService service = ServiceFactory.getInstance().getStudentService();
            service.delete(id);
            logOperation(req, "删除学生", "删除学生ID：" + id);
            successAndRedirect(req, resp, "学生删除成功", "/student/list");
        } catch (Exception e) {
            req.getSession().setAttribute("message", "删除失败：" + e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/student/list");
        }
    }
}

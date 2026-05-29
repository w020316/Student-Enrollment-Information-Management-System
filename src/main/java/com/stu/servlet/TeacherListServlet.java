package com.stu.servlet;

import com.stu.entity.Teacher;
import com.stu.factory.ServiceFactory;
import com.stu.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/teacher/list")
public class TeacherListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        String sizeStr = req.getParameter("size");
        int page = 1;
        int size = 5;
        try { page = Integer.parseInt(pageStr); } catch (Exception ignored) {}
        try { size = Integer.parseInt(sizeStr); } catch (Exception ignored) {}
        if (page < 1) page = 1;

        TeacherService service = ServiceFactory.getInstance().getTeacherService();
        List<Teacher> teachers = service.findByPage(page, size);
        int totalCount = service.count();
        int totalPages = (int) Math.ceil((double) totalCount / size);
        if (totalPages < 1) totalPages = 1;

        req.setAttribute("teachers", teachers);
        req.setAttribute("currentPage", page);
        req.setAttribute("pageSize", size);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/teacher/TeacherList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

package com.stu.servlet;

import com.stu.entity.Student;
import com.stu.factory.ServiceFactory;
import com.stu.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student/list")
public class StudentListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        String sizeStr = req.getParameter("size");
        int page = 1;
        int size = 5;
        try { page = Integer.parseInt(pageStr); } catch (Exception ignored) {}
        try { size = Integer.parseInt(sizeStr); } catch (Exception ignored) {}
        if (page < 1) page = 1;

        StudentService service = ServiceFactory.getInstance().getStudentService();
        List<Student> students = service.findByPage(page, size);
        int totalCount = service.count();
        int totalPages = (int) Math.ceil((double) totalCount / size);
        if (totalPages < 1) totalPages = 1;

        req.setAttribute("students", students);
        req.setAttribute("currentPage", page);
        req.setAttribute("pageSize", size);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/student/StudentList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

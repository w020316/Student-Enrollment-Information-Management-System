package com.stu.servlet;

import com.stu.entity.Student;
import com.stu.factory.ServiceFactory;
import com.stu.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/student/list")
public class StudentListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = getIntParam(req, "page", 1);
        int size = getIntParam(req, "size", 5);
        String keyword = getStringParam(req, "name");
        page = normalizePage(page);

        StudentService service = ServiceFactory.getInstance().getStudentService();
        List<Student> students;
        int totalCount;

        if (!keyword.isEmpty()) {
            students = service.findByName(keyword);
            totalCount = students.size();
            // 对搜索结果进行内存分页
            int start = (page - 1) * size;
            int end = Math.min(start + size, students.size());
            if (start < students.size()) {
                students = students.subList(start, end);
            } else {
                students = new ArrayList<>();
            }
        } else {
            students = service.findByPage(page, size);
            totalCount = service.count();
        }

        int totalPages = getTotalPages(totalCount, size);

        req.setAttribute("students", students);
        req.setAttribute("currentPage", page);
        req.setAttribute("pageSize", size);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("keyword", keyword);

        req.getRequestDispatcher("/student/StudentList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

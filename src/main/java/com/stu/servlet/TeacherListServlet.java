package com.stu.servlet;

import com.stu.entity.Teacher;
import com.stu.factory.ServiceFactory;
import com.stu.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/teacher/list")
public class TeacherListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = normalizePage(getIntParam(req, "page", 1));
        int size = getIntParam(req, "size", 5);
        String keyword = getStringParam(req, "name");

        TeacherService service = ServiceFactory.getInstance().getTeacherService();
        List<Teacher> teachers;
        int totalCount;

        if (!keyword.isEmpty()) {
            teachers = service.findByName(keyword);
            totalCount = teachers.size();
            int start = (page - 1) * size;
            int end = Math.min(start + size, teachers.size());
            if (start < teachers.size()) {
                teachers = teachers.subList(start, end);
            } else {
                teachers = new ArrayList<>();
            }
        } else {
            teachers = service.findByPage(page, size);
            totalCount = service.count();
            keyword = "";
        }

        int totalPages = getTotalPages(totalCount, size);

        req.setAttribute("teachers", teachers);
        req.setAttribute("currentPage", page);
        req.setAttribute("pageSize", size);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("keyword", keyword);

        req.getRequestDispatcher("/teacher/TeacherList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

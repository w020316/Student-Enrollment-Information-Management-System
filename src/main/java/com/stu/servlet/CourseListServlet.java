package com.stu.servlet;

import com.stu.entity.Course;
import com.stu.factory.ServiceFactory;
import com.stu.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/course/list")
public class CourseListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = normalizePage(getIntParam(req, "page", 1));
        int size = getIntParam(req, "size", 5);
        String keyword = getStringParam(req, "name");

        CourseService service = ServiceFactory.getInstance().getCourseService();
        List<Course> courses;
        int totalCount;

        if (!keyword.isEmpty()) {
            courses = service.findByName(keyword);
            totalCount = courses.size();
            int start = (page - 1) * size;
            int end = Math.min(start + size, courses.size());
            if (start < courses.size()) {
                courses = courses.subList(start, end);
            } else {
                courses = new ArrayList<>();
            }
        } else {
            courses = service.findByPage(page, size);
            totalCount = service.count();
            keyword = "";
        }

        int totalPages = getTotalPages(totalCount, size);

        req.setAttribute("courses", courses);
        req.setAttribute("currentPage", page);
        req.setAttribute("pageSize", size);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("keyword", keyword);

        req.getRequestDispatcher("/course/CourseList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

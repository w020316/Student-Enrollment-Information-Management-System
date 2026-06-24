package com.stu.servlet;

import com.stu.dao.StudentDao;
import com.stu.dao.TeacherDao;
import com.stu.dao.CourseDao;
import com.stu.entity.EntityInfo;
import com.stu.entity.Student;
import com.stu.entity.Teacher;
import com.stu.entity.Course;
import com.stu.factory.DaoFactory;
import com.stu.util.EntityInfoHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/generic/list")
public class GenericListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String entity = req.getParameter("entity");
        String pageStr = req.getParameter("page");
        String sizeStr = req.getParameter("size");
        int page = 1;
        int size = 5;
        try { page = Integer.parseInt(pageStr); } catch (Exception ignored) {}
        try { size = Integer.parseInt(sizeStr); } catch (Exception ignored) {}
        if (page < 1) page = 1;

        Class<?> clazz;
        List<?> dataList;
        int totalCount;

        if ("teacher".equals(entity)) {
            clazz = Teacher.class;
            TeacherDao dao = DaoFactory.getInstance().getTeacherDao();
            dataList = dao.findByPage(page, size);
            totalCount = dao.count();
        } else if ("course".equals(entity)) {
            clazz = Course.class;
            CourseDao dao = DaoFactory.getInstance().getCourseDao();
            dataList = dao.findByPage(page, size);
            totalCount = dao.count();
        } else {
            clazz = Student.class;
            StudentDao dao = DaoFactory.getInstance().getStudentDao();
            dataList = dao.findByPage(page, size);
            totalCount = dao.count();
        }

        int totalPages = (int) Math.ceil((double) totalCount / size);
        if (totalPages < 1) totalPages = 1;

        EntityInfo entityInfo = EntityInfoHelper.getEntityInfo(clazz);

        req.setAttribute("entityInfo", entityInfo);
        req.setAttribute("dataList", dataList);
        req.setAttribute("entityName", entity);
        req.setAttribute("currentPage", page);
        req.setAttribute("pageSize", size);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/generic/GenericList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

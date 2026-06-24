package com.stu.servlet;

import com.stu.entity.Teacher;
import com.stu.factory.ServiceFactory;
import com.stu.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacher/update")
public class TeacherUpdateServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try {
            id = getRequiredIntParam(req, "id", "教师ID");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/teacher/TeacherList.jsp");
            return;
        }

        TeacherService service = ServiceFactory.getInstance().getTeacherService();
        Teacher teacher = service.findById(id);
        req.setAttribute("teacher", teacher);
        req.getRequestDispatcher("/teacher/TeacherEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try {
            id = getRequiredIntParam(req, "id", "教师ID");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/teacher/TeacherList.jsp");
            return;
        }

        String name;
        try {
            name = getRequiredStringParam(req, "name", "姓名");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/teacher/TeacherEdit.jsp");
            return;
        }

        int age;
        try {
            age = getRequiredIntParam(req, "age", "年龄");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/teacher/TeacherEdit.jsp");
            return;
        }
        if (age < 1 || age > 150) {
            errorAndForward(req, resp, "年龄必须在1-150之间", "/teacher/TeacherEdit.jsp");
            return;
        }

        String salaryStr = getStringParam(req, "salary");
        double salary = 0;
        try {
            salary = Double.parseDouble(salaryStr);
        } catch (NumberFormatException e) {
            errorAndForward(req, resp, "薪资必须是数字", "/teacher/TeacherEdit.jsp");
            return;
        }
        if (salary < 0) {
            errorAndForward(req, resp, "薪资不能为负数", "/teacher/TeacherEdit.jsp");
            return;
        }

        String gender = getStringParam(req, "gender");

        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setTeacherNo(getStringParam(req, "teacherNo"));
        teacher.setName(name);

        if (teacher.getTeacherNo() == null || teacher.getTeacherNo().isEmpty()) {
            errorAndForward(req, resp, "工号不能为空", "/teacher/TeacherEdit.jsp");
            return;
        }

        teacher.setGender(gender.isEmpty() ? "男" : gender);
        teacher.setAge(age);
        teacher.setSalary(salary);

        TeacherService service = ServiceFactory.getInstance().getTeacherService();
        service.update(teacher);

        logOperation(req, "修改教师", "修改教师ID：" + teacher.getId());
        successAndRedirect(req, resp, "教师信息更新成功", "/teacher/list");
    }
}

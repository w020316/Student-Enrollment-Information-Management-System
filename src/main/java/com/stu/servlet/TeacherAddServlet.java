package com.stu.servlet;

import com.stu.entity.Teacher;
import com.stu.factory.ServiceFactory;
import com.stu.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacher/add")
public class TeacherAddServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/teacher/TeacherAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name;
        try {
            name = getRequiredStringParam(req, "name", "姓名");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/teacher/TeacherAdd.jsp");
            return;
        }

        int age;
        try {
            age = getRequiredIntParam(req, "age", "年龄");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/teacher/TeacherAdd.jsp");
            return;
        }
        if (age < 1 || age > 150) {
            errorAndForward(req, resp, "年龄必须在1-150之间", "/teacher/TeacherAdd.jsp");
            return;
        }

        String salaryStr = getStringParam(req, "salary");
        double salary = 0;
        try {
            salary = Double.parseDouble(salaryStr);
        } catch (NumberFormatException e) {
            errorAndForward(req, resp, "薪资必须是数字", "/teacher/TeacherAdd.jsp");
            return;
        }
        if (salary < 0) {
            errorAndForward(req, resp, "薪资不能为负数", "/teacher/TeacherAdd.jsp");
            return;
        }

        String gender = getStringParam(req, "gender");

        Teacher teacher = new Teacher();
        teacher.setTeacherNo(getStringParam(req, "teacherNo"));
        teacher.setName(name);

        if (teacher.getTeacherNo() == null || teacher.getTeacherNo().isEmpty()) {
            errorAndForward(req, resp, "工号不能为空", "/teacher/TeacherAdd.jsp");
            return;
        }

        teacher.setGender(gender.isEmpty() ? "男" : gender);
        teacher.setAge(age);
        teacher.setSalary(salary);

        TeacherService service = ServiceFactory.getInstance().getTeacherService();
        service.insert(teacher);

        logOperation(req, "新增教师", "新增教师：" + teacher.getName());
        successAndRedirect(req, resp, "教师添加成功", "/teacher/list");
    }
}

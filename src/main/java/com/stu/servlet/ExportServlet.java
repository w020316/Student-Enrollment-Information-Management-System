package com.stu.servlet;

import com.stu.entity.Student;
import com.stu.entity.Teacher;
import com.stu.entity.Course;
import com.stu.entity.Score;
import com.stu.factory.ServiceFactory;
import com.stu.service.StudentService;
import com.stu.service.TeacherService;
import com.stu.service.CourseService;
import com.stu.service.ScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/export")
public class ExportServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = getStringParam(req, "type");
        if (type.isEmpty()) {
            resp.sendError(400, "缺少导出类型参数");
            return;
        }

        switch (type) {
            case "student":
                exportStudents(resp);
                break;
            case "teacher":
                exportTeachers(resp);
                break;
            case "course":
                exportCourses(resp);
                break;
            case "score":
                exportScores(resp);
                break;
            default:
                resp.sendError(400, "不支持的导出类型：" + type);
        }
    }

    private void exportStudents(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/csv;charset=UTF-8");
        resp.setHeader("Content-Disposition", "attachment;filename=students.csv");

        StudentService service = ServiceFactory.getInstance().getStudentService();
        List<Student> list = service.findAll();

        PrintWriter out = resp.getWriter();
        out.println("\uFEFF");
        out.println("编号,学号,姓名,性别,年龄,在籍信息");
        for (Student s : list) {
            out.println(s.getId() + "," +
                    csvSafe(s.getStudentNo()) + "," +
                    csvSafe(s.getName()) + "," +
                    csvSafe(s.getGender()) + "," +
                    s.getAge() + "," +
                    csvSafe(s.getStatus()));
        }
        out.flush();
    }

    private void exportTeachers(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/csv;charset=UTF-8");
        resp.setHeader("Content-Disposition", "attachment;filename=teachers.csv");

        TeacherService service = ServiceFactory.getInstance().getTeacherService();
        List<Teacher> list = service.findAll();

        PrintWriter out = resp.getWriter();
        out.println("\uFEFF");
        out.println("编号,工号,姓名,性别,年龄,薪资");
        for (Teacher t : list) {
            out.println(t.getId() + "," +
                    csvSafe(t.getTeacherNo()) + "," +
                    csvSafe(t.getName()) + "," +
                    csvSafe(t.getGender()) + "," +
                    t.getAge() + "," +
                    t.getSalary());
        }
        out.flush();
    }

    private void exportCourses(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/csv;charset=UTF-8");
        resp.setHeader("Content-Disposition", "attachment;filename=courses.csv");

        CourseService service = ServiceFactory.getInstance().getCourseService();
        List<Course> list = service.findAll();

        PrintWriter out = resp.getWriter();
        out.println("\uFEFF");
        out.println("编号,课程编号,课程名称,学分,学时");
        for (Course c : list) {
            out.println(c.getId() + "," +
                    csvSafe(c.getCourseNo()) + "," +
                    csvSafe(c.getName()) + "," +
                    c.getCredit() + "," +
                    c.getHours());
        }
        out.flush();
    }

    private void exportScores(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/csv;charset=UTF-8");
        resp.setHeader("Content-Disposition", "attachment;filename=scores.csv");

        ScoreService service = ServiceFactory.getInstance().getScoreService();
        List<Score> list = service.findAll();

        PrintWriter out = resp.getWriter();
        out.println("\uFEFF");
        out.println("编号,学生姓名,课程名称,成绩");
        for (Score s : list) {
            out.println(s.getId() + "," +
                    csvSafe(s.getStudentName()) + "," +
                    csvSafe(s.getCourseName()) + "," +
                    s.getScore());
        }
        out.flush();
    }

    private String csvSafe(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}

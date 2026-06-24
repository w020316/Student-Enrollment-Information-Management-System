package com.stu.servlet;

import com.stu.entity.Score;
import com.stu.factory.ServiceFactory;
import com.stu.service.ScoreService;
import com.stu.service.StudentService;
import com.stu.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/score/update")
public class ScoreUpdateServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try {
            id = getRequiredIntParam(req, "id", "成绩ID");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/score/ScoreList.jsp");
            return;
        }

        ScoreService service = ServiceFactory.getInstance().getScoreService();
        Score score = service.findById(id);
        req.setAttribute("score", score);

        StudentService studentService = ServiceFactory.getInstance().getStudentService();
        CourseService courseService = ServiceFactory.getInstance().getCourseService();
        req.setAttribute("students", studentService.findAll());
        req.setAttribute("courses", courseService.findAll());

        req.getRequestDispatcher("/score/ScoreEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        try {
            id = getRequiredIntParam(req, "id", "成绩ID");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/score/ScoreEdit.jsp");
            return;
        }

        int studentId;
        try {
            studentId = getRequiredIntParam(req, "studentId", "学生");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/score/ScoreEdit.jsp");
            return;
        }

        int courseId;
        try {
            courseId = getRequiredIntParam(req, "courseId", "课程");
        } catch (ServletException e) {
            errorAndForward(req, resp, e.getMessage(), "/score/ScoreEdit.jsp");
            return;
        }

        String scoreStr = getStringParam(req, "score");
        double scoreVal = 0;
        try {
            scoreVal = Double.parseDouble(scoreStr);
        } catch (NumberFormatException e) {
            errorAndForward(req, resp, "成绩必须是数字", "/score/ScoreEdit.jsp");
            return;
        }
        if (scoreVal < 0 || scoreVal > 100) {
            errorAndForward(req, resp, "成绩必须在0-100之间", "/score/ScoreEdit.jsp");
            return;
        }

        Score score = new Score();
        score.setId(id);
        score.setStudentId(studentId);
        score.setCourseId(courseId);
        score.setScore(scoreVal);

        ScoreService service = ServiceFactory.getInstance().getScoreService();
        service.update(score);

        logOperation(req, "修改成绩", "修改成绩ID：" + id);
        successAndRedirect(req, resp, "成绩信息更新成功", "/score/list");
    }
}

package com.stu.servlet;

import com.stu.entity.Score;
import com.stu.factory.ServiceFactory;
import com.stu.service.ScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/score/list")
public class ScoreListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = normalizePage(getIntParam(req, "page", 1));
        int size = getIntParam(req, "size", 5);
        if (size != 5 && size != 10 && size != 20) size = 5;

        ScoreService service = ServiceFactory.getInstance().getScoreService();

        Integer studentId = null;
        String studentIdStr = req.getParameter("studentId");
        if (studentIdStr != null && !studentIdStr.trim().isEmpty()) {
            try { studentId = Integer.parseInt(studentIdStr.trim()); } catch (NumberFormatException e) { studentId = null; }
        }

        List<Score> scores;
        int totalCount;
        if (studentId != null) {
            scores = service.findByStudentId(studentId);
            totalCount = scores.size();
            int start = (page - 1) * size;
            int end = Math.min(start + size, scores.size());
            if (start < scores.size()) {
                scores = scores.subList(start, end);
            } else {
                scores = new java.util.ArrayList<>();
            }
        } else {
            scores = service.findByPage(page, size);
            totalCount = service.count();
        }

        int totalPages = getTotalPages(totalCount, size);

        req.setAttribute("scores", scores);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("pageSize", size);
        req.setAttribute("studentId", studentIdStr);

        req.getRequestDispatcher("/score/ScoreList.jsp").forward(req, resp);
    }
}

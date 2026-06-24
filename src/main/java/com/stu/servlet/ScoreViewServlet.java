package com.stu.servlet;

import com.stu.entity.Score;
import com.stu.factory.ServiceFactory;
import com.stu.service.ScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/score/view")
public class ScoreViewServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = getIntParam(req, "id", 0);
        ScoreService service = ServiceFactory.getInstance().getScoreService();
        Score score = service.findById(id);
        req.setAttribute("score", score);
        req.getRequestDispatcher("/score/ScoreView.jsp").forward(req, resp);
    }
}

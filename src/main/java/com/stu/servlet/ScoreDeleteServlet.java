package com.stu.servlet;

import com.stu.factory.ServiceFactory;
import com.stu.service.ScoreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/score/delete")
public class ScoreDeleteServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = getIntParam(req, "id", 0);
        if (id <= 0) {
            req.getSession().setAttribute("message", "参数错误");
            resp.sendRedirect(req.getContextPath() + "/score/list");
            return;
        }

        ScoreService service = ServiceFactory.getInstance().getScoreService();
        service.delete(id);

        logOperation(req, "删除成绩", "删除成绩ID：" + id);
        successAndRedirect(req, resp, "成绩删除成功", "/score/list");
    }
}

package com.stu.servlet;

import com.stu.util.OperationLog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet基类
 * 提供通用的参数解析、操作日志、安全校验等方法
 */
public abstract class BaseServlet extends HttpServlet {

    /**
     * 安全获取整数参数，解析失败返回默认值
     */
    protected int getIntParam(HttpServletRequest req, String name, int defaultValue) {
        String value = req.getParameter(name);
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 安全获取必填整数参数，解析失败设置错误信息并返回null
     */
    protected Integer getRequiredIntParam(HttpServletRequest req, String name, String label) throws ServletException {
        String value = req.getParameter(name);
        if (value == null || value.trim().isEmpty()) {
            throw new ServletException(label + "不能为空");
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new ServletException(label + "格式不正确");
        }
    }

    /**
     * 安全获取字符串参数，去除首尾空格
     */
    protected String getStringParam(HttpServletRequest req, String name) {
        String value = req.getParameter(name);
        return value != null ? value.trim() : "";
    }

    /**
     * 安全获取必填字符串参数
     */
    protected String getRequiredStringParam(HttpServletRequest req, String name, String label) throws ServletException {
        String value = req.getParameter(name);
        if (value == null || value.trim().isEmpty()) {
            throw new ServletException(label + "不能为空");
        }
        return value.trim();
    }

    /**
     * 记录操作日志
     */
    protected void logOperation(HttpServletRequest req, String action, String detail) {
        OperationLog.log(req.getSession(), action, detail);
    }

    /**
     * 设置成功消息并重定向
     */
    protected void successAndRedirect(HttpServletRequest req, HttpServletResponse resp, String message, String url) throws IOException {
        req.getSession().setAttribute("message", message);
        resp.sendRedirect(req.getContextPath() + url);
    }

    /**
     * 设置错误消息并转发
     */
    protected void errorAndForward(HttpServletRequest req, HttpServletResponse resp, String error, String jspPath) throws ServletException, IOException {
        req.setAttribute("error", error);
        req.getRequestDispatcher(jspPath).forward(req, resp);
    }

    /**
     * 计算总页数
     */
    protected int getTotalPages(int totalCount, int pageSize) {
        return Math.max(1, (int) Math.ceil((double) totalCount / pageSize));
    }

    /**
     * 规范化页码
     */
    protected int normalizePage(int page) {
        return Math.max(1, page);
    }
}

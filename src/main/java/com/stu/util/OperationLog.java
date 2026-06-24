package com.stu.util;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 操作日志工具类
 * 记录用户在系统中的关键操作，日志存储在Session中
 */
public class OperationLog {

    private static final int MAX_LOG_SIZE = 50;

    private String time;
    String user;
    private String action;
    private String detail;

    public OperationLog(String time, String user, String action, String detail) {
        this.time = time;
        this.user = user;
        this.action = action;
        this.detail = detail;
    }

    public String getTime() { return time; }
    public String getUser() { return user; }
    public String getAction() { return action; }
    public String getDetail() { return detail; }

    /**
     * 记录操作日志到Session
     */
    public static void log(HttpSession session, String action, String detail) {
        if (session == null) return;

        @SuppressWarnings("unchecked")
        List<OperationLog> logs = (List<OperationLog>) session.getAttribute("operationLogs");
        if (logs == null) {
            logs = Collections.synchronizedList(new ArrayList<OperationLog>());
            session.setAttribute("operationLogs", logs);
        }

        String user = (String) session.getAttribute("user");
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        logs.add(0, new OperationLog(time, user != null ? user : "系统", action, detail));

        // 保持日志数量上限
        while (logs.size() > MAX_LOG_SIZE) {
            logs.remove(logs.size() - 1);
        }
    }

    /**
     * 获取操作日志列表
     */
    @SuppressWarnings("unchecked")
    public static List<OperationLog> getLogs(HttpSession session) {
        if (session == null) return new ArrayList<OperationLog>();
        List<OperationLog> logs = (List<OperationLog>) session.getAttribute("operationLogs");
        return logs != null ? new ArrayList<OperationLog>(logs) : new ArrayList<OperationLog>();
    }
}

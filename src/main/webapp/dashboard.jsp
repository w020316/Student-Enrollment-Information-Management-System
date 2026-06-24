<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.stu.util.OperationLog" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>系统首页 - 清雅学苑</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Serif+SC:wght@400;600;700&family=Noto+Sans+SC:wght@300;400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="navbar">
    <h1>学籍管理</h1>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/dashboard" class="active">首页</a>
        <a href="${pageContext.request.contextPath}/student/list">学生管理</a>
        <a href="${pageContext.request.contextPath}/teacher/list">教师管理</a>
        <a href="${pageContext.request.contextPath}/course/list">课程管理</a>
        <a href="${pageContext.request.contextPath}/score/list">成绩管理</a>
        <a href="${pageContext.request.contextPath}/logout">退出</a>
    </div>
</div>

<div class="container">
    <div class="breadcrumb">
        <a href="${pageContext.request.contextPath}/dashboard">首页</a>
        <span class="separator">&#9656;</span>
        <span class="current">仪表盘</span>
    </div>

    <div class="welcome-section">
        <h2>欢迎使用学籍管理系统</h2>
        <p>清雅学苑 · 高效管理 · 数据驱动</p>
    </div>

    <div class="dashboard-cards">
        <div class="dash-card" onclick="location.href='${pageContext.request.contextPath}/student/list'">
            <div class="dash-card-icon" style="background: linear-gradient(135deg, #2c3e6b 0%, #4a6fa5 100%);">
                <span>学</span>
            </div>
            <div class="dash-card-info">
                <h3><%= request.getAttribute("studentCount") %></h3>
                <p>学生总数</p>
            </div>
        </div>
        <div class="dash-card" onclick="location.href='${pageContext.request.contextPath}/teacher/list'">
            <div class="dash-card-icon" style="background: linear-gradient(135deg, #c8553d 0%, #d4923a 100%);">
                <span>师</span>
            </div>
            <div class="dash-card-info">
                <h3><%= request.getAttribute("teacherCount") %></h3>
                <p>教师总数</p>
            </div>
        </div>
        <div class="dash-card" onclick="location.href='${pageContext.request.contextPath}/course/list'">
            <div class="dash-card-icon" style="background: linear-gradient(135deg, var(--jade) 0%, var(--jade-light) 100%);">
                <span>课</span>
            </div>
            <div class="dash-card-info">
                <h3><%= request.getAttribute("courseCount") %></h3>
                <p>课程总数</p>
            </div>
        </div>
        <div class="dash-card" onclick="location.href='${pageContext.request.contextPath}/score/list'">
            <div class="dash-card-icon" style="background: linear-gradient(135deg, #6b5b95 0%, #8e7cc3 100%);">
                <span>绩</span>
            </div>
            <div class="dash-card-info">
                <h3><%= request.getAttribute("scoreCount") %></h3>
                <p>成绩记录</p>
            </div>
        </div>
    </div>

    <div style="display:grid; grid-template-columns:1fr 1fr; gap:22px; margin-top:22px;">
        <div class="quick-links">
            <h3>快捷操作</h3>
            <div class="quick-link-items">
                <a href="${pageContext.request.contextPath}/student/list" class="quick-link-item">
                    <span class="quick-icon" style="background: var(--jade);">&#128100;</span>
                    <span>学生管理</span>
                </a>
                <a href="${pageContext.request.contextPath}/student/add" class="quick-link-item">
                    <span class="quick-icon" style="background: var(--jade-light);">&#10133;</span>
                    <span>新增学生</span>
                </a>
                <a href="${pageContext.request.contextPath}/teacher/list" class="quick-link-item">
                    <span class="quick-icon" style="background: var(--gold);">&#127891;</span>
                    <span>教师管理</span>
                </a>
                <a href="${pageContext.request.contextPath}/teacher/add" class="quick-link-item">
                    <span class="quick-icon" style="background: var(--gold-light);">&#10133;</span>
                    <span>新增教师</span>
                </a>
                <a href="${pageContext.request.contextPath}/course/list" class="quick-link-item">
                    <span class="quick-icon" style="background: var(--ink-light);">&#128218;</span>
                    <span>课程管理</span>
                </a>
                <a href="${pageContext.request.contextPath}/course/add" class="quick-link-item">
                    <span class="quick-icon" style="background: var(--ink-pale);">&#10133;</span>
                    <span>新增课程</span>
                </a>
                <a href="${pageContext.request.contextPath}/score/list" class="quick-link-item">
                    <span class="quick-icon" style="background: #6b5b95;">&#128200;</span>
                    <span>成绩管理</span>
                </a>
                <a href="${pageContext.request.contextPath}/score/add" class="quick-link-item">
                    <span class="quick-icon" style="background: #8e7cc3;">&#10133;</span>
                    <span>录入成绩</span>
                </a>
            </div>
        </div>

        <div class="log-panel">
            <h3>最近操作</h3>
            <%
                List<OperationLog> logs = OperationLog.getLogs(session);
                if (logs != null && !logs.isEmpty()) {
            %>
            <div class="log-list">
                <%
                    for (OperationLog log : logs) {
                %>
                <div class="log-item">
                    <span class="log-time"><%= log.getTime() %></span>
                    <span class="log-action"><%= log.getAction() %></span>
                    <span class="log-detail"><%= log.getDetail() %></span>
                </div>
                <%
                    }
                %>
            </div>
            <%
                } else {
            %>
            <div class="log-empty">暂无操作记录</div>
            <%
                }
            %>
        </div>
    </div>
</div>

<div class="site-footer">
    清雅学苑 · 学籍管理系统 &copy; 2024 &nbsp;|&nbsp; 技术支持：<a href="#">Java高级与算法编程实验</a>
</div>

<button class="back-to-top" onclick="window.scrollTo({top:0,behavior:'smooth'})" title="返回顶部">&#9650;</button>

<script>
    window.addEventListener('scroll', function() {
        var btn = document.querySelector('.back-to-top');
        if (window.scrollY > 200) {
            btn.style.display = 'block';
        } else {
            btn.style.display = 'none';
        }
    });
</script>
</body>
</html>

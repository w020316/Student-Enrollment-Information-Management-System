<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.stu.entity.Score" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>查看成绩 - 清雅学苑</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Serif+SC:wght@400;600;700&family=Noto+Sans+SC:wght@300;400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="navbar">
    <h1>学籍管理</h1>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/dashboard">首页</a>
        <a href="${pageContext.request.contextPath}/student/list">学生管理</a>
        <a href="${pageContext.request.contextPath}/teacher/list">教师管理</a>
        <a href="${pageContext.request.contextPath}/course/list">课程管理</a>
        <a href="${pageContext.request.contextPath}/score/list" class="active">成绩管理</a>
        <a href="${pageContext.request.contextPath}/logout">退出</a>
    </div>
</div>

<div class="container">
    <div class="breadcrumb"><a href="${pageContext.request.contextPath}/dashboard">首页</a><span class="separator">&#9656;</span><a href="${pageContext.request.contextPath}/score/list">成绩管理</a><span class="separator">&#9656;</span><span class="current">查看成绩</span></div>
    <%
        Score score = (Score) request.getAttribute("score");
    %>
    <div class="view-container">
        <h3>成绩详情</h3>
        <div class="field-row">
            <div class="field-label">编号</div>
            <div class="field-value"><%= score != null ? score.getId() : "" %></div>
        </div>
        <div class="field-row">
            <div class="field-label">学生姓名</div>
            <div class="field-value"><%= score != null && score.getStudentName() != null ? score.getStudentName() : "" %></div>
        </div>
        <div class="field-row">
            <div class="field-label">课程名称</div>
            <div class="field-value"><%= score != null && score.getCourseName() != null ? score.getCourseName() : "" %></div>
        </div>
        <div class="field-row">
            <div class="field-label">成绩</div>
            <div class="field-value"><%= score != null ? score.getScore() : "" %></div>
        </div>
        <div class="btn-group" style="margin-top:28px;">
            <a href="${pageContext.request.contextPath}/score/update?id=<%= score != null ? score.getId() : "" %>" class="btn btn-warning">编辑</a>
            <a href="${pageContext.request.contextPath}/score/list" class="btn btn-default">返回列表</a>
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

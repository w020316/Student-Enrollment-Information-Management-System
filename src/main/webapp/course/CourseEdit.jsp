<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.stu.entity.Course" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>编辑课程 - 清雅学苑</title>
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
        <a href="${pageContext.request.contextPath}/course/list" class="active">课程管理</a>
        <a href="${pageContext.request.contextPath}/score/list">成绩管理</a>
        <a href="${pageContext.request.contextPath}/logout">退出</a>
    </div>
</div>

<div class="container">
    <div class="breadcrumb"><a href="${pageContext.request.contextPath}/dashboard">首页</a><span class="separator">&#9656;</span><a href="${pageContext.request.contextPath}/course/list">课程管理</a><span class="separator">&#9656;</span><span class="current">编辑课程</span></div>
    <%
        Course course = (Course) request.getAttribute("course");
    %>
    <div class="form-container">
        <h3>编辑课程</h3>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
        <div class="alert alert-danger"><%= error %></div>
        <%
            }
        %>
        <form action="${pageContext.request.contextPath}/course/update" method="post">
            <input type="hidden" name="id" value="<%= course != null ? course.getId() : "" %>">
            <div class="form-group">
                <label>课程编号 <span class="required">*</span></label>
                <input type="text" name="courseNo" value="<%= course != null && course.getCourseNo() != null ? course.getCourseNo() : "" %>" required maxlength="20">
            </div>
            <div class="form-group">
                <label>课程名称 <span class="required">*</span></label>
                <input type="text" name="name" value="<%= course != null ? course.getName() : "" %>" required maxlength="20">
            </div>
            <div class="form-group">
                <label>学分 <span class="required">*</span></label>
                <input type="number" step="0.5" name="credit" value="<%= course != null ? course.getCredit() : "" %>" required min="0" max="10">
            </div>
            <div class="form-group">
                <label>学时 <span class="required">*</span></label>
                <input type="number" name="hours" value="<%= course != null ? course.getHours() : "" %>" required min="0">
            </div>
            <div class="btn-group">
                <button type="submit" class="btn btn-success">保 存</button>
                <a href="${pageContext.request.contextPath}/course/list" class="btn btn-default">取 消</a>
            </div>
        </form>
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

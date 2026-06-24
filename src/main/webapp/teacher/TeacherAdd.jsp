<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新增教师 - 清雅学苑</title>
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
        <a href="${pageContext.request.contextPath}/teacher/list" class="active">教师管理</a>
        <a href="${pageContext.request.contextPath}/course/list">课程管理</a>
        <a href="${pageContext.request.contextPath}/score/list">成绩管理</a>
        <a href="${pageContext.request.contextPath}/logout">退出</a>
    </div>
</div>

<div class="container">
    <div class="breadcrumb"><a href="${pageContext.request.contextPath}/dashboard">首页</a><span class="separator">&#9656;</span><a href="${pageContext.request.contextPath}/teacher/list">教师管理</a><span class="separator">&#9656;</span><span class="current">新增教师</span></div>
    <div class="form-container">
        <h3>新增教师</h3>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
        <div class="alert alert-danger"><%= error %></div>
        <%
            }
        %>
        <form action="${pageContext.request.contextPath}/teacher/add" method="post">
            <div class="form-group">
                <label>工号 <span class="required">*</span></label>
                <input type="text" name="teacherNo" placeholder="请输入工号" required maxlength="20">
            </div>
            <div class="form-group">
                <label>姓名 <span class="required">*</span></label>
                <input type="text" name="name" placeholder="请输入姓名" required maxlength="10">
            </div>
            <div class="form-group">
                <label>性别</label>
                <div class="radio-group">
                    <label class="radio-label"><input type="radio" name="gender" value="男" checked> 男</label>
                    <label class="radio-label"><input type="radio" name="gender" value="女"> 女</label>
                </div>
            </div>
            <div class="form-group">
                <label>年龄 <span class="required">*</span></label>
                <input type="number" name="age" placeholder="请输入年龄" required min="1" max="150">
            </div>
            <div class="form-group">
                <label>薪资 <span class="required">*</span></label>
                <input type="number" step="0.01" name="salary" placeholder="请输入薪资" required min="0">
            </div>
            <div class="btn-group">
                <button type="submit" class="btn btn-success">保 存</button>
                <a href="${pageContext.request.contextPath}/teacher/list" class="btn btn-default">取 消</a>
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

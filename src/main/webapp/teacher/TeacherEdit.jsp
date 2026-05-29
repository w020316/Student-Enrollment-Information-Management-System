<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.stu.entity.Teacher" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑教师</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="navbar">
    <h1>学生学籍信息管理系统</h1>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/student/list">学生管理</a>
        <a href="${pageContext.request.contextPath}/teacher/list">教师管理</a>
        <a href="${pageContext.request.contextPath}/logout">退出登录</a>
    </div>
</div>

<div class="container">
    <%
        Teacher teacher = (Teacher) request.getAttribute("teacher");
    %>
    <div class="form-container">
        <h3>编辑教师信息</h3>
        <form action="${pageContext.request.contextPath}/teacher/update" method="post">
            <input type="hidden" name="id" value="<%= teacher != null ? teacher.getId() : "" %>">
            <div class="form-group">
                <label>姓名</label>
                <input type="text" name="name" value="<%= teacher != null ? teacher.getName() : "" %>" required>
            </div>
            <div class="form-group">
                <label>年龄</label>
                <input type="number" name="age" value="<%= teacher != null ? teacher.getAge() : "" %>" required>
            </div>
            <div class="form-group">
                <label>薪资</label>
                <input type="number" step="0.01" name="salary" value="<%= teacher != null ? teacher.getSalary() : "" %>" required>
            </div>
            <div class="btn-group">
                <button type="submit" class="btn btn-success">保 存</button>
                <a href="${pageContext.request.contextPath}/teacher/list" class="btn btn-danger">取 消</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.stu.entity.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑学生</title>
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
        Student student = (Student) request.getAttribute("student");
    %>
    <div class="form-container">
        <h3>编辑学生信息</h3>
        <form action="${pageContext.request.contextPath}/student/update" method="post">
            <input type="hidden" name="id" value="<%= student != null ? student.getId() : "" %>">
            <div class="form-group">
                <label>姓名</label>
                <input type="text" name="name" value="<%= student != null ? student.getName() : "" %>" required>
            </div>
            <div class="form-group">
                <label>年龄</label>
                <input type="number" name="age" value="<%= student != null ? student.getAge() : "" %>" required>
            </div>
            <div class="btn-group">
                <button type="submit" class="btn btn-success">保 存</button>
                <a href="${pageContext.request.contextPath}/student/list" class="btn btn-danger">取 消</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>

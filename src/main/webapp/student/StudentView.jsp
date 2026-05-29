<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.stu.entity.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>查看学生</title>
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
    <div class="view-container">
        <h3>学生信息详情</h3>
        <div class="field-row">
            <div class="field-label">编号</div>
            <div class="field-value"><%= student != null ? student.getId() : "" %></div>
        </div>
        <div class="field-row">
            <div class="field-label">姓名</div>
            <div class="field-value"><%= student != null ? student.getName() : "" %></div>
        </div>
        <div class="field-row">
            <div class="field-label">年龄</div>
            <div class="field-value"><%= student != null ? student.getAge() : "" %></div>
        </div>
        <div style="text-align:center; margin-top:24px;">
            <a href="${pageContext.request.contextPath}/student/list" class="btn btn-info">返回列表</a>
        </div>
    </div>
</div>
</body>
</html>

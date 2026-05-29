<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新增学生</title>
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
    <div class="form-container">
        <h3>新增学生信息</h3>
        <form action="${pageContext.request.contextPath}/student/add" method="post">
            <div class="form-group">
                <label>姓名</label>
                <input type="text" name="name" placeholder="请输入姓名" required>
            </div>
            <div class="form-group">
                <label>年龄</label>
                <input type="number" name="age" placeholder="请输入年龄" required>
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

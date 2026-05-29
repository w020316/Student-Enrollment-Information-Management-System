<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生学籍信息管理系统 - 登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="login-container">
    <div class="login-box">
        <h2>学生学籍信息管理系统</h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="error-msg">
                ${error != null ? error : ''}
            </div>
            <div class="form-group">
                <label>用户名</label>
                <input type="text" name="username" placeholder="请输入用户名" required>
            </div>
            <div class="form-group">
                <label>密码</label>
                <input type="password" name="password" placeholder="请输入密码" required>
            </div>
            <button type="submit" class="btn btn-primary">登 录</button>
        </form>
    </div>
</div>
</body>
</html>

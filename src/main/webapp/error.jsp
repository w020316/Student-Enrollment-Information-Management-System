<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>系统错误 - 清雅学苑</title>
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
        <a href="${pageContext.request.contextPath}/logout">退出</a>
    </div>
</div>

<div class="container">
    <div class="error-container">
        <div class="error-code">500</div>
        <h2>系统开小差了</h2>
        <p class="error-desc">抱歉，服务器处理您的请求时遇到了问题，请稍后重试。</p>
        <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-success">返回首页</a>
    </div>
</div>
</body>
</html>

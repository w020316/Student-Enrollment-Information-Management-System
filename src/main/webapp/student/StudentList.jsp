<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.stu.entity.Student" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生信息列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="navbar">
    <h1>学生学籍信息管理系统</h1>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/student/list">学生管理</a>
        <a href="${pageContext.request.contextPath}/teacher/list">教师管理</a>
        <a href="${pageContext.request.contextPath}/generic/list?entity=student">通用列表(学生)</a>
        <a href="${pageContext.request.contextPath}/generic/list?entity=teacher">通用列表(教师)</a>
        <a href="${pageContext.request.contextPath}/logout">退出登录</a>
    </div>
</div>

<div class="container">
    <div class="table-container">
        <div class="table-header">
            <h3>学生信息列表</h3>
            <a href="${pageContext.request.contextPath}/student/add" class="btn btn-success">新增学生</a>
        </div>
        <table>
            <thead>
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>年龄</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            <%
                List<Student> students = (List<Student>) request.getAttribute("students");
                if (students != null && !students.isEmpty()) {
                    for (Student s : students) {
            %>
                <tr>
                    <td><%= s.getId() %></td>
                    <td><%= s.getName() %></td>
                    <td><%= s.getAge() %></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/student/view?id=<%= s.getId() %>" class="btn btn-info btn-sm">查看</a>
                        <a href="${pageContext.request.contextPath}/student/update?id=<%= s.getId() %>" class="btn btn-warning btn-sm">编辑</a>
                        <a href="${pageContext.request.contextPath}/student/delete?id=<%= s.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('确定删除该学生吗？')">删除</a>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="4" style="text-align:center; padding:30px; color:#999;">暂无学生数据</td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>

        <div class="pagination">
            <%
                int currentPage = (Integer) request.getAttribute("currentPage");
                int totalPages = (Integer) request.getAttribute("totalPages");
                int totalCount = (Integer) request.getAttribute("totalCount");
                String ctx = request.getContextPath();

                if (currentPage > 1) {
            %>
                <a href="<%= ctx %>/student/list?page=1">首页</a>
                <a href="<%= ctx %>/student/list?page=<%= currentPage - 1 %>">上一页</a>
            <%
                } else {
            %>
                <span class="disabled">首页</span>
                <span class="disabled">上一页</span>
            <%
                }

                for (int i = 1; i <= totalPages; i++) {
                    if (i == currentPage) {
            %>
                <span class="active"><%= i %></span>
            <%
                    } else {
            %>
                <a href="<%= ctx %>/student/list?page=<%= i %>"><%= i %></a>
            <%
                    }
                }

                if (currentPage < totalPages) {
            %>
                <a href="<%= ctx %>/student/list?page=<%= currentPage + 1 %>">下一页</a>
                <a href="<%= ctx %>/student/list?page=<%= totalPages %>">末页</a>
            <%
                } else {
            %>
                <span class="disabled">下一页</span>
                <span class="disabled">末页</span>
            <%
                }
            %>
            <span style="margin-left:10px; color:#999; font-size:13px;">共 <%= totalCount %> 条</span>
        </div>
    </div>
</div>
</body>
</html>

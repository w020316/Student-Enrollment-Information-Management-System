<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.stu.entity.Score" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>成绩信息列表 - 清雅学苑</title>
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
    <div class="breadcrumb">
        <a href="${pageContext.request.contextPath}/dashboard">首页</a>
        <span class="separator">&#9656;</span>
        <span class="current">成绩管理</span>
    </div>
    <%
        String message = (String) session.getAttribute("message");
        if (message != null) {
            session.removeAttribute("message");
    %>
    <div class="alert alert-success"><%= message %></div>
    <%
        }
    %>

    <div class="table-container">
        <div class="table-header">
            <h3>成绩信息</h3>
            <div class="table-actions">
                <form action="${pageContext.request.contextPath}/score/list" method="get" class="search-form">
                    <input type="number" name="studentId" placeholder="按学生ID筛选..." value="<%= request.getAttribute("studentId") != null ? request.getAttribute("studentId") : "" %>" class="search-input" style="width:160px;">
                    <button type="submit" class="btn btn-info btn-sm">筛选</button>
                    <% String studentIdParam = (String) request.getAttribute("studentId"); %>
                    <% if (studentIdParam != null && !studentIdParam.isEmpty()) { %>
                    <a href="${pageContext.request.contextPath}/score/list" class="btn btn-default btn-sm">清除</a>
                    <% } %>
                </form>
                <a href="${pageContext.request.contextPath}/score/add" class="btn btn-success">新增成绩</a>
                <a href="${pageContext.request.contextPath}/export?type=score" class="btn btn-info">导出CSV</a>
            </div>
        </div>
        <table>
            <thead>
                <tr>
                    <th>编号</th>
                    <th>学生姓名</th>
                    <th>课程名称</th>
                    <th>成绩</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            <%
                List<Score> scores = (List<Score>) request.getAttribute("scores");
                if (scores != null && !scores.isEmpty()) {
                    for (Score s : scores) {
            %>
                <tr>
                    <td><%= s.getId() %></td>
                    <td><%= s.getStudentName() != null ? s.getStudentName() : "" %></td>
                    <td><%= s.getCourseName() != null ? s.getCourseName() : "" %></td>
                    <td><%= s.getScore() %></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/score/view?id=<%= s.getId() %>" class="btn btn-info btn-sm">查看</a>
                        <a href="${pageContext.request.contextPath}/score/update?id=<%= s.getId() %>" class="btn btn-warning btn-sm">编辑</a>
                        <form action="${pageContext.request.contextPath}/score/delete" method="post" style="display:inline;" onsubmit="return confirm('确定删除该成绩记录吗？')">
                            <input type="hidden" name="id" value="<%= s.getId() %>">
                            <button type="submit" class="btn btn-danger btn-sm">删除</button>
                        </form>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="5" style="text-align:center; padding:40px; color:var(--ink-pale);">暂无成绩数据</td>
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
                String sid = request.getAttribute("studentId") != null ? (String) request.getAttribute("studentId") : "";
                String filterParam = sid.isEmpty() ? "" : "&studentId=" + sid;

                if (currentPage > 1) {
            %>
                <a href="<%= ctx %>/score/list?page=1<%= filterParam %>">首页</a>
                <a href="<%= ctx %>/score/list?page=<%= currentPage - 1 %><%= filterParam %>">上一页</a>
            <%
                } else {
            %>
                <span class="disabled">首页</span>
                <span class="disabled">上一页</span>
            <%
                }

                int startPage = Math.max(1, currentPage - 2);
                int endPage = Math.min(totalPages, currentPage + 2);
                for (int i = startPage; i <= endPage; i++) {
                    if (i == currentPage) {
            %>
                <span class="active"><%= i %></span>
            <%
                    } else {
            %>
                <a href="<%= ctx %>/score/list?page=<%= i %><%= filterParam %>"><%= i %></a>
            <%
                    }
                }

                if (currentPage < totalPages) {
            %>
                <a href="<%= ctx %>/score/list?page=<%= currentPage + 1 %><%= filterParam %>">下一页</a>
                <a href="<%= ctx %>/score/list?page=<%= totalPages %><%= filterParam %>">末页</a>
            <%
                } else {
            %>
                <span class="disabled">下一页</span>
                <span class="disabled">末页</span>
            <%
                }
            %>
            <span style="margin-left:12px; color:var(--ink-pale); font-size:12px;">共 <%= totalCount %> 条</span>
        </div>
        <div class="stats-bar">
            <span>共 <%= totalCount %> 条记录，第 <%= currentPage %>/<%= totalPages %> 页</span>
            <div class="page-size-selector">
                <span>每页</span>
                <select onchange="changePageSize(this.value)" id="pageSizeSelect">
                    <option value="5">5条</option>
                    <option value="10">10条</option>
                    <option value="20">20条</option>
                </select>
            </div>
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

    (function() {
        var sel = document.getElementById('pageSizeSelect');
        if (sel) {
            var currentSize = '<%= request.getAttribute("pageSize") != null ? request.getAttribute("pageSize") : "5" %>';
            for (var i = 0; i < sel.options.length; i++) {
                if (sel.options[i].value === currentSize) {
                    sel.options[i].selected = true;
                    break;
                }
            }
        }
    })();

    function changePageSize(val) {
        var url = window.location.pathname;
        var studentId = '<%= request.getAttribute("studentId") != null ? request.getAttribute("studentId") : "" %>';
        var params = '?page=1&size=' + val;
        if (studentId && studentId.trim() !== '') {
            params += '&studentId=' + studentId.trim();
        }
        window.location.href = url + params;
    }
</script>
</body>
</html>

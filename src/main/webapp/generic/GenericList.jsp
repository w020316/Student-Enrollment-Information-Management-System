<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.stu.entity.EntityInfo" %>
<%@ page import="com.stu.entity.ColumnInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Field" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>通用信息列表</title>
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
    <%
        EntityInfo entityInfo = (EntityInfo) request.getAttribute("entityInfo");
        List<?> dataList = (List<?>) request.getAttribute("dataList");
        String entityName = (String) request.getAttribute("entityName");
        int currentPage = (Integer) request.getAttribute("currentPage");
        int totalPages = (Integer) request.getAttribute("totalPages");
        int totalCount = (Integer) request.getAttribute("totalCount");
        String ctx = request.getContextPath();
    %>

    <div class="table-container">
        <div class="table-header">
            <h3><%= entityInfo != null ? entityInfo.getTableName() : "" %> 信息列表（注解驱动）</h3>
        </div>
        <table>
            <thead>
                <tr>
                <%
                    if (entityInfo != null) {
                        for (ColumnInfo col : entityInfo.getColumns()) {
                %>
                    <th><%= col.getLabel() %></th>
                <%
                        }
                    }
                %>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            <%
                if (dataList != null && !dataList.isEmpty()) {
                    for (Object obj : dataList) {
            %>
                <tr>
                <%
                        if (entityInfo != null) {
                            Class<?> clazz = obj.getClass();
                            for (ColumnInfo col : entityInfo.getColumns()) {
                                try {
                                    Field field = clazz.getDeclaredField(col.getFieldName());
                                    field.setAccessible(true);
                                    Object value = field.get(obj);
                %>
                    <td><%= value != null ? value.toString() : "" %></td>
                <%
                                } catch (Exception e) {
                %>
                    <td></td>
                <%
                                }
                            }
                        }
                %>
                    <td>
                        <a href="<%= ctx %>/<%= entityName %>/list" class="btn btn-info btn-sm">返回专用列表</a>
                    </td>
                </tr>
            <%
                    }
                } else {
                    int colSpan = entityInfo != null ? entityInfo.getColumns().size() + 1 : 1;
            %>
                <tr>
                    <td colspan="<%= colSpan %>" style="text-align:center; padding:30px; color:#999;">暂无数据</td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>

        <div class="pagination">
            <%
                if (currentPage > 1) {
            %>
                <a href="<%= ctx %>/generic/list?entity=<%= entityName %>&page=1">首页</a>
                <a href="<%= ctx %>/generic/list?entity=<%= entityName %>&page=<%= currentPage - 1 %>">上一页</a>
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
                <a href="<%= ctx %>/generic/list?entity=<%= entityName %>&page=<%= i %>"><%= i %></a>
            <%
                    }
                }

                if (currentPage < totalPages) {
            %>
                <a href="<%= ctx %>/generic/list?entity=<%= entityName %>&page=<%= currentPage + 1 %>">下一页</a>
                <a href="<%= ctx %>/generic/list?entity=<%= entityName %>&page=<%= totalPages %>">末页</a>
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

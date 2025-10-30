<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 将根路径重定向到HRM人力资源管理系统
    // 如需查看所有功能导航，请访问 /page/index
    response.sendRedirect(request.getContextPath() + "/hrm/userList");
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 将根路径重定向到通用页面控制器 /page/index，由视图解析器渲染 /WEB-INF/jsp/index.jsp
    response.sendRedirect(request.getContextPath() + "/page/index");
%>

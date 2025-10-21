<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>图书列表</title>
    <style>
        body{font-family: -apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Helvetica,Arial,"PingFang SC","Microsoft YaHei",sans-serif; background:#f5f7fb; margin:0}
        .container{max-width: 960px; margin: 20px auto; background:#fff; border:1px solid #e5e7eb; border-radius:12px; padding:20px}
        h2{margin:0 0 14px}
        .top{display:flex; justify-content:space-between; align-items:center; margin-bottom:10px}
        a{color:#2563eb; text-decoration:none}
        table{width:100%; border-collapse:collapse}
        th,td{border:1px solid #999; padding:8px}
        th{background:#f3f4f6}
        img{width:80px}
        .tip{color:#ef4444; font-size:18px; margin:14px 0}
    </style>
</head>
<body>
<div class="container">
    <div class="top">
        <h2>图书列表</h2>
        <div>
            <c:choose>
                <c:when test="${not empty sessionScope.loginUser}">
                    欢迎：${sessionScope.loginUser} | <a href="${pageContext.request.contextPath}/logout">退出登录</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login?needLogin=1&redirect=/books">请先登录</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <table>
        <thead>
        <tr>
            <th>封面</th>
            <th>书名</th>
            <th>作者</th>
            <th>价格</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="b" items="${books}">
            <tr>
                <td><img alt="封面" src="${pageContext.request.contextPath}${b.cover}"/></td>
                <td>${b.title}</td>
                <td>${b.author}</td>
                <td>${b.price}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div style="margin-top:12px">
        <a href="${pageContext.request.contextPath}/">返回首页</a>
    </div>
</div>
</body>
</html>

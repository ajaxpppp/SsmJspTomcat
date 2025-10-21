<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>用户登录</title>
    <style>
        body{font-family: -apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Helvetica,Arial,"PingFang SC","Microsoft YaHei",sans-serif; background:#f5f7fb; margin:0}
        .wrap{max-width: 960px; margin: 20px auto; background:#fff; border:1px solid #e5e7eb; border-radius:12px; padding:20px}
        h2{margin:0 0 10px}
        .tip{color:#ef4444; margin: 10px 0; font-size: 18px}
        .form{margin-top:10px}
        .field{display:flex; align-items:center; margin:10px 0}
        .field label{width:80px}
        .field input{flex:1; padding:8px 10px; border:1px solid #e5e7eb; border-radius:8px}
        .actions{margin-top:10px}
        .btn{border:0; background:#2563eb; color:#fff; border-radius:8px; padding:10px 16px; cursor:pointer}
        .err{color:#ef4444; margin-top:8px}
        a{color:#2563eb; text-decoration:none}
    </style>
</head>
<body>
<div class="wrap">
    <h2>登录</h2>
    <c:if test="${not empty needLogin}">
        <div class="tip">请先登录</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="err">${error}</div>
    </c:if>
    <form class="form" action="${pageContext.request.contextPath}/login" method="post">
        <input type="hidden" name="redirect" value="${redirect}"/>
        <div class="field">
            <label>用户名：</label>
            <input type="text" name="username" value="admin" required />
        </div>
        <div class="field">
            <label>密码：</label>
            <input type="password" name="password" value="123456" required />
        </div>
        <div class="actions">
            <button class="btn" type="submit">登录</button>
            <span style="margin-left:12px">测试账号：admin / 123456</span>
        </div>
    </form>
    <div style="margin-top:12px">
        <a href="${pageContext.request.contextPath}/">返回首页</a>
    </div>
</div>
</body>
</html>

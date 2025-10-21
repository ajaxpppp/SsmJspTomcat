<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>已注册用户列表</title>
    <style>
        :root{
            --bg:#f5f7fb; --panel:#ffffff; --text:#0f172a; --muted:#64748b; --border:#e5e7eb;
            --primary:#3b82f6; --primary-600:#2563eb; --radius:14px; --shadow:0 10px 25px rgba(2,6,23,.08),0 6px 12px rgba(2,6,23,.04);
            --success:#16a34a;
        }
        *{box-sizing:border-box}
        body{
            margin:0; color:var(--text); background:radial-gradient(1200px 800px at 10% -10%, #e8eefc 0%, transparent 40%),
            radial-gradient(900px 600px at 110% 0%, #f4e8ff 0%, transparent 35%), var(--bg);
            font: 15px/1.6 -apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Helvetica,Arial,"PingFang SC","Microsoft YaHei","Noto Sans CJK SC",sans-serif;
            padding: 24px;
        }
        .container{ max-width: 980px; margin: 0 auto; }
        .card{ background:var(--panel); border:1px solid var(--border); border-radius:var(--radius); box-shadow:var(--shadow); padding:22px }
        h2{ text-align:center; margin: 0 0 16px }
        .toolbar{ display:flex; gap:10px; justify-content:flex-end; margin-bottom: 14px }
        .btn{ appearance:none; border:0; border-radius:10px; padding:10px 14px; cursor:pointer; font-weight:600; letter-spacing:.2px; text-decoration:none; display:inline-block }
        .btn-primary{ color:#fff; background: linear-gradient(180deg, var(--primary) 0%, var(--primary-600) 100%); box-shadow: 0 6px 14px rgba(37,99,235,.25) }
        .btn-primary:hover{ filter:brightness(1.03) }
        .btn-ghost{ color:var(--primary-600); background: transparent; border:1px solid var(--primary-600) }
        .btn-ghost:hover{ background: rgba(37,99,235,.06) }

        .alert-success{ background: rgba(22,163,74,.08); color: var(--success); border:1px solid rgba(22,163,74,.35); padding:12px 14px; border-radius:10px; margin-bottom:12px }

        table{ width:100%; border-collapse:separate; border-spacing:0; overflow:hidden; border:1px solid var(--border); border-radius:12px }
        thead th{ background:#f8fafc; color:#0f172a; font-weight:700; text-align:left; padding:12px 14px; border-bottom:1px solid var(--border) }
        tbody td{ padding:12px 14px; border-bottom:1px solid var(--border); background:#fff }
        tbody tr:last-child td{ border-bottom:none }
        tbody tr:hover td{ background:#f9fbff }
    </style>
</head>
<body>
<div class="container">
    <div class="card">
        <h2>已注册用户列表</h2>

        <div class="toolbar">
            <a class="btn btn-ghost" href="${pageContext.request.contextPath}/zhuce">去注册</a>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/">返回首页</a>
        </div>

        <c:if test="${not empty message}">
            <div class="alert-success"><strong>${message}</strong></div>
        </c:if>

        <table>
            <thead>
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>班级</th>
                <th>电话</th>
                <th>Email</th>
                <th>出生日期</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${empty users}">
                    <tr>
                        <td colspan="6" style="text-align:center;color:#888;">暂无注册用户</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="u" items="${users}">
                        <tr>
                            <td>${u.studentId}</td>
                            <td>${u.name}</td>
                            <td>${u.className}</td>
                            <td>${u.phone}</td>
                            <td>${u.email}</td>
                            <td><fmt:formatDate value="${u.birthDate}" pattern="yyyy-MM-dd"/></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

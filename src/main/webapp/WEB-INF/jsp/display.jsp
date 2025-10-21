<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生注册信息显示</title>
    <style type="text/css">
        :root{
            --bg:#f5f7fb; --panel:#ffffff; --text:#0f172a; --muted:#64748b; --border:#e5e7eb;
            --primary:#3b82f6; --primary-600:#2563eb; --success:#16a34a; --radius:14px;
            --shadow:0 10px 25px rgba(2,6,23,.08),0 6px 12px rgba(2,6,23,.04);
        }
        *{box-sizing:border-box}
        body{
            margin:0; color:var(--text); background:radial-gradient(1200px 800px at 10% -10%, #e8eefc 0%, transparent 40%),
            radial-gradient(900px 600px at 110% 0%, #f4e8ff 0%, transparent 35%), var(--bg);
            font: 15px/1.6 -apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Helvetica,Arial,"PingFang SC","Microsoft YaHei","Noto Sans CJK SC",sans-serif;
            padding: 24px;
        }
        .container{ max-width: 840px; margin:0 auto; }
        .card{ background:var(--panel); border:1px solid var(--border); border-radius:var(--radius); box-shadow:var(--shadow); padding:22px }
        h2{ text-align:center; margin: 0 0 16px }
        .alert-success{ background: rgba(22,163,74,.08); color: var(--success); border:1px solid rgba(22,163,74,.35); padding:12px 14px; border-radius:10px; text-align:center }

        .table{ width:100%; border-collapse:separate; border-spacing:0; margin-top:14px; overflow:hidden; border:1px solid var(--border); border-radius:12px }
        .table thead th{ background:#f8fafc; color:#0f172a; font-weight:700; text-align:left; padding:12px 14px; border-bottom:1px solid var(--border); width:30% }
        .table tbody td{ padding:12px 14px; border-bottom:1px solid var(--border); background:#fff }
        .table tbody tr:last-child td{ border-bottom:none }
        .table tbody tr:hover td{ background:#f9fbff }

        .button-group{ text-align:center; margin-top:18px; display:flex; gap:10px; justify-content:center }
        .btn{ appearance:none; border:0; border-radius:10px; padding:10px 16px; cursor:pointer; font-weight:600; letter-spacing:.2px; text-decoration:none; display:inline-block }
        .btn-primary{ color:#fff; background: linear-gradient(180deg, var(--primary) 0%, var(--primary-600) 100%); box-shadow: 0 6px 14px rgba(37,99,235,.25) }
        .btn-primary:hover{ filter:brightness(1.03) }
        .btn-ghost{ color:var(--primary-600); background: transparent; border:1px solid var(--primary-600) }
        .btn-ghost:hover{ background: rgba(37,99,235,.06) }
    </style>
</head>
<body>
    <div class="container">
        <div class="card">
            <h2>学生注册信息确认</h2>

            <!-- 显示成功消息 -->
            <c:if test="${not empty message}">
                <div class="alert-success"><strong>${message}</strong></div>
            </c:if>

            <!-- 显示学生注册信息表格 -->
            <table class="table">
                <thead>
                    <tr>
                        <th>信息项</th>
                        <th>内容</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><strong>学号</strong></td>
                        <td>${student.studentId}</td>
                    </tr>
                    <tr>
                        <td><strong>姓名</strong></td>
                        <td>${student.name}</td>
                    </tr>
                    <tr>
                        <td><strong>班级</strong></td>
                        <td>${student.className}</td>
                    </tr>
                    <tr>
                        <td><strong>电话</strong></td>
                        <td>${student.phone}</td>
                    </tr>
                    <tr>
                        <td><strong>Email</strong></td>
                        <td>${student.email}</td>
                    </tr>
                    <tr>
                        <td><strong>出生日期</strong></td>
                        <td><fmt:formatDate value="${student.birthDate}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                </tbody>
            </table>

            <!-- 操作按钮 -->
            <div class="button-group">
                <a href="${pageContext.request.contextPath}/zhuce" class="btn btn-ghost">返回注册页面</a>
                <a href="${pageContext.request.contextPath}/" class="btn btn-primary">返回首页</a>
            </div>

            <!-- 调试信息（可选，生产环境可删除） -->
            <c:if test="${pageContext.request.getParameter('debug') == 'true'}">
                <div style="margin-top:16px; padding: 12px 14px; background: #f8fafc; border:1px dashed var(--border); border-radius:10px; color:var(--muted)">
                    <h4 style="margin:0 0 6px;">调试信息：</h4>
                    <p style="margin:4px 0">Student对象: ${student}</p>
                    <p style="margin:4px 0">Request编码: ${pageContext.request.characterEncoding}</p>
                    <p style="margin:4px 0">Response编码: ${pageContext.response.characterEncoding}</p>
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>

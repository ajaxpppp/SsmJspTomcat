<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生注册</title>
    <style type="text/css">
        :root{
            --bg:#f5f7fb; --panel:#ffffff; --text:#0f172a; --muted:#64748b;
            --primary:#3b82f6; --primary-600:#2563eb; --success:#22c55e; --danger:#ef4444; --border:#e5e7eb;
            --radius:14px; --shadow:0 10px 25px rgba(2,6,23,.08),0 6px 12px rgba(2,6,23,.04);
        }
        *{box-sizing:border-box}
        body{
            margin:0; color:var(--text); background:radial-gradient(1200px 800px at 10% -10%, #e8eefc 0%, transparent 40%),
            radial-gradient(900px 600px at 110% 0%, #f4e8ff 0%, transparent 35%), var(--bg);
            font: 15px/1.6 -apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Helvetica,Arial,"PingFang SC","Microsoft YaHei","Noto Sans CJK SC",sans-serif;
            padding: 24px;
        }
        .container{
            max-width: 720px; margin: 0 auto; background: var(--panel);
            border:1px solid var(--border); border-radius: var(--radius); box-shadow: var(--shadow);
            padding: 26px 22px;
        }
        h2{ text-align: center; margin: 0 0 18px; letter-spacing:.3px }
        .form-group{ margin-bottom: 14px }
        label{ display:block; margin-bottom:6px; color: var(--muted); font-weight: 600 }
        input[type="text"], input[type="email"], input[type="tel"], input[type="date"]{
            width:100%; padding: 10px 12px; border:1px solid var(--border); border-radius: 10px; background:#fff;
            outline:none; transition: box-shadow .2s ease, border-color .2s ease; font-size:14px;
        }
        input[type="text"]:focus, input[type="email"]:focus, input[type="tel"]:focus, input[type="date"]:focus{
            border-color: var(--primary); box-shadow: 0 0 0 3px rgba(59,130,246,.15);
        }
        .button-group{ text-align:center; margin-top: 18px }
        input[type="submit"], input[type="reset"]{
            appearance:none; border:0; border-radius: 10px; padding: 10px 18px; cursor:pointer; font-weight:600; letter-spacing:.2px;
            color:#fff; background: linear-gradient(180deg, var(--primary) 0%, var(--primary-600) 100%); box-shadow: 0 6px 14px rgba(37,99,235,.25);
            margin:0 8px;
        }
        input[type="submit"]:hover, input[type="reset"]:hover{ filter: brightness(1.05) }
        input[type="reset"]{ background: linear-gradient(180deg, var(--danger) 0%, #dc2626 100%); box-shadow: 0 6px 14px rgba(239,68,68,.22) }
        .required{ color: #ef4444 }
        .alert-error{ background: rgba(239,68,68,.08); color:#b91c1c; border:1px solid rgba(239,68,68,.35); padding:12px 14px; border-radius:10px; margin-bottom:12px; text-align:center }
    </style>
</head>
<body>
    <div class="container">
        <h2>学生注册表单</h2>
        <c:if test="${not empty messageError}">
            <div class="alert-error"><strong>${messageError}</strong></div>
        </c:if>
        
        <!-- 注册表单，提交到/zhuce路径，使用POST方法 -->
        <form action="${pageContext.request.contextPath}/zhuce" method="post">
            
            <div class="form-group">
                <label for="studentId">学号 <span class="required">*</span></label>
                <input type="text" id="studentId" name="studentId" 
                       placeholder="请输入学号" value="${student.studentId}" required>
            </div>
            
            <div class="form-group">
                <label for="name">姓名 <span class="required">*</span></label>
                <input type="text" id="name" name="name" 
                       placeholder="请输入姓名" value="${student.name}" required>
            </div>
            
            <div class="form-group">
                <label for="className">班级 <span class="required">*</span></label>
                <input type="text" id="className" name="className" 
                       placeholder="请输入班级（如：计算机科学与技术2021级1班）" value="${student.className}" required>
            </div>
            
            <div class="form-group">
                <label for="phone">电话 <span class="required">*</span></label>
                <input type="tel" id="phone" name="phone" 
                       placeholder="请输入手机号码" value="${student.phone}"
                       pattern="[0-9]{11}" 
                       title="请输入11位手机号码" required>
            </div>
            
            <div class="form-group">
                <label for="email">Email <span class="required">*</span></label>
                <input type="email" id="email" name="email" 
                       placeholder="请输入电子邮箱" value="${student.email}" required>
            </div>
            
            <c:if test="${not empty student and not empty student.birthDate}">
                <fmt:formatDate value="${student.birthDate}" pattern="yyyy-MM-dd" var="birthStr"/>
            </c:if>
            <div class="form-group">
                <label for="birthDate">出生日期 <span class="required">*</span></label>
                <input type="date" id="birthDate" name="birthDate" value="${birthStr}" required>
            </div>
            
            <div class="button-group">
                <input type="submit" value="提交注册">
                <input type="reset" value="重置表单">
            </div>
            
        </form>
    </div>
</body>
</html>

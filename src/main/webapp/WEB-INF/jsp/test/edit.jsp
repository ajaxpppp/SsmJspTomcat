<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改学生 - 学生管理系统</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            padding: 20px;
            box-sizing: border-box;
        }
        .container {
            max-width: 600px;
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>修改学生信息</h1>
            <p>Edit Student Information</p>
        </div>
        
        <div class="content">
            <form action="${pageContext.request.contextPath}/test/update" method="post">
                <div class="form-group">
                    <label for="sno">学号 <span class="required">*</span></label>
                    <input type="text" id="sno" name="sno" value="${student.sno}" readonly disabled>
                    <input type="hidden" name="sno" value="${student.sno}">
                    <div class="info-tip">学号不可修改</div>
                </div>
                
                <div class="form-group">
                    <label for="name">姓名 <span class="required">*</span></label>
                    <input type="text" id="name" name="name" value="${student.name}" required placeholder="请输入姓名">
                </div>
                
                <div class="form-group">
                    <label for="age">年龄</label>
                    <input type="number" id="age" name="age" value="${student.age}" placeholder="请输入年龄" min="1" max="150">
                </div>
                
                <div class="form-group">
                    <label for="tel">手机</label>
                    <input type="text" id="tel" name="tel" value="${student.tel}" placeholder="请输入手机号码" maxlength="11">
                </div>
                
                <div class="form-group">
                    <label for="address">地址</label>
                    <input type="text" id="address" name="address" value="${student.address}" placeholder="请输入地址">
                </div>
                
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">✔️ 保存</button>
                    <a href="${pageContext.request.contextPath}/test/index" class="btn btn-secondary">✖️ 取消</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>

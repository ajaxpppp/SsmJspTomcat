<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户列表 - HRM人力资源管理系统</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: "Microsoft YaHei", Arial, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            border-radius: 10px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
            padding: 30px;
        }
        .header {
            text-align: center;
            margin-bottom: 30px;
            padding-bottom: 20px;
            border-bottom: 3px solid #667eea;
        }
        .header h1 {
            color: #333;
            font-size: 32px;
            margin-bottom: 10px;
        }
        .header p {
            color: #666;
            font-size: 14px;
        }
        .message {
            padding: 12px 20px;
            margin-bottom: 20px;
            border-radius: 5px;
            font-size: 14px;
        }
        .message.success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .message.error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
        .toolbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            padding: 15px;
            background: #f8f9fa;
            border-radius: 5px;
        }
        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            text-decoration: none;
            display: inline-block;
            transition: all 0.3s;
        }
        .btn-primary {
            background: #667eea;
            color: white;
        }
        .btn-primary:hover {
            background: #5568d3;
        }
        .btn-success {
            background: #28a745;
            color: white;
        }
        .btn-success:hover {
            background: #218838;
        }
        .btn-warning {
            background: #ffc107;
            color: #333;
        }
        .btn-warning:hover {
            background: #e0a800;
        }
        .btn-danger {
            background: #dc3545;
            color: white;
        }
        .btn-danger:hover {
            background: #c82333;
        }
        .btn-sm {
            padding: 5px 12px;
            font-size: 12px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table thead {
            background: #667eea;
            color: white;
        }
        table th, table td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #dee2e6;
        }
        table th {
            font-weight: 600;
            text-align: center;
        }
        table td {
            text-align: center;
        }
        table tbody tr:hover {
            background-color: #f8f9fa;
        }
        .no-data {
            text-align: center;
            padding: 40px;
            color: #999;
            font-size: 16px;
        }
        .sex-badge {
            display: inline-block;
            padding: 4px 12px;
            border-radius: 12px;
            font-size: 12px;
            font-weight: bold;
        }
        .sex-male {
            background: #e3f2fd;
            color: #1976d2;
        }
        .sex-female {
            background: #fce4ec;
            color: #c2185b;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>📋 用户列表</h1>
            <p>HRM人力资源管理系统 - SSM框架整合实验</p>
        </div>

        <!-- 成功或错误消息 -->
        <c:if test="${not empty message}">
            <div class="message success">${message}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="message error">${error}</div>
        </c:if>

        <!-- 工具栏 -->
        <div class="toolbar">
            <div>
                <span style="font-weight: bold; color: #333;">总用户数：</span>
                <span style="font-size: 18px; color: #667eea; font-weight: bold;">${userCount}</span>
            </div>
            <a href="${pageContext.request.contextPath}/hrm/toAddUser" class="btn btn-success">
                ➕ 添加用户
            </a>
        </div>

        <!-- 用户表格 -->
        <c:choose>
            <c:when test="${empty users}">
                <div class="no-data">
                    <p>暂无用户数据</p>
                    <p style="font-size: 14px; margin-top: 10px;">
                        <a href="${pageContext.request.contextPath}/hrm/toAddUser" class="btn btn-primary">立即添加</a>
                    </p>
                </div>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>姓名</th>
                            <th>年龄</th>
                            <th>性别</th>
                            <th>部门</th>
                            <th>备注</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.name}</td>
                                <td>${user.age}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.sex == 1}">
                                            <span class="sex-badge sex-male">男</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="sex-badge sex-female">女</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${user.depart}</td>
                                <td>${user.remark}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/hrm/toEditUser?id=${user.id}" 
                                       class="btn btn-warning btn-sm">✏️ 编辑</a>
                                    <a href="${pageContext.request.contextPath}/hrm/deleteUser?id=${user.id}" 
                                       class="btn btn-danger btn-sm"
                                       onclick="return confirm('确定要删除用户【${user.name}】吗？')">🗑️ 删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>

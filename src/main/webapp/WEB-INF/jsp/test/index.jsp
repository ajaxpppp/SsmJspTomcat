<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生管理系统</title>
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
            max-width: 1200px;
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>学生管理系统</h1>
        </div>
        
        <div class="content">
            <!-- 消息提示 -->
            <c:if test="${not empty message}">
                <div class="message ${messageType}">
                    <span>${message}</span>
                </div>
            </c:if>
            
            <!-- 工具栏 -->
            <div class="toolbar">
                <div class="student-count">
                    共有 <strong>${students.size()}</strong> 名学生
                </div>
                <a href="${pageContext.request.contextPath}/test/add" class="btn btn-primary">
                    ➕ 添加学生
                </a>
            </div>
            
            <!-- 学生列表 -->
            <c:choose>
                <c:when test="${empty students}">
                    <div class="empty-state">
                        <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z"></path>
                        </svg>
                        <h3>暂无学生信息</h3>
                        <p>点击上方"添加学生"按钮开始添加学生</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                            <tr>
                                <th>学号</th>
                                <th>姓名</th>
                                <th>年龄</th>
                                <th>手机</th>
                                <th>地址</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${students}" var="student">
                                <tr>
                                    <td>${student.sno}</td>
                                    <td>${student.name}</td>
                                    <td>${student.age}</td>
                                    <td>${student.tel}</td>
                                    <td>${student.address}</td>
                                    <td>
                                        <div class="actions">
                                            <a href="${pageContext.request.contextPath}/test/edit/${student.sno}" class="btn btn-success">✏️ 修改</a>
                                            <a href="${pageContext.request.contextPath}/test/delete/${student.sno}" 
                                               class="btn btn-danger" 
                                               onclick="return confirm('确定要删除学生【${student.name}】吗？')">🗑️ 删除</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>

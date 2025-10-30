<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>课程类型管理系统</title>
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
            <h1>课程类型管理系统</h1>
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
                <div class="type-count">
                    共有 <strong>${kclxbList.size()}</strong> 个课程类型
                </div>
                <a href="${pageContext.request.contextPath}/test/add" class="btn btn-primary">
                    添加课程类型
                </a>
            </div>
            
            <!-- 课程类型列表 -->
            <c:choose>
                <c:when test="${empty kclxbList}">
                    <div class="empty-state">
                        <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6.253v13m0-13C10.832 5.477 9.246 5 7.5 5S4.168 5.477 3 6.253v13C4.168 18.477 5.754 18 7.5 18s3.332.477 4.5 1.253m0-13C13.168 5.477 14.754 5 16.5 5c1.747 0 3.332.477 4.5 1.253v13C19.832 18.477 18.247 18 16.5 18c-1.746 0-3.332.477-4.5 1.253"></path>
                        </svg>
                        <h3>暂无课程类型信息</h3>
                        <p>点击上方"添加课程类型"按钮开始添加课程类型</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>类型名称</th>
                                <th>备注</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${kclxbList}" var="kclx">
                                <tr>
                                    <td>${kclx.id}</td>
                                    <td><strong>${kclx.lxm}</strong></td>
                                    <td>${kclx.bz}</td>
                                    <td>
                                        <div class="actions">
                                            <a href="${pageContext.request.contextPath}/test/edit/${kclx.id}" class="btn btn-success">✏️ 修改</a>
                                            <a href="${pageContext.request.contextPath}/test/delete/${kclx.id}" 
                                               class="btn btn-danger" 
                                               onclick="return confirm('确定要删除课程类型【${kclx.lxm}】吗？')">删除</a>
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

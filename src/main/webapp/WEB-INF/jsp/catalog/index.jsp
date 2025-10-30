<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>图书分类管理系统</title>
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
            <h1>图书分类管理系统</h1>
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
                <div class="catalog-count">
                    共有 <strong>${catalogList.size()}</strong> 个图书分类
                </div>
                <a href="${pageContext.request.contextPath}/test/add" class="btn btn-primary">
                    添加图书分类
                </a>
            </div>
            
            <!-- 图书分类列表 -->
            <c:choose>
                <c:when test="${empty catalogList}">
                    <div class="empty-state">
                        <svg fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6.253v13m0-13C10.832 5.477 9.246 5 7.5 5S4.168 5.477 3 6.253v13C4.168 18.477 5.754 18 7.5 18s3.332.477 4.5 1.253m0-13C13.168 5.477 14.754 5 16.5 5c1.747 0 3.332.477 4.5 1.253v13C19.832 18.477 18.247 18 16.5 18c-1.746 0-3.332.477-4.5 1.253"></path>
                        </svg>
                        <h3>暂无图书分类信息</h3>
                        <p>点击上方"添加图书分类"按钮开始添加分类</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <table>
                        <thead>
                            <tr>
                                <th>分类ID</th>
                                <th>分类名称</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${catalogList}" var="catalog">
                                <tr>
                                    <td>${catalog.catalogid}</td>
                                    <td><strong>${catalog.catalogname}</strong></td>
                                    <td>
                                        <div class="actions">
                                            <a href="${pageContext.request.contextPath}/test/edit/${catalog.catalogid}" class="btn btn-success">修改</a>
                                            <a href="${pageContext.request.contextPath}/test/delete/${catalog.catalogid}" 
                                               class="btn btn-danger" 
                                               onclick="return confirm('确定要删除图书分类【${catalog.catalogname}】吗？')">删除</a>
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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加图书分类 - 图书分类管理系统</title>
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
            <h1>添加图书分类</h1>
            <p>Add New Book Catalog</p>
        </div>
        
        <div class="content">
            <form action="${pageContext.request.contextPath}/test/add" method="post">
                <div class="form-group">
                    <label for="catalogname">分类名称 <span class="required">*</span></label>
                    <input type="text" id="catalogname" name="catalogname" required placeholder="请输入图书分类名称">
                    <div class="info-tip">例如：小说、科技、历史、艺术等</div>
                </div>
                
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">提交</button>
                    <a href="${pageContext.request.contextPath}/test/index" class="btn btn-secondary">取消</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加课程类型 - 课程类型管理系统</title>
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
            <h1>添加课程类型</h1>
            <p>Add New Course Type</p>
        </div>
        
        <div class="content">
            <form action="${pageContext.request.contextPath}/test/add" method="post">
                <div class="form-group">
                    <label for="lxm">类型名称 <span class="required">*</span></label>
                    <input type="text" id="lxm" name="lxm" required placeholder="请输入课程类型名称">
                    <div class="info-tip">例如：必修课、选修课、公共课等</div>
                </div>
                
                <div class="form-group">
                    <label for="bz">备注</label>
                    <textarea id="bz" name="bz" placeholder="请输入备注信息（可选）"></textarea>
                </div>
                
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary"> 提交</button>
                    <a href="${pageContext.request.contextPath}/test/index" class="btn btn-secondary"> 取消</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>

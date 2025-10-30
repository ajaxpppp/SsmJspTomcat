<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑用户 - HRM人力资源管理系统</title>
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
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            max-width: 600px;
            width: 100%;
            background: white;
            border-radius: 10px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
            padding: 40px;
        }
        .header {
            text-align: center;
            margin-bottom: 30px;
            padding-bottom: 20px;
            border-bottom: 3px solid #667eea;
        }
        .header h1 {
            color: #333;
            font-size: 28px;
            margin-bottom: 10px;
        }
        .header p {
            color: #666;
            font-size: 14px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: 600;
            font-size: 14px;
        }
        .form-group label .required {
            color: #dc3545;
        }
        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
            transition: border-color 0.3s;
        }
        .form-group input:focus,
        .form-group select:focus,
        .form-group textarea:focus {
            outline: none;
            border-color: #667eea;
        }
        .form-group textarea {
            resize: vertical;
            min-height: 80px;
        }
        .form-group input[readonly] {
            background-color: #e9ecef;
            cursor: not-allowed;
        }
        .button-group {
            display: flex;
            gap: 15px;
            margin-top: 30px;
        }
        .btn {
            flex: 1;
            padding: 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            font-weight: 600;
            transition: all 0.3s;
            text-decoration: none;
            text-align: center;
            display: inline-block;
        }
        .btn-warning {
            background: #ffc107;
            color: #333;
        }
        .btn-warning:hover {
            background: #e0a800;
        }
        .btn-secondary {
            background: #6c757d;
            color: white;
        }
        .btn-secondary:hover {
            background: #5a6268;
        }
        .radio-group {
            display: flex;
            gap: 20px;
        }
        .radio-group label {
            display: flex;
            align-items: center;
            font-weight: normal;
            cursor: pointer;
        }
        .radio-group input[type="radio"] {
            width: auto;
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>✏️ 编辑用户</h1>
            <p>修改用户信息</p>
        </div>

        <form action="${pageContext.request.contextPath}/hrm/updateUser" method="post">
            <input type="hidden" name="id" value="${user.id}">
            
            <div class="form-group">
                <label>用户ID</label>
                <input type="text" value="${user.id}" readonly>
            </div>

            <div class="form-group">
                <label>姓名 <span class="required">*</span></label>
                <input type="text" name="name" value="${user.name}" placeholder="请输入姓名" required>
            </div>

            <div class="form-group">
                <label>年龄 <span class="required">*</span></label>
                <input type="number" name="age" value="${user.age}" placeholder="请输入年龄" min="18" max="100" required>
            </div>

            <div class="form-group">
                <label>性别 <span class="required">*</span></label>
                <div class="radio-group">
                    <label>
                        <input type="radio" name="sex" value="1" ${user.sex == 1 ? 'checked' : ''}> 男
                    </label>
                    <label>
                        <input type="radio" name="sex" value="2" ${user.sex == 2 ? 'checked' : ''}> 女
                    </label>
                </div>
            </div>

            <div class="form-group">
                <label>部门 <span class="required">*</span></label>
                <select name="depart" required>
                    <option value="">请选择部门</option>
                    <option value="人事部" ${user.depart == '人事部' ? 'selected' : ''}>人事部</option>
                    <option value="财务部" ${user.depart == '财务部' ? 'selected' : ''}>财务部</option>
                    <option value="技术部" ${user.depart == '技术部' ? 'selected' : ''}>技术部</option>
                    <option value="市场部" ${user.depart == '市场部' ? 'selected' : ''}>市场部</option>
                    <option value="销售部" ${user.depart == '销售部' ? 'selected' : ''}>销售部</option>
                    <option value="行政部" ${user.depart == '行政部' ? 'selected' : ''}>行政部</option>
                    <option value="研发部" ${user.depart == '研发部' ? 'selected' : ''}>研发部</option>
                    <option value="客服部" ${user.depart == '客服部' ? 'selected' : ''}>客服部</option>
                </select>
            </div>

            <div class="form-group">
                <label>备注</label>
                <textarea name="remark" placeholder="请输入备注信息（选填）">${user.remark}</textarea>
            </div>

            <div class="button-group">
                <button type="submit" class="btn btn-warning">💾 保存修改</button>
                <a href="${pageContext.request.contextPath}/hrm/userList" class="btn btn-secondary">❌ 取消</a>
            </div>
        </form>
    </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>系统错误</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif, 'Microsoft YaHei';
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        
        .error-container {
            max-width: 600px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
            padding: 40px;
            text-align: center;
        }
        
        .error-icon {
            font-size: 80px;
            color: #ef4444;
            margin-bottom: 20px;
        }
        
        h1 {
            color: #1f2937;
            margin-bottom: 10px;
        }
        
        .error-message {
            color: #6b7280;
            margin-bottom: 30px;
            line-height: 1.6;
        }
        
        .error-details {
            background: #f9fafb;
            border-left: 4px solid #ef4444;
            padding: 15px;
            text-align: left;
            margin-bottom: 30px;
            border-radius: 5px;
        }
        
        .error-details pre {
            color: #991b1b;
            font-size: 12px;
            overflow-x: auto;
            white-space: pre-wrap;
            word-wrap: break-word;
        }
        
        .btn {
            display: inline-block;
            padding: 12px 30px;
            background: #667eea;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: all 0.3s;
        }
        
        .btn:hover {
            background: #5568d3;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }
    </style>
</head>
<body>
    <div class="error-container">
        <div class="error-icon">⚠️</div>
        <h1>系统错误</h1>
        <p class="error-message">抱歉，系统遇到了一个错误。请稍后重试或联系管理员。</p>
        
        <% if (exception != null) { %>
        <div class="error-details">
            <strong>错误信息：</strong>
            <pre><%= exception.getMessage() %></pre>
        </div>
        <% } %>
        
        <a href="${pageContext.request.contextPath}/test/index" class="btn">返回首页</a>
    </div>
</body>
</html>

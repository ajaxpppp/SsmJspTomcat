<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>上传失败</title>
    <style>
        :root{
            --bg:#f5f7fb; --panel:#ffffff; --text:#0f172a; --muted:#64748b;
            --primary:#3b82f6; --primary-600:#2563eb; --danger:#ef4444; --border:#e5e7eb;
            --radius:14px; --shadow:0 10px 25px rgba(2,6,23,.08),0 6px 12px rgba(2,6,23,.04);
        }
        *{box-sizing:border-box}
        body{
            margin:0; color:var(--text); background:radial-gradient(1200px 800px at 10% -10%, #ffe8e8 0%, transparent 40%),
            radial-gradient(900px 600px at 110% 0%, #fff1e6 0%, transparent 35%), var(--bg);
            font: 15px/1.6 -apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Helvetica,Arial,"PingFang SC","Microsoft YaHei","Noto Sans CJK SC",sans-serif;
        }
        .container{ max-width: 820px; margin: 0 auto; padding: 36px 20px 60px; }
        .card{ background:var(--panel); border:1px solid var(--border); border-radius:var(--radius); box-shadow:var(--shadow); padding:22px }
        .title{ display:flex; align-items:center; gap:10px; font-size:20px; font-weight:700; margin:0 0 8px; color:#b91c1c }
        .title .badge{ display:inline-flex; width:28px; height:28px; border-radius:50%; align-items:center; justify-content:center; background:rgba(239,68,68,.12); color:var(--danger) }
        .muted{ color:var(--muted); margin:0 0 6px }
        .actions{ margin-top:16px; display:flex; gap:10px }
        .btn{ appearance:none; border:0; border-radius:10px; padding:10px 14px; cursor:pointer; font-weight:600; letter-spacing:.2px }
        .btn-primary{ color:#fff; background: linear-gradient(180deg, var(--primary) 0%, var(--primary-600) 100%); box-shadow: 0 6px 14px rgba(37,99,235,.25) }
        .btn-primary:hover{ filter:brightness(1.03) }
    </style>
</head>
<body>
<div class="container">
    <div class="card">
        <h1 class="title"><span class="badge">!</span> 上传失败</h1>
        <p class="muted">${message}</p>
        <div class="actions">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/page/index">返回上传页</a>
        </div>
    </div>
    
</div>
</body>
</html>

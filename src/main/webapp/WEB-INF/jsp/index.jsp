<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>项目首页 · 功能导航与文件上传</title>
    <style>
        :root{
            --bg:#f5f7fb;
            --panel:#ffffff;
            --text:#0f172a;
            --muted:#64748b;
            --primary:#3b82f6;
            --primary-600:#2563eb;
            --success:#22c55e;
            --danger:#ef4444;
            --border:#e5e7eb;
            --radius:14px;
            --shadow:0 10px 25px rgba(2,6,23,.08),0 6px 12px rgba(2,6,23,.04);
        }
        *{box-sizing:border-box}
        body{
            margin:0; color:var(--text); background:radial-gradient(1200px 800px at 10% -10%, #e8eefc 0%, transparent 40%),
            radial-gradient(900px 600px at 110% 0%, #f4e8ff 0%, transparent 35%), var(--bg);
            font: 15px/1.6 -apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Helvetica,Arial,"PingFang SC","Microsoft YaHei","Noto Sans CJK SC",sans-serif;
        }
        .container{ max-width: 980px; margin: 0 auto; padding: 32px 20px 60px; }
        .page-header{ text-align:center; margin-bottom: 22px; }
        .page-header h1{ font-size: 28px; margin: 0 0 6px; letter-spacing:.5px }
        .page-header p{ margin:0; color: var(--muted) }

        .grid{ display:grid; grid-template-columns: 1fr; gap:18px; }
        @media (min-width: 840px){ .grid{ grid-template-columns: 1fr 1fr; } }

        .card{
            background: var(--panel);
            border: 1px solid var(--border);
            border-radius: var(--radius);
            box-shadow: var(--shadow);
            padding: 18px 18px 16px;
        }
        .card-title{ font-size: 16px; font-weight: 600; margin-bottom: 12px; display:flex; align-items:center; gap:8px }
        .card .tip{ color: var(--muted); font-size: 12px; margin-top: -6px; margin-bottom: 8px }

        form .field{ display:flex; align-items:center; gap:10px; margin: 8px 0 10px; flex-wrap: wrap }
        input[type="text"], input[type="file"]{
            width:100%; max-width: 520px;
            padding: 10px 12px; border: 1px solid var(--border); border-radius: 10px;
            background: #fff; color: var(--text);
            outline: none; transition: box-shadow .2s ease, border-color .2s ease;
        }
        input[type="text"]:focus, input[type="file"]:focus{ border-color: var(--primary); box-shadow: 0 0 0 3px rgba(59,130,246,.15) }

        .actions{ margin-top: 8px; display:flex; gap:10px }
        .btn{
            appearance:none; border:0; border-radius: 10px; padding: 10px 14px; cursor:pointer; font-weight:600;
            transition: transform .06s ease, filter .2s ease, background .2s ease, box-shadow .2s ease; letter-spacing:.2px
        }
        .btn-primary{ color:#fff; background: linear-gradient(180deg, var(--primary) 0%, var(--primary-600) 100%); box-shadow: 0 6px 14px rgba(37,99,235,.25) }
        .btn-primary:hover{ filter:brightness(1.03) }
        .btn-primary:active{ transform: translateY(1px) }

        .section{ display:flex; flex-direction:column; gap:8px }
        .section small{ color:var(--muted) }

        /* 功能导航 */
        .nav-grid{ display:grid; grid-template-columns: 1fr; gap:12px; margin: 18px 0 26px }
        @media (min-width: 840px){ .nav-grid{ grid-template-columns: repeat(3, 1fr); } }
        .nav-link{ text-decoration:none; display:flex; align-items:center; justify-content:center; padding:12px 10px; border:1px solid var(--border); border-radius:12px; background:#fff; box-shadow: var(--shadow); color: var(--text) }
        .nav-link:hover{ background:#f8fafc }

        /* API 在线测试 */
        .api-grid{ display:grid; grid-template-columns: 1fr; gap:18px; margin-top: 18px }
        @media (min-width: 840px){ .api-grid{ grid-template-columns: 1fr 1fr; } }
        .api-actions{ display:flex; gap:8px; flex-wrap:wrap; margin-top:6px }
        .api-actions input[type="text"], .api-actions input[type="number"], .api-actions textarea{ max-width: 520px }
        .mono{ font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", "Courier New", monospace; }
        .output{ white-space: pre-wrap; background:#0b1020; color:#e2e8f0; padding:10px 12px; border-radius:10px; border:1px solid #0f172a; min-height: 82px; }
    </style>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>项目功能导航</h1>
        <p>这里集中展示所有已实现的页面入口与JSON接口在线测试，同时保留文件上传示例。</p>
    </div>

    <!-- 功能导航（页面入口） -->
    <div class="nav-grid">
        <a class="nav-link" href="${pageContext.request.contextPath}/hrm/userList">🏢 HRM人力资源管理 (/hrm/userList)</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/zhuce">📝 学生注册页面 (/zhuce)</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/listuser">👥 已注册用户列表 (/listuser)</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/page/index">📤 文件上传首页 (/page/index)</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/books">📚 图书列表 (/books)</a>
    </div>

    <!-- 文件上传示例 -->
    <h2 style="margin:10px 0 6px;">文件上传</h2>
    <p style="margin:0 0 10px; color:var(--muted)">上传的文件将保存到应用的 <code>/upload</code> 目录中。</p>
    <div class="grid">
        <!-- 单文件上传 -->
        <section class="card section">
            <div class="card-title">📄 单文件上传</div>
            <form action="${pageContext.request.contextPath}/upload/one" method="post" enctype="multipart/form-data">
                <div class="field">
                    <input type="file" name="file" required />
                </div>
                <div class="actions">
                    <button class="btn btn-primary" type="submit">上传</button>
                </div>
            </form>
        </section>

        <!-- 单文件（用户名作为文件夹名） -->
        <section class="card section">
            <div class="card-title">👤 单文件上传（用户名作为文件夹名）</div>
            <small class="tip">会在 /upload 下创建以用户名命名的子文件夹</small>
            <form action="${pageContext.request.contextPath}/upload/oneByUser" method="post" enctype="multipart/form-data">
                <div class="field">
                    <input type="text" name="username" placeholder="请输入用户名" required />
                </div>
                <div class="field">
                    <input type="file" name="file" required />
                </div>
                <div class="actions">
                    <button class="btn btn-primary" type="submit">上传</button>
                </div>
            </form>
        </section>

        <!-- 多文件上传 -->
        <section class="card section">
            <div class="card-title">🗂️ 多文件上传</div>
            <small class="tip">按住Ctrl/Command选择多个文件</small>
            <form action="${pageContext.request.contextPath}/upload/multi" method="post" enctype="multipart/form-data">
                <div class="field">
                    <input type="file" name="files" multiple required />
                </div>
                <div class="actions">
                    <button class="btn btn-primary" type="submit">上传</button>
                </div>
            </form>
        </section>
    </div>

    <!-- API 在线测试（GET/JSON接口） -->
    <h2 style="margin:24px 0 6px;">API 在线测试</h2>
    <p style="margin:0 0 10px; color:var(--muted)">在此直接调用后端JSON接口并查看返回结果</p>
    <div class="api-grid">
        <!-- 用户API -->
        <section class="card">
            <div class="card-title">👤 用户API (/user)</div>
            <div class="api-actions">
                <button class="btn btn-primary" onclick="call('GET', ctx + '/user/users')">获取所有用户</button>
                <button class="btn btn-primary" onclick="call('GET', ctx + '/user/count')">用户总数</button>
            </div>
            <div class="api-actions">
                <input type="number" id="uid" placeholder="用户ID"/>
                <button class="btn btn-primary" onclick="call('GET', ctx + '/user/' + get('uid'))">按ID查询</button>
            </div>
            <div class="api-actions">
                <input type="text" id="uname" placeholder="用户名"/>
                <button class="btn btn-primary" onclick="call('GET', ctx + '/user/name/' + encode(get('uname')))">按姓名查询</button>
            </div>
            <div class="api-actions">
                <textarea id="uadd" class="mono" rows="4" placeholder='{"username":"alice","password":"123456","phone":"13800000000","address":"Beijing"}'></textarea>
                <button class="btn btn-primary" onclick="call('POST', ctx + '/user/add', val('uadd'))">添加用户(POST)</button>
            </div>
            <div class="api-actions">
                <textarea id="uupd" class="mono" rows="4" placeholder='{"id":1,"username":"alice","password":"654321","phone":"13800000000","address":"Shanghai"}'></textarea>
                <button class="btn btn-primary" onclick="call('PUT', ctx + '/user/update', val('uupd'))">更新用户(PUT)</button>
            </div>
            <div class="api-actions">
                <input type="number" id="udel" placeholder="用户ID"/>
                <button class="btn btn-primary" onclick="call('DELETE', ctx + '/user/' + get('udel'))">删除用户(DELETE)</button>
            </div>
            <pre id="out-user" class="output mono"></pre>
        </section>

        <!-- 学生API -->
        <section class="card">
            <div class="card-title">🎓 学生API (/student)</div>
            <div class="api-actions">
                <button class="btn btn-primary" onclick="call('GET', ctx + '/student/students', null, 'out-stu')">所有学生</button>
            </div>
            <div class="api-actions">
                <input type="number" id="sid" placeholder="学生ID"/>
                <button class="btn btn-primary" onclick="call('GET', ctx + '/student/' + get('sid'), null, 'out-stu')">按ID查询</button>
            </div>
            <div class="api-actions">
                <input type="number" id="scid" placeholder="班级ID"/>
                <button class="btn btn-primary" onclick="call('GET', ctx + '/student/clazz/' + get('scid'), null, 'out-stu')">按班级ID</button>
            </div>
            <div class="api-actions">
                <input type="text" id="sname" placeholder="学生姓名"/>
                <button class="btn btn-primary" onclick="call('GET', ctx + '/student/name/' + encode(get('sname')), null, 'out-stu')">按姓名</button>
            </div>
            <pre id="out-stu" class="output mono"></pre>
        </section>

        <!-- 班级API -->
        <section class="card">
            <div class="card-title">🏫 班级API (/clazz)</div>
            <div class="api-actions">
                <button class="btn btn-primary" onclick="call('GET', ctx + '/clazz/classes', null, 'out-clazz')">所有班级</button>
            </div>
            <div class="api-actions">
                <input type="number" id="cid" placeholder="班级ID"/>
                <button class="btn btn-primary" onclick="call('GET', ctx + '/clazz/' + get('cid'), null, 'out-clazz')">按ID查询</button>
            </div>
            <div class="api-actions">
                <input type="text" id="ccode" placeholder="班级编码"/>
                <button class="btn btn-primary" onclick="call('GET', ctx + '/clazz/code/' + encode(get('ccode')), null, 'out-clazz')">按编码</button>
            </div>
            <div class="api-actions">
                <input type="text" id="cname" placeholder="班级名称"/>
                <button class="btn btn-primary" onclick="call('GET', ctx + '/clazz/name/' + encode(get('cname')), null, 'out-clazz')">按名称</button>
            </div>
            <pre id="out-clazz" class="output mono"></pre>
        </section>

        <!-- 订单API -->
        <section class="card">
            <div class="card-title">🧾 订单API (/order)</div>
            <div class="api-actions">
                <button class="btn btn-primary" onclick="call('GET', ctx + '/order/list', null, 'out-order')">所有订单</button>
            </div>
            <div class="api-actions">
                <input type="number" id="oid" placeholder="订单ID"/>
                <button class="btn btn-primary" onclick="call('GET', ctx + '/order/' + get('oid'), null, 'out-order')">订单详情</button>
            </div>
            <div class="api-actions">
                <input type="number" id="ouid" placeholder="用户ID"/>
                <button class="btn btn-primary" onclick="call('GET', ctx + '/order/user/' + get('ouid'), null, 'out-order')">按用户ID</button>
            </div>
            <pre id="out-order" class="output mono"></pre>
        </section>
    </div>
</div>

<script>
    const ctx = '${pageContext.request.contextPath}';
    const get = id => (document.getElementById(id).value || '').trim();
    const val = id => document.getElementById(id).value;
    const encode = s => encodeURIComponent(s || '');
    function call(method, url, body=null, outId){
        const target = document.getElementById(outId || 'out-user');
        target.textContent = '请求中: ' + method + ' ' + url + (body? ('\nBody: ' + body) : '');
        const opt = { method, headers: {} };
        if (body){ opt.headers['Content-Type'] = 'application/json;charset=UTF-8'; opt.body = body; }
        fetch(url, opt).then(r=>r.text()).then(t=>{
            try{ target.textContent = JSON.stringify(JSON.parse(t), null, 2); }
            catch(e){ target.textContent = t; }
        }).catch(err=>{
            target.textContent = '请求失败:\n' + err;
        });
    }
</script>
</body>
</html>

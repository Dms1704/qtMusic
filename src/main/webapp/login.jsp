<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>qq音乐用户登录</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<!-- 登录 注册 购物车... -->
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column" style="text-align: center">
            <div class="page-header">
                <h1>登录</h1>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column" align="center">
                    <%--此处设置登录信息--%>
                    <form role="form" action="user/login">
                        <div class="form-group">
                            <label>用户名</label><input name="username" type="text" style="width: 40%" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>密码</label><input name="password" type="password" style="width: 40%" class="form-control"/>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" />Check me out</label>
                        </div>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

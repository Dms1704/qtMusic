<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>页头</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        ul.dropdown-menu {
            margin-left: 660px;
            margin-top: -23px;
            background: yellowgreen;
        }
    </style>

    <script>
        //搜索按钮点击事件
        function searchSong(){
            var str = $("#searchInput").value;
            if (str != ""){
                return true;
            }
            else
                return false;
        }
    </script>

</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <%--最大的头--%>
            <div class="col-md-12 column page-header">
                <div class="col-md-6 column">
                    <ul class="nav nav-pills">
                        <li><a href="${pageContext.request.contextPath}/song/listSong?currentPage=1" style="font-size: 20px"><B>QQ音乐</B></a></li>
                        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/song/listSong?currentPage=1">音乐馆</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/mysong/findMysong">我的音乐</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/login.jsp">登录</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/register.jsp">注册</a></li>
                    </ul>
                </div>
                <div class="col-md-6 column">

                    <form method="post" action="${pageContext.request.contextPath}/song/searchSong?currentPage=1">
                        <div class="input-group" style="width: 60%">
                            <input type="text" id="searchInput" name="str" class="form-control" placeholder="搜索音乐">
                            <span class="input-group-btn">
                                <input id="btn1" onclick="return searchSong()" class="btn btn-default" type="submit" value="Go!">
                            </span>
                        </div>
                    </form>
                </div>
            </div>
            <%--二级头--%>
            <div class="col-md-12 column" align="center">
                <a href="${pageContext.request.contextPath}/song/listSong?currentPage=1">首页</a>&emsp;&emsp;&emsp;&emsp;&emsp;
                <a href="${pageContext.request.contextPath}/category/allCname">分类</a>&emsp;&emsp;&emsp;&emsp;&emsp;
                <a href="${pageContext.request.contextPath}/MusicList.jsp">音乐排行</a>&emsp;&emsp;&emsp;&emsp;&emsp;
                <a href="${pageContext.request.contextPath}/MvList.jsp">MV排行</a>&emsp;&emsp;&emsp;&emsp;&emsp;
                <hr>
            </div>
        </div>
    </div>
</div>
</body>
</html>

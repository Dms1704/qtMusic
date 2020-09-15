<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
</head>
<body>

<c:import url="header.jsp"></c:import>



<div class="container" style="background-color: #dddddd">
    <br>
    <div class="row clearfix" >
        <div class="col-md-12 column" style="align-content: center">
            <center>
                <form action="${pageContext.request.contextPath}/collect.jsp" method="post">
                    <div class="input-group" style="width: 30%;">
                        <input type = "text" class="form-control" name = "id" placeholder="请输入要收藏的歌曲名">
                        <span class="input-group-btn">
                            <input id="btn1" onclick="return searchSong()" class="btn btn-default" type="submit" value="确定">
                        </span>
                    </div>
                    <br>
                </form>
            </center>

        </div>
        <br>

        <div class="col-md-12 column" style="line-height: 1.5">
            <c:forEach items="${songPageBean.list}" var="song">
                <div class="col-md-4 column">
                    <a href="${pageContext.request.contextPath}/PlayMusic.jsp?sid=${song.sid}">
                        <div style="float: left; width: 35%">
                            <img src="${song.img}" width="100%">&nbsp;
                        </div>
                        <div style="float: left; width: 65%; text-align: center; line-height: 50px">
                            ${song.songname}
                            <br>
                                <span>歌手:</span>${song.author}
                        </div>
                    </a>
                    <br/>
                </div>
            </c:forEach>
        </div>
        <div class="col-md-12 column">
            <nav aria-label="...">
                <ul class="pager">
                    <c:if test="${isQuery == false}">
                        <li><a href="${pageContext.request.contextPath}/song/listSong?currentPage=${songPageBean.currentPage-1}">上一页</a></li>
                        <li><a href="${pageContext.request.contextPath}/song/listSong?currentPage=${songPageBean.currentPage+1}">下一页</a></li>
                    </c:if>
                    <c:if test="${isQuery == true}">
                        <li><a href="${pageContext.request.contextPath}/song/searchSong?currentPage=${songPageBean.currentPage-1}&str=${queryStr}">上一页</a></li>
                        <li><a href="${pageContext.request.contextPath}/song/searchSong?currentPage=${songPageBean.currentPage+1}&str=${queryStr}">下一页</a></li>
                    </c:if>
                </ul>
            </nav>
        </div>

    </div>
</div>
<br>
<c:import url="footer.jsp"></c:import>

</body>
</html>

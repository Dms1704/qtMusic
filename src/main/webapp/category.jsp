<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>

    <style>
        .category-span{
            color: black;
            text-decoration: none;
            hover: rgba(20, 141, 0, 0.51);
        }
    </style>


</head>
<body>
<c:import url="header.jsp"></c:import>

<div class="container" style="background-color: #dddddd">
    <div class="row clearfix" >
        <%--标签页--%>
        <div id="category-names" class="col-md-12 column" style="line-height: 1.5">
            <h3 style="color: #999999; font-size: 15px; text-align: center">标签</h3>
            <c:forEach items="${allCategoryName}" var="cname">
                <div class="col-md-2 column">
                    <div>
                        <a href="${pageContext.request.contextPath}/category/listSongByCname?cname=${cname}&currentPage=1" class="category-span">${cname}</a>
                    </div>
                    <br/>
                </div>
            </c:forEach>
        </div>
        <br><br>
        <div class="col-md-12 column" style="line-height: 1.5">
            <c:forEach items="${songsByCname.list}" var="song">
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
                    <li><a href="${pageContext.request.contextPath}/category/listSongByCname?cname=${cname}&currentPage=${songsByCname.currentPage-1}">上一页</a></li>
                    <li><a href="${pageContext.request.contextPath}/category/listSongByCname?cname=${cname}&currentPage=${songsByCname.currentPage+1}">下一页</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>
<br>
<c:import url="footer.jsp"></c:import>

<script>
/*    $(function(){
        $.ajax({
            type:"post",
            dataType:"json",
            data:"{}",
            contentType:'application/json;charset=UTF-8',
            url:"category/allCname",
            success:function (result) {
                var html = $("#category-names").innerHTML;
                alert($("#category-names").innerHTML);
                for (var i=0; i<result.size(); i++){
                    html += "                <div class=\"col-md-2 column\">\n" +
                        "                    <div>\n" +
                        "                        <a class=\"category-span\">"+ result[i] +"</a>\n" +
                        "                    </div>\n" +
                        "                    <br/>\n" +
                        "                </div>";
                }
                alert(html)
                $("#category-names").innerHTML = html;
            },
            error:function (XMLHttpRequest, textStatus) {
                console.log(textStatus);
                alert("错误");
            }
        })
    });*/
</script>
</body>
</html>

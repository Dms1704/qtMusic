<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 净火妖莲
  Date: 2020/6/3
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>查看评论</title>
    <!-- 引入 Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<h1>
    <a href="${pageContext.request.contextPath}/song/listSong?currentPage=1"><B>返回首页</B></a>
    <div id="box">
           <tr class="active" align="center">
               <th align="center"><strong>评论</strong></th>
           </tr>
    <table class="table table-bordered" >
           <c:forEach items="${discussMap}" var="item" varStatus="s">
               <tr class="warning">
                   <td>
                       <div class="media">
                           <div class="media-left">
                               <a href="#">
                                   <img class="media-object" style="width: 50px; height: 50px">
                               </a>
                           </div>
                           <div class="media-body">

                                   <h4 class="media-heading">${item.value}</h4>

                                   ${item.key.describe}
                               <c:if test="${item.value==user.username}">
                                   <a href='${pageContext.request.contextPath}/Discuss/DeleteDiscuss?did=${item.key.did}'>删除</a>

                               </c:if>
                           </div>
                       </div>
                   </td>
               </tr>
           </c:forEach>

        <a href="${pageContext.request.contextPath}/Discuss/ListDiscuss?sid=${sid}&currentPage=1&uid=${user.uid}" class="btn">查看评论</a>

        <td>

                <form method="post" action="${pageContext.request.contextPath}/Discuss/SaveDiscuss">

                    <div class="input-group">
                        <input type="text" required="required" id="descriptionInput" name="describe" class="form-control" aria-label="...">
                        <div class="input-group-btn">
                            <!-- Buttons -->
                            <input type="hidden" name="uid" required value="${user.uid}">
                            <input  id="Dtime" type="hidden" name="Dtime" value="" >
                            <input  id ="sid" type="hidden" name="sid" value="${sid}">
                            <input type="submit" onclick="return comment()" id="commentButton" class="btn btn-default" style="display: inline-block" value="[发表评论]" required="required"></input>
                        </div>
                    </div>
                </form>
        </td>
    </table>

        <div id="page">
            <a href="${pageContext.request.contextPath}/Discuss/ListDiscuss?currentPage=1&sid=${sid}" class="page">首页</a>
            <a href="${pageContext.request.contextPath}/Discuss/ListDiscuss?currentPage=${discussPageBean.currentPage-1}&sid=${sid}" class="page">上一页</a>
            <a href="${pageContext.request.contextPath}/Discuss/ListDiscuss?currentPage=${discussPageBean.currentPage+1}&sid=${sid}" class="page">下一页</a>
            <a href="${pageContext.request.contextPath}/Discuss/ListDiscuss?currentPage=${discussPageBean.totalPage}&sid=${sid}" class="page">尾页</a>
            <a href="javaScript:void(0)">共<span id="totalCounts">${discussPageBean.totalCount}</span>条数据</a>
        </div>
    </div>
</h1>
</body>

<script>

    function comment(){
        var value = $("#commentButton").value;
        if (value == "" || value == null){
            return false;
        }
        else
            return true;
    }

    window.onload = function(){
        function getDate(){
            debugger;
            var today = new Date();
            var date;
            date = (today.getFullYear()) +"/" + (today.getMonth() + 1 ) + "/" + today.getDate() + "/";
            return date;
        }
        window.setInterval(function(){
            document.getElementById("Dtime").value=getDate();
        }, 1000);
    }
</script>
</html>
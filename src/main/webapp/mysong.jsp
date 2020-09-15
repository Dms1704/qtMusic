<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的音乐</title>
</head>
<body>

<c:import url="header.jsp"></c:import>

<div class="container">
    <div class="row">
        <div class="col-md-12 columu" style="height: 315px; background: url(http://localhost:8080/Novel_war_exploded/images/mysong_background.jpg) no-repeat; background-size: cover; text-align: center; color: #fff;">
            <span class="glyphicon glyphicon-user" style="line-height: 264px; font-size: 34px">${user.username}</span>
        </div>
        <div class="col-md-12 columu">
            <br>
            <div>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/collection.jsp">我的音乐</a>
            </div>
        </div>
    </div>
</div>

<c:import url="footer.jsp"></c:import>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.xupt.domain.collection" %>
<%@ page import="com.xupt.collection.collectionDao" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 岁月如歌
  Date: 2020/6/3
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <a href="${pageContext.request.contextPath}/song/listSong?currentPage=1"><font size = "8" color = "#f0f8ff" face = "楷体" >返回首页</font></a>
    <p align = "center" >
        <br/>
        <font size = "10" color = "red" face = "楷体">欢迎进入您的收藏</font>
    </p>
    <title>我的收藏</title>

    <%
    ArrayList<collection> ccc = new collectionDao().getAllinfo();
    if(ccc == null){
        ccc = new ArrayList<>();
    }
%>
</head>
<body style="background: url(${pageContext.request.contextPath}/images/timg.jpg)" >
<center><table border = "10" style = "text-align: center;border-collapse:collapse " >
    <form action="delete.jsp" method="post">
        <p align="center">
        <input type = "text" name = "name" placeholder="请输入要取消收藏的歌曲名"  >
        <input type="submit" value="确定" >
            </p>
    </form>
    <tr>
        <th><font size="5" color="white" face = "楷体">歌名</font></th>
        <th><font size="5" color="white" face = "楷体">作者</font></th>
        <th><font size="5" color="white" face = "楷体">播放地址</font></th>
    </tr>
    <%
    for(collection col : ccc){
    %>
    <tr>
        <td><font size="5" color="white" face = "楷体"><%=col.getSongname()%></font></td>
        <td><font size="5" color="white" face = "楷体"><%=col.getAuthor()%></font></td>
        <td><a href="<%=col.getUrl()%>"><font size="5" color="white" face = "楷体">链接</font></a></td>
    </tr>
    <%
        }
    %>
</table></center>
<br>
</body>
</html>

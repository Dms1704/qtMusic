<%@ page import="com.xupt.util.DBConn" %>
<%@ page import="java.sql.Statement" %><%--
  Created by IntelliJ IDEA.
  User: 岁月如歌
  Date: 2020/6/4
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    String name = request.getParameter("name");
    DBConn db = new DBConn();
    Statement state = db.getConnection().createStatement();
    String sql = "delete from collection where songname = '" + name + "'";
    int flag = state.executeUpdate(sql);
    if(flag > 0){
        out.println("<script>alert('删除成功');history.go(-1)</script>");
    }else{
        out.println("<script>alert('删除失败，请确认歌曲名是否正确');history.go(-1)</script>");
    }
    state.close();
%>
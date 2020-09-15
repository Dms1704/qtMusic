<%@ page import="com.xupt.util.DBConn" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="com.xupt.collection.collectionDao" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
<%--
  Created by IntelliJ IDEA.
  User: 岁月如歌
  Date: 2020/6/4
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    DBConn dbbd = new DBConn();
    String Name = request.getParameter("id");
    String sql1 = "select songname from collection where songname= '"+Name+"'";
    PreparedStatement pst = dbbd.getConnection().prepareStatement(sql1);
    ResultSet rs = pst.executeQuery(sql1);
    if(rs.next()){
        out.println("<script>alert('此歌已经收藏');history.go(-1)</script>");

    }else {
        String name = request.getParameter("id");
        DBConn db = new DBConn();
        String sql = "insert into collection(songname,author,url) select songname,author, url from song where songname = '" + name + "'";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        int flag = ps.executeUpdate(sql);
        if (flag > 0) {
            out.println("<script>alert('收藏成功');history.go(-1)</script>");
        } else {
            out.println("<script>alert('收藏失败，请确认歌名是否正确');history.go(-1)</script>");
        }
    }
%>
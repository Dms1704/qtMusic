<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="css/MvList.css">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->

    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <!-- 官网提供的 axios 在线地址 -->
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="js/jquery.min.js"></script>
</head>
<body>

<c:import url="header.jsp"></c:import>
<%--mv排行--%>
    <div class="container" id="box">
        <table class="table " id="table1">
            <thead>
            <tr>
                <th>Mv热播排行</th>
                <th>播放量</th>
            </tr>
            </thead>
            <tbody id="QueryTable">
            <tr  v-for="(obj,index) in MvList">
                  <td>
                      <%--**********--%>
                      <a :href ="'PlayMv.jsp?sid='+obj.sid">
                      {{index+1}}&nbsp;{{obj.author}}——{{obj.songname}}
                      </a>
                  </td>
                <td>{{obj.mv_num}}</td>
            </tr>
            </tbody>
        </table>
        <table class="table  " id="table2">
            <thead>
            <tr>
                <th>Mv热门推荐</th>
            </tr>
            </thead>
            <tbody id="QueryTable1" >
            <tr v-for="obj in MvArr2">
                <td v-for="obj1 in obj">
                    <a :href ="'PlayMv.jsp?sid='+obj1.sid"><img :src="obj1.img"></a>
                    <h5><a :href ="'PlayMv.jsp?sid='+obj1.sid">{{obj1.author}}—{{obj1.songname}}</a></h5>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
<br>
</body>
<script>
  new Vue({
      el:'#box',
      data:{
          MvList:[],
          MvArr2:[]
      },
      methods:{
         MVList:function(){
              var that=this;
              $.ajax({
                  type:"get",
                  url:"${pageContext.request.contextPath}/mv/MvList",
                  dataType:"json",
                  success:function(result) {
                      if(result.success){
                          that.MvList=result.datas;
                          that.MVArray(that.MvList,4);
                      }else{
                          alert(result.datas);
                      }

                  }
              })
          },
          MVArray:function (arr,size) {
             var that=this;
             for(var x=0;x<Math.ceil(arr.length/size);x++){
                 var start=x*size;
                 var end=start+size;
                 that.MvArr2.push(arr.slice(start,end));
             }
          }
      },
      created:function () {
          this.MVList();
      }
  })
</script>
</html>


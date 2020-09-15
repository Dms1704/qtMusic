<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>排行</title>
    <link rel="stylesheet" type="text/css" href="css/PlayMv.css">
    <script src="js/jquery.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <!-- 官网提供的 axios 在线地址 -->
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>

<c:import url="header.jsp"></c:import>
<div id="wrap">
    <div class="video_con" >
        <video :src="url" controls="controls" ref="mv"></video>
        <!-- 遮罩层，给他绑定事件，取消遮罩 -->
    </div>
</div>
</body>
<script>
    var sid= window.location.href.split("?")[1].split("=")[1];
    var flag=false;
    new Vue({
        el:'#wrap',
        data:{
            url:'',
            id:'',
        },
        methods:{
            playmv:function (sid) {
                var that=this;
                myajax=$.ajax({
                    type:"get",
                    url:"${pageContext.request.contextPath}/mv/PlayMv",
                    data:{sid:sid},
                    dataType:"json",
                    success:function(result){
                        if(result.success){
                           that.url=result.datas.mv_url;
                           that.id=result.datas.mid;
                           flag=true;
                        }else{
                            alert(result.datas);
                        }
                    }
                })

            },
            MvNumber:function (mid) {
                $.ajax({
                    type: "get",
                    url: "${pageContext.request.contextPath}/mv/MvPlayNumber",
                    data: {mid: mid},
                    dataType: "json",
                })
            }
        },
        created:function () {
            var that=this;
            this.playmv(sid)
            /*等待ajax执行完再执行后续操作*/
            $.when(myajax).done(function () {
                that.MvNumber(that.id);
            });
        }
    })
</script>
</html>

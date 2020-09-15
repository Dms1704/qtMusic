<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>排行</title>
    <link rel="stylesheet" type="text/css" href="css/MusicList.css">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">

    <script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
</head>
<body>

<c:import url="header.jsp"></c:import>

<div class="container">
    <div class="container">
        <table class="table table-striped table-hover ">
            <thead>
            <tr>
                <th>歌曲排行榜</th>
                <th>播放量</th>
                <th>收藏</th>
            </tr>
            </thead>
            <tbody id="QueryTable">
            </tbody>
        </table>
    </div>
    <div id="page">
        <a href="javaScript:void(0)" class="page">首页</a>
        <a href="javaScript:void(0)" class="page">上一页</a>
        <a href="javaScript:void(0)" class="page">下一页</a>
        <a href="javaScript:void(0)" class="page">尾页</a>
        <a href="javaScript:void(0)">第<span id="correntPage"></span>页</a>
        <a href="javaScript:void(0)">共<span id="totalPages"></span>页</a>
        <a href="javaScript:void(0)">共<span id="totalCounts"></span>条数据</a>
    </div>
</div>
<br>
</body>
<script>
    var correntPage;//当前是第几页
    var lastPage;//最后一页

    //dom渲染完毕就需要将第一页的数据加载出来
    $(function (){
        PageQuery(1);
    });

    function PageQuery(PageIndex) {
        $.ajax({
            type:"post",
            dataType: "json",
            url: "${pageContext.request.contextPath}/song/SelectList",
            data:{PageIndex:PageIndex},
            success:function (result) {

                $("#QueryTable").empty();
                if(result.success){
                    var data=result.datas.data;
                    var tr='';
                    var i=1;
                    $(data).each(function (index, ele) {
                        //说明该歌曲有mv
                        if(ele.mv_status!=0){
                             tr= "<tr>" +

                                "<td>" +
                                "<a id='playmusic' href='${pageContext.request.contextPath}/PlayMusic.jsp?sid="+ele.sid+"'>\n" +
                                (i++)+"&nbsp;&nbsp;&nbsp;"+ ele.author +"——"+ele.songname+
                                "</a>" +
                                 /*mv图标*/
                                 "<a id='playmv' href='${pageContext.request.contextPath}/PlayMv.jsp?sid="+ele.sid+"' >\n" +
                                 "<img src='${pageContext.request.contextPath}/images/play.png'>\n"+
                                 "</a>" +
                                "</td>" +
                                 /*播放量*/

                                "<td>" + ele.play_num+"</td>" +

/*                                /!*收藏*!/
                                "<td>" +
                                "<button id='upload_" +ele.sid +"' class='btn btn-success btnCollect'>" +
                                "点击收藏" +
                                "</button>" +
                                "</td>" +*/

                            "</tr>";
                        }else{
                            /*点击播放时将id传过去*/
                            tr = "<tr>" +

                                "<td>" +
                                "<a id='playmusic' href='${pageContext.request.contextPath}/PlayMusic.jsp?sid="+ele.sid+"'>\n" +
                                (index+1)+"&nbsp;&nbsp;&nbsp;"+ ele.author +"——"+ele.songname+
                                "</a>" +
                                "</td>" +

                                "<td>" + ele.play_num+"</td>" +

/*                                "<td>" +
                                "<button id='upload_" +ele.sid +"' class='btn btn-success btnCollect'>\n" +
                                "点击收藏" +
                                "</button>" +
                                "</td>" +*/

                                 "</tr>";

                        }
                        $("#QueryTable").append(tr);
                        $("#correntPage").text(result.datas.currentPage);
                        $("#totalPages").text(result.datas.totalPages);
                        $("#totalCounts").text(result.datas.totalCounts);
                        correntPage=result.datas.currentPage;
                        lastPage=result.datas.totalPages;
                    })
                    btnCollect();
                }else{
                    alert(result.datas);
                }
            }
        })
    }
    //分页点击事件
    $(function () {
        $(".page").click(function () {
            //这里需要进行区分
            var msg= $(this).text();
            if(msg=='首页'){
                if(correntPage==1){
                    alert("当前就是首页")
                    layer.msg("当前就是首页")
                }else{
                    correntPage=1;
                }
            }else if(msg=='上一页'){
                if(correntPage==1){
                    alert("当前就是首页")
                    layer.msg("已经是首页")
                }else{
                    correntPage=correntPage-1;
                }
            }else if(msg=='下一页'){
                if(correntPage==lastPage){
                    alert("已经是最后一页")
                    layer.msg("已经是最后一页")
                }else{
                    correntPage=correntPage+1;
                }
            }else{
                if(correntPage==lastPage){
                    alert("已经是最后一页")
                    layer.msg("当前就是尾页")
                }else{
                    correntPage=lastPage;
                }
            }
            //需要将页码发送到后台进行处理
            PageQuery(correntPage)
        })
    })
    /*点击收藏事件*/
    function btnCollect() {
        $(".btnCollect").click(function () {
            var sid=$(this).attr("id").split("_")[1];
            $.ajax({
                type:"get",
                url: "${pageContext.request.contextPath}/song/SongCollect",
                data:{sid:sid},
                success:function (result) {
                    if(result.msg==1){
                        alert("请先登录再执行此操作")
                    }else if(result.msg==2){
                        alert("你已收藏这首歌");
                    }else{
                        /*隐藏收藏按钮*/

                        alert("收藏成功")
                    }
                }
            })
        })
    }
</script>
</html>

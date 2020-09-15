<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="css/PlayMusic.css"><!-- 开发环境版本，包含了有帮助的命令行警告 -->
    <script src="js/jquery.js"></script>
   <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <!-- 官网提供的 axios 在线地址 -->
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>

<c:import url="header.jsp"></c:import>

<div class="container">
    <div class="wrap">
        <!-- 播放器主体区域 -->
        <div class="play_wrap" id="player">
            <div class="center_con">
                <!-- 歌曲封面信息容器 -->
                <div class="player_con" :class="{playing:isplay}">
                    <img src="images/player_bar.png" class="play_bar" />
                    <!-- 黑胶碟片 -->
                    <img src="images/disc.png" class="disc autoRotate" />
                    <!-- 歌曲封面图片 -->
                    <img :src="imgurl" class="cover autoRotate"/>
                </div>
                <!-- 歌曲容器 -->
                <div id="words">
                   <span class="player">{{song_user}}——{{song_name}}</span>
                </div>
                <div class="comment_wrapper">
                        <div id="song_words">
                          <ul id="lrclist" ><!-- 保证歌词在正中间 -->
                          </ul>
                        </div>
                    <img src="images/line.png" class="right_line">
                </div>
            </div>
            <div class="audio_con">
                <audio id="audio" ref='audio' :src="url" @play="play()" @pause="pause()" controls autoplay loop class="myaudio"></audio>
            </div>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/Discuss/ListDiscuss?sid=${sid}&currentPage=1&uid=${user.uid}" class="btn">查看评论</a>
        </div>
    </div>
</div>
</body>
<script>
    var sid= window.location.href.split("?")[1].split("=")[1];
    new Vue({
        el: '#player',
        data: {
            url: '',
            imgurl: '',
            song_name:'',
            song_user:'',
            isplay: false,
        },
        methods: {
            playMusic: function (sid) {
                var that = this;
                $.ajax({
                    type: "get",
                    url: "${pageContext.request.contextPath}/song/PlaySong",
                    data: {sid: sid},
                    dataType: "json",
                    success: function (result) {
                        if (result.success) {
                            var data = result.datas;
                            that.url = data.url;//歌曲地址
                            that.imgurl = data.img;
                            that.song_name = data.songname;
                            that.song_user = data.author;
                        } else {
                            alert(result.datas);
                        }
                    },
                });
            },
            play() {
                this.isplay = true
            },
            pause() {
                this.isplay = false
            },
            songWords: function (sid) {
                var that = this;
                /*发送ajax获取歌词*/
                $.ajax({
                    type: "get",
                    url: "https://autumnfish.cn/lyric?id=" + sid,
                    dataType: "json",
                    success: function (result) {
                        var words = result.lrc.lyric;
                        $.ajax({
                            type: "get",
                            url: "${pageContext.request.contextPath}/song/SongWordsHandle",
                            data: {words: words},
                            dataType: "json",
                            success: function (result) {
                                /*拿到后台处理好的歌词数据*/
                                var song_words = result.words;
                                var lrcTime = [];//歌词对应的时间数组
                                var ul = $("#lrclist")[0];//获取ul
                                var i = 0;
                                $(song_words).each(function (index, ele) {//遍历lrc

                                    lrcTime[i++] = parseFloat(ele.time.substr(0, 2)) * 60 + parseFloat(ele.time.substring(3, 8));//00:00.000转化为00.000格式
                                    ul.innerHTML += "<li><p>" + ele.song_words + "</p></li>";//ul里填充歌词
                                });
                                lrcTime[lrcTime.length] = lrcTime[lrcTime.length - 1] + 3;//如不另加一个结束时间，到最后歌词滚动不到最后一句


                                var $li = $("#lrclist>li");//获取所有li

                                var currentLine = 0;//当前播放到哪一句了
                                var currentTime;//当前播放的时间
                                var audio = document.getElementById("audio");
                                var ppxx;//保存ul的translateY值

                                audio.ontimeupdate = function () {//audio时间改变事件
                                    currentTime = audio.currentTime;
                                    for (j = currentLine, len = lrcTime.length; j < len; j++) {
                                        if (currentTime < lrcTime[j + 1] && currentTime > lrcTime[j]) {
                                            currentLine = j;
                                            ppxx = 250 - (currentLine * 32);
                                            ul.style.transform = "translateY(" + ppxx + "px)";
                                            $li.get(currentLine - 1).className = "";
                                            console.log("on" + currentLine);
                                            $li.get(currentLine).className = "on";
                                            break;
                                        }
                                    }
                                };

                                audio.onseeked = function () {//audio进度更改后事件
                                    currentTime = audio.currentTime;
                                    console.log("  off" + currentLine);
                                    $li.get(currentLine).className = "";
                                    for (k = 0, len = lrcTime.length; k < len; k++) {
                                        if (currentTime < lrcTime[k + 1] && currentTime < lrcTime[k]) {
                                            currentLine = k;
                                            break;
                                        }
                                    }
                                };
                            }
                        });
                    }
                })
            },
            /*播放量加1*/
            PlayNum: function (sid) {
                $.ajax({
                    type: "get",
                    url: "${pageContext.request.contextPath}/song/SongPlayNumber",
                    data: {sid: sid},
                    dataType: "json",
                });
            },
        },
        created: function () {
            this.playMusic(sid);
            this.songWords(sid);
            this.PlayNum(sid);
        }
    })
</script>

</html>

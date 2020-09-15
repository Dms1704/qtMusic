package com.xupt.controller;

import com.alibaba.fastjson.JSON;
import com.xupt.domain.PageBean;
import com.xupt.domain.Song;
import com.xupt.domain.SongListPage;
import com.xupt.domain.Song_Words;
import com.xupt.service.impl.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/song")
@SessionAttributes("sid")
public class SongController {

    @Autowired
    private SongService songService;

    /**
     * 分页展示歌曲到首页
     * @param currentPage
     * @param model
     * @return
     */
    @RequestMapping("/listSong")
    public String listSong(int currentPage, Model model){
        PageBean<Song> pageBean = songService.pageSong(currentPage);
        model.addAttribute("songPageBean", pageBean);
        //添加判断是否执行查询的信息
        model.addAttribute("isQuery", false);
        return "index";
    }

    /*歌曲排行*/
    @RequestMapping("/SelectList")
    public void SelectList(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        //获取页数
        String pageIndex = request.getParameter("PageIndex");
        SongListPage list = songService.SelectList(pageIndex);

        HashMap<String, Object> hashMap = new HashMap<>();
        if (list!=null){
            hashMap.put("success",true);
            hashMap.put("datas",list);
        } else {
            hashMap.put("success",false);
            hashMap.put("datas","无数据");
        }
        response.getWriter().print(JSON.toJSONString(hashMap));
    }
    /*歌曲播放*/
    @RequestMapping("/PlaySong")
    public void SelectById(Model model, HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        int sid = Integer.parseInt(request.getParameter("sid"));
        model.addAttribute("sid", sid);
        Song song = songService.SelectById(sid);
        HashMap<String, Object> hashMap = new HashMap<>();
        if (song!=null){
            hashMap.put("success",true);
            hashMap.put("datas",song);
        } else {
            hashMap.put("success",false);
            hashMap.put("datas","当前这首歌的资源不存在");
        }
        response.getWriter().print(JSON.toJSONString(hashMap));
    }
    /*歌词处理*/
    @RequestMapping("/SongWordsHandle")
    public void SongWordsHandle(HttpServletResponse response,HttpServletRequest request) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String words = request.getParameter("words");//拿到歌词
        //将该字符串存入文件中
        BufferedWriter song_words = new BufferedWriter(new FileWriter("D:\\song_words.txt"));
        song_words.write(words);
        /*需要刷新*/
        song_words.flush();
        //从文件中读取之后进行封装
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\song_words.txt"));
        HashMap<String, Object> map = new HashMap<>();
        List<Object> list = new ArrayList<>();
        String line =null ;
        while((line=bufferedReader.readLine())!=null){
            //对每一行的数据进行解析
            String time = line.split("\\]")[0].split("\\[")[1];//时间
            String songwords= null;//歌词
            try {
                songwords = line.split("\\]")[1];
            } catch (Exception e) {
                songwords="";
            }
            Song_Words song_words1 = new Song_Words();
            song_words1.setTime(time);
            song_words1.setSong_words(songwords);
            list.add(song_words1);
        }
        map.put("words",list);
        bufferedReader.close();
        song_words.close();
        response.getWriter().print(JSON.toJSONString(map));
    }

    @RequestMapping("/SongPlayNumber")
    public void SongPlayNumber(HttpServletRequest request){
        String sid = request.getParameter("sid");
        songService.SongPlayNumber(Integer.parseInt(sid));
    }

    @RequestMapping("/searchSong")
    public String findSongBySongnameOrUsername(int currentPage, String str, Model model){
        PageBean<Song> pageBean = songService.pageSong(currentPage, str);
        model.addAttribute("songPageBean", pageBean);
        //添加判断是否执行查询的信息
        model.addAttribute("isQuery", true);
        //添加执行查询的信息
        model.addAttribute("queryStr", str);
        return "index";
    }
}

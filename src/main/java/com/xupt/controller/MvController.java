package com.xupt.controller;

import com.alibaba.fastjson.JSON;
import com.xupt.domain.Mv;
import com.xupt.domain.MvList;
import com.xupt.service.IMvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/mv")
public class MvController {
    @Autowired
    private IMvService mvService;
    /*MV播放*/
    @RequestMapping("/PlayMv")
    public void SelectMvById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        Integer sid = Integer.valueOf(request.getParameter("sid"));
        Mv mv = mvService.SelectMvById(sid);
        HashMap<String, Object> hashMap = new HashMap<>();
        if (mv!=null){
            hashMap.put("success",true);
            hashMap.put("datas",mv);
        } else {
            hashMap.put("success",false);
            hashMap.put("datas","无数据");
        }
        response.getWriter().print(JSON.toJSONString(hashMap));
    }
    /*MV排行*/
    @RequestMapping("/MvList")
    public void SelectMvNumber(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        List<MvList> list = mvService.SelectMvNumber();
        HashMap<String, Object> hashMap = new HashMap<>();
        if (list!=null){
            hashMap.put("success",true);
            hashMap.put("datas",list);
        } else {
            hashMap.put("success",false);
            hashMap.put("datas","无数据或当前mv资源未上传");
        }
        System.out.println(JSON.toJSONString(hashMap));
        response.getWriter().print(JSON.toJSONString(hashMap));
    }
    @RequestMapping("/MvPlayNumber")
    public void MvPlayNumber(HttpServletRequest request){
        String mid = request.getParameter("mid");
        mvService.MvPlayNumber(Integer.parseInt(mid));
    }
}

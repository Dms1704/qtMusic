package com.xupt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mysong")
public class MysongController {

    @RequestMapping("/findMysong")
    public String findMysong(){
        return "mysong";
    }
}

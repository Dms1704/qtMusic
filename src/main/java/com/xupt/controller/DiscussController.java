package com.xupt.controller;

import com.xupt.domain.Discuss;
import com.xupt.domain.PageBean;
import com.xupt.service.IDiscussService;
import com.xupt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("sid")
@RequestMapping("/Discuss")
public class DiscussController {

    @Autowired
    private IDiscussService discussService;
    @Autowired
    private IUserService userService;

    //存评论
    @RequestMapping("/SaveDiscuss")
    public String SaveDiscuss( Integer sid,Integer currentPage, Discuss discuss){
        System.out.println("存评论的sid："+sid);
        currentPage=1;
        System.out.println("你能走到这里嘛");
        if (discussService.saveDiscuss(discuss)==1) {
            return "redirect:ListDiscuss?sid="+sid+"&currentPage="+currentPage;
        }
        else
            return "fail";
    }

    //找评论
    @RequestMapping("/ListDiscuss")
    public String ListDisucss(Integer sid ,Integer currentPage, Model model) {
        PageBean<Discuss> discussList = discussService.pageDiscuss(sid,currentPage);
        model.addAttribute("sid",sid);

        Map<Discuss,String>discussMap = new HashMap<>();
        for(Discuss discuss:discussList.getList()){

            discussMap.put(discuss,userService.finduserNameByuid(discuss.getUid()));
        }
        model.addAttribute("discussPageBean",discussList);
        model.addAttribute("discussMap",discussMap);
        return "discussList";
    }

    //删除评论
        @RequestMapping("/DeleteDiscuss")
        public String DeltDiscuss(Integer currentPage ,Integer did ,Integer sid) {
        currentPage=1;
        if (discussService.deleteDiscussByDid(did) == 1) {
            System.out.println("过来了没");

            System.out.println("删评论："+sid);
            return "redirect:ListDiscuss?&currentPage="+currentPage;
            }
        else
            return "fail";
    }

}

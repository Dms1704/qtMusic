package com.xupt.controller;

import com.xupt.domain.PageBean;
import com.xupt.domain.Song;
import com.xupt.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequestMapping("/category")
@SessionAttributes(value = {"allCategoryName"})
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("/allCname")
    public String findAll(Model model){
        List<String> allCname = categoryService.findAll();
        model.addAttribute("allCategoryName", allCname);
        return "category";
    }

    /**
     * 按照分类名分页展示歌曲
     * @return
     */
    @RequestMapping("/listSongByCname")
    public String pageSongByCategory(Integer currentPage, String cname, Model model){
        PageBean<Song> songsByCname = categoryService.findSongsByCname(cname, currentPage);
        model.addAttribute("songsByCname", songsByCname);
        model.addAttribute("cname", cname);
        return "category";
    }
}

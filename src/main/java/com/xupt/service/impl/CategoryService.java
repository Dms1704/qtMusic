package com.xupt.service.impl;

import com.xupt.dao.ICategoryDao;
import com.xupt.domain.PageBean;
import com.xupt.domain.Song;
import com.xupt.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    public List<String> findAll() {
        return categoryDao.findAll();
    }

    public PageBean<Song> findSongsByCname(String cname, int currentPage) {
        if (currentPage <= 0){
            currentPage = 1;
        }
        int count = 9;
        PageBean<Song> pageBean = new PageBean<>();
        pageBean.setCurrentCount(count);
        //总条数
        int titleCount = categoryDao.findTitleCountByCname(cname);
        pageBean.setTotalCount(titleCount);
        //总页数
        int titlePages = (titleCount % count == 0) ? (titleCount / count): (titleCount / count + 1);
        if (titlePages < currentPage){
            //如果当前页大于最后一页
            currentPage = titlePages;
        }
        pageBean.setTotalPage(titlePages);
        int start = (currentPage-1) * count;
        List<Song> songs = categoryDao.findSongsByCategory(cname, start, 9);
        pageBean.setList(songs);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }
}

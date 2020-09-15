package com.xupt.service;

import com.xupt.domain.PageBean;
import com.xupt.domain.Song;

import java.util.List;

public interface ICategoryService {

    /**
     * 查询出所有分类名(去重)
     * @return
     */
    List<String> findAll();

    /**
     * 根据分类名查出这一类歌曲
     * @param cname
     * @return
     */
    PageBean<Song> findSongsByCname(String cname, int currentPage);
}

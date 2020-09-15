package com.xupt.service;

import com.xupt.domain.PageBean;
import com.xupt.domain.Song;
import com.xupt.domain.SongListPage;

public interface ISongService {

    /**
     * 根据当前页将数据库中的歌曲封装成一个bean并返回
     * @param currentPage
     * @return
     */
    PageBean<Song> pageSong(int currentPage);

    public SongListPage SelectList(String PageIndex);

    public Song SelectById(int sid);

    public int SongPlayNumber(int sid);
}

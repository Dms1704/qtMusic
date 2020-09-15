package com.xupt.service.impl;

import com.xupt.dao.ISongDao;
import com.xupt.domain.PageBean;
import com.xupt.domain.Song;
import com.xupt.domain.SongList;
import com.xupt.domain.SongListPage;
import com.xupt.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SongService implements ISongService {

    @Autowired
    private ISongDao songDao;

    public SongListPage SelectList(String PageIndex){
        SongListPage songListPage = new SongListPage();
        if(PageIndex==""){
            PageIndex="1";
        }
        //获取数据总条数
        int totalCount = songDao.CountIndex();
        //每页显示的数据条数
        int pageCount = songListPage.getPageCount();
        //计算一共有多少页
        int totalPages = totalCount / pageCount;
        if(totalCount / pageCount!=0){
            totalPages++;
        }
        if (totalCount<8){
            totalPages=1;
        }
        int anInt = Integer.parseInt(PageIndex);
        int PageIndex1 = (anInt - 1) * pageCount;
        //要对查询的结果按照播放量来排序
        List<SongList> songPlayNumbers = songDao.SelectList(PageIndex1,pageCount);

        songPlayNumbers.sort(new Comparator<SongList>() {
            @Override
            public int compare(SongList o1, SongList o2) {

                int num=o2.getPlay_num()-o1.getPlay_num();
                int num1=o2.getSongname().compareTo(o2.getSongname());
                if(num==0){
                    return num1;
                }
                return num;
            }
        });
        //对所有的数据进行封装
        songListPage.setTotalPages(totalPages);//总页数
        songListPage.setTotalCounts(totalCount);//总条数
        songListPage.setCurrentPage(Integer.parseInt(PageIndex));//当前第多少页
        songListPage.setData(songPlayNumbers);//设置查询出的结果
        return songListPage;
    }

    @Override
    public Song SelectById(int sid) {
        return songDao.SelectById(sid);
    }

    @Override
    public int SongPlayNumber(int sid) {
        return songDao.SongPlayNumber(sid);
    }

    /**
     * 根据当前页将数据库中的歌曲封装成一个bean并返回
     * @param currentPage
     * @return
     */
    public PageBean<Song> pageSong(int currentPage) {
        if (currentPage <= 0){
            currentPage = 1;
        }
        int count = 9;
        PageBean<Song> pageBean = new PageBean<>();
        pageBean.setCurrentCount(count);
        //总条数
        int titleCount = songDao.findTitleCount();
        pageBean.setTotalCount(titleCount);
        //总页数
        int titlePages = (titleCount % count == 0) ? (titleCount / count): (titleCount / count + 1);
        if (titlePages == 0){
            titlePages = 1;
        }
        if (titlePages < currentPage){
            //如果当前页大于最后一页
            currentPage = titlePages;
        }
        pageBean.setTotalPage(titlePages);
        int start = (currentPage-1) * count;
        List<Song> songs = songDao.findLimit(start, 9);
        pageBean.setList(songs);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }

    /**
     * 根据当前页和查询条件str将数据库中的歌曲封装成一个bean并返回
     * @param currentPage
     * @return
     */
    public PageBean<Song> pageSong(int currentPage, String str) {
        if (currentPage <= 0){
            currentPage = 1;
        }
        int count = 9;
        PageBean<Song> pageBean = new PageBean<>();
        pageBean.setCurrentCount(count);
        //总条数
        int titleCount = songDao.findTitleCount();
        pageBean.setTotalCount(titleCount);
        //总页数
        int titlePages = (titleCount % count == 0) ? (titleCount / count): (titleCount / count + 1);
        if (titlePages == 0){
            titlePages = 1;
        }
        if (titlePages < currentPage){
            //如果当前页大于最后一页
            currentPage = titlePages;
        }
        pageBean.setTotalPage(titlePages);
        int start = (currentPage-1) * count;
        List<Song> songs = songDao.findLimitByStr("%"+str+"%", start, 9);
        pageBean.setList(songs);
        pageBean.setCurrentPage(currentPage);
        return pageBean;
    }
}

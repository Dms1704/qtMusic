package com.xupt.dao;

import com.xupt.domain.Song;
import com.xupt.domain.SongList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISongDao {

    /*歌曲排行*/
    @Select("SELECT p.sid,p.play_num,s.songname,s.author,s.mv_status FROM music_number p INNER JOIN song s ON s.sid=p.sid limit #{PageIndex},#{PageCount}")
    public List<SongList> SelectList(@Param("PageIndex") int PageIndex, @Param("PageCount") int PageCount);
    /*歌曲播放*/
    @Select("select *from song where sid=#{sid}")
    public Song SelectById(int sid);
    /*计算歌曲排行的数据总条数*/
    @Select("SELECT COUNT(pid) FROM music_number")
    public int CountIndex();
    /*歌曲播放量加1*/
    @Update("UPDATE music_number SET play_num=play_num+1 WHERE sid =#{sid}")
    public int SongPlayNumber(int sid);
    /*收藏-获取用户的uid*/

    /**
     * 查所有
     * @return
     */
    @Select("select * from song")
    List<Song> findAll();

    /**
     * 计算数据总数
     * @return
     */
    @Select("select count(*) from song")
    int findTitleCount();

    /**
     * 查询数据库中从start+1开始共count条数据
     * @param start
     * @param count
     * @return
     */
    @Select("select * from song limit #{start}, #{count}")
    List<Song> findLimit(@Param("start") Integer start, @Param("count") Integer count);

    /**
     * 带str查询条件来查询数据库中从start+1开始共count条数据
     * @return
     */
    @Select("select * from `song` where `songname` like #{str1} or `author` like #{str1} limit #{start},#{count}")
    List<Song> findLimitByStr(@Param("str1") String str1, @Param("start") Integer start, @Param("count") Integer count);
}

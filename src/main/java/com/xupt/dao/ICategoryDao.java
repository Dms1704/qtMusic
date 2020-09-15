package com.xupt.dao;

import com.xupt.domain.Song;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryDao {

    /**
     * 通过分类名查找歌曲
     * @return
     */
    @Select("select * from song where sid = any(select sid from category where cname =#{cname}) limit #{start}, #{count}")
    List<Song> findSongsByCategory(@Param("cname") String cname, @Param("start") int start, @Param("count") int count);

    /**
     * 查分类名为cname的歌曲总数
     * @param cname
     * @return
     */
    @Select("select count(*) from category where cname = #{cname}")
    int findTitleCountByCname(String cname);

    /**
     * 查出所有分类名
     * @return
     */
    @Select("select distinct cname from category")
    List<String> findAll();
}

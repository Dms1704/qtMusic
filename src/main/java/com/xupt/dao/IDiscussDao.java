package com.xupt.dao;


import com.xupt.domain.Discuss;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("discussDao1")
public interface IDiscussDao {

    /**
     * 找所有
     * @return
     */
    @Select( "select *from discuss")
    List<Discuss> findAll();

    /**
     * 保存评论
     */
    @Insert("insert into discuss(uid,Dtime,`describe`,sid) values(#{uid}, #{Dtime}, #{describe} ,#{sid})")
    void saveDiscuss(Discuss discuss);

    /**
     * 根据did查找评论
     */
    @Select("select * from did where did = #{did}")
    Discuss findDiscussByDid(Integer did);

    /**
     *
     * @param sid
     * @return
     */

    @Select("select * from discuss where sid = #{sid}")
    List<Discuss> findDiscussBySid(Integer sid);

    /**
     * 找出这首歌评论得用户
     * @param sid
     * @return
     */

    @Select("select uid from discuss where sid = #{sid}")
    List<Integer> findUidBysid(Integer sid);
    /**
     *
     * @param did
     */

    @Delete("delete from discuss where did =#{did}")
    void deleteDiscussByDid(Integer did);

    /**
     *
     * @param sid
     */
    @Delete("delete from discuss where sid =#{sid}")
    void deleteDiscussBySid(Integer sid);

    /**
     *
     * @param uid
     */
    @Delete("delete from discuss where uid =#{uid}")
    void deleteDiscussByUid(Integer uid);

    /**
     * 找出评论数目
     * @return
     */
    @Select("select count(*) from discuss where sid=#{sid}")
    Integer findDiscussCount(Integer sid);

    /**
     *
     * @param sid
     * @param start
     * @param count
     * @return
     */
    @Select("select * from discuss where sid =#{sid} limit #{start}, #{count}")
    List<Discuss> findLimit(@Param("sid") Integer sid, @Param("start") Integer start, @Param("count") Integer count);


}

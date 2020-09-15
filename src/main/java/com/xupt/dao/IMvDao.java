package com.xupt.dao;

import com.xupt.domain.Mv;
import com.xupt.domain.MvList;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMvDao {
    /*mv播放*/
    @Select("select *from mv where sid=#{sid}")
    public Mv SelectMvById(int sid);
    /*mv排行*/
    @Select("SELECT n.mv_num,m.mv_url,m.sid,s.author,s.songname,s.img FROM mv_number n INNER JOIN mv m ON n.`mid`=m.`mid` INNER JOIN song s ON m.`sid`=s.`sid`")
    public List<MvList> SelectMvNumber();
    @Update("UPDATE mv_number SET mv_num=mv_num+1 WHERE MID =#{mid}")
    public int MvPlayNumber(int mid);
}

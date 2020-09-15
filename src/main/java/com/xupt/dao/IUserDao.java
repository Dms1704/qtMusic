package com.xupt.dao;

import com.xupt.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {

    /**
     * 找所有
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 保存用户
     */
    @Insert("insert into user(username, sex, address, password) values(#{username}, #{sex}, #{address}, #{password})")
    void saveUser(User user);

    /**
     * 根据username查询用户
     * @return
     */
    @Select("select * from user where username = #{username}")
    User findUserByUsername(String username);

    /**
     *
     * @param uid
     * @return
     */
    @Select("select username from user where uid = #{uid}")
    String finduserNameByuid(Integer uid);
}

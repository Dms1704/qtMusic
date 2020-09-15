package com.xupt.service;

import com.xupt.domain.User;

import java.util.List;

public interface IUserService {

    /**
     * 找名字
     * @param uid
     * @return
     */
    String finduserNameByuid(Integer uid);

    /**
     * 查所有
     * @return
     */
    List<User> findAll();

    /**
     * 注册用户
     * @param user
     */
    int register(User user);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    int login(String username, String password);

    /**
     * 根据用户名查找User
     * @return
     */
    User findByUsername(String username);
}

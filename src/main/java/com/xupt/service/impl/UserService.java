package com.xupt.service.impl;

import com.xupt.dao.IUserDao;
import com.xupt.domain.User;
import com.xupt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    public User findByUsername(String username){
        return userDao.findUserByUsername(username);
    }

    public String finduserNameByuid(Integer uid) {
        return userDao.finduserNameByuid(uid);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 注册成功返回1，失败返回0
     * @param user 待注册的用户
     * @return
     */
    public int register(User user) {
        User user1 = userDao.findUserByUsername(user.getUsername());
        if (user1 == null) {
            userDao.saveUser(user);
            return 1;
        }
        return 0;
    }

    /**
     * 处理登录业务
     *      若返回0表示用户名不存在或用户名密码不匹配
     *      若返回1表示登陆成功
     * @param username
     * @param password
     * @return
     */
    public int login(String username, String password) {
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            //没有这个用户
            return 0;
        }
        if (user.getPassword().equals(password)){
            //登录成功
            return 1;
        }
        else {
            return 0;
        }
    }
}

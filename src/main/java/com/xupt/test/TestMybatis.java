package com.xupt.test;

import com.xupt.dao.IUserDao;
import com.xupt.domain.User;
import com.xupt.service.impl.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {

    public static void main(String[] args) throws IOException {
/*        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);*/

        UserService service = new UserService();
        List<User> users = service.findAll();

        for (User user:users){
            System.out.println(user);
        }
    }
}

package cn.aceo.product.test;

import cn.aceo.product.dao.impl.UserDaoImpl;
import cn.aceo.product.domain.User;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {
    @Test
    public void getUserByusernameTest(){
        UserDaoImpl userDao=new UserDaoImpl();
        User admin=new User();
        admin = userDao.getUserByusername("wu");
        System.out.println(admin);
    }
}

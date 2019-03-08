package cn.aceo.product.dao;

import cn.aceo.product.domain.User;

import java.util.List;

public interface IUserDao {
    User getUserByusername(String username);
}

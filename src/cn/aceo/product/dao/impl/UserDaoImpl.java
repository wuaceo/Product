package cn.aceo.product.dao.impl;

import cn.aceo.product.dao.IUserDao;
import cn.aceo.product.domain.User;
import cn.aceo.product.template.IResultSetHandler;
import cn.aceo.product.template.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public User getUserByusername(String username) {
        //binary区分大小写
        String sql="SELECT * FROM t_user WHERE binary username=?";
        return JdbcTemplate.query(sql, new IResultSetHandler<User>() {
            @Override
            public User handdler(ResultSet rs) throws SQLException {
                while (rs.next()){
                    User user=new User();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }
                return null;
            }
        },username);
    }
}

package cn.aceo.product.template;

import cn.aceo.product.domain.Product;
import cn.aceo.product.productHandler.ProductResultSetHandler;
import cn.aceo.product.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    //DML模板
    public static void update(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        conn = JdbcUtil.getConn();
        try {
            ps = conn.prepareStatement(sql);
            //====设置参数====
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn, ps, null);
        }

    }

    //DQL模板
    public static <T> T query(String sql, IResultSetHandler<T> rsh, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        /* 贾连欲执事 */
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            //====设置参数====
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();
            /* 处理结果集 */
            return rsh.handdler(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }
}

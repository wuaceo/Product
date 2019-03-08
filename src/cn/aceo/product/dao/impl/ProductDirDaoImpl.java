package cn.aceo.product.dao.impl;

import cn.aceo.product.dao.IProductDirDao;
import cn.aceo.product.domain.ProductDir;
import cn.aceo.product.template.IResultSetHandler;
import cn.aceo.product.template.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDirDaoImpl  implements IProductDirDao {
    @Override
    public List<ProductDir> listAll() {
        String sql="SELECT * FROM productdir";
       return JdbcTemplate.query(sql,new IResultSetHandler<List<ProductDir>>(){
            @Override
            public List<ProductDir> handdler(ResultSet rs) throws SQLException {
                List<ProductDir> list=new ArrayList<>();
                while (rs.next()){
                    ProductDir productDir =new ProductDir();
                    productDir.setId(rs.getLong("id"));
                    productDir.setDirName(rs.getString("dirName"));
                    productDir.setParent_id(rs.getLong("parent_id"));
                    list.add(productDir);
                }
                return list;
            }
        });
    }
}

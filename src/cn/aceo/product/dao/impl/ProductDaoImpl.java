package cn.aceo.product.dao.impl;

import cn.aceo.product.dao.IProductDao;
import cn.aceo.product.domain.Product;
import cn.aceo.product.productHandler.ProductResultSetHandler;
import cn.aceo.product.query.ProductQueryObject;
import cn.aceo.product.template.JdbcTemplate;
import cn.aceo.product.util.JdbcUtil;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements IProductDao {
    //追加查询
    @Override
    public void save(Product product) {
        String sql = "INSERT INTO product (productName,dir_id,salePrice,supplier,brand,cutoff,costPrice) VALUES (?,?,?,?,?,?,?)";
        JdbcTemplate.update(sql, product.getProductName(), product.getDir_id(), product.getSalePrice(), product.getSupplier(), product.getBrand(), product.getCutoff(), product.getCostPrice());
    }
    //删除查询
    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM product WHERE id=?";
       JdbcTemplate.update(sql,id);
    }
//更新查询
    @Override
    public void update(Long id, Product product) {
        String sql = "UPDATE  product SET productName=?,dir_id=?,salePrice=?,supplier=?,brand=?,cutoff=?,costPrice=? WHERE  id=?";
        JdbcTemplate.update(sql,product.getProductName(), product.getDir_id(), product.getSalePrice(), product.getSupplier(), product.getBrand(), product.getCutoff(), product.getCostPrice(),id);
    }
//根据id查询一条记录
    @Override
    public Product get(Long id) {
        String sql = "SELECT * FROM product WHERE id=?";
        List<Product> list = JdbcTemplate.query(sql, new ProductResultSetHandler(), id);
        return list.size()==1?list.get(0):null;
    }
//查询所有记录
    @Override
    public List<Product> listAll() {
            //class.forName("com.mysql.jdbc.Driver"); tomcat8.0不能用了
            String sql = "SELECT * FROM product";
          return JdbcTemplate.query(sql,new ProductResultSetHandler());
    }

    @Override
    public List<Product> query(ProductQueryObject queryObject) {
        String sql="SELECT * FROM product"+queryObject.getQuery();
        return JdbcTemplate.query(sql,new ProductResultSetHandler(),queryObject.getParam().toArray());
    }
}

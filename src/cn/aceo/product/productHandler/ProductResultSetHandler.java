package cn.aceo.product.productHandler;

import cn.aceo.product.domain.Product;
import cn.aceo.product.template.IResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductResultSetHandler implements IResultSetHandler<List<Product>> {

    @Override
    public List<Product> handdler(ResultSet rs) throws SQLException {
        //结果集处理
        List<Product> list=new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setProductName(rs.getString("productName"));
            product.setDir_id(rs.getLong("dir_id"));
            product.setSalePrice(rs.getBigDecimal("salePrice"));
            product.setSupplier(rs.getString("supplier"));
            product.setBrand(rs.getString("brand"));
            product.setCostPrice(rs.getBigDecimal("costPrice"));
            product.setCutoff(rs.getDouble("cutoff"));
            list.add(product);
        }
        return list;
    }
}

package cn.aceo.product.dao;

import cn.aceo.product.domain.Product;
import cn.aceo.product.query.ProductQueryObject;

import java.math.BigDecimal;
import java.util.List;

public interface IProductDao {
    /**
     *
     * @param product  需要保存的商品信息
     */
    void save(Product product);
    /**
     * 删除操作
     * @param id   需要操作商品信息的ID
     */
    void delete(Long id);

    /**
     * 更新操作
     * @param id   需要操作的商品信息ID
     * @param product   需要更新的商品信息
     */
    void update(Long id, Product product);

    /**
     * 根据id返回一条商品信息
     * @param id  需要操作的商品信息ID
     * @return  返回一个product对象，如果没有信息就返回为null
     */
    Product get(Long id);

    /**
     * 查询所有的信息
     * @return  返回一个list集合对象，如果没有信息就返回一个空的list对象
     */
    List<Product> listAll();

    /**
     * 高级查询
     * @param name
     * @param maxSalePrice
     * @param minSalePrice
     * @return
     */
    List<Product> query(ProductQueryObject queryObject);
}

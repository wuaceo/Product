package cn.aceo.product.test;

import cn.aceo.product.dao.impl.ProductDaoImpl;
import cn.aceo.product.domain.Product;
import cn.aceo.product.query.ProductQueryObject;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class ProductDaoTest {
    ProductDaoImpl productDao=new ProductDaoImpl();
    //增
    @Test
    public void saveTest(){
        Product p=new Product();
        p=productDao.get(1L);
        p.setProductName("我是新来的2号");
        System.out.println(p);
        productDao.save(p);
    }
    //删
    @Test
    public void deleteTest(){
        productDao.delete(38L);
    }
    //改
    @Test
    public void updateTest(){
        Product p=new Product();
        p=  productDao.get(1L);
        p.setProductName("我是小宝贝88号");
        productDao.update(36L,p);
    }
    //id  查一条
    @Test
    public void getTest(){

        Product p=new Product();
                p=productDao.get(35L);
        System.out.println(p);
    }
    //查
    @Test
    public void listAllTest(){
            List<Product> list=productDao.listAll();
        for (Product p:list) {
            System.out.println(p);
        }
    }
    @Test
    public void queryTest(){
        ProductQueryObject queryObject=new ProductQueryObject();
        queryObject.setName(null);
        queryObject.setMinSalePrice(new BigDecimal(10));
        queryObject.setMaxSalePrice(new BigDecimal(200));
        queryObject.setDir_id(1L);
        System.out.println(queryObject);
        //========================
        List<Product> list = productDao.query(queryObject);
        for (Product p:list) {
            System.out.println(p);
        }
    }
}

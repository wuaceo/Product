package cn.aceo.product.test;

import cn.aceo.product.dao.impl.ProductDirDaoImpl;
import cn.aceo.product.domain.ProductDir;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoDirTest {
    ProductDirDaoImpl productDirDao=new ProductDirDaoImpl();
    @Test
    public void listAllTest(){
        List<ProductDir> productDirs =new ArrayList<>();
        productDirs=  productDirDao.listAll();
        for (ProductDir p:productDirs) {
            System.out.println(p);
        }
    }
}

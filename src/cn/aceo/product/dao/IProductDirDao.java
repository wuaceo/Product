package cn.aceo.product.dao;

import cn.aceo.product.domain.ProductDir;

import java.util.List;

public interface IProductDirDao {
    //暂时只提供查询所有

    /**
     * 查询所有信息
     * @return
     */
    List<ProductDir> listAll();
}

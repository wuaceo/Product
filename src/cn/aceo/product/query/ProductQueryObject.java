package cn.aceo.product.query;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductQueryObject extends  QueryObject{
    //封装高级查询的参数
    private String name;
    private BigDecimal minSalePrice;
    private BigDecimal maxSalePrice;
    private Long dir_id=-1L;
    //自身自定义
    public void customized(){
        if(StringUtils.isNoneBlank(name)){
            addQuery("productName LIKE ?","%"+name+"%");
        }
        if(minSalePrice!=null){
            addQuery("salePrice >= ?",minSalePrice);
        }
        if(maxSalePrice!=null){
            addQuery("salePrice <= ?",maxSalePrice);
        }
        if(dir_id!=-1){
            addQuery("dir_id = ?",dir_id);
        }
    }
}

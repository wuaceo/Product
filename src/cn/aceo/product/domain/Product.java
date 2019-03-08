package cn.aceo.product.domain;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Product {
    private Long id;//
    private String productName;//商品名
    private Long dir_id;//商品分类ID
    private BigDecimal salePrice;//售价
    private String supplier;//供应商
    private String brand;//品牌
    private  Double cutoff;//折扣
    private  BigDecimal costPrice;//成本价
}

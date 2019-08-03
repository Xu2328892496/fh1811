package com.fh.shop.api.product.mapper;

import com.fh.shop.api.product.po.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IProductMapper {

    List<Product> queryProductList(@Param("id") Integer id);
}

package com.fh.shop.api.product.biz;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.api.common.HardCode;
import com.fh.shop.api.product.mapper.IProductMapper;
import com.fh.shop.api.product.po.Product;
import com.fh.shop.api.product.vo.ProductVo;
import com.fh.shop.jar.RedisAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductMapper productMapper;

    public List<ProductVo> queryProductList(Integer id) {
        //获取redis中的缓存
        String str = RedisAction.get(HardCode.PRODUCT_REDIS);
        //判断缓存中是否存在该缓存
        if (str != null){
            //将获取到的数据转换格式
            List<ProductVo> productVoList = JSONObject.parseArray(str, ProductVo.class);
            return productVoList;
        }
        List<Product> product = productMapper.queryProductList(id);
        //转换数据格式
        String proStr = JSONObject.toJSONString(product);
        RedisAction.setex(HardCode.PRODUCT_REDIS , proStr , 30*60);
        return convertVo(product);
    }

    //po转vo
    private List<ProductVo> convertVo(List<Product> productList){
        List<ProductVo> productVoList = new ArrayList<ProductVo>();
        for (Product product : productList) {
            //实例化productVo
            ProductVo productVo = new ProductVo();
            productVo.setId(product.getId());
            productVo.setName(product.getName());
            productVo.setPrice(product.getPrice());
            productVo.setPhotoName(product.getPhotoName());
            productVoList.add(productVo);
        }
        return productVoList;
    }
}

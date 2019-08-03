package com.fh.shop.api.product.controller;

import com.fh.shop.api.annotation.HeaderAnnotation;
import com.fh.shop.api.product.biz.IProductService;
import com.fh.shop.api.product.common.ServerException;
import com.fh.shop.api.product.vo.ProductVo;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource(name = "productService")
    private IProductService productService;

    @RequestMapping(value = "/toList" , method = {RequestMethod.GET , RequestMethod.POST})
    public Object toList(String callback , Integer id){
        List<ProductVo> productVoList = productService.queryProductList(id);
        ServerException serverException = ServerException.serverException(productVoList);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(serverException);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}

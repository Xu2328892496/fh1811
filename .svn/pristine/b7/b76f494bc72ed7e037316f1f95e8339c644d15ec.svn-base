package com.fh.shop.api.brand.controller;

import com.fh.shop.api.brand.biz.IBrandService;
import com.fh.shop.api.product.common.ServerException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Resource(name = "brandServiceImpl")
    private IBrandService iBrandService;

    //查询品牌列表
    @RequestMapping(value = "queryBrandList" , method = RequestMethod.GET)
    public ServerException queryBrandList(){
        return iBrandService.queryBrandList();
    }
}

package com.fh.shop.api.area.controller;

import com.fh.shop.api.area.biz.IAreaService;
import com.fh.shop.api.product.common.ServerException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("area")
public class AreaController {

    @Resource(name = "areaServiceImpl")
    private IAreaService iAreaService;

    //查询地区列表
    @RequestMapping(value = "queryAreaList" , method = RequestMethod.GET)
    public ServerException queryAreaList(Integer fid){
        return iAreaService.queryAreaList(fid);
    }
}

package com.fh.shop.api.member.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.fh.shop.api.annotation.HeaderAnnotation;
import com.fh.shop.api.common.HardCode;
import com.fh.shop.api.member.biz.IMemberService;
import com.fh.shop.api.member.po.Member;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.product.common.ResponseEnum;
import com.fh.shop.api.product.common.ServerException;
import com.fh.shop.api.util.RedisKey;
import com.fh.shop.jar.RedisAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource(name = "memberServiceImpl")
    private IMemberService iMemberService;

    @Autowired
    private HttpServletRequest request;

    //判断会员名是否存在
    @RequestMapping(value = "queryName" , method = RequestMethod.GET)
    public ServerException queryName(String name){
        String str = iMemberService.queryName(name);
        //判断会员名是否存在
        if (StringUtils.isNotEmpty(str)){
            return ServerException.serverException(ResponseEnum.MEMBER_EXIST);
        }
        return ServerException.serverException();
    }

    //注册会员
    @RequestMapping(value = "saveMember" , method = RequestMethod.POST)
    public ServerException saveMember(Member member){
        return iMemberService.saveMember(member);
    }

    //登录
    @RequestMapping(value = "login" , method = RequestMethod.GET)
    public ServerException login(String memberName , String password){
        return iMemberService.login(memberName , password);
    }

    //展示头信息
    @HeaderAnnotation
    @RequestMapping(value = "findMemberName" , method = RequestMethod.GET)
    public ServerException findMemberName(){
        //获取信息
        MemberVo memberVo = (MemberVo) request.getAttribute(HardCode.MEMBERVO);
        return ServerException.serverException(memberVo);
    }

    //退出
    @HeaderAnnotation
    @RequestMapping(value = "exit" , method = RequestMethod.GET)
    public ServerException exit(){
        //获取信息
        MemberVo memberVo = (MemberVo) request.getAttribute(HardCode.MEMBERVO);
        RedisAction.del(RedisKey.redisKey(memberVo.getName() , memberVo.getUuid()));
        return ServerException.serverException();
    }
}

package com.fh.shop.api.member.biz;

import com.alibaba.fastjson.JSONObject;
import com.fh.shop.api.common.HardCode;
import com.fh.shop.api.member.mapper.IMemberMapper;
import com.fh.shop.api.member.po.Member;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.product.common.ResponseEnum;
import com.fh.shop.api.product.common.ServerException;
import com.fh.shop.api.util.RedisKey;
import com.fh.shop.jar.Md5Util;
import com.fh.shop.jar.RedisAction;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("memberServiceImpl")
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private IMemberMapper iMemberMapper;

    //判断会员是否存在
    public String queryName(String name) {
        String str = iMemberMapper.queryName(name);
        return str;
    }

    //注册会员
    public ServerException saveMember(Member member) {
        //判断各个字段不为空
        String name = member.getName();
        String passwd = member.getPasswd();
        String realName = member.getRealName();
        String emily = member.getEmily();
        Date birthday = member.getBirthday();
        String phone = member.getPhone();
        String code = member.getCode();
        Integer a1 = member.getA1();
        Integer a2 = member.getA2();
        Integer a3 = member.getA3();
        if (StringUtils.isEmpty(name)){
            return ServerException.serverException(ResponseEnum.MEMBER_NAME_IS_EMPTY);
        }
        if (StringUtils.isEmpty(passwd)){
            return ServerException.serverException(ResponseEnum.MEMBER_PASSWD_IS_EMPTY);
        }
        if (StringUtils.isEmpty(realName)){
            return ServerException.serverException(ResponseEnum.MEMBER_REALNAME_IS_EMPTY);
        }
        Pattern pattern1 = Pattern.compile("^[\\u4e00-\\u9fa5]+$");
        Matcher matcher1 = pattern1.matcher(realName);
        if (!matcher1.matches()){
            return ServerException.serverException(ResponseEnum.MEMBER_REALNAME_ISCHINESE);
        }
        if (StringUtils.isEmpty(emily)){
            return ServerException.serverException(ResponseEnum.MEMBER_EMILY_IS_EMPTY);
        }
        if (birthday == null){
            return ServerException.serverException(ResponseEnum.MEMBER_BIRTHDAY_IS_EMPTY);
        }
        if (StringUtils.isEmpty(phone)){
            return ServerException.serverException(ResponseEnum.MEMBER_PHONE_IS_EMPTY);
        }
        Pattern pattern = Pattern.compile("^[1][358]+\\d{9}$");
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()){
            return ServerException.serverException(ResponseEnum.SMS_PHONE_ERROR);
        }
        if (StringUtils.isEmpty(code)){
            return ServerException.serverException(ResponseEnum.MEMBER_CODE_IS_EMPTY);
        }
        if (a1 == null && a1 == HardCode.REGISTER_MEMBER_AREA){
            return ServerException.serverException(ResponseEnum.MEMBER_A1_IS_EMPTY);
        }
        if (a2 == null && a2 == HardCode.REGISTER_MEMBER_AREA){
            return ServerException.serverException(ResponseEnum.MEMBER_A2_IS_EMPTY);
        }
        if (a3 == null && a3 == HardCode.REGISTER_MEMBER_AREA){
            return ServerException.serverException(ResponseEnum.MEMBER_A3_IS_EMPTY);
        }
        //判断验证码是否过期
        boolean exist = RedisAction.exist(RedisKey.smsCode(phone));
        if (!exist){
            return ServerException.serverException(ResponseEnum.SMS_CODE_TIMEOUT);
        }
        //获取验证码
        String smsCode = RedisAction.get(RedisKey.smsCode(phone));
        if (!code.equals(smsCode)){
            return ServerException.serverException(ResponseEnum.SMS_CODE_ERROR);
        }
        //生成salt盐
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //M5进行加密
        member.setPasswd(Md5Util.md5(Md5Util.md5(passwd)+uuid));
        //设置salt盐
        member.setSalt(uuid);
        iMemberMapper.saveMember(member);
        return ServerException.serverException();
    }

    //登录验证
    public ServerException login(String memberName , String password) {
        //验证会员名和密码不能为空
        if (StringUtils.isEmpty(memberName) || StringUtils.isEmpty(password)){
            //如果为空返回前台 , 并给予提示
            return ServerException.serverException(ResponseEnum.MEMBER_NOTEMPTY);
        }
        //查询会员名是否存在
        Member member = iMemberMapper.login(memberName);
        //判断会员名是否存在
        if (member == null){
            //会员名不存在 , 给予提示
            return ServerException.serverException(ResponseEnum.MEMBERNAME_ERROR);
        }
        //判断会员密码是否正确
        if (!Md5Util.md5(password+member.getSalt()).equals(member.getPasswd())){
            //密码不正确 , 给予提示
            return ServerException.serverException(ResponseEnum.MEMBERPASSWD_ERROR);
        }
        //判断会员是否被停用
        if (member.getDead().equals("0")){
            return ServerException.serverException(ResponseEnum.MEMBER_DEAD);
        }
        MemberVo memberVo = new MemberVo();
        memberVo.setId(member.getId());
        memberVo.setName(member.getName());
        memberVo.setRealName(member.getRealName());
        String uuid = UUID.randomUUID().toString().replace("-", "");
        memberVo.setUuid(uuid);
        String str = JSONObject.toJSONString(memberVo);
        //64位编码
        String base64 = Base64.getEncoder().encodeToString(str.getBytes());
        //生成签名 , 秘钥是关键
        String secret = Base64.getEncoder().encodeToString(Md5Util.sign(base64, HardCode.SECRET).getBytes());
        //登录成功设置过期时间
        RedisAction.setex(RedisKey.redisKey(member.getName() , uuid) , "1" , HardCode.EXPIRE_SECONDS);
        return ServerException.serverException(base64+"."+secret);
    }
}

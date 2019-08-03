package com.fh.shop.api.member.biz;

import com.fh.shop.api.member.po.Member;
import com.fh.shop.api.product.common.ServerException;

public interface IMemberService {

    String queryName(String name);

    ServerException saveMember(Member member);

    ServerException login(String memberName , String password);
}

package com.fh.shop.api.member.mapper;

import com.fh.shop.api.member.po.Member;

public interface IMemberMapper {

    String queryName(String name);

    void saveMember(Member member);

    Member login(String memberName);
}

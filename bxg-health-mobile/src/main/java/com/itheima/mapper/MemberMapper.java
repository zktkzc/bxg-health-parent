package com.itheima.mapper;

import com.itheima.pojo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {
    @Select("select * from t_member where name=#{name}")
    Member selectByName(String name);

    void update(Member member);

    @Select("select * from t_member where id=#{memberId}")
    Member selectById(Integer memberId);
}

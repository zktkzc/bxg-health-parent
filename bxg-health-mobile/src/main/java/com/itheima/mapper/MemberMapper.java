package com.itheima.mapper;

import com.itheima.pojo.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {
    @Select("select * from t_member where name=#{name}")
    Member selectByName(String name);

    void update(Member member);

    @Select("select * from t_member where id=#{memberId}")
    Member selectById(Integer memberId);

    @Select("select * from t_member where phoneNumber=#{telephone}")
    Member selectByPhone(String telephone);

    @Insert("insert into t_member(fileNumber, name, sex, idCard, phoneNumber, regTime, password, email, birthday, remark) " +
            "values (#{fileNumber} ,#{name} ,#{sex} ,#{idCard} ,#{phoneNumber} ,#{regTime} ,#{password} ,#{email} ,#{birthday} ,#{remark})")
    void insert(Member member);
}

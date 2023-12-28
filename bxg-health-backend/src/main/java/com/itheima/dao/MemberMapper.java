package com.itheima.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

@Mapper
public interface MemberMapper {
    @Select("select count(*) from t_member where regTime <= #{month}")
    Integer countMemberByMonth(LocalDate month);

    @Select("select count(*) from t_member where regTime = #{today}")
    Integer countByDate(LocalDate today);

    @Select("select count(*) from t_member")
    Integer selectAll();

    @Select("select count(*) from t_member where regTime between #{begin} and #{end}")
    Integer countMemberByWeek(@Param("begin") LocalDate begin, @Param("end") LocalDate end);

    @Select("select count(*) from t_member where regTime like concat(#{month}, '%')")
    Integer countNewMemberByMonth(String month);
}

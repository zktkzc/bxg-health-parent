package com.itheima.controller;

import com.itheima.common.constant.MessageConstant;
import com.itheima.common.dto.LoginDTO;
import com.itheima.common.entity.Result;
import com.itheima.mapper.MemberMapper;
import com.itheima.pojo.Member;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private MemberMapper memberMapper;

    /**
     * 手机验证码登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        String code = (String) redisTemplate.opsForValue().get("ValidateCode4Login_" + loginDTO.getTelephone());
        if (code == null) {
            return Result.fail("验证码已过期");
        } else {
            if (!code.equals(loginDTO.getValidateCode())) {
                return Result.fail(MessageConstant.VALIDATECODE_ERROR);
            }
        }
        Member member = memberMapper.selectByPhone(loginDTO.getTelephone());
        if (member == null) {
            member = new Member();
            member.setPhoneNumber(loginDTO.getTelephone());
            member.setRegTime(new Date());
            memberMapper.insert(member);
        }
        return Result.success("登录成功");
    }
}

package com.itheima.controller;

import com.itheima.common.constant.MessageConstant;
import com.itheima.common.dto.ValidateDTO;
import com.itheima.common.entity.Result;
import com.itheima.common.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/validatecode")
@Slf4j
public class ValidateCodeController {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 发送短信验证码
     */
    @GetMapping("/send4Login")
    public Result send4Login(@RequestBody ValidateDTO validateDTO) {
        // 生成验证码
        String validateCode = ValidateCodeUtils.generateValidateCode4String(6);
        log.info("发送登录验证码成功，手机号：{}，验证码：{}", validateDTO.getTelephone(), validateCode);
        redisTemplate.delete("ValidateCode4Login_" + validateDTO.getTelephone());
        redisTemplate.opsForValue().set("ValidateCode4Login_" + validateDTO.getTelephone(), validateCode, 5, TimeUnit.MINUTES);
        return Result.success(MessageConstant.SEND_VALIDATECODE_SUCCESS, validateCode);
    }

    /**
     * 体检预约发送验证码
     */
    @PostMapping("/send4Order")
    public Result send4Order(@RequestBody ValidateDTO validateDTO) {
        // 生成验证码
        String validateCode = ValidateCodeUtils.generateValidateCode4String(4);
        log.info("发送预约验证码成功，手机号：{}，验证码：{}", validateDTO.getTelephone(), validateCode);
        redisTemplate.delete("ValidateCode4Order_" + validateDTO.getTelephone());
        redisTemplate.opsForValue().set("ValidateCode4Order_" + validateDTO.getTelephone(), validateCode, 5, TimeUnit.MINUTES);
        return Result.success(MessageConstant.SEND_VALIDATECODE_SUCCESS, validateCode);
    }
}

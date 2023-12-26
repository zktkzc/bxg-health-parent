package com.itheima.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Set;

@Component
@Slf4j
public class SetmealTask {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 每分钟执行一次，对比数据库中的图片和oss中的图片，找出oss中的垃圾图片
     */
    @Scheduled(cron = "0 * * * * ?")
    public void compareImage() {
        Set difference = redisTemplate.opsForSet().difference("oss_images", "db_images");
        log.info("oss里的垃圾图片：{}", Arrays.toString(difference.toArray()));
    }
}

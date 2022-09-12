package com.shy.mall.tiny.service.impl;

import com.shy.mall.tiny.api.CommonResult;
import com.shy.mall.tiny.service.RedisService;
import com.shy.mall.tiny.service.UmsMemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author hongyuan.shan
 * @date 2022/09/12 14:41
 * @description
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public CommonResult generateAuthCode(String phone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + phone, sb.toString());
        System.out.println("AUTH_CODE_EXPIRE_SECONDS = " + AUTH_CODE_EXPIRE_SECONDS);
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE, AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(), "获取验证码成功");
    }

    @Override
    public CommonResult verifyAuthCode(String phone, String authCode) {
        if (StringUtils.isBlank(authCode)){
            return CommonResult.failed("请输入验证码");
        }
        String realAuthCode  = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + phone);

        if (StringUtils.isNotBlank(realAuthCode) && realAuthCode.equals(authCode)){
            return CommonResult.success( "验证码校验成功");
        }else {
            return CommonResult.failed("验证码不正确");
        }
    }
}

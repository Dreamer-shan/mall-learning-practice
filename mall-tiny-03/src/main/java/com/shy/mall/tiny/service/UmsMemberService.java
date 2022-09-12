package com.shy.mall.tiny.service;

import com.shy.mall.tiny.api.CommonResult;

/**
 * @author hongyuan.shan
 * @date 2022/09/12 14:40
 * @description
 */

public interface UmsMemberService {

    CommonResult generateAuthCode(String phone);

    CommonResult verifyAuthCode(String phone, String authCode);
}

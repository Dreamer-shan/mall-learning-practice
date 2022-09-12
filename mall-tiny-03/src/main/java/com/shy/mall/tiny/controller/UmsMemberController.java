package com.shy.mall.tiny.controller;

import com.shy.mall.tiny.api.CommonResult;
import com.shy.mall.tiny.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hongyuan.shan
 * @date 2022/09/12 14:34
 * @description
 */
@RestController
@RequestMapping("/sso")
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
public class UmsMemberController {
    @Autowired
    private UmsMemberService umsMemberService;

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(String phone){
        return umsMemberService.generateAuthCode(phone);
    }

    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updatePassword(String phone, String authCode){
        return umsMemberService.verifyAuthCode(phone, authCode);
    }
}

package com.shy.mall.tiny.controller;

import com.shy.mall.tiny.api.CommonPage;
import com.shy.mall.tiny.api.CommonResult;
import com.shy.mall.tiny.mbg.model.PmsBrand;
import com.shy.mall.tiny.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author :shy
 * @date : 2022/9/1 0:17
 */
@RestController
@RequestMapping("/brand")
@Api(tags = "PmsBrandController", description = "商品品牌管理")

@Slf4j
public class PmsBrandController {
    @Autowired
    private PmsBrandService pmsBrandService;

    @ApiOperation("获取所有品牌列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> BrandListByPage(Integer pageNum, Integer pageSize) {
        List<PmsBrand> brandList = pmsBrandService.listBrandByPage(pageNum, pageSize);
        CommonPage<PmsBrand> page = CommonPage.restPage(brandList);
        return CommonResult.success(page);
    }

    @ApiOperation("添加品牌")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    // 忘记加@RequestBody注解的话，参数是传不进来的，识别的时候全都是null
    public CommonResult creatBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int result = pmsBrandService.creatBrand(pmsBrand);
        if (result == 1){
            commonResult = CommonResult.success(pmsBrand);
            log.info("creatBrand success:{}", pmsBrand);
        }else {
            commonResult = CommonResult.failed("操作失败");
            log.error("creatBrand fail:{}", pmsBrand);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的品牌")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    // 忘记加@RequestBody注解的话，使用json参数是传不进来的，识别的时候全都是null
    public CommonResult creatBrand( Long id) {
        CommonResult commonResult;
        int result = pmsBrandService.deleteBrand(id);
        if (result == 1){
            commonResult = CommonResult.success(null);
            log.info("deleteBrand success, id:{}", id);
        }else {
            commonResult = CommonResult.failed("删除失败");
            log.error("deleteBrand fail, id:{}", id);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id品牌信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = pmsBrandService.updateBrand(id, pmsBrand);
        if (count == 1){
            commonResult = CommonResult.success(pmsBrand);
            log.debug("updateBrand success:{}", pmsBrand);
        }else {
            commonResult = CommonResult.failed("操作失败");
            log.debug("updateBrand failed:{}", pmsBrand);
        }
        System.out.println("aaaa");
        return commonResult;
    }

    @ApiOperation("获取指定id的品牌详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
//    @PathVariable("id")相当于用占位符绑定id这个参数  http://localhost:8080/brand/3这么请求就好了
//    如果没有@PathVariable("id"),就要这么请求 http://localhost:8080/brand/id?id=1
    public CommonResult<PmsBrand> updateBrand(@PathVariable("id") Long id) {
        CommonResult commonResult;
        PmsBrand pmsBrand = pmsBrandService.selectBrandById(id);
        if (Objects.nonNull(pmsBrand)){
            commonResult = CommonResult.success(pmsBrand);
            log.debug("queryBrand success:{}", pmsBrand);
        }else {
            commonResult = CommonResult.failed("操作失败");
            log.debug("queryBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }
}



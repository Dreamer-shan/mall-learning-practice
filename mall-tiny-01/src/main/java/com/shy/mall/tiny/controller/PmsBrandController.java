package com.shy.mall.tiny.controller;

import com.shy.mall.tiny.api.CommonPage;
import com.shy.mall.tiny.api.CommonResult;
import com.shy.mall.tiny.mbg.model.PmsBrand;
import com.shy.mall.tiny.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author :shy
 * @date : 2022/9/1 0:17
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);
    @Autowired
    private PmsBrandService pmsBrandService;

    // @RequestMapping("/listAll")
    // public List<PmsBrand> listAll(){
    //     return pmsBrandService.listAll();
    // }

    @RequestMapping("/listAll")
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @RequestMapping("/list")
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> BrandListByPage(Integer pageNum, Integer pageSize) {
        List<PmsBrand> brandList = pmsBrandService.listBrandByPage(pageNum, pageSize);
        CommonPage<PmsBrand> page = CommonPage.restPage(brandList);
        return CommonResult.success(page);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = pmsBrandService.updateBrand(id, pmsBrand);
        if (count == 1){
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("updateBrand success:{}", pmsBrand);
        }else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateBrand failed:{}", pmsBrand);
        }
        System.out.println("aaaa");
        return commonResult;
        // 加一行注释

    }

}



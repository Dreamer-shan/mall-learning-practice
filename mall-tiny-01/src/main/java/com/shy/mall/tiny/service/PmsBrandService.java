package com.shy.mall.tiny.service;

import com.shy.mall.tiny.mbg.model.PmsBrand;

import java.util.List;

/**
 * @author :shy
 * @date : 2022/9/1 0:18
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    List<PmsBrand> listBrandByPage(Integer pageNum, Integer pageSize);

    int updateBrand(Long id, PmsBrand brand);
}

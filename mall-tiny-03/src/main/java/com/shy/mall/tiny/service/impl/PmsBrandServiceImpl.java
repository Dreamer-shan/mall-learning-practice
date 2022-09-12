package com.shy.mall.tiny.service.impl;

import com.github.pagehelper.PageHelper;
import com.shy.mall.tiny.mbg.mapper.PmsBrandMapper;
import com.shy.mall.tiny.mbg.model.PmsBrand;
import com.shy.mall.tiny.mbg.model.PmsBrandExample;

import com.shy.mall.tiny.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :shy
 * @date : 2022/9/1 0:19
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        return pmsBrandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public List<PmsBrand> listBrandByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return pmsBrandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public int updateBrand(Long id, PmsBrand brand) {
        brand.setId(id);
        return pmsBrandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int creatBrand(PmsBrand pmsBrand) {
        int count = pmsBrandMapper.insert(pmsBrand);
        return count;
    }

    @Override
    public int deleteBrand(Long id) {
        int count = pmsBrandMapper.deleteByPrimaryKey(id);
        return count;
    }

    @Override
    public PmsBrand selectBrandById(Long id) {
        PmsBrand pmsBrand = pmsBrandMapper.selectByPrimaryKey(id);
        return pmsBrand;
    }
}

package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.macro.mall.mapper.PmsBrandMapper;
import com.macro.mall.mapper.PmsProductMapper;
import com.macro.mall.model.PmsBrand;
import com.macro.mall.model.PmsBrandExample;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.model.PmsProductExample;
import com.macro.mall.query.PmsBrandParam;
import com.macro.mall.service.PmsBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 商品品牌业务
 */
@Service
@Transactional
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    @Autowired
    private PmsProductMapper pmsProductMapper;

    @Override //查询所有的商品品牌
    @Transactional(readOnly = true)
    public List<PmsBrand> listAllBrand() {
        return pmsBrandMapper.selectByExample(new PmsBrandExample());
    }

    @Override//分页查询商品品牌
    @Transactional(readOnly = true)
    public List<PmsBrand> listBrand(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);//mybatis分页插件
        PmsBrandExample pmsBrandExample=new PmsBrandExample();
        pmsBrandExample.setOrderByClause("sort desc");//倒序
        if(!StringUtils.isEmpty(keyword)){
            PmsBrandExample.Criteria criteria = pmsBrandExample.createCriteria();
            criteria.andNameLike("%"+keyword+"%");
        }
        return pmsBrandMapper.selectByExample(pmsBrandExample);
    }

    @Override//添加商品品牌
    public Integer create(PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand=new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam,pmsBrand);
        //如果品牌的首字母为空 拿品牌名称的第一个首字母
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())){
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0,1));
        }
        return pmsBrandMapper.insertSelective(pmsBrand);
    }

    @Override//查询商品品牌
    public PmsBrand getPmsBrand(Long id) {
        return pmsBrandMapper.selectByPrimaryKey(id);
    }

    @Override//修改商品品牌
    public Integer update(Long id, PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand=new PmsBrand();
        pmsBrand.setId(id);
        BeanUtils.copyProperties(pmsBrandParam,pmsBrand);
        //如果品牌的首字母为空 拿品牌名称的第一个首字母
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())){
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0,1));
        }
        //修改商品中的品牌名称
        PmsProduct pmsProduct=new PmsProduct();
        pmsProduct.setBrandName(pmsBrand.getName());
        PmsProductExample pmsProductExample=new PmsProductExample();
        pmsProductExample.createCriteria().andBrandIdEqualTo(id);
        pmsProductMapper.updateByExampleSelective(pmsProduct,pmsProductExample);
        //修改品牌
        PmsBrandExample pmsBrandExample=new PmsBrandExample();
        pmsBrandExample.createCriteria().andIdEqualTo(id);
        return pmsBrandMapper.updateByExample(pmsBrand,pmsBrandExample);
    }

    @Override//删除商品品牌
    public Integer delete(Long id) {
        return pmsBrandMapper.deleteByPrimaryKey(id);
    }

    @Override//批量修改商品品牌是否是制造商
    public Integer updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        PmsBrand pmsBrand= new PmsBrand();
        pmsBrand.setFactoryStatus(factoryStatus);
        PmsBrandExample pmsBrandExample=new PmsBrandExample();
        pmsBrandExample.createCriteria().andIdIn(ids);
        return pmsBrandMapper.updateByExampleSelective(pmsBrand,pmsBrandExample);
    }


    @Override//批量修改商品品牌是否显示
    public Integer updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsBrand pmsBrand=new PmsBrand();
        pmsBrand.setShowStatus(showStatus);
        PmsBrandExample pmsBrandExample=new PmsBrandExample();
        pmsBrandExample.createCriteria().andIdIn(ids);
        return pmsBrandMapper.updateByExampleSelective(pmsBrand,pmsBrandExample);
    }
}

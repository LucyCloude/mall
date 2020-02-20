package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.PmsProductResultDao;
import com.macro.mall.mapper.*;
import com.macro.mall.model.*;
import com.macro.mall.query.PmsProductParam;
import com.macro.mall.query.PmsProductQueryParam;
import com.macro.mall.query.PmsProductResult;
import com.macro.mall.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 商品业务实现类
 */
@Service
@Transactional
public class PmsProductServiceImpl implements PmsProductService {
    @Autowired
    private PmsProductMapper pmsProductMapper;
    @Autowired//商品会员价格
    private PmsMemberPriceMapper pmsMemberPriceMapper;
    @Autowired//商品阶梯价格
    private PmsProductLadderMapper pmsProductLadderMapper;
    @Autowired//商品满减价格
    private PmsProductFullReductionMapper pmsProductFullReductionMapper;
    @Autowired//商品SKU
    private PmsSkuStockMapper pmsSkuStockMapper;
    @Autowired//商品参数规格属性
    private PmsProductAttributeValueMapper pmsProductAttributeValueMapper;
    @Autowired//专题
    private CmsSubjectProductRelationMapper cmsSubjectProductRelationMapper;
    @Autowired//优选专区
    private CmsPrefrenceAreaProductRelationMapper cmsPrefrenceAreaProductRelationMapper;
    @Autowired
    private PmsProductResultDao pmsProductResultDao;

    @Override//保存商品
    public Integer create(PmsProductParam pmsProductParam) {
        int success=0;
        pmsProductParam.setId(null);
        pmsProductMapper.insertSelective(pmsProductParam);
        //保存相关联的 商品会员价格
        insertAssociated(pmsMemberPriceMapper,pmsProductParam.getMemberPriceList(),pmsProductParam.getId());
        //保存相关联的 商品阶梯价格
        insertAssociated(pmsProductLadderMapper,pmsProductParam.getProductLadderList(),pmsProductParam.getId());
        //保存相关联的 商品满减价格
        insertAssociated(pmsProductFullReductionMapper,pmsProductParam.getProductFullReductionList(),pmsProductParam.getId());
        //给每个商品SKU生成SKU编码
        skuStrockCoke(pmsProductParam.getSkuStockList(),pmsProductParam.getId());
        //保存相关联的 商品SKU
        insertAssociated(pmsSkuStockMapper,pmsProductParam.getSkuStockList(),pmsProductParam.getId());
        //保存相关联的 商品参数规格属性
        insertAssociated(pmsProductAttributeValueMapper,pmsProductParam.getProductAttributeValueList(),pmsProductParam.getId());
        //保存相关联的 专题
        insertAssociated(cmsSubjectProductRelationMapper,pmsProductParam.getSubjectProductRelationList(),pmsProductParam.getId());
        //保存相关联的 优选专区
        insertAssociated(cmsPrefrenceAreaProductRelationMapper,pmsProductParam.getPrefrenceAreaProductRelationList(),pmsProductParam.getId());
        success=1;
        return success;
    }

    @Override//查询单个商品及关联的属性到修改页面
    public PmsProductResult getUpdateInfo(Long id) {
        PmsProductResult updateInfo = pmsProductResultDao.getUpdateInfo(id);
 /*       System.out.println(updateInfo.getSubjectProductRelationList().size()+" -------------");
        System.out.println(updateInfo.getPrefrenceAreaProductRelationList().size()+" ---------------------");
*/        return updateInfo;
    }


    //设置关联并且保存关联对象
    private  <M,T> void insertAssociated(M mapper,List<T> list,Long productId){
        try{
            if (CollectionUtils.isEmpty(list)) return;//判断list是否为null 如果没有元素存储一样 返回true
            for (T entity:list) {
                Method setId = entity.getClass().getMethod("setId", Long.class);
                setId.invoke(entity,(Long)null);//给关联对象主键设置null
                Method productId1 = entity.getClass().getMethod("setProductId", Long.class);
                productId1.invoke(entity,productId);//给关联对象 对对应商品的id设值关联
                //每循环一次保存该关联商品的对象
                Method insertSelective = mapper.getClass().getMethod("insertSelective", entity.getClass());
                insertSelective.invoke(mapper,entity);
            }
        }catch (Exception e){
            System.out.println(e.getMessage()+"--");
            throw new RuntimeException(e.getMessage());
        }

    }
    //生成sku编码
    private void skuStrockCoke(List<PmsSkuStock> list,Long productId){
        if(CollectionUtils.isEmpty(list)) return;
        for (int i=0;i<list.size();i++){
            if (StringUtils.isEmpty(list.get(i).getSkuCode())){
                StringBuilder sb=new StringBuilder();
                SimpleDateFormat sd=new SimpleDateFormat("yyyyMMdd");
                sb.append(sd.format(new Date()));
                sb.append(String.format("%04d",productId));//如果id为1 生成 0001
                sb.append(String.format("%03d",i+1));//根据循环次数来001
                list.get(i).setSkuCode(sb.toString());
            }

        }
    }


    @Override
    @Transactional(readOnly = true) //商品分页
    public List<PmsProduct> list(PmsProductQueryParam pmsProductQueryParam, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        PmsProductExample pmsProductExample=new PmsProductExample();
        PmsProductExample.Criteria criteria = pmsProductExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);//DeleteStatus 0:未删除  1:已删除
        if (!StringUtils.isEmpty(pmsProductQueryParam.getKeyword())){//商品名称
            criteria.andNameLike("%"+pmsProductQueryParam.getKeyword()+"%");
        }
        if (!StringUtils.isEmpty(pmsProductQueryParam.getProductSn())){//商品货号
            criteria.andProductSnEqualTo(pmsProductQueryParam.getProductSn());
        }
        if (pmsProductQueryParam.getProductCategoryId()!=null){//商品分类id
            criteria.andProductCategoryIdEqualTo(pmsProductQueryParam.getProductCategoryId());
        }
        if (pmsProductQueryParam.getBrandId()!=null){//商品品牌id
            criteria.andBrandIdEqualTo(pmsProductQueryParam.getBrandId());
        }
        if (pmsProductQueryParam.getVerifyStatus()!=null){//上架状态 0:下架 1:上架
            criteria.andVerifyStatusEqualTo(pmsProductQueryParam.getVerifyStatus());
        }
        if (pmsProductQueryParam.getPublishStatus()!=null){//商品的审核状态 0:未审核 1：已审核
            criteria.andPublishStatusEqualTo(pmsProductQueryParam.getPublishStatus());
        }
        return pmsProductMapper.selectByExample(pmsProductExample);
    }

    @Override//批量修改商品的上下架
    public Integer updatePublishStatus(List<Long> ids, Integer publishStatus) {
        PmsProduct pmsProduct=new PmsProduct();
        pmsProduct.setPublishStatus(publishStatus);
        PmsProductExample pmsProductExample=new PmsProductExample();
        PmsProductExample.Criteria criteria = pmsProductExample.createCriteria();
        criteria.andIdIn(ids);  //注意使用Selective结尾的update
        return pmsProductMapper.updateByExampleSelective(pmsProduct, pmsProductExample);
    }

    @Override//批量修改商品的推荐
    public Integer updateRecommandStatus(List<Long> ids, Integer recommandStatus){
        PmsProduct pmsProduct=new PmsProduct();
        pmsProduct.setRecommandStatus(recommandStatus);
        PmsProductExample pmsProductExample=new PmsProductExample();
        PmsProductExample.Criteria criteria = pmsProductExample.createCriteria();
        criteria.andIdIn(ids);
        return pmsProductMapper.updateByExampleSelective(pmsProduct,pmsProductExample);
    }

    @Override//批量修改商品的新品
    public Integer updateNewStatus(List<Long> ids, Integer newStatus) {
        PmsProduct pmsProduct=new PmsProduct();
        pmsProduct.setNewStatus(newStatus);
        PmsProductExample pmsProductExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = pmsProductExample.createCriteria();
        criteria.andIdIn(ids);
        return pmsProductMapper.updateByExampleSelective(pmsProduct,pmsProductExample);
    }



    @Override
    public Integer update(Long id, PmsProductParam pmsProductParam) {
        Integer count=0;
        pmsProductParam.setId(id);
        pmsProductMapper.updateByPrimaryKeySelective(pmsProductParam);//根据商品的id修改商品的信息
        //删除原来的会员价格
        PmsMemberPriceExample pmsMemberPriceExample=new PmsMemberPriceExample();
        pmsMemberPriceExample.createCriteria().andProductIdEqualTo(id);
        pmsMemberPriceMapper.deleteByExample(pmsMemberPriceExample);
        //保存现在的会员价格
        insertAssociated(pmsMemberPriceMapper,pmsProductParam.getMemberPriceList(),id);
        //删除原来的阶梯价格
        PmsProductLadderExample pmsProductLadderExample=new PmsProductLadderExample();
        pmsMemberPriceExample.createCriteria().andProductIdEqualTo(id);
        pmsProductLadderMapper.deleteByExample(pmsProductLadderExample);
        //保存现在的阶梯价格
        insertAssociated(pmsProductLadderMapper,pmsProductParam.getProductLadderList(),id);
        //删除原来的满减价格
        PmsProductFullReductionExample pmsProductFullReductionExample=new PmsProductFullReductionExample();
        pmsMemberPriceExample.createCriteria().andProductIdEqualTo(id);
        pmsProductFullReductionMapper.deleteByExample(pmsProductFullReductionExample);
        //保存现在的满减价格
        insertAssociated(pmsProductFullReductionMapper,pmsProductParam.getProductFullReductionList(),id);
        //删除原来的SKU编码
        PmsSkuStockExample pmsSkuStockExample=new PmsSkuStockExample();
        pmsSkuStockExample.createCriteria().andProductIdEqualTo(id);
        pmsSkuStockMapper.deleteByExample(pmsSkuStockExample);
        //给每个商品SKU生成SKU编码
        skuStrockCoke(pmsProductParam.getSkuStockList(),pmsProductParam.getId());
        //保存现在的SKU编码
        insertAssociated(pmsSkuStockMapper,pmsProductParam.getSkuStockList(),id);
        //删除原来的属性参数
        PmsProductAttributeValueExample pmsProductAttributeValueExample=new PmsProductAttributeValueExample();
        pmsProductAttributeValueExample.createCriteria().andProductIdEqualTo(id);
        pmsProductAttributeValueMapper.deleteByExample(pmsProductAttributeValueExample);
        //保存现在的属性参数
        insertAssociated(pmsProductAttributeValueMapper,pmsProductParam.getProductAttributeValueList(),id);
        //删除原来的专题
        CmsSubjectProductRelationExample cmsSubjectProductRelationExample=new CmsSubjectProductRelationExample();
        cmsSubjectProductRelationExample.createCriteria().andProductIdEqualTo(id);
        cmsSubjectProductRelationMapper.deleteByExample(cmsSubjectProductRelationExample);
        //保存现在的专题
        insertAssociated(cmsSubjectProductRelationMapper,pmsProductParam.getSubjectProductRelationList(),id);
        //删除原来的优选
        CmsPrefrenceAreaProductRelationExample cmsPrefrenceAreaProductRelationExample=new CmsPrefrenceAreaProductRelationExample();
        cmsPrefrenceAreaProductRelationExample.createCriteria().andProductIdEqualTo(id);
        cmsPrefrenceAreaProductRelationMapper.deleteByExample(cmsPrefrenceAreaProductRelationExample);
        //保存现在的优选
        insertAssociated(cmsPrefrenceAreaProductRelationMapper,pmsProductParam.getPrefrenceAreaProductRelationList(),id);
        count=1;
        return count;
    }


}

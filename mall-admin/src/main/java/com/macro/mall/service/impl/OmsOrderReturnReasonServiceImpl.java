package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.OmsOrderReturnReasonMapper;
import com.macro.mall.model.OmsOrderReturnReason;
import com.macro.mall.model.OmsOrderReturnReasonExample;
import com.macro.mall.service.OmsOrderReturnReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *退货原因设置业务实现类
 */
@Service
@Transactional
public class OmsOrderReturnReasonServiceImpl implements OmsOrderReturnReasonService {

    @Autowired
    private OmsOrderReturnReasonMapper omsOrderReturnReasonMapper;

    /**
     * 分页查询退货原因设置参数
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<OmsOrderReturnReason> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        OmsOrderReturnReasonExample omsOrderReturnReasonExample=new OmsOrderReturnReasonExample();
        omsOrderReturnReasonExample.setOrderByClause("sort desc");
        return omsOrderReturnReasonMapper.selectByExample(omsOrderReturnReasonExample);
    }

    @Override//增加退货原因设置
    public int create(OmsOrderReturnReason omsOrderReturnReason){
        omsOrderReturnReason.setCreateTime(new Date());
       return omsOrderReturnReasonMapper.insertSelective(omsOrderReturnReason);
    }

    @Override//查询指定处指定的退货原因设置
    @Transactional(readOnly = true)
    public OmsOrderReturnReason getReturnReason(Long id) {
        return omsOrderReturnReasonMapper.selectByPrimaryKey(id);
    }

    @Override//修改指定退货原因设置
    public int updateReturnReason(Long id, OmsOrderReturnReason omsOrderReturnReason) {
        omsOrderReturnReason.setId(id);
        return omsOrderReturnReasonMapper.updateByPrimaryKeySelective(omsOrderReturnReason);
    }

    @Override//批量删除退货原因设置
    public int deleteReturnReason(List<Long> ids) {
        OmsOrderReturnReasonExample omsOrderReturnReasonExample=new OmsOrderReturnReasonExample();
        omsOrderReturnReasonExample.createCriteria().andIdIn(ids);
        return omsOrderReturnReasonMapper.deleteByExample(omsOrderReturnReasonExample);
    }
}

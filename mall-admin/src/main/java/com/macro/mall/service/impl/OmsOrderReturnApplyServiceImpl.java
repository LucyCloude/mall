package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.OmsOrderReturnApplyDao;
import com.macro.mall.mapper.OmsOrderReturnApplyMapper;
import com.macro.mall.model.OmsOrderReturnApply;
import com.macro.mall.model.OmsOrderReturnApplyExample;
import com.macro.mall.query.OmsOrderReturnApplyResult;
import com.macro.mall.query.OmsReturnApplyQueryParam;
import com.macro.mall.query.OmsUpdateStatusParam;
import com.macro.mall.service.OmsOrderReturnApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * 退货处理业务实现类
 */

@Service
@Transactional
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {

    @Autowired
    private OmsOrderReturnApplyDao omsOrderReturnApplyDao;

    @Autowired
    private OmsOrderReturnApplyMapper omsOrderReturnApplyMapper;

    @Override//查询退货订单
    @Transactional(readOnly = true)
    public List<OmsOrderReturnApply> getList(OmsReturnApplyQueryParam omsReturnApplyQueryParam, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<OmsOrderReturnApply> list = omsOrderReturnApplyDao.getList(omsReturnApplyQueryParam);
        return list;
    }

    @Override//查询单个退货订单及收货地址
    @Transactional(readOnly = true)
    public OmsOrderReturnApplyResult getReturnApply(Long id) {
        return omsOrderReturnApplyDao.getReturnApply(id);
    }

    @Override//
    public Integer updateStatus(Long id, OmsUpdateStatusParam updateStatusParam) {
        Integer status = updateStatusParam.getStatus();
        OmsOrderReturnApply omsOrderReturnApply=new OmsOrderReturnApply();
        omsOrderReturnApply.setId(id);
        omsOrderReturnApply.setStatus(status);
        if (status.equals(1)){//确认退货
            omsOrderReturnApply.setReturnAmount(updateStatusParam.getReturnAmount());//退货金额
            omsOrderReturnApply.setCompanyAddressId(updateStatusParam.getCompanyAddressId());//退货地址
            omsOrderReturnApply.setHandleTime(new Date());//处理时间
            omsOrderReturnApply.setHandleMan(updateStatusParam.getHandleMan());//处理人
            omsOrderReturnApply.setHandleNote(updateStatusParam.getHandleNote());//处理备注
        }else if(status.equals(2)){//完成退货
            omsOrderReturnApply.setReceiveTime(new Date());//收货时间
            omsOrderReturnApply.setReceiveNote(updateStatusParam.getReceiveNote());//收货备注
        }else if (status.equals(3)){//拒绝退货
            omsOrderReturnApply.setHandleTime(new Date());//处理时间
            omsOrderReturnApply.setHandleMan(updateStatusParam.getHandleMan());//处理人
            omsOrderReturnApply.setHandleNote(updateStatusParam.getHandleNote());//处理备注
        }else {
            return 0;
        }
        return omsOrderReturnApplyMapper.updateByPrimaryKeySelective(omsOrderReturnApply);
    }
}

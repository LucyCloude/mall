package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.OmsOrderDao;
import com.macro.mall.mapper.OmsOrderMapper;
import com.macro.mall.mapper.OmsOrderOperateHistoryMapper;
import com.macro.mall.model.OmsOrder;
import com.macro.mall.model.OmsOrderExample;
import com.macro.mall.model.OmsOrderOperateHistory;
import com.macro.mall.query.OmsOrderDetails;
import com.macro.mall.query.OmsOrderQueryParam;
import com.macro.mall.service.OmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 订单业务实现类
 */
@Service
@Transactional
public class OmsOrderServiceImpl implements OmsOrderService {

    @Autowired
    private OmsOrderMapper omsOrderMapper;
    @Autowired
    private OmsOrderOperateHistoryMapper omsOrderOperateHistoryMapper;
    @Autowired
    private OmsOrderDao omsOrderDao;

    @Override//分页查询订单
    public List<OmsOrder> getList(OmsOrderQueryParam omsOrderQueryParam, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return omsOrderDao.getList(omsOrderQueryParam);
    }

    @Override
    public OmsOrderDetails getOmsOderDetails(Long id) {
        return omsOrderDao.getOmsOderDetails(id);
    }

    @Override//订单备注信息
    public Integer updateNote(Long id, String note, Integer status,String adminName) {
        int count=0;
        OmsOrder omsOrder=new OmsOrder();
        omsOrder.setId(id);
        omsOrder.setNote(note);
        omsOrder.setModifyTime(new Date());
        omsOrderMapper.updateByPrimaryKeySelective(omsOrder);//修改订单备注信息
        OmsOrderOperateHistory omsOrderOperateHistory=new OmsOrderOperateHistory();
        omsOrderOperateHistory.setOrderId(id);
        omsOrderOperateHistory.setOperateMan(adminName);
        omsOrderOperateHistory.setCreateTime(new Date());
        omsOrderOperateHistory.setOrderStatus(status);
        omsOrderOperateHistory.setNote("修改备注信息: "+note);
        omsOrderOperateHistoryMapper.insert(omsOrderOperateHistory);//新增操作订单记录
        count =1;
        return count;
    }
}

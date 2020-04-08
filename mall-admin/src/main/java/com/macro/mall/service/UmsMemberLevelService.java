package com.macro.mall.service;

import com.macro.mall.model.UmsMemberLevel;

import java.util.List;

/**
 * 会员等级业务接口
 */
public interface UmsMemberLevelService {
    /**
     * 查询所有会员等级
     * @param defaultStatus 是否是默认的会员等级 0不是 1是
     * @return
     */
    List<UmsMemberLevel> getList(Integer defaultStatus);
}

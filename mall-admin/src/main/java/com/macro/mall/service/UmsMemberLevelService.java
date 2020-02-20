package com.macro.mall.service;

import com.macro.mall.model.UmsMemberLevel;

import java.util.List;

/**
 * 会员等级业务接口
 */
public interface UmsMemberLevelService {
    List<UmsMemberLevel> getList(Integer defaultStatus);
}

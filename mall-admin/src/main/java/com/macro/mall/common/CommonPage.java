package com.macro.mall.common;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页数据封装类
 */
@Setter
@Getter
public class CommonPage<T> {
    private Integer pageNum;//当前页
    private Integer pageSize;//页的数据数
    private Integer totalPage;//总页数
    private Long total;//总数据
    private List<T> list;//当前页的数据

    /**
     * 将PageHelper分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setTotalPage(pageInfo.getPages());//总页数
        result.setPageNum(pageInfo.getPageNum());//当前页
        result.setPageSize(pageInfo.getPageSize());//页的数据
        result.setTotal(pageInfo.getTotal());//总数据
        result.setList(pageInfo.getList());//当前页的数据
        return result;
    }

    /**
     * 将SpringData分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage(pageInfo.getTotalPages());//总页数
        result.setPageNum(pageInfo.getNumber());//当前页
        result.setPageSize(pageInfo.getSize());//页的数据数
        result.setTotal(pageInfo.getTotalElements());//总数据
        result.setList(pageInfo.getContent());//当前页的数据
        return result;
    }

}

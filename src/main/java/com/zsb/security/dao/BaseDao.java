package com.zsb.security.dao;

import java.util.List;

/**
 * @ClassName BaseDao
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/20 9:56
 * @Version 1.0
 */
public interface BaseDao<T> {
    /**
     * 公共批量添加
     * @param list
     */
    void batchData(List<T> list);

}

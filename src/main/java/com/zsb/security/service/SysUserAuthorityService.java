package com.zsb.security.service;

import com.zsb.security.vo.SysAuthorityVo;
import com.zsb.security.vo.SysUserAuthorityVo;

import java.util.List;

/**
 * @ClassName SysUserAuthorityService
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 15:11
 * @Version 1.0
 */
public interface SysUserAuthorityService {
    /**
     * 根据用户id查询用户权限
     * @param uid
     * @return
     */
    List<SysAuthorityVo> findByUserAuthorityId(int uid);

    /**
     * 查询列表
     * @return
     */
    List<SysAuthorityVo> queryList();

    /**
     * 批量添加
     * @param sysUserAuthorityVo
     */
    void batchData(SysUserAuthorityVo sysUserAuthorityVo);

    /**
     * 根据用户ID删除
     * @param uid
     */
    void delAuthorityByUid(int uid);
}

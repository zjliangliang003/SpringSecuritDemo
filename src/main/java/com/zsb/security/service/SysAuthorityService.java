package com.zsb.security.service;

import com.zsb.security.vo.SysAuthorityVo;

import java.util.List;

/**
 * @ClassName SysAuthorityService
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/19 14:03
 * @Version 1.0
 */
public interface SysAuthorityService {

    /**
     * 查询权限列表
     * @return
     */
    List<SysAuthorityVo> querySysAuthorityList();

    /**
     * 添加权限
     * @param sysAuthorityVo
     */
    void save(SysAuthorityVo sysAuthorityVo);

    /**
     * 修改权限
     * @param sysAuthorityVo
     */
    void update(SysAuthorityVo sysAuthorityVo);

    /**
     * 删除权限
     * @param authorityId
     */
    void delAuthority(int authorityId);
}

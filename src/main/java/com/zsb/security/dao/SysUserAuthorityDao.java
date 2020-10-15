package com.zsb.security.dao;

import com.zsb.security.vo.SysAuthorityVo;
import com.zsb.security.vo.SysUserAuthorityVo;

import java.util.List;

/**
 * @ClassName SysUserAuthorityDao
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 15:09
 * @Version 1.0
 */
public interface SysUserAuthorityDao {
    /**
     * 根据用户id查询用户权限
     * @param uid
     * @return
     */
    List<SysUserAuthorityVo> findByUserAuthorityId(int uid);

    /**
     * 查询权限列表
     * @return
     */
    List<SysAuthorityVo> queryAuthorityList();
}
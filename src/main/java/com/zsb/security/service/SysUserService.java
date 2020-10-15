package com.zsb.security.service;

import com.zsb.security.vo.SysUserVo;

import java.util.List;

/**
 * @ClassName SysUserService
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 13:33
 * @Version 1.0
 */
public interface SysUserService {
    /**
     * 查询所有用户信息
     * @return
     */
    List<SysUserVo> queryUserList();

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    SysUserVo  findByLoginName(String username);
}

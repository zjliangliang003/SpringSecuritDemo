package com.zsb.security.dao;

import com.zsb.security.vo.SysUserVo;

import java.util.List;

/**
 * @ClassName SysUserDao
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 11:45
 * @Version 1.0
 */

public interface SysUserDao {
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

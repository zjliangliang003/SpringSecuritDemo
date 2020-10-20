package com.zsb.security.service;

import com.zsb.security.vo.SysMenuVo;
import com.zsb.security.vo.SysUserMenuVo;

import java.util.List;

/**
 * @ClassName SysUserMenuService
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/15 11:25
 * @Version 1.0
 */
public interface SysUserMenuService {

    /**
     * 根据用户ID查询菜单
     * @param id
     * @return
     */
    List<SysMenuVo> queryMenuByUserId(int id);

    /**
     * 批量添加
     * @param sysUserMenuVo
     */
    void batchMenu(SysUserMenuVo sysUserMenuVo);

}

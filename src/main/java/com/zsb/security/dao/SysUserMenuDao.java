package com.zsb.security.dao;

import com.zsb.security.vo.SysMenuVo;
import com.zsb.security.vo.SysUserMenuVo;

import java.util.List;

/**
 * @ClassName SysUserMenuDao
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/15 11:20
 * @Version 1.0
 */
public interface SysUserMenuDao extends BaseDao<SysUserMenuVo>{
    /**
     * 根据用户ID查询菜单
     * @param id
     * @return
     */
    List<SysMenuVo> queryMenuByUserId(int id);

    /**
     * 根据菜单ID删除
     * @param menuId
     */
    void deleteMenuByMenuId(int menuId);

    /**
     * 根据用户ID删除
     * @param uid
     */
    void deleteMenuByUid(int uid);

}

package com.zsb.security.service;

import com.zsb.security.vo.SysMenuVo;

import java.util.List;

/**
 * @ClassName SysMenuService
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/19 9:27
 * @Version 1.0
 */
public interface SysMenuService {
    /**
     * 查询系统菜单列表
     * @return
     */
    List<SysMenuVo> queryMenuList();

    /**
     * 删除菜单
     * @param menuId
     */
    void deleteMenu (int menuId);

    /**
     * 添加菜单
     * @param sysMenuVo
     */
    void saveMenu (SysMenuVo sysMenuVo);

    /**
     * 更新菜单
     * @param sysMenuVo
     */
    void updateMenu (SysMenuVo sysMenuVo);
}

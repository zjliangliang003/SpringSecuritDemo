package com.zsb.security.service;

import com.zsb.security.dao.SysMenuDao;
import com.zsb.security.dao.SysUserMenuDao;
import com.zsb.security.vo.SysMenuVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SysMenuServiceImpl
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/19 9:27
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysMenuServiceImpl implements SysMenuService{

    @Resource
    SysMenuDao sysMenuDao;

    @Resource
    SysUserMenuDao userMenuDao;

    @Override
    public List<SysMenuVo> queryMenuList() {
        return sysMenuDao.queryMenuList();
    }

    @Override
    public void deleteMenu(int menuId) {
        sysMenuDao.deleteMenu(menuId);
        userMenuDao.deleteMenuByMenuId(menuId);
    }

    @Override
    public void saveMenu(SysMenuVo sysMenuVo) {
        sysMenuDao.saveMenu(sysMenuVo);
    }

    @Override
    public void updateMenu(SysMenuVo sysMenuVo) {
        sysMenuDao.updateMenu(sysMenuVo);
    }
}

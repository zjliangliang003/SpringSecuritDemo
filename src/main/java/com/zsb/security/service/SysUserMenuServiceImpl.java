package com.zsb.security.service;

import com.zsb.security.dao.SysUserMenuDao;
import com.zsb.security.vo.SysMenuVo;
import com.zsb.security.vo.SysUserMenuVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysUserMenuServiceImpl
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/15 11:25
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserMenuServiceImpl implements SysUserMenuService{

    @Resource
    SysUserMenuDao menuDao;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<SysMenuVo> queryMenuByUserId(int id) {
        List<SysMenuVo> sysMenuVos = menuDao.queryMenuByUserId(id);
        return SysMenuVo.getTree(sysMenuVos);
    }

    @Override
    public void batchMenu(SysUserMenuVo sysUserMenuVo) {
        List<SysUserMenuVo> list = new ArrayList<>();
        for (String s:sysUserMenuVo.getMenuIdList().split(",")){
            SysUserMenuVo sysUserMenu = new SysUserMenuVo();
            sysUserMenu.setUserId(sysUserMenuVo.getUserId());
            sysUserMenu.setMenuId(Integer.parseInt(s));
            sysUserMenu.setCreateTime(Timestamp.valueOf(sdf.format(System.currentTimeMillis())));
            sysUserMenu.setUpdateTime(Timestamp.valueOf(sdf.format(System.currentTimeMillis())));
            list.add(sysUserMenu);
        }
        menuDao.batchData(list);
    }
}

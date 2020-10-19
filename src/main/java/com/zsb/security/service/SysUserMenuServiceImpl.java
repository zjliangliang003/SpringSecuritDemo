package com.zsb.security.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.zsb.security.dao.SysUserMenuDao;
import com.zsb.security.vo.SysMenuVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    @Override
    public List<SysMenuVo> queryMenuByUserId(int id) {
        List<SysMenuVo> sysMenuVos = menuDao.queryMenuByUserId(id);
        return SysMenuVo.getTree(sysMenuVos);
    }
}

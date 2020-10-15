package com.zsb.security.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.zsb.security.dao.SysUserMenuDao;
import com.zsb.security.vo.SysMenuVo;
import org.springframework.stereotype.Service;

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
public class SysUserMenuServiceImpl implements SysUserMenuService{

    @Resource
    SysUserMenuDao menuDao;

    @Override
    public List<SysMenuVo> queryMenuByUserId(int id) {
        List<SysMenuVo> sysMenuVos = menuDao.queryMenuByUserId(id);
        return getTree(sysMenuVos);

    }

    public List<SysMenuVo> getTree(List<SysMenuVo> list){
        List<SysMenuVo> listResult =new ArrayList<>();
        for (SysMenuVo sysMenuVo:list){
            if (sysMenuVo.getMenuParentId() == 0){
                listResult.add(sysMenuVo);
            }
        }
        getChirdrenTree(list,listResult);
        return listResult;
    }
    public void getChirdrenTree(List<SysMenuVo> list, List<SysMenuVo> listResult){
        for (SysMenuVo sysMenuVo : listResult){
            List<SysMenuVo> chirdrenList =new ArrayList<>();
            for (SysMenuVo vo : list){
                if (vo.getMenuParentId() == 0){
                    continue;
                }
                if (vo.getMenuParentId() == sysMenuVo.getMenuId()){
                    chirdrenList.add(vo);
                }
            }
            sysMenuVo.setChildren(chirdrenList);
            if (!sysMenuVo.getChildren().isEmpty()){
                getChirdrenTree(list,sysMenuVo.getChildren());
            }
        }
    }
}

package com.zsb.security.controller;

import com.zsb.security.service.SysUserMenuService;
import com.zsb.security.vo.SysUserMenuVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName SysUserMenuController
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/20 10:07
 * @Version 1.0
 */
@RestController
@RequestMapping("/sys/sysUserMenu")
public class SysUserMenuController {

    @Resource
    SysUserMenuService sysUserMenuService;

    @PostMapping("/saveAllByUserId")
    public void saveUser(SysUserMenuVo sysUserMenuVo){
        sysUserMenuService.deleteMenuByUid(sysUserMenuVo.getUserId());
        sysUserMenuService.batchMenu(sysUserMenuVo);
    }
}

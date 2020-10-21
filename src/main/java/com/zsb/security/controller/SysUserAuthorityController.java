package com.zsb.security.controller;

import com.zsb.security.service.SysUserAuthorityService;
import com.zsb.security.vo.SysUserAuthorityVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName SysUserAuthorityContorller
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/20 10:35
 * @Version 1.0
 */
@RestController
@RequestMapping("/sys/sysUserAuthority")
public class SysUserAuthorityController {

    @Resource
    SysUserAuthorityService sysUserAuthorityService;
    @PostMapping("/saveAllByUserId")
    public void saveUser(SysUserAuthorityVo sysUserAuthorityVo){
        sysUserAuthorityService.delAuthorityByUid(sysUserAuthorityVo.getUserId());
        sysUserAuthorityService.batchData(sysUserAuthorityVo);
    }
}

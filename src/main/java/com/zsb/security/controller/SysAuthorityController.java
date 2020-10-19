package com.zsb.security.controller;

import com.zsb.security.service.SysAuthorityService;
import com.zsb.security.util.CommonResult;
import com.zsb.security.vo.SysAuthorityVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName SysAuthorityController
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/19 14:08
 * @Version 1.0
 */
@RestController
@RequestMapping("/sys/sysAuthority")
public class SysAuthorityController {

    @Resource
    SysAuthorityService authorityService;

    @PostMapping("/authorityList")
    public CommonResult getAllAuthorityList() {
        return CommonResult.success(authorityService.querySysAuthorityList());
    }

    @PostMapping("/save")
    public CommonResult saveAuthority(SysAuthorityVo sysAuthorityVo){
        authorityService.save(sysAuthorityVo);
        return CommonResult.success("");
    }
    @PostMapping("/update")
    public CommonResult updateAuthority(SysAuthorityVo sysAuthorityVo){
        authorityService.update(sysAuthorityVo);
        return CommonResult.success("");
    }

    @PostMapping("/delAuthority")
    public CommonResult delAuthority(int authorityId){
        authorityService.delAuthority(authorityId);
        return CommonResult.success("");
    }
}

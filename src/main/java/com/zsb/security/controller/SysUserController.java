package com.zsb.security.controller;

import com.zsb.security.service.SysUserService;
import com.zsb.security.util.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName SysUserController
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 13:36
 * @Version 1.0
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    SysUserService userService;

    @GetMapping("/getUserList")
    public CommonResult getUserList(){
        return CommonResult.success(userService.queryUserList());
    }

}

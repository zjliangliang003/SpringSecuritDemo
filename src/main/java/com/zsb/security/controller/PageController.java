package com.zsb.security.controller;

import com.zsb.security.service.SysSettingService;
import com.zsb.security.service.SysUserService;
import com.zsb.security.util.SecurityUtil;
import com.zsb.security.vo.SysUserVo;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @ClassName PageController
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/15 16:43
 * @Version 1.0
 */
@RestController
public class PageController {

    @Resource
    private SysSettingService sysSettingService;

    @Resource
    SysUserService sysUserService;

    @GetMapping("/sys/sysSetting/setting")
    public ModelAndView setting() {
        User loginUser = SecurityUtil.getLoginUser();
        SysUserVo sysUserVo = sysUserService.findByLoginName(loginUser.getUsername());
        ModelAndView modelAndView = new ModelAndView("/sys/setting/setting", "sys", sysSettingService.querySysInfoById(sysUserVo.getUserId()));
        return modelAndView;
    }

    @GetMapping("/sys/sysMenu/menu")
    public ModelAndView menuPage(){
        return new ModelAndView("/sys/menu/menu");
    }

    @GetMapping("/sys/sysAuthority/authority")
    public ModelAndView authorityPage(){
        return new ModelAndView("/sys/authority/authority");
    }

    @GetMapping("/sys/sysUser/user")
    public ModelAndView userPage(){
        return new ModelAndView("/sys/sysUser/user");
    }

    @GetMapping("/user/userinfo")
    public ModelAndView userInfoPage(){
        User loginUser = SecurityUtil.getLoginUser();
        SysUserVo sysUserVo = sysUserService.findByLoginName(loginUser.getUsername());
        return new ModelAndView("/user/userinfo","user", sysUserVo);
    }
}

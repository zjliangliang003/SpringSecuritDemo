package com.zsb.security.controller;

import com.zsb.security.service.SysUserAuthorityService;
import com.zsb.security.service.SysUserMenuService;
import com.zsb.security.service.SysUserService;
import com.zsb.security.util.CommonResult;
import com.zsb.security.vo.SysUserAuthorityVo;
import com.zsb.security.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysUserController
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 13:36
 * @Version 1.0
 */
@RestController
@RequestMapping("/sys/sysUser")
public class SysUserController {

    @Resource
    SysUserService userService;

    @Resource
    SysUserMenuService sysUserMenuService;

    @Resource
    SysUserAuthorityService sysUserAuthorityService;

    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping("/getUserList")
    public CommonResult getUserList(){
        return CommonResult.success(userService.queryUserList());
    }

    @PostMapping("/findUserMenuAndAllSysMenuByUserId")
    public CommonResult findUserMenuAndAllSysMenuByUserId(int uid){
        return CommonResult.success(sysUserMenuService.queryMenuByUserId(uid));
    }

    @PostMapping("/findUserAuthorityAndAllSysAuthorityByUserId")
    public CommonResult findUserAuthorityAndAllSysAuthorityByUserId(int uid){
        return CommonResult.success(sysUserAuthorityService.findByUserAuthorityId(uid));
    }

    @PostMapping("/pageOnLine")
    public CommonResult getOnline(String loginName){
        //清除remember-me持久化tokens
        PersistentTokenRepository persistentTokenRepository = userService.getPersistentTokenRepository2();
        persistentTokenRepository.removeUserTokens(loginName);
        ArrayList<SysUserVo> sysUserVoList = new ArrayList<>();
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        for (Object object : allPrincipals){
            SysUserVo sysUserVo = new SysUserVo();
            User user=(User)object;
            sysUserVo.setLoginName(user.getUsername());
            sysUserVoList.add(sysUserVo);
        }
        return CommonResult.success(sysUserVoList);
    }
    @GetMapping("/forcedOffline")
    public CommonResult forcedOffline(String loginName){
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        for (Object bean : allPrincipals){
            User user = (User) bean;
            if (user.getUsername().equals(loginName)){
                List<SessionInformation> allSessions = sessionRegistry.getAllSessions(bean, true);
                if (allSessions != null){
                    for (SessionInformation ses: allSessions){
                        //设置立即过期
                        ses.expireNow();
                        //移除sessionId
                        sessionRegistry.removeSessionInformation(ses.getSessionId());
                    }
                }
                break;
            }
        }
        return CommonResult.success("");
    }
}

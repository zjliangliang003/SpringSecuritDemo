package com.zsb.security.controller;

import com.zsb.security.service.*;
import com.zsb.security.util.CommonResult;
import com.zsb.security.util.SysSettingUtil;
import com.zsb.security.util.Validator;
import com.zsb.security.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Resource
    SysMenuService sysMenuService;

    @Resource
    SysAuthorityService sysAuthorityService;

    @Resource
    SessionRegistry sessionRegistry;


    @GetMapping("/getUserList")
    public CommonResult getUserList(){
        return CommonResult.success(userService.queryUserList());
    }
    @GetMapping("/delUser")
    public CommonResult delUser(int uid){
        userService.delByUid(uid);
        return CommonResult.success("");
    }
    @PostMapping("/findUserMenuAndAllSysMenuByUserId")
    public CommonResult findUserMenuAndAllSysMenuByUserId(int uid){
        HashMap<String, Object> map = new HashMap<>();
        List<SysMenuVo> voList = sysMenuService.queryMenuList();
        List<SysMenuVo> tree = SysMenuVo.getTree(voList);
        map.put("sysMenuVoList",tree);
        List<SysMenuVo> voList1 = sysUserMenuService.queryMenuByUserId(uid);
        List<SysMenuVo> tree1 = SysMenuVo.getTree(voList1);
        map.put("userSysMenuVoList",tree1);
        return CommonResult.success(map);
    }

    @PostMapping("/findUserAuthorityAndAllSysAuthorityByUserId")
    public CommonResult findUserAuthorityAndAllSysAuthorityByUserId(int uid){
        HashMap<String, Object> map = new HashMap<>();
        List<SysAuthorityVo> sysUserAuthorityVoList = sysUserAuthorityService.findByUserAuthorityId(uid);
        map.put("sysUserAuthorityVoList", sysUserAuthorityVoList);
        List<SysAuthorityVo> sysAuthorityVoList = sysAuthorityService.querySysAuthorityList();
        map.put("sysAuthorityVoList", sysAuthorityVoList);
        return CommonResult.success(map);
    }

    @PostMapping("/pageOnLine")
    public CommonResult getOnline(String loginName){
        ArrayList<SysUserVo> sysUserVoList = new ArrayList<>();
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        for (Object object : allPrincipals){
            User user=(User)object;
            SysUserVo sysUserVo = SysUserVo.builder()
                    .loginName(user.getUsername()).build();
            sysUserVoList.add(sysUserVo);
        }
        return CommonResult.success(sysUserVoList);
    }
    @GetMapping("/forcedOffline")
    public CommonResult forcedOffline(String loginName){
        //清除remember-me持久化tokens
        PersistentTokenRepository persistentTokenRepository = userService.getPersistentTokenRepository2();
        persistentTokenRepository.removeUserTokens(loginName);
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

    @PostMapping("/save")
    public CommonResult saveUser(SysUserVo sysUserVo){
        if (sysUserVo.getUserId() != 0){
            userService.updateUser(sysUserVo);
            return CommonResult.success(sysUserVo);
        }
        SysUserVo vo = userService.findByLoginName(sysUserVo.getLoginName());
        if (vo != null){
            if (vo.getLoginName().equals(sysUserVo.getLoginName())){
                return CommonResult.other(-1,"登录名已存在","");
            }
            if (vo.getUserName().equals(sysUserVo.getUserName())){
                return CommonResult.other(-1,"用户名已存在","");
            }
        }
        Validator.validateBeenValue(sysUserVo);
        return userService.saveUser(sysUserVo);
    }
}

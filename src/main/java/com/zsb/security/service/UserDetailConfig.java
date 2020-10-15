package com.zsb.security.service;

import com.zsb.security.vo.SysUserAuthorityVo;
import com.zsb.security.vo.SysUserVo;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName UserDetailConfig
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 14:18
 * @Version 1.0
 */
@Component
public class UserDetailConfig implements UserDetailsService {
    @Resource
    SysUserService userService;

    @Resource
    SysUserAuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserVo sysUserVo = userService.findByLoginName(username);
        if (Objects.isNull(sysUserVo)){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<SysUserAuthorityVo> SysUserAuthorityVoList = authorityService.findByUserAuthorityId(sysUserVo.getUserId());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SysUserAuthorityVoList.size(); i++){
            sb.append(SysUserAuthorityVoList.get(i).getAuthorityName());
            if (i != SysUserAuthorityVoList.size() - 1) {
                sb.append(",");
            }
        }
        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        return new User(sysUserVo.getLoginName(),sysUserVo.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(sb.toString()));
    }
}

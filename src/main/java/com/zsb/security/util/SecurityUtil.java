package com.zsb.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @ClassName SecurityUtil
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 13:47
 * @Version 1.0
 */
public class SecurityUtil {

    public static User getLoginUser(){
        User user = null;
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails){
            user = (User) auth.getPrincipal();
        }
        assert user != null;
        return user;
    }

}

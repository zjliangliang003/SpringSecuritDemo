package com.zsb.security.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysUserAuthorityVo
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 14:28
 * @Version 1.0
 */
@Data
public class SysUserAuthorityVo extends SysUserVo{
    /**
     * 权限id
     */
    private int authorityId;
    /**
     * 用户权限表id
     */
    private int userAuthorityId;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 权限名称，ROLE_开头，全大写
     */
    private String authorityName;
    /**
     * 权限内容，可访问的url，多个时用,隔开
     */
    private String authorityContent;
    /**
     * 权限描述
     */
    private String authorityRemark;

//    /**
//     * 用户
//     */
//    private SysUserVo sysUser;
//    /**
//     * 权限
//     */
//    private SysAuthorityVo sysAuthority;

}

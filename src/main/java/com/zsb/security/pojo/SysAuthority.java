package com.zsb.security.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysAuthorityVo
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 14:28
 * @Version 1.0
 */
@Data
public class SysAuthority {
    /**
     * 权限id
     */
    private int authorityId;
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
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}

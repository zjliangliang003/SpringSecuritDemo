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
public class SysUserAuthorityVo{
    /**
     * 用户权限表id
     */
    private int userAuthorityId;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 权限id
     */
    private int authorityId;

    private Date createTime;

    private Date updateTime;

    private String authorityIdList;

}

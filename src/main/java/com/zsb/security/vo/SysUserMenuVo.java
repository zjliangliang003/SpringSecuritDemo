package com.zsb.security.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysUserMenuVo
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/20 10:09
 * @Version 1.0
 */
@Data
public class SysUserMenuVo {

    private int userMenuId;

    private int userId;

    private int menuId;

    private Date createTime;

    private Date updateTime;
    /**
     *新增、修改用户权限时权限id集合
     */
    private String menuIdList;
}

package com.zsb.security.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SysMenu
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/15 11:21
 * @Version 1.0
 */
@Data
public class SysMenuVo {
    private int menuId;
    private String menuName;
    private String menuPath;
    private int menuParentId;
    private Date createTime;
    private Date updateTime;
    private List<SysMenuVo> children;

}

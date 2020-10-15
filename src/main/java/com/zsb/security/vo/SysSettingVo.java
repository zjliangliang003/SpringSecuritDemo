package com.zsb.security.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysSettingVo
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/15 10:12
 * @Version 1.0
 */
@Data
public class SysSettingVo {
    /**
     * 表id
     */
    private String id;
    /**
     * 系统名称
     */
    private String sysName;
    /**
     * 系统logo图标
     */
    private String sysLogo;
    /**
     * 系统底部信息
     */
    private String sysBottomText;
    /**
     * 系统颜色
     */
    private String sysColor;
    /**
     * 系统公告
     */
    private String sysNoticeText;
    /**
     * API加密 Y/N
     */
    private String sysApiEncrypt;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 用户管理：初始、重置密码
     */
    private String userInitPassword;
}

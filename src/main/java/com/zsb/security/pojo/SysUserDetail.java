package com.zsb.security.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysUser
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 9:31
 * @Version 1.0
 */
@Data
public class SysUserDetail implements Serializable {

    /**
     * 用户id
     */
    private int userId;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 软删除标识，Y/N
     */
    private String valid;
    /**
     * 限制允许登录的IP集合
     */
    private String limitedIp;
    /**
     * 账号失效时间，超过时间将不能登录系统
     */
    private Date expiredTime;
    /**
     * 最近修改密码时间，超出时间间隔，提示用户修改密码
     */
    private Date lastChangePwdTime;
    /**
     * 是否允许账号同一个时刻多人在线，Y/N
     */
    private String limitMultiLogin;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}

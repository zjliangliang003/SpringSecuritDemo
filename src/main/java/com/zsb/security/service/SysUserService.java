package com.zsb.security.service;

import com.zsb.security.util.CommonResult;
import com.zsb.security.vo.SysUserVo;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.List;

/**
 * @ClassName SysUserService
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 13:33
 * @Version 1.0
 */
public interface SysUserService {
    /**
     * 查询所有用户信息
     * @return
     */
    List<SysUserVo> queryUserList();

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    SysUserVo  findByLoginName(String username);

    /**
     * 获取永久令牌存储库
     * @return
     */
    PersistentTokenRepository getPersistentTokenRepository2();

    /**
     * 添加用户
     * @param sysUserVo
     */
    CommonResult saveUser(SysUserVo sysUserVo);


    /**
     * 根据用户id删除
     * @param uid
     */
    void delByUid(int uid);

    /**
     * 修改用户
     * @param sysUserVo
     */
    void updateUser(SysUserVo sysUserVo);
}

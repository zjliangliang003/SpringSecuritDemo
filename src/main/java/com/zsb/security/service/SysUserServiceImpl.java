package com.zsb.security.service;

import com.zsb.security.annotation.RateLimit;
import com.zsb.security.dao.SysUserAuthorityDao;
import com.zsb.security.dao.SysUserDao;
import com.zsb.security.dao.SysUserMenuDao;
import com.zsb.security.util.CommonResult;
import com.zsb.security.util.SecurityUtil;
import com.zsb.security.util.SysSettingUtil;
import com.zsb.security.vo.SysSettingVo;
import com.zsb.security.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * @ClassName SysUserServiceImpl
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 13:34
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService{

    @Resource
    SysUserDao sysUserDao;

    @Autowired
    DataSource dataSource;

    @Resource
    SysUserMenuDao sysUserMenuDao;

    @Resource
    SysUserAuthorityDao sysUserAuthorityDao;

    @RateLimit(limitNum = 5.0)
    @Override
    public List<SysUserVo> queryUserList() {
        return sysUserDao.queryUserList();
    }

    @Override
    public SysUserVo findByLoginName(String username) {
        return sysUserDao.findByLoginName(username);
    }

    @Override
    public PersistentTokenRepository getPersistentTokenRepository2() {
        return persistentTokenRepository2();
    }

    @Override
    public CommonResult saveUser(SysUserVo sysUserVo) {
        SysSettingVo sysSettingMap = SysSettingUtil.getSysSettingMap();
        String userInitPassword = sysSettingMap.getUserInitPassword();
        SysUserVo userVo = SysUserVo.builder()
                .userName(sysUserVo.getUserName())
                .loginName(sysUserVo.getLoginName())
                .expiredTime(sysUserVo.getExpiredTime())
                .lastChangePwdTime(sysUserVo.getLastChangePwdTime())
                .limitedIp(sysUserVo.getLimitedIp())
                .valid(sysUserVo.getValid())
                .limitMultiLogin(sysUserVo.getLimitMultiLogin())
                .password(new BCryptPasswordEncoder().encode(userInitPassword))
                .createTime(sysUserVo.getCreateTime())
                .updateTime(sysUserVo.getUpdateTime())
                .build();
        sysUserDao.saveUser(userVo);
        SysUserVo vo = sysUserDao.findByLoginName(sysUserVo.getLoginName());
        return CommonResult.success(vo);
    }

    @Override
    public void delByUid(int uid) {
        sysUserDao.delByUid(uid);
        sysUserMenuDao.deleteMenuByUid(uid);
        sysUserAuthorityDao.delAuthorityByUid(uid);
    }

    @Override
    public void updateUser(SysUserVo sysUserVo) {
        sysUserDao.updateUser(sysUserVo);
    }

    @Override
    public void updateUsername(SysUserVo sysUserVo) {
        sysUserDao.updateUsername(sysUserVo);
    }

    @Override
    public int updatePassword(String oldPassword, String newPassword) {
        User loginUser = SecurityUtil.getLoginUser();
        SysUserVo sysUserVo = sysUserDao.findByLoginName(loginUser.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = sysUserVo.getPassword();
        boolean matches = encoder.matches(oldPassword, password);
        if (!matches){
            return -1;
        }
        sysUserVo.setPassword(encoder.encode(newPassword));
        sysUserDao.updateUser(sysUserVo);
        return 0;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository2() {
        JdbcTokenRepositoryImpl persistentTokenRepository = new JdbcTokenRepositoryImpl();
        persistentTokenRepository.setDataSource(dataSource);
        return persistentTokenRepository;
    }
}

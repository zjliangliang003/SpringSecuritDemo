package com.zsb.security.service;

import com.zsb.security.dao.SysUserDao;
import com.zsb.security.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public PersistentTokenRepository persistentTokenRepository2() {
        JdbcTokenRepositoryImpl persistentTokenRepository = new JdbcTokenRepositoryImpl();
        persistentTokenRepository.setDataSource(dataSource);
        return persistentTokenRepository;
    }
}

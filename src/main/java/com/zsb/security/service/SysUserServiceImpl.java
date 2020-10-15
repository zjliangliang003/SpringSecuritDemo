package com.zsb.security.service;

import com.zsb.security.dao.SysUserDao;
import com.zsb.security.vo.SysUserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SysUserServiceImpl
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 13:34
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService{

    @Resource
    SysUserDao sysUserDao;

    @Override
    public List<SysUserVo> queryUserList() {
        return sysUserDao.queryUserList();
    }

    @Override
    public SysUserVo findByLoginName(String username) {
        return sysUserDao.findByLoginName(username);
    }
}

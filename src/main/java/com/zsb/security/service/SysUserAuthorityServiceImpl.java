package com.zsb.security.service;

import com.zsb.security.dao.SysUserAuthorityDao;
import com.zsb.security.vo.SysAuthorityVo;
import com.zsb.security.vo.SysUserAuthorityVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SysUserAuthorityServiceImpl
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 15:11
 * @Version 1.0
 */
@Service
public class SysUserAuthorityServiceImpl implements SysUserAuthorityService{

    @Resource
    SysUserAuthorityDao authorityDao;

    @Override
    public List<SysUserAuthorityVo> findByUserAuthorityId(int uid) {
        return authorityDao.findByUserAuthorityId(uid);
    }

    @Override
    public List<SysAuthorityVo> queryList() {
        return authorityDao.queryAuthorityList();
    }
}

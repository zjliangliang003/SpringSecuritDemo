package com.zsb.security.service;

import com.zsb.security.dao.SysAuthorityDao;
import com.zsb.security.vo.SysAuthorityVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName SysAuthorityServiceImpl
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/19 14:04
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysAuthorityServiceImpl implements SysAuthorityService{

    @Resource
    SysAuthorityDao sysAuthorityDao;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<SysAuthorityVo> querySysAuthorityList() {
        return sysAuthorityDao.querySysAuthorityList();
    }

    @Override
    public void save(SysAuthorityVo sysAuthorityVo) {
        sysAuthorityVo.setCreateTime(Timestamp.valueOf(sdf.format(System.currentTimeMillis())));
        sysAuthorityDao.save(sysAuthorityVo);
    }

    @Override
    public void update(SysAuthorityVo sysAuthorityVo) {
        sysAuthorityVo.setUpdateTime(Timestamp.valueOf(sdf.format(System.currentTimeMillis())));
        sysAuthorityDao.update(sysAuthorityVo);
    }

    @Override
    public void delAuthority(int authorityId) {
        sysAuthorityDao.delAuthority(authorityId);
    }
}

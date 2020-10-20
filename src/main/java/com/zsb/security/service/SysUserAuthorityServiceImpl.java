package com.zsb.security.service;

import com.zsb.security.dao.SysUserAuthorityDao;
import com.zsb.security.vo.SysAuthorityVo;
import com.zsb.security.vo.SysUserAuthorityVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysUserAuthorityServiceImpl
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 15:11
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserAuthorityServiceImpl implements SysUserAuthorityService{

    @Resource
    SysUserAuthorityDao authorityDao;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<SysAuthorityVo> findByUserAuthorityId(int uid) {
        return authorityDao.findByUserAuthorityId(uid);
    }

    @Override
    public List<SysAuthorityVo> queryList() {
        return authorityDao.queryAuthorityList();
    }

    @Override
    public void batchData(SysUserAuthorityVo sysUserAuthorityVo) {
        List<SysUserAuthorityVo> list = new ArrayList<>();
        for (String s :sysUserAuthorityVo.getAuthorityIdList().split(",")){
            SysUserAuthorityVo sysUserAuthority = new SysUserAuthorityVo();
            sysUserAuthority.setAuthorityId(Integer.parseInt(s));
            sysUserAuthority.setUserId(sysUserAuthorityVo.getUserId());
            sysUserAuthority.setCreateTime(Timestamp.valueOf(sdf.format(System.currentTimeMillis())));
            sysUserAuthority.setUpdateTime(Timestamp.valueOf(sdf.format(System.currentTimeMillis())));
            list.add(sysUserAuthority);
        }
        authorityDao.batchData(list);
    }
}

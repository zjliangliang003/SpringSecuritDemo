package com.zsb.security.service;

import com.sun.jna.platform.win32.Sspi;
import com.zsb.security.dao.SysSettingDao;
import com.zsb.security.util.SysSettingUtil;
import com.zsb.security.vo.SysSettingVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @ClassName SysSettingServiceImpl
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/15 10:17
 * @Version 1.0
 */
@Service
public class SysSettingServiceImpl implements SysSettingService{

    @Resource
    SysSettingDao sysSettingDao;

    @Override
    public SysSettingVo querySysInfoById(int id) {
        return sysSettingDao.querySysInfoById(id);
    }

    @Override
    public void saveSysSetting(SysSettingVo sysSettingVo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sysSettingVo.setUpdateTime(Timestamp.valueOf(sdf.format(sysSettingVo.getCreateTime())));
        sysSettingVo.setUpdateTime(Timestamp.valueOf(sdf.format(sysSettingVo.getUpdateTime())));
        sysSettingDao.saveSysSetting(sysSettingVo);
        SysSettingUtil.setSysSettingMap(sysSettingVo);
    }
}

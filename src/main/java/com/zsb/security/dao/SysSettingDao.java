package com.zsb.security.dao;

import com.zsb.security.vo.SysSettingVo;

/**
 * @ClassName SysSettingDao
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/15 10:15
 * @Version 1.0
 */
public interface SysSettingDao {

    /**
     * 根据ID查询系统信息
     * @param id
     * @return
     */
    SysSettingVo querySysInfoById(int id);

    /**
     * 添加系统设置
     * @param sysSettingVo
     */
    void saveSysSetting(SysSettingVo sysSettingVo);

    /**
     * 更新
     * @param sysSettingVo
     */
    void updateSetting(SysSettingVo sysSettingVo);
}

package com.zsb.security.util;

import com.zsb.security.vo.SysSettingVo;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName SysSettingUtil
 * @Description TODO  系统设置工具类 系统启动时获取数据库数据，设置到公用静态集合sysSettingMap更新系统设置时同步更新公用静态集合sysSettingMap
 * @Author shangBangZheng
 * @Date 2020/10/15 10:23
 * @Version 1.0
 */
public class SysSettingUtil {

    /**
     * 使用线程安全的ConcurrentHashMap来存储系统设置
     */
    private static ConcurrentHashMap<String , SysSettingVo> map =new ConcurrentHashMap<>();

    /**
     * 存放系统设置信息
     * @param sysSet
     */
    public static void setSysSettingMap(SysSettingVo sysSet){
        if (map.isEmpty()){
            map.put("sysSetting",sysSet);
        }else {
            map.replace("sysSetting",sysSet);
        }
    }

    /**
     * 从公用静态集合sysSettingMap获取系统设置
     * @return
     */
    public static SysSettingVo getSysSettingMap(){
        return map.get("sysSetting");
    }
}

package com.zsb.security.vo;

import lombok.Data;

/**
 * @ClassName MonitorVo
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/20 16:21
 * @Version 1.0
 */
@Data
public class MonitorVo {
    /**
     * 操作系统
     */
    private String os;
    /**
     * 程序启动时间
     */
    private String runTime;
    /**
     * java版本
     */
    private String jvmJavaVersion;
/*--------------------jvm--------------------*/
    /**
     * jvm内存的初始大小
     */
    private String jvmHeapInit;
    /**
     * jvm最大可用内存量
     */
    private String jvmHeapMax;
    /**
     * jvm已使用的内存量
     */
    private String jvmHeapUsed;
    /**
     * jvm已申请的内存量
     */
    private String jvmHeapCommitted;
    /**
     * jvm内存的初始大小
     */
    private String jvmNonHeapInit;
    /**
     * jvm最大可用内存量
     */
    private String jvmNonHeapMax;
    /**
     * jvm已使用的内存量
     */
    private String jvmNonHeapUsed;
    /**
     * jvm已申请的内存量
     */
    private String jvmNonHeapCommitted;

    /*--------------------硬件信息--------------------*/
    /**
     * CPU信息
     */
    private String cpuInfo;
    /**
     * CPU使用率
     */
    private String cpuUseRate;
    /**
     * 系统内存总量
     */
    private String ramTotal;
    /**
     * 已使用的系统内存量
     */
    private String ramUsed;
    /**
     * 系统磁盘总量
     */
    private String diskTotal;
    /**
     * 已使用的系统磁盘量
     */
    private String diskUsed;
}

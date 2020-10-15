package com.zsb.security.vo;

import lombok.Data;

/**
 * ip
 */
@Data
public class IpVo {
    /**
     * IP地址
     */
    private String ip;
    /**
     * 省
     */
    private String pro;
    /**
     * 省编码
     */
    private String proCode;
    /**
     * 城市
     */
    private String city;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 区
     */
    private String region;
    /**
     * 区编码
     */
    private String regionCode;
    /**
     * 详细地址 + 运营商
     */
    private String addr;

    /**
     * 主要用于接参，无实际意义
     */
    private String regionNames;
    private String err;
}

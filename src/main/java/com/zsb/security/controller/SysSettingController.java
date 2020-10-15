package com.zsb.security.controller;

import com.zsb.security.service.SysSettingService;
import com.zsb.security.util.CommonResult;
import com.zsb.security.vo.SysSettingVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @ClassName SysSettingController
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/15 17:21
 * @Version 1.0
 */
@RestController
@RequestMapping("/sys/sysSetting")
public class SysSettingController {

    @Resource
    SysSettingService settingService;

    /**
     *
     * @return
     */
    @PostMapping(value = "/save")
    public CommonResult save(SysSettingVo sysSettingVo){
        SysSettingVo exitVo = settingService.querySysInfoById(sysSettingVo.getId());
        if (Objects.isNull(exitVo)){
            settingService.saveSysSetting(sysSettingVo);
        }else {
            settingService.updateSetting(sysSettingVo);
        }
        return CommonResult.success(sysSettingVo);
    }
}

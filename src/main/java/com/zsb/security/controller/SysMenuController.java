package com.zsb.security.controller;

import com.zsb.security.service.SysMenuService;
import com.zsb.security.util.CommonResult;
import com.zsb.security.vo.SysMenuVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @ClassName SysMenuController
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/19 9:26
 * @Version 1.0
 */
@RestController
@RequestMapping("/sys/sysMenu")
public class SysMenuController {

    @Resource
    SysMenuService sysMenuService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @PostMapping("/listByTier")
    public CommonResult queryMenuList(){
        return CommonResult.success(SysMenuVo.getTree(sysMenuService.queryMenuList()));
    }

    @PostMapping("/delete")
    public CommonResult deleteMenuByMenuId(int menuId){
        sysMenuService.deleteMenu(menuId);
        return CommonResult.success("");
    }

    @PostMapping("/save")
    public CommonResult saveMenu(SysMenuVo sysMenuVo){
        if (sysMenuVo.getMenuId() == 0){
            sysMenuVo.setCreateTime(Timestamp.valueOf(sdf.format(System.currentTimeMillis())));
            sysMenuService.saveMenu(sysMenuVo);
        }else {
            sysMenuVo.setUpdateTime(Timestamp.valueOf(sdf.format(System.currentTimeMillis())));
            sysMenuService.updateMenu(sysMenuVo);
        }
        return CommonResult.success("");
    }

}

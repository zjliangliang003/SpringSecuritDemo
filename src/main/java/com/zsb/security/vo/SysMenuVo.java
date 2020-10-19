package com.zsb.security.vo;

import lombok.Data;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SysMenu
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/15 11:21
 * @Version 1.0
 */
@Data
public class SysMenuVo {
    private int menuId;
    private String menuName;
    private String menuPath;
    private int menuParentId;
    private Date createTime;
    private Date updateTime;
    @Transient
    private List<SysMenuVo> children;

    public static List<SysMenuVo> getTree(List<SysMenuVo> list){
        List<SysMenuVo> listResult =new ArrayList<>();
        for (SysMenuVo sysMenuVo:list){
            if (sysMenuVo.getMenuParentId() == 0){
                listResult.add(sysMenuVo);
            }
        }
        getChirdrenTree(list,listResult);
        return listResult;
    }
    public static void  getChirdrenTree(List<SysMenuVo> list, List<SysMenuVo> listResult){
        for (SysMenuVo sysMenuVo : listResult){
            List<SysMenuVo> chirdrenList =new ArrayList<>();
            for (SysMenuVo vo : list){
                if (vo.getMenuParentId() == 0){
                    continue;
                }
                if (vo.getMenuParentId() == sysMenuVo.getMenuId()){
                    chirdrenList.add(vo);
                }
            }
            sysMenuVo.setChildren(chirdrenList);
            if (!sysMenuVo.getChildren().isEmpty()){
                getChirdrenTree(list,sysMenuVo.getChildren());
            }
        }
    }

}

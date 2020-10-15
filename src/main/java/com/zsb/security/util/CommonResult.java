package com.zsb.security.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CommonResult
 * @Description TODO 返回类
 * @Author shangBangZheng
 * @Date 2020/10/14 11:23
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {

    private int code;

    private String msg;

    private Object data;


    public static CommonResult generateResult(int code, String msg, Object data){
        CommonResult commonResult =new CommonResult();
        commonResult.setCode(code);
        commonResult.setMsg(msg);
        commonResult.setData(data);
        return commonResult;
    }
    public static CommonResult success (Object data){
        return generateResult(200,"操作成功",data);
    }

    public static CommonResult failure (Object data){
        return generateResult(-100,"操作失败",data);
    }

    public static CommonResult other (int code, String msg, Object data){
        return generateResult(code,msg,data);
    }
}

package com.zsb.security.config;

import com.zsb.security.util.CommonException;
import com.zsb.security.util.CommonResult;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GlobalException
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 15:17
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public CommonResult commonException(CommonException commonException){
        return CommonResult.other(-1,commonException.getMessage(),null);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseBody
    public CommonResult usernameNotFoundException(UsernameNotFoundException usernameNotFoundException){
        return CommonResult.other(-1,usernameNotFoundException.getMessage(),null);
    }
}

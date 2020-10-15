package com.zsb.security.util;

import java.util.UUID;

/**
 * @ClassName UUIDUtil
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 10:24
 * @Version 1.0
 */
public class UUIDUtil {

    public static String getUUID(){
      return UUID .randomUUID().toString().trim().replaceAll("-", "");
    }
}

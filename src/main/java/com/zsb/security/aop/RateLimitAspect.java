package com.zsb.security.aop;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import com.zsb.security.annotation.RateLimit;
import com.zsb.security.util.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName RateLimitAspect
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/23 16:02
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class RateLimitAspect {

    /**
     * 用来存放不同接口的RateLimiter(key为接口名称，value为RateLimiter)
     */
    private ConcurrentHashMap<String,RateLimiter> map =new ConcurrentHashMap<>();
    /**
     * 字符串与对象互转
     */
    private static ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 限流令牌桶
     */
    private RateLimiter rateLimiter;

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
    @Around("@annotation(rateLimit)")
    public Object around(ProceedingJoinPoint joinPoint , RateLimit rateLimit) throws Throwable {
        Object proceed = null;
        //获取拦截方法
        MethodSignature method = (MethodSignature) joinPoint.getSignature();
        //返回被织入增加处理目标对象
        Object target = joinPoint.getTarget();
        //获取方法名
        String methodName = method.getName();
        if (!map.containsKey(methodName)) {
            map.put(methodName, RateLimiter.create(rateLimit.limitNum()));
        }
        rateLimiter = map.get(methodName);
        try {
            if (rateLimiter.tryAcquire()){
                proceed = joinPoint.proceed();
            }else {
                throw new CommonException("服务器拒绝了请求");
            }
        }catch (Throwable e) {
            e.printStackTrace();
        }
        return proceed;
    }
}

package com.zsb.security.util;

import com.mysql.cj.util.StringUtils;
import com.zsb.security.annotation.NotNullField;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName Validator
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/20 15:02
 * @Version 1.0
 */
public class Validator {

    @Resource
    RestTemplate restTemplate;

    public static void validateBeenValue(Object object){
        Class<?> objectClass = object.getClass();
        Field[] fields = objectClass.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            if (field.isAnnotationPresent(NotNullField.class)){
                Object obj = getField(object, field.getName());
                if (Objects.isNull(obj) || StringUtils.isNullOrEmpty(obj.toString())){
                    NotNullField annotation = field.getAnnotation(NotNullField.class);
                    throw new CommonException(annotation.errorMsg());
                }
            }
        }
    }
    public static Object getField(Object object,String fieldName){
        Class<?> objectClass = object.getClass();
        Method[] methods = objectClass.getDeclaredMethods();
        for (Method method : methods){
            method.setAccessible(true);
            if (("get"+fieldName).toLowerCase().equalsIgnoreCase(method.getName().toLowerCase())){
                try {
                    return method.invoke(object);
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String url="http://tool.bitefu.net/jiari/";
        Integer forObject = restTemplate.getForObject(url+"?d="+ sdf.format(new Date()) , Integer.class);
        System.out.println(forObject);
    }
}

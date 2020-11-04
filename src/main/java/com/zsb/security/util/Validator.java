package com.zsb.security.util;

import com.mysql.cj.util.StringUtils;
import com.sun.deploy.util.WinRegistry;
import com.zsb.security.annotation.NotNullField;
import org.apache.tomcat.jni.Registry;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName Validator
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/20 15:02
 * @Version 1.0
 */
public class Validator {


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
    public static Boolean readNode(String nodePath) {
        try {
            Process process = Runtime.getRuntime().exec("reg query " + nodePath);
            process.getOutputStream().close();
            InputStreamReader isr = new InputStreamReader(process.getInputStream());
            String line = null;
            BufferedReader ir = new BufferedReader(isr);
            while ((line = ir.readLine()) != null) {
                String[] arr = line.split("    ");
                if(arr.length != 4){
                    continue;
                }
                return true;
            }
            process.destroy();
        } catch (IOException e) {
            System.out.println("读取注册表失败, nodePath: " + nodePath);
        }
        return false;
    }

    public static void main(String[] args) throws IOException {

    }
}

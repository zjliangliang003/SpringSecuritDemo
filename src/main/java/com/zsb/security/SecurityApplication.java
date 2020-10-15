package com.zsb.security;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.zsb.security.service.SysSettingService;
import com.zsb.security.service.SysUserMenuService;
import com.zsb.security.service.SysUserService;
import com.zsb.security.util.SecurityUtil;
import com.zsb.security.util.SysSettingUtil;
import com.zsb.security.vo.SysMenuVo;
import com.zsb.security.vo.SysSettingVo;
import com.zsb.security.vo.SysUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author shangbangZheng
 */
@EnableAsync
@SpringBootApplication
@MapperScan("com.zsb.security.dao")
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    /**
     * 解决不能注入session注册表问题
     */
    @Bean
    SessionRegistry sessionRegistry (){
        return new SessionRegistryImpl();
    }
}

@Slf4j
@Controller
@Configuration
@RequestMapping("/")
class IndexController{

    @Value("${server.port}")
    int port;

    @Resource
    SysUserService sysUserService;

    @Resource
    SysSettingService sysSettingService;

    @Resource
    SysUserMenuService sysUserMenuService;
    /**
     * 启动成功
     */
    @Bean
    public ApplicationRunner applicationRunner() {
        return applicationArguments -> {
            try {
                //系统启动时获取数据库数据，设置到公用静态集合sysSettingMap
                SysSettingVo sysSettingVo = sysSettingService.querySysInfoById(1);
                SysSettingUtil.setSysSettingMap(sysSettingVo);
                //获取本机内网IP
                log.info("启动成功：" + "http://" + InetAddress.getLocalHost().getHostAddress()+":"+port);
            } catch (UnknownHostException e) {
                //输出到日志文件中
                log.error(e.getMessage());
            }
        };
    }
    /**
     * 跳转登录页面
     */
    @GetMapping("loginPage")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    /**
     * 跳转首页
     */
    @GetMapping("")
    public void index1(HttpServletResponse response){
        //内部重定向
        try {
            response.sendRedirect("/index");
        } catch (IOException e) {
            //输出到日志文件中
            log.error(e.getMessage());
        }
    }
    @GetMapping("index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");

        //登录用户
        SysUserVo sysUserVo = sysUserService.findByLoginName(SecurityUtil.getLoginUser().getUsername());
        sysUserVo.setPassword(null);//隐藏部分属性
        modelAndView.addObject( "loginUser", sysUserVo);

        //系统信息
        modelAndView.addObject("sys", SysSettingUtil.getSysSettingMap());
        //登录用户系统菜单
        List<SysMenuVo> menuVoList = sysUserMenuService.queryMenuByUserId(sysUserVo.getUserId());
        modelAndView.addObject("menuList",menuVoList);
//
//        //登录用户快捷菜单
//        List<SysShortcutMenuVo> shortcutMenuVoList= sysShortcutMenuService.findByUserId(sysUserVo.getUserId()).getData();
//        modelAndView.addObject("shortcutMenuList",shortcutMenuVoList);

        return modelAndView;
    }
    @RequestMapping("/getVerifyCodeImage")
    public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 自定义纯数字的验证码（随机4位数字，可重复）
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(91, 38);
        lineCaptcha.setGenerator(randomGenerator);
        request.getSession().setAttribute("verifyCode",lineCaptcha.getCode());
        log.info("验证码:"+lineCaptcha.getCode());
        lineCaptcha.write(response.getOutputStream());
        response.getOutputStream().close();
    }
}

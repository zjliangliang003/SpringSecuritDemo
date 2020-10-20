package com.zsb.security.config;

import com.zsb.security.service.SysUserAuthorityService;
import com.zsb.security.service.UserDetailConfig;
import com.zsb.security.vo.SysAuthorityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SecurityConfig
 * @Description TODO
 * @Author shangBangZheng
 * @Date 2020/10/14 14:11
 * @Version 1.0
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailConfig userDetailConfig;

    @Autowired
    CaptchaFilterConfig captchaFilterConfig;

    @Autowired
    private LoginFailureHandlerConfig loginFailureHandlerConfig;

    @Autowired
    private LoginSuccessHandlerConfig loginSuccessHandlerConfig;

    @Autowired
    private LogoutHandlerConfig logoutHandlerConfig;

    @Autowired
    private DataSource dataSource;

    @Autowired
    SysUserAuthorityService sysAuthorityService;

    @Autowired
    MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailConfig).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http// 关闭csrf防护
                .csrf().disable()
                .headers().frameOptions().disable();
        http
                .addFilterBefore(captchaFilterConfig, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginProcessingUrl("/login")
                //未登录时默认跳转页面
                .loginPage("/loginPage")
                .failureHandler(loginFailureHandlerConfig)
                .successHandler(loginSuccessHandlerConfig)
                .permitAll();
        http
                //登出处理
                .logout()
                .addLogoutHandler(logoutHandlerConfig)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loginPage")
                .permitAll();
        http
//                //定制url访问权限，动态权限读取，参考：https://www.jianshu.com/p/0a06496e75ea
//                .addFilterAfter(dynamicallyUrlInterceptor(), FilterSecurityInterceptor.class)
                .authorizeRequests()
                //无需权限访问
                .antMatchers("/favicon.ico","/common/**", "/webjars/**", "/getVerifyCodeImage","/error/*").permitAll()
                //其他接口需要登录后才能访问
                .anyRequest().authenticated();

        http
                //开启记住我
                .rememberMe()
                // 记住我的时间(秒) 七天免登陆
                .tokenValiditySeconds(604800)
                // 设置数据访问层
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailConfig);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    protected PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl persistentTokenRepository = new JdbcTokenRepositoryImpl();
        persistentTokenRepository.setDataSource(dataSource);
        return persistentTokenRepository;
    }

    /**
     * 配置filter
     * @return
     */
//    @Bean
//    public DynamicallyUrlInterceptor dynamicallyUrlInterceptor(){
//        //首次获取
//        List<SysAuthorityVo> authorityVoList = sysAuthorityService.queryList();
//        myFilterInvocationSecurityMetadataSource.setRequestMap(authorityVoList);
//        //初始化拦截器并添加数据源
//        DynamicallyUrlInterceptor interceptor = new DynamicallyUrlInterceptor();
//        interceptor.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
//
//        //配置RoleVoter决策
//        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>();
//        decisionVoters.add(new RoleVoter());
//
//        //设置认证决策管理器
//        interceptor.setAccessDecisionManager(new MyAccessDecisionManager(decisionVoters));
//        return interceptor;
//    }
}

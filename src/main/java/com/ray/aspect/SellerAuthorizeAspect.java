package com.ray.aspect;

import com.ray.Util.CookieUtil;
import com.ray.constant.CookieConstant;
import com.ray.constant.RedisConstant;
import com.ray.exception.SellerAuthorizeException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 卖家端验证身份
 * aop验证身份
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;
    //定义切入点
    @Pointcut("execution(public * com.ray.controller.Seller*.*(..))"+
            "&& !execution(public * com.ray.controller.SellerUserController.*(..))")//controller中除去登录登出
    public void verify(){

    }

    @Before("verify()")//切入点前操作
    public void doVerify(){
        //获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie == null){
            log.warn("【登录校验】cookie中查不到token");
            throw  new SellerAuthorizeException();//由handler捕获
        }
        //去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            log.warn("【登录校验】redis中查不到token");
            throw new SellerAuthorizeException();
        }
    }
}

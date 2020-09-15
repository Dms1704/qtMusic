package com.xupt.aspect;

import com.xupt.domain.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 切面类：①：用于登录验证
 */
@Aspect
@Component("section")
public class Login {

    @Pointcut("execution(* com.xupt.controller.MysongController.*(..))")
    private void pt1(){};

    @Around("pt1()")
    public Object confirmLogin(ProceedingJoinPoint pjp){
        Object object = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            User user = (User) request.getSession().getAttribute("user");

            if (user == null){
                return "redirect:/login.jsp";
            }
            else {
                Object[] args = pjp.getArgs();
                object = pjp.proceed(args);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return object;
    }
}

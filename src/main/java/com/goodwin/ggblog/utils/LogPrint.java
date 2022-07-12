package com.goodwin.ggblog.utils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author goodwin
 */
@Component
@Aspect
public class LogPrint {
    /*
    @Before("execution(* com.goodwin.ggblog.service.impl.*.*(..))")
    public void methodBegin(JoinPoint joinPoint){
        System.out.println("方法开始了");
    }*/

    @Before("execution(* com.goodwin.ggblog.service.impl.*.*(..))")
    public void methodBegin1(JoinPoint joinPoint){
        System.out.println("=========>" + joinPoint.getTarget()+" "+joinPoint.getSignature().getName()+"方法开始了");
    }

    @After("execution(* com.goodwin.ggblog.service.impl.*.*(..))")
    public void methodEnd(JoinPoint joinPoint){
        System.out.println("=========>" + joinPoint.getTarget()+" "+joinPoint.getSignature().getName()+"方法结束了");
    }


}

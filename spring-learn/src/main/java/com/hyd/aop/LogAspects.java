package com.hyd.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @Aspect：告诉Spring当前类是一个切面类
 */

@Aspect
public class LogAspects {
    //抽取公共的切入点表达式
    //1. 本类引用
    //2. 其他的切面类应用
    @Pointcut("execution(public int com.hyd.aop.MathCalculator.*(..))")
    public void pointCut() {}

    //@Before 在目标方法之前切入；切入点表达式（指定在哪个方法切入）
    //JoinPoint 必须是第一个参数
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName() + " 除法运行。。。@Before:参数列表是:" + Arrays.asList(args));
    }

    @After("com.hyd.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName() + " 除法结束。。。@After");
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature().getName() + " 除法正常返回。。。@AfterReturning:运行结果："+result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        System.out.println(joinPoint.getSignature().getName()+" 除法异常。。。@AfterThrowing:异常信息："+exception);
    }
}

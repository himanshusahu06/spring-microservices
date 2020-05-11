package com.hsahu.application.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Other usage:
 * <p>
 * 1. Match all public method
 * execution(public * com.hsahu.application.controller.*.*(..))
 * <p>
 * 2. Match all public method with specific return type
 * execution(public someDTO com.hsahu.application.controller.*.*(..))
 * <p>
 * 3. Match all public method with specific return type and with first parameter as AnotherDTO
 * execution(public someDTO com.hsahu.application.controller.*.*(AnotherDTO, ..))
 * <p>
 * More information: https://howtodoinjava.com/spring-aop/aspectj-pointcut-expressions/
 */
@Aspect
@Slf4j
@Configuration
@Order
public class MethodInterceptor {

    /**
     * before method invocation
     *
     * @param joinPoint target joinpoint
     */
    @Before("execution(* com.hsahu.application.controller.*.*(..))")
    void beforeMethodInterceptor(JoinPoint joinPoint) {
        log.trace("Intercepted before executing {} method", joinPoint.getSignature());
    }

    /**
     * after method invocation
     *
     * @param joinPoint target joinpoint
     */
    @After(value = "execution(* com.hsahu.application.controller.*.*(..))")
    void afterMethodInterceptor(JoinPoint joinPoint) {
        log.trace("Intercepted after executing {} method", joinPoint.getSignature());
    }

    /**
     * after method invocation that returns any value
     *
     * @param joinPoint target joinpoint
     */
    @AfterReturning(
            value = "execution(* com.hsahu.application.controller.*.*(..))",
            returning = "returnObject"
    )
    void afterMethodReturnedInterceptor(JoinPoint joinPoint, Object returnObject) {
        log.trace("Intercepted after method {} returned {}", joinPoint.getSignature(), returnObject);
    }

    /**
     * after method invocation that throws any exception
     *
     * @param joinPoint target joinpoint
     */
    @AfterThrowing(
            value = "execution(* com.hsahu.application.controller.*.*(..))",
            throwing = "exception"
    )
    void afterMethodThrowInterceptor(JoinPoint joinPoint, Exception exception) {
        log.trace("Intercepted after {} throws the exception {}", joinPoint.getSignature(), exception);
    }

    /**
     * Calculate time taken by an method invocation.
     * you can proceed with next advice of methods invocation.
     *
     * @param proceedingJoinPoint target proceedingJoinPoint
     * @return result of method invocation if returned
     * @throws Throwable exception of method invocation if thrown
     */
    @Around("com.hsahu.application.aop.CommonPointcutConfig.controllerExecutionPointcut()")
    Object methodExecutionTimeTaken(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTimeInMs = System.currentTimeMillis();
        try {
            return proceedingJoinPoint.proceed();
        } finally {
            long timeTaken = System.currentTimeMillis() - startTimeInMs;
            log.debug("time taken for method {} execution is {} ms", proceedingJoinPoint.getSignature(), timeTaken);
        }
    }
}

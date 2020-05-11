package com.hsahu.application.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Keep all pointcuts in a single place and reuse them in aspects
 */
public class CommonPointcutConfig {
    @Pointcut("execution(* com.hsahu.application.controller.*.*(..))")
    void controllerExecutionPointcut() {}

    @Pointcut("execution(* com.hsahu.application.biz.*.*(..))")
    void bizExecutionPointcut() {}

    /**
     * pointcut for any bean ending with "Dao"
     */
    @Pointcut("bean(*Dao)")
    void daoBeanPointcut() {}

    /**
     * pointcut for any method annotated with given annotation.
     * This is best way to crate annotation based aspect
     */
    @Pointcut("@annotation(com.hsahu.application.annotation.TrackTime)")
    void trackedTimedPointcut(){}
}

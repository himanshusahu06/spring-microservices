package com.hsahu.application.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
@Configuration
@Order(value = 0)
public class TrackTimeAnnotationAspect {

    /**
     * Aspect for any method annotated with TrackTime annotation
     *
     * @param proceedingJoinPoint proceeding joinpoint
     * @return return value of method invocation if any
     * @throws Throwable exception thrown by method invocation if any
     */
    @Around("com.hsahu.application.aop.CommonPointcutConfig.trackedTimedPointcut()")
    Object trackTimeAnnotationProcessor(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return proceedingJoinPoint.proceed();
        } finally {
            long executionTime = System.currentTimeMillis() - startTime;
            log.trace("time taken by method {} - {} ms", proceedingJoinPoint.getSignature(), executionTime);
        }
    }
}

package com.lididimi.restaurant.controller.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class MonitoringAspect {

    @Around("Pointcuts.onWarnIfExecutionTimeExceeds()")
    Object monitorExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        WarnIfExecutionExceeds annotation = getAnnotation(pjp);

        long threshold = annotation.threshold();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // before
        var result = pjp.proceed();
        // after
        stopWatch.stop();
        long methodExecutionTime = stopWatch.lastTaskInfo().getTimeMillis();

        if(methodExecutionTime > threshold) {
            log.warn("The method {} executed in {} millis which is more than the acceptable threshold of {} millis. Threshold exceeded by {}",
                    pjp.getSignature(),
                    methodExecutionTime,
                    threshold,
                    methodExecutionTime - threshold
            );
        }

        return result;
    }

    private static WarnIfExecutionExceeds getAnnotation(ProceedingJoinPoint pjp) {
       Method method =  ((MethodSignature)pjp.getSignature()).getMethod();
       return method.getAnnotation(WarnIfExecutionExceeds.class);
    }
}

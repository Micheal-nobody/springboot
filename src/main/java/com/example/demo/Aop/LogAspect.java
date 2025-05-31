package com.example.demo.Aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Before("execution(* com.example.demo.Controller..*.*(..))")
    public void log(JoinPoint joinPoint) throws Throwable {

        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("Method {} is called with arguments: {}", name, Arrays.toString(args));
    }
}

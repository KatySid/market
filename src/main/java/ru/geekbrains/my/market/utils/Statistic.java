package ru.geekbrains.my.market.utils;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Data
public class Statistic {

    public Map<String, Long> statistic = new HashMap<>();

    @Around("execution(public * ru.geekbrains.my.market.services.*.*(..))")
    public Object statistic(ProceedingJoinPoint proceedingJoinPoint) {
        Object out = null;
        long begin = 0, end = 0;
        try {
            begin = System.currentTimeMillis();
            out = proceedingJoinPoint.proceed();
            end = System.currentTimeMillis();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String serviceName = methodSignature.getDeclaringType().getSimpleName();
        long result = end - begin;
        if (statistic.containsKey(serviceName)) {
            result += statistic.get(serviceName);
        }
        statistic.put(serviceName, result);
        return out;
    }
}

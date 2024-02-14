package ru.sakaev.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserActionLoggerAspect {

    @Pointcut("@annotation(ru.sakaev.annotation.TrackUserAction)")
    public void trackUserAction() {}

    @AfterReturning(pointcut = "trackUserAction()", returning = "result")
    public void logUserAction(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        String arguments = "";
        if (args.length > 0) {
            arguments = args[0].toString(); // преобразование аргументов в строку
        }
        System.out.println("User action: Method " + methodName + " in class " + className + " called with arguments: " + arguments);
    }
}

